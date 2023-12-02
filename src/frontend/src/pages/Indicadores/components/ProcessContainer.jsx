// PieMetricCard.js
import React from 'react';
import { Card, Divider } from 'antd';
import { Pie } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Popover, Typography, Space } from 'antd';
import styled from 'styled-components';

ChartJS.register(ArcElement, Tooltip, Legend);

const { Title } = Typography;

const ProcessContainer = ({ processName, processNumber = 1, chartsContainer }) => {
    return (
        <ProcessContainerContainer>
            <ProcesssTitle 
            level={3}
            italic={true}
            >
                {`Processo ${processNumber} - ${processName}`}
            </ProcesssTitle>
            <div>
                {chartsContainer}
            </div>
        </ProcessContainerContainer>
    )
};

export default ProcessContainer;

const ProcessContainerContainer = styled(Space)`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    `;

const ProcesssTitle = styled(Title)`
    display: flex;
    justify-content: center;
    align-items: center;
    `;