// Indicadores.jsx
import React from 'react';
import styled from 'styled-components';
import { Divider } from 'antd';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import Processo1 from 'pages/Indicadores/process/Processo1';
import Processo2 from 'pages/Indicadores/process/Processo2';
import Processo4 from 'pages/Indicadores/process/Processo4';
import Processo6 from 'pages/Indicadores/process/Processo6';
import Processo7 from 'pages/Indicadores/process/Processo7';

const Indicadores = () => {
  const currentUser = useSelector(state => state.currentUser.value)
  const navigate = useNavigate()

  if (currentUser.user?.role !== 'admin') {
    navigate('/')
    return null
  }

  return (
    <>
      <h1>Indicadores</h1>
      <AllMetricsContanier>
        <Processo1 />
        <Divider />
        <Processo2 />
        <Divider />
        <Processo4 />
        <Divider />
        <Processo6 />
        <Divider />
        <Processo7 />
        {/* TODO - add other indicators here */}
      </AllMetricsContanier>
    </>
  );
};

export default Indicadores;

const AllMetricsContanier = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  gap: 40px;
`;