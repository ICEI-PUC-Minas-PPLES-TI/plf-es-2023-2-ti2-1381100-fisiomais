import React, { useState, useEffect } from 'react';
import { HeaderTable } from "./components/HeaderTable";
import { TableTop } from "./components/TableTop";
import { ContentLine } from "./components/ContentLine/ContentLine";
import { Filters } from "./components/FiltersLine/Filters";
import axios from 'axios';
import { useSelector } from 'react-redux';

const Agenda = () => {
    const [consultas, setConsultas] = useState([]);
    const [filtroData, setFiltroData] = useState('');
    const [filtroStatus, setFiltroStatus] = useState('');
    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    useEffect(() => {
        fetchConsultas();
    }, []);

    const fetchConsultas = async () => {
        try {
            const response = await axios.get('http://localhost:8081/api/consulta/all');
            setConsultas(response.data);
            // console.log(response.data);
        } catch (error) {
            // console.error("Erro ao buscar consultas", error);
        }
    };

    const handleDataChange = (event) => {
        setFiltroData(event.target.value);
    };

    const handleStatusChange = (event) => {
        setFiltroStatus(event.target.value);
    };

    const consultasFiltradas = consultas.filter((consulta) => {
        return (
            (!filtroData || consulta.dataEHora === filtroData) &&
            (!filtroStatus || consulta.confirmacao === filtroStatus)
        );
    });

    return (
        <div className="container mx-auto px-4 py-8">
            <HeaderTable />
            
            <Filters
                totalAppointments={consultasFiltradas.length}
                statusOptions={["Todos", "Confirmado", "Pendente", "Cancelado", "Realizado"]}
                onStatusChange={handleStatusChange}
            />

            <div className="overflow-x-auto bg-white rounded-lg shadow overflow-y-auto relative">
                <table className="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
                    <TableTop />
                    <tbody>
                        {consultasFiltradas.map((consulta, i) => (
                            <ContentLine
                                key={i}
                                paciente={consulta.paciente}
                                fisioterapeuta={consulta.fisioterapeuta}
                                dataHora={consulta.dataEHora}
                                status={consulta.status}
                                observacoes={consulta.observacoes}
                                linkConsulta={consulta.link}
                            />
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Agenda;
