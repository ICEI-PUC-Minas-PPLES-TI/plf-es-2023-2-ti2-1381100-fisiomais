// PieMetricCard.js
import React from 'react';
import { Card, Divider } from 'antd';
import { Pie } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Popover, Typography, Spin, Alert } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import styled from 'styled-components';

ChartJS.register(ArcElement, Tooltip, Legend);

const { Title } = Typography;

const PieMetricCard = ({ title, objectives, description, chartData, cardHeaderInput = null, loading, error, moreDataOpener }) => {
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
          message="Erro de carregamento"
          description={`Erro ao carregar dados da métrica ${title}.\nTente recarregar a página.`}
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
      <Pie data={chartData} />
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

export default PieMetricCard;