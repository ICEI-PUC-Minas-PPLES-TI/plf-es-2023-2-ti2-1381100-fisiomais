// LineMetricCard.js
import React from 'react';
import { Card, Divider, Typography, Popover, Spin, Alert } from 'antd';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  Tooltip,
  Legend,
} from 'chart.js';
import { LoadingOutlined } from '@ant-design/icons';
import styled from 'styled-components';

ChartJS.register(
  CategoryScale,
  LinearScale,
  LineElement,
  Tooltip,
  PointElement,
  Legend
);

const { Title } = Typography;

const LineMetricCard = ({ title, objectives, description, chartData, cardHeaderInput = null, loading, error, moreDataOpener = null }) => {
  const chartOptions = {
    plugins: {
      legend: {
        display: false,
      },
    },
    responsive: true,
    scales: {
      y: {
        min: 0,
        max: Math.max(...chartData.datasets.map(dataset => Math.max(...dataset.data))) + Math.max(...chartData.datasets.map(dataset => Math.max(...dataset.data))) / 2,
      },
    },
  };

  const titlePopOver = (
    <>
      <WrapedMaxWidthParagraph>
        <span><strong>Objetivos: </strong>{objectives}</span>
      </WrapedMaxWidthParagraph>
      <Divider />
      <WrapedMaxWidthParagraph>
        <span><strong>Descrição: </strong>{description}</span>
      </WrapedMaxWidthParagraph>
    </>
  );

  if (loading) {
    return (
      <Card>
        <CardHeaderContainer>
          <TitleContainer>
            <Title level={4}>
              {title}
            </Title>
          </TitleContainer>
        </CardHeaderContainer>
        <Divider />
        <ChartLoading
          indicator={
            <LoadingOutlined
              style={{
                fontSize: 24,
              }}
              spin
            />}
          size="large" />
      </Card>
    )
  }

  if (error) {
    return (
      <Card>
        <CardHeaderContainer>
          <Popover content={titlePopOver}>
            <TitleContainer style={{ cursor: 'help' }}>
              <Title level={4}>
                {title}
              </Title>
              {cardHeaderInput}
            </TitleContainer>
          </Popover>
        </CardHeaderContainer>
        <Divider />
        <Alert
          type="error"
          message="Error Loading Data"
          description={`Error loading data for ${title}.\nPlease try again.`}
          showIcon
        />
      </Card>
    );
  }

  return (
    <Card>
      <CardHeaderContainer>
        <Popover content={titlePopOver}>
          <TitleContainer style={{ cursor: 'help' }}>
            <Title level={4}>
              {title}
            </Title>
          </TitleContainer>
        </Popover>
        {cardHeaderInput}
      </CardHeaderContainer>
      <Divider />
      <Line data={chartData} options={chartOptions} />
      <CenteredButtonContainer>
        {moreDataOpener}
      </CenteredButtonContainer>
    </Card>
  );
};

const CardHeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const WrapedMaxWidthParagraph = styled.p`
  font-size: 1rem;
  margin: 8px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 250px;
`;

const TitleContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: left;
`;

const ChartLoading = styled(Spin)`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 300px;
`;

const CenteredButtonContainer = styled.div`
  margin-top: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default LineMetricCard;