import React, { useEffect, useState } from 'react';
import { Row, Col } from 'antd';
import PieMetricCard from 'pages/Indicadores/components/PieMetricCard';
import ProcessContainer from 'pages/Indicadores/components/ProcessContainer';
import axios from 'axios';
import { useSelector } from 'react-redux';

const Processo2 = () => {
    const [consultasConcluidas, setconsultasConcluidas] = useState(0);
    const [consultasReagendadas, setconsultasReagendadas] = useState(0);

    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    useEffect(() => {
        const fetchIndicators = async () => {
            try {
                const responseConclusao = await axios.get('http://localhost:8081/api/consulta/taxa-conclusao');
                const responseReagendamento = await axios.get('http://localhost:8081/api/consulta/taxa-reagendamento');
                
                setconsultasConcluidas(responseConclusao.data);
                setconsultasReagendadas(responseReagendamento.data);
            
            } catch (error) {
                console.error('Erro ao buscar dados', error.message);
            }
        };
    
        fetchIndicators();
    }, []);

    const conclusaoData = {
        labels: ['Concluídas', 'Não Concluídas'],
        datasets: [
            {
                label: 'Taxa de Conclusão (%)',
                data: [consultasConcluidas, 100 - consultasConcluidas],
                backgroundColor: ['#00c3a5', '#0BBFD9'],
                hoverBackgroundColor: ['#37dec4', '#23d1eb'],
            },
        ],
    };

    const reagendamentoData = {
        labels: ['Reagendadas', 'Não Reagendadas'],
        datasets: [
            {
                label: 'Taxa de Reagendamento (%)',
                data: [consultasReagendadas, 100 - consultasReagendadas],
                backgroundColor: ['#00c3a5', '#0BBFD9'],
                hoverBackgroundColor: ['#37dec4', '#23d1eb'],
            },
        ],
    };

    return (
        <>
            <ProcessContainer 
            processName="Controlar Consultas"
            processNumber={2}
            chartsContainer = {
                <Row gutter={16} style={{ display: 'flex', alignItems: 'stretch' }}>
                    <Col span={12}>
                        <PieMetricCard
                            title="Taxa de Conclusão"
                            description="Taxa de consultas concluídas"
                            chartData={conclusaoData}
                            objectives="Objetivo"
                            loading={false}
                            error={false}
                            moreDataOpener={false}
                        />
                    </Col>
                    <Col span={12}>
                        <PieMetricCard
                            title="Taxa de Reagendamento"
                            description="Taxa de consultas reagendadas"
                            chartData={reagendamentoData}
                            objectives="Objetivo"
                            loading={false}
                            error={false}
                            moreDataOpener={false}
                        />
                    </Col>
                </Row>
            }
            />
        </>
    )

};

export default Processo2;