import React, { useEffect, useState } from 'react';
import { Layout, Card, Typography, Divider, Button, Result } from 'antd';
import styled from 'styled-components';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const { Content } = Layout;
const { Title, Text } = Typography;

const Consulta = () => {
    // TODO - remove this, backend will provide this date in the correct format
    const parseDate = (date) => {
        const [year, month, day] = date.split('-');
        return `${day}/${month}/${year}`;
    }

    // TODO - remove this, backend will provide this cpf in the correct format
    const formatCPF = (cpf) => {
        const cpfParts = cpf.match(/.{1,3}/g);
        const cpfWithDots = cpfParts.join('.');
        return cpfWithDots.replace(/\.(\d{3})\.(\d{3})-/, '-$1-$2');
    }

    const parseUserObject = (user) => {
        try {
            const { nome, email, data_nascimento, cpf, telefone, genero, endereco } = user.user;
            return {
                nome,
                email,
                data_nascimento: parseDate(data_nascimento), // TODO - remove this, backend will provide this date in the correct format
                cpf: formatCPF(cpf), // TODO - remove this, backend will provide this cpf in the correct format
                telefone,
                genero,
                endereco,
            }
        } catch (error) {
            console.error('Error parsing user object: ', error.message);
            return {};
        }
    }

    const navigate = useNavigate()
    const currentUser = useSelector(state => state.currentUser.value);

    const pacienteInfo = parseUserObject(currentUser);
    const role = currentUser.user.role;

    const handleNextStep = () => {
        navigate('/nova-consulta/dados');
    }

    if (role === 'fisioterapeuta') {
        return (
            <Result title="Usuário não tem permissão para acessar essa página"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        )
    }

    if (Object.keys(pacienteInfo).length === 0) {
        return (
            <Result title="Usuário não está logado"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        )
    }

    return (
        <Content>
            <Title level={2}>Nova consulta</Title>
            <Divider />
            <Card title="Informações cadastradas do usuário" type={'inner'}>
                <Title level={3}>{pacienteInfo.nome}</Title>
                <BaseInfoContainer>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Email:</Text> {pacienteInfo.email}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Data de Nascimento:</Text> {pacienteInfo.data_nascimento}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>CPF:</Text> {pacienteInfo.cpf}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Telefone:</Text> {pacienteInfo.telefone} <br /></InfoRow >
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Gênero:</Text> {pacienteInfo.genero} <br /></InfoRow >
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Endereço:</Text> {pacienteInfo.endereco}</InfoRow >
                </BaseInfoContainer >
                <Divider />
                <ButtonContainer>
                    <NextStepButton size="large"
                        onClick={handleNextStep}>
                        Preencher informações da consulta
                    </NextStepButton>
                </ButtonContainer>
            </Card >
        </Content >
    )
};

export default Consulta;

export const BaseInfoContainer = styled.div`
    margin-top: 50px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 10px;
    gap: 20px;
`;

export const InfoRow = styled.span`
    font-size: 1.2rem;
    display: flex;
    flex-direction: row;
    justify-content: start;
    margin-bottom: 10px;
    gap: 5px;
`;

export const ButtonContainer = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    width: 100%;
`;

export const NextStepButton = styled(Button)`
    font-size: 1.2rem;
    border-radius: 5px;
    background-color: #0BD980 !important;
    border-color: #fff !important;
    color: #fff !important;
    &:hover{
        background-color: #5ee0a8 !important;
        border-color: #fff !important;
        color: #fff !important;
    }
`