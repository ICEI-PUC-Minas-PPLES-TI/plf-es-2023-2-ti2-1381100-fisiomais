import React, { useState, useEffect } from 'react';
import { HeaderTable } from "./components/HeaderTable";
import { TableTop } from "./components/TableTop";
import { ContentLine } from "./components/ContentLine/ContentLine";
import { Filters } from "./components/FiltersLine/Filters";
import axios from 'axios';
import { useSelector } from 'react-redux';
import { set } from 'date-fns';

const Agenda = () => {
    const [consultas, setConsultas] = useState([]);
    const [checkFilterDate, setCheckFilterDate] = useState(false);
    const [filtroData, setFiltroData] = useState(new Date().toISOString().split('T')[0]);
    const [showData, setShowData] = useState([]);
    const [selectedStatus, setSelectedStatus] = useState('todos');
    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    const userRole = currentUser.user.role

    const consultasFiltradas = consultas.filter((consulta) => {
        const dataConsulta = new Date(consulta.dataEHora.split('T')[0]);
        const dataFiltro = new Date(filtroData);

        const matchData = dataConsulta.toISOString().split('T')[0] === dataFiltro.toISOString().split('T')[0];
        const matchStatus = selectedStatus === 'todos' || consulta.status.toLowerCase() === selectedStatus;
    
        return matchData && matchStatus;
    });

    useEffect(() => {
        fetchConsultas();
    }, [filtroData, selectedStatus]);

    useEffect(() => {
        if (!checkFilterDate) {
            setShowData(consultas);
        } else {
            setShowData(consultasFiltradas);
        }
    }, [checkFilterDate, consultas, consultasFiltradas, filtroData]);
    

    const fetchConsultas = async () => {
        try {
            let apiUrl = `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/consulta`
            if (userRole === 'fisioterapeuta') {
                apiUrl += `/fisioterapeuta/${currentUser.user.id}`
            }
            else if (userRole === 'paciente') {
                apiUrl += `/paciente/${currentUser.user.id}`
            }
            else {
                apiUrl += `/all`
            }
            const response = await axios.get(apiUrl, {
                params: { data: filtroData, status: selectedStatus },
                headers: { Authorization: `Bearer ${token}` }
            });
            setConsultas(response.data);
            setShowData(response.data);
        } catch (error) {
             console.error("Erro ao buscar consultas", error);
        }
    };

    const handleDataChange = (event) => {
        setFiltroData(event.target.value);
    };

    const updateFiltroData = (newDate) => {
        const formattedDate = newDate.toISOString().split('T')[0];
        setFiltroData(formattedDate);
    };

    const handleStatusChange = (event) => {
        setSelectedStatus(event.target.value.toLowerCase());
    };

    const handleDelete = async (idToDelete) => {
        try {
            await axios.delete(`${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/consulta/${idToDelete}`);
            fetchConsultas();
        } catch (error) {
            console.error('Erro ao deletar consulta', error);
        }
    };


    return (
        <div className="container mx-auto px-4 py-8">
            <HeaderTable updateFiltroData={updateFiltroData} checkFilterDate={checkFilterDate}
                setCheckFilterDate={setCheckFilterDate} />
            <Filters
                totalAppointments={checkFilterDate ? consultasFiltradas.length : consultas.length}
                statusOptions={["Todos", "Confirmado", "Pendente", "Cancelado", "Realizado"]}
                onStatusChange={handleStatusChange}
                selectedStatus={selectedStatus}
            />

            <div className="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
                <table className="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
                    <TableTop />
                    <tbody>
                        {showData.map((consulta, i) => (
                            <ContentLine
                                key={i}
                                id={consulta.id}
                                paciente={consulta.paciente}
                                fisioterapeuta={consulta.fisioterapeuta}
                                dataHora={consulta.dataEHora}
                                status={consulta.status}
                                observacoes={consulta.observacoes}
                                linkConsulta={consulta.link}
                                onDelete={fetchConsultas}
                            />
                        ))}

                        {!showData.length && (
                            <tr>
                                <td colSpan="7" className="py-4 px-6 text-center">
                                    Nenhuma consulta encontrada
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Agenda;