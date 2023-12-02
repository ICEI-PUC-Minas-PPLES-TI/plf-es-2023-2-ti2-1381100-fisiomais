import React, { useEffect, useState } from 'react';
import { Row, Col } from 'antd';
import PieMetricCard from 'pages/Indicadores/components/PieMetricCard';
import ProcessContainer from 'pages/Indicadores/components/ProcessContainer';
import axios from 'axios';
import { useSelector } from 'react-redux';

const Processo4 = () => {
    const [taxaCrescimento, setTaxaCrescimento] = useState(0);
    const [indicePerfisCompletos, setIndicePerfisCompletos] = useState(0);

    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    useEffect(() => {
        const fetchIndicators = async () => {
            try {
                const responseCrescimento = await axios.get('http://localhost:8081/api/fisioterapeuta/taxaCrescimento');
                const responsePerfis = await axios.get('http://localhost:8081/api/fisioterapeuta/indicePerfisCompletos');
                
                setTaxaCrescimento(responseCrescimento.data);
                setIndicePerfisCompletos(responsePerfis.data);
            } catch (error) {
                console.error('Erro ao buscar dados', error.response || error);
            }
        };
    
        fetchIndicators();
    }, []);

    const taxaCrescimentoData = {
        labels: ['Crescimento', 'Estagnação'],
        datasets: [
            {
                label: 'Taxa de Crescimento (%)',
                data: [taxaCrescimento, 100 - taxaCrescimento],
                backgroundColor: ['#00c3a5', '#0BBFD9'],
                hoverBackgroundColor: ['#37dec4', '#23d1eb'],
            },
        ],
    };

    const indicePerfisData = {
        labels: ['Perfis Completos', 'Perfis Incompletos'],
        datasets: [
            {
                label: 'Índice de Perfis Completos (%)',
                data: [indicePerfisCompletos, 100 - indicePerfisCompletos],
                backgroundColor: ['#00c3a5', '#0BBFD9'],
                hoverBackgroundColor: ['#37dec4', '#23d1eb'],
            },
        ],
    };

    return (
        <ProcessContainer
            processName={"Cadastro de Fisioterapeutas"}
            processNumber={4}
            chartsContainer={
                <Row gutter={16} style={{ display: 'flex', alignItems: 'stretch' }}>
                    <Col span={12}>
                        <PieMetricCard
                            title="Taxa de Crescimento de Cadastros"
                            objectives="Avaliar o crescimento da rede de fisioterapeutas."
                            description="Variação percentual mensal no número de novos cadastros."
                            chartData={taxaCrescimentoData}
                        />
                    </Col>
                    <Col span={12}>
                        <PieMetricCard
                            title="Índice de Perfis Completos"
                            objectives="Garantir a completude dos perfis dos fisioterapeutas."
                            description="Porcentagem de fisioterapeutas com perfis completos."
                            chartData={indicePerfisData}
                        />
                    </Col>
                </Row>
            }
        />
    );
};

export default Processo4;
