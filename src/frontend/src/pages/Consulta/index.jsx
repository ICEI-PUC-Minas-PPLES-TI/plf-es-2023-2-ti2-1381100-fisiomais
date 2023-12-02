import React, { useEffect, useState } from 'react';
import { Layout, Card, Typography, Divider, Button, Result, Skeleton } from 'antd';
import styled from 'styled-components';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const { Content } = Layout;
const { Title, Text } = Typography;

const Consulta = () => {

    const parseDate = (date) => {
        const [year, month, day] = date.split('-');
        return `${day}/${month}/${year}`;
    }


    const formatCPF = (cpf) => {
        const cpfParts = cpf.match(/.{1,3}/g);
        const cpfWithDots = cpfParts.join('.');
        return cpfWithDots.replace(/\.(\d{3})\.(\d{3})-/, '-$1-$2');
    }

    const parseUserObject = (user) => {
        try {
            const { nome, email, dataNascimento, cpf, telefone, genero, endereco } = user;
            return {
                nome,
                email,
                data_nascimento: parseDate(dataNascimento),
                cpf: formatCPF(cpf),
                telefone,
                genero,
                endereco,
            }
        } catch (error) {
            // console.error('Error parsing user object: ', error.message);
            return {};
        }
    }

    const [paciente, setPaciente] = useState(null);
    const [statusPaciente, setStatusPaciente] = useState('idle'); // ['loading', 'success', 'error', 'idle']
    const navigate = useNavigate()
    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    const role = currentUser.user.role;

    const fetchPacienteInfo = async () => {
        try {
            const response = await axios.get(`${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/paciente/${currentUser.user.id}`)
            const { data } = response;
            setPaciente(parseUserObject(data));
            setStatusPaciente('success');
        } catch (error) {
            setStatusPaciente('error');
            setPaciente({});
        }

    }

    useEffect(() => {
        if (role === 'paciente' && paciente === null && currentUser.user.id) {
            setStatusPaciente('loading');
            fetchPacienteInfo();
        }
    }, [currentUser])

    const handleNextStep = () => {
        navigate('/nova-consulta/dados');
    }

    if (statusPaciente === 'loading') {
        return (
            <Content>
                <Title level={2}>
                    <Skeleton.Input style={{ width: '10rem' }} active />
                </Title>
                <Divider />
                <Card title={<Skeleton.Input style={{ width: '15rem' }} active />} type="inner">
                    <Title level={3}>
                        <Skeleton.Input style={{ width: '10rem' }} active />
                    </Title>
                    <Skeleton.Input style={{ width: '20rem' }} active />
                    <Divider />

                    {/* Base Info Container */}
                    <Skeleton.Input style={{ width: '15rem' }} active />
                    <Skeleton.Input style={{ width: '20rem' }} active />
                    <Skeleton.Input style={{ width: '15rem' }} active />
                    <Skeleton.Input style={{ width: '10rem' }} active />
                    <Skeleton.Input style={{ width: '10rem' }} active />
                    <Skeleton.Input style={{ width: '20rem' }} active />
                    <Divider />

                    {/* Button Container */}
                    <Skeleton.Input style={{ width: '10rem' }} active />
                    <Button type="primary" size="large" onClick={() => { }} disabled>
                        <Skeleton.Input style={{ width: '20rem' }} active />
                    </Button>
                </Card>
            </Content>
        )
    }

    if (statusPaciente === 'error') {
        return (
            <Result title="Usuário não encontrado"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário. Tente recarregar a página.">
            </Result>
        )
    }

    if (role === 'fisioterapeuta') {
        return (
            <Result title="Usuário não tem permissão para acessar essa página"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        )
    }

    if (paciente === null && statusPaciente === 'success' || statusPaciente === 'idle') {
        return null;
    }

    return (
        <Content>
            <Title level={2}>Nova consulta</Title>
            <Divider />
            <Card title="Informações cadastradas do usuário" type={'inner'}>
                <Title level={3}>{paciente.nome}</Title>
                <BaseInfoContainer>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Email:</Text> {paciente.email}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Data de Nascimento:</Text> {paciente.data_nascimento}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>CPF:</Text> {paciente.cpf}<br /></InfoRow>
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Telefone:</Text> {paciente.telefone} <br /></InfoRow >
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Gênero:</Text> {paciente.genero} <br /></InfoRow >
                    <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Endereço:</Text> {paciente.endereco}</InfoRow >
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