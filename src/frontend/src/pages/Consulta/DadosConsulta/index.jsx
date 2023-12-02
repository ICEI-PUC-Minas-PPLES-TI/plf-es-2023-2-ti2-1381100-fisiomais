import React, { useEffect, useState } from 'react';
import { Card, Layout, Typography, Divider, Result, Select, Space, DatePicker, notification, TimePicker } from 'antd';
import locale from 'antd/es/date-picker/locale/pt_BR';
import dayjs from 'dayjs';
import axios from 'axios';
import moment from 'moment';
import { BaseInfoContainer, ButtonContainer, InfoRow, NextStepButton } from '..';
import { useSelector } from 'react-redux';
import { Confirmada, Pendente, NaoConfirmada, FisioInputLabel, BottomInputsContainer, DatePickerContainer, LinkContainer } from './styled';
// redirect icon
import { FiExternalLink } from 'react-icons/fi'
import { useNavigate } from 'react-router-dom';

const { Content } = Layout;
const { Title, Text, Link } = Typography;

const DadosConsulta = () => {
    const [fisioterapeutas, setFisioterapeutas] = useState([]);
    const [selectedFisioterapeuta, setSelectedFisioterapeuta] = useState(null);
    const [agenda, setAgenda] = useState([]);
    const [consultas, setConsultas] = useState([]);
    const [dateSelected, setDateSelected] = useState(null);
    const [timeSelected, setTimeSelected] = useState(null);
    const [consultaData, setConsultaData] = useState(null);

    const [fisioFetchStatus, setFisioFetchStatus] = useState('idle');
    const [agendaFetchStatus, setAgendaFetchStatus] = useState('idle');
    const [requestStatus, setRequestStatus] = useState('idle');
    const [finished, setFinished] = useState(null); // ['confirmacao', 'pendente', null]

    const [api, contextHolder] = notification.useNotification();

    const isErroredFisio = fisioFetchStatus === 'error';
    const isSuccessAgenda = agendaFetchStatus === 'success';
    const isLoadingRequest = requestStatus === 'loading';

    const currentUser = useSelector(state => state.currentUser.value);
    const role = currentUser.user.role;
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    const navigate = useNavigate();

    const openNotification = (type, title, description) => {
        api[type]({
            message: title,
            description: description,
            duration: 2,
            placement: 'bottomRight',
        });
    };

    const fetchFisioData = async (id) => {
        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/fisioterapeuta/${id}` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/fisioterapeuta/${id}`;
        let fisio = null;
        await axios.get(apiRoute)
            .then((res) => {
                // console.log('fisio', res.data)
                fisio = res.data;
            })
            .catch((err) => {
                openNotification('error', 'Erro ao buscar profissional', err.message);
            })
            .finally(() => {
            });
        return fisio;
    }

    const fetchAllFisioterapeutas = async () => {
        setFisioFetchStatus('loading');
        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/fisioterapeuta` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/fisioterapeuta/nomes`;

        await axios.get(apiRoute)
            .then((res) => {
                setFisioterapeutas(res.data);
            })
            .catch((err) => {
                setFisioFetchStatus('error');
                openNotification('error', 'Erro ao buscar profissionais', err.message);
            })
            .finally(() => {
                setFisioFetchStatus('success');
            });
    };

    const fetchAgendaFromFisioterapeuta = async () => {
        setAgendaFetchStatus('loading');
        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}agenda?fisioterapeuta__id=${selectedFisioterapeuta.id}` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/agenda/fisioterapeuta/${selectedFisioterapeuta.id}`;

        await axios.get(apiRoute)
            .then((res) => {
                // console.log(`Agenda do fisioterapeuta ${selectedFisioterapeuta.nome}:`, res.data);
                setAgenda(res.data);
            })
            .catch((err) => {
                setAgendaFetchStatus('error');
                openNotification('error', 'Erro ao buscar agenda', err.message);
            })
            .finally(() => {
                // setAgendaFetchStatus('success');
            });
    };

    const fetchConsultasFromFisioterapeuta = async () => {
        setAgendaFetchStatus('loading');
        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}consulta?fisioterapeuta__id=${selectedFisioterapeuta.id}` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/consulta/fisioterapeuta/${selectedFisioterapeuta.id}`;
        await axios.get(apiRoute)
            .then((res) => {
                // console.log(`Consultas do fisioterapeuta ${selectedFisioterapeuta.nome}:`, res.data)
                setConsultas(res.data);
            })
            .catch((err) => {
                setAgendaFetchStatus('error');
                openNotification('error', 'Erro ao buscar consultas', err.message);
            })
            .finally(() => {
                setAgendaFetchStatus('success');
            });
    };

    const sendAppointmentRequest = async () => {
        setRequestStatus('loading');
        // console.log(selectedFisioterapeuta)
        if (!selectedFisioterapeuta.controle_automatico && process.env.API_TYPE === "json") { return setRequestStatus('error') }
        const requestBody = process.env.API_TYPE === 'json' ?
            {
                fisioterapeuta__id: selectedFisioterapeuta.id,
                paciente__id: currentUser.user.id,
                dataEHora: dayjs(dateSelected).hour(timeSelected.hour()).minute(timeSelected.minute()).second(0).format('YYYY-MM-DD HH:mm:ss'),
                observacoes: '',
                status: 'confirmado',
            } :
            {
                pacienteId: currentUser.user.id,
                fisioterapeutaId: selectedFisioterapeuta.id,
                dataHora: dayjs(dateSelected).hour(timeSelected.hour()).minute(timeSelected.minute()).second(0).format('YYYY-MM-DDTHH:mm:ss'),
            };

        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/consulta` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/consulta`;

        await axios.post(apiRoute, requestBody)
            .then((res) => {
                // console.log(`Consulta criada:`, res.data);
                setConsultaData(res.data);
                if (res.data.status === 'confirmado') {
                    openNotification('success', 'Solicitação de agendamento confirmada', 'Lembre-se de comparecer no horário');
                }
                else {
                    openNotification('success', 'Solicitação de agendamento enviada', 'Aguarde a confirmação do profissional');
                }
                setFinished(res.data.status);
            })
            .catch((err) => {
                // console.log('erro ao criar consulta', err)
                setRequestStatus('error');
                openNotification('error', 'Erro ao enviar solicitação de agendamento', err.message);
            })
            .finally(() => {
                setRequestStatus('success');
            });
        // }
    };

    useEffect(() => {
        fetchAllFisioterapeutas();
    }, []);

    useEffect(() => {
        if (selectedFisioterapeuta !== null) {
            fetchAgendaFromFisioterapeuta();
            fetchConsultasFromFisioterapeuta();
        }
    }, [selectedFisioterapeuta]);

    const handleDatePicker = (date) => {
        setDateSelected(date.$d);
    };

    const updateFisioSelected = async (value) => {
        const fisio = await fetchFisioData(value);
        setSelectedFisioterapeuta(fisio);
    };

    const convertConsultasDateTime = () => {
        const consultasObj = consultas.map((consulta) => {
            const date = consulta.dataEHora.split(' ')[0];
            const time = consulta.dataEHora.split(' ')[1];
            const dateObj = dayjs(date).hour(time.split(':')[0]).minute(time.split(':')[1]).second(time.split(':')[2]);
            return {
                dateObj,
                fisioterapeuta: consulta.fisioterapeuta,
                paciente: consulta.paciente,
            };
        });

        return consultasObj;
    };

    const handleHourPicker = (time) => {
        if (!time) return;

        // check if the selected time is not in disabledDateTime
        const { disabledHours, disabledMinutes } = disabledTime();
        const disabledHoursArray = disabledHours();
        const disabledMinutesArray = disabledMinutes();
        const consultasObj = convertConsultasDateTime();

        if (disabledHoursArray.includes(time.hour())) {
            openNotification('error', 'Horário indisponível', `Por favor, selecione outro horário`);
            setTimeSelected(null);
            return;
        }

        if (disabledMinutesArray.includes(time.minute())) {
            openNotification('error', 'Horário indisponível', 'Por favor, selecione outro horário');
            setTimeSelected(null);
            return;
        }

        if (consultasObj.length > 0) {
            const dateSelectedObj = dayjs(dateSelected);

            // verifica se a data é a mesma
            const consultasObjFiltered = consultasObj.filter((obj) => {
                return obj.dateObj.isSame(dateSelectedObj, 'day');
            });

            // verifica se o horário é o mesmo use, $H and $m
            const consultasObjFiltered2 = consultasObjFiltered.filter((obj) => {
                return obj.dateObj.$H === time.hour() && obj.dateObj.$m === time.minute();
            });

            if (consultasObjFiltered2.length > 0) {
                openNotification('error', 'Horário já utilizado', 'Por favor, selecione outro horário');
                setTimeSelected(null);
                return;
            }
        }

        setTimeSelected(time);
    };

    const disabledDate = (current) => {
        // Can not select days before today and today
        const disabeld = current && current < dayjs().endOf('day');

        // also disable days that are disabled in the agenda
        const filteredObjects = agenda.filter(obj => obj.dia === current.$W && obj.disponivel === 0);
        const disabledInAgenda = filteredObjects.length > 0;

        return disabeld || disabledInAgenda;
    };

    function range(start, end) {
        const result = [];
        for (let i = start; i < end; i++) {
            result.push(i);
        }
        return result;
    }

    function disabledTime() {
        let hoursDisabledFinal = [];
        let minutesDisabledFinal = [];

        // disable all hours before now if date is today
        const hoursDisabled = dateSelected && dayjs(dateSelected).isSame(dayjs(), 'day') ? range(0, moment().hour() + 1) : [];
        const minutesDisabled = dateSelected && dayjs(dateSelected).isSame(dayjs(), 'day') ? range(0, moment().minute() + 1) : [];

        const dayNumber = dateSelected.getDay();
        const agenda_day = agenda.find((obj) => obj.dia === dayNumber);

        if (agenda_day) {
            const startHour = parseInt(agenda_day.horarioInicio.split(':')[0]);
            const endHour = parseInt(agenda_day.horarioFim.split(':')[0]);

            let hours1 = range(0, startHour);
            let hours2 = range(endHour, 24);
            const hours = hours1.concat(hours2).concat(hoursDisabled);

            hoursDisabledFinal = hoursDisabledFinal.concat(hours);
            minutesDisabledFinal = minutesDisabledFinal.concat(minutesDisabled);
        }

        // disable times that are already booked
        const dateSelectedObj = dayjs(dateSelected);

        // verifica se a data é a mesma
        const consultasObjFiltered = convertConsultasDateTime().filter((obj) => {
            return obj.dateObj.isSame(dateSelectedObj, 'day');
        });

        let consultasHoursArr = consultasObjFiltered.map((obj) => {
            return obj.dateObj.$H;
        });

        const remapedHousArr = consultasHoursArr.map((hour) => {
            const isBookedAt00 = consultasObjFiltered.find((obj) => obj.dateObj.$H === hour && obj.dateObj.$m === 0);
            const isBookedAt30 = consultasObjFiltered.find((obj) => obj.dateObj.$H === hour && obj.dateObj.$m === 30);
            if (isBookedAt00 && isBookedAt30) {
                return hour;
            }
        });

        hoursDisabledFinal = hoursDisabledFinal.concat(remapedHousArr);

        return {
            disabledHours: () => hoursDisabledFinal,
            disabledMinutes: () => minutesDisabledFinal,
        };
    }

    const formatDate = (date) => {
        const dateObj = dayjs(date);
        const dateFormatted = dateObj.format('DD/MM/YYYY');
        return `${dateFormatted}`;
    }

    const formatTime = (time) => {
        const timeObj = dayjs(time);
        const timeFormatted = timeObj.format('HH:mm');
        return `${timeFormatted}`;
    }

    const formatTelefone = (telefone) => {
        const tel = telefone.replace(/\D/g, '');
        const telFormatted = tel.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
        return `${telFormatted}`;
    }

    if (role === 'fisioterapeuta') {
        return (
            <Result title="Usuário não tem permissão para acessar essa página"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        );
    }

    if (currentUser === null || currentUser === undefined || currentUser.user === null || currentUser.user === undefined) {
        return (
            <Result title="Usuário não está logado"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        );
    }

    if (isErroredFisio) {
        return (
            <Content>
                {contextHolder}
                <Result
                    status="error"
                    title="Erro ao buscar profissionais"
                    subTitle="Tente novamente mais tarde" />
            </Content>
        );
    }

    if (finished) {
        const title = finished === 'confirmado' ? 'Solicitação de agendamento confirmada' : 'Solicitação de agendamento enviada';
        const description = finished === 'confirmado' ? 'Lembre-se de comparecer no horário' : 'Aguarde a confirmação do profissional';
        const status = finished === 'confirmado' ? 'success' : 'info';

        let consultaStatus;
        if (consultaData.status === 'confirmado') {
            consultaStatus = <Confirmada> Confirmada </Confirmada>;
        } else if (consultaData.status === 'pendente') {
            consultaStatus = <Pendente> Aguardando Confirmação </Pendente>;
        } else {
            consultaStatus = <NaoConfirmada> Não Confirmada </NaoConfirmada>;
        }
        const showConsultaStatus = consultaStatus;

        return (
            <Content>
                {contextHolder}
                <Result
                    status={status}
                    title={title}
                    subTitle={description} />
                <Divider />
                <Card title="Dados da consulta" type={'inner'}>
                    <BaseInfoContainer>
                        <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Status da consulta:</Text> {showConsultaStatus} <br /></InfoRow>
                        <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Data da consulta:</Text> {formatDate(consultaData.dataEHora)}<br /></InfoRow>
                        <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Horário:</Text> {formatTime(consultaData.dataEHora)}<br /></InfoRow>
                        <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Nome do profissional:</Text> {selectedFisioterapeuta.nome}<br /></InfoRow>
                        <InfoRow><Text strong style={{ fontSize: '1.2rem' }}>Telefone do profissional para contato:</Text> {formatTelefone(selectedFisioterapeuta.telefone)} <br /></InfoRow>
                        {consultaData.link &&
                            <InfoRow style={{
                                display: 'flex',
                                alignItems: 'center',
                                textAlign: 'center',
                            }}><Text strong style={{ fontSize: '1.2rem' }}>Link da consulta:</Text>
                                <LinkContainer onClick={() => { window.open(consultaData.link, '_blank') }}
                                >
                                    Acessar <FiExternalLink />
                                </LinkContainer>
                            </InfoRow>
                        }
                    </BaseInfoContainer>
                    <Divider />
                    <ButtonContainer>
                        <Link onClick={() => navigate('/')} style={{ fontSize: '1.2rem' }}>Voltar para a página inicial</Link>
                    </ButtonContainer>
                </Card>
            </Content>
        );
    }

    return (
        <Content>
            {contextHolder}
            <Title level={2}>Dados Consulta</Title>
            <Divider />
            <Space>
                <FisioInputLabel>Selecione um profissional:</FisioInputLabel>
                <Select
                    loading={fisioFetchStatus === 'loading'}
                    disabled={fisioFetchStatus !== 'success'}
                    showSearch
                    style={{
                        width: 200,
                    }}
                    onChange={(value) => { updateFisioSelected(value); }}
                    placeholder="Pesquise por nome"
                    optionFilterProp="children"
                    filterOption={(input, option) => (option?.label.toLowerCase() ?? '').includes(input.toLowerCase())}
                    filterSort={(optionA, optionB) => (optionA?.label ?? '').toLowerCase().localeCompare((optionB?.label ?? '').toLowerCase())}
                    options={fisioterapeutas.map((fisio) => {
                        return {
                            value: fisio.id,
                            label: fisio.name,
                        };
                    })} />
            </Space>

            {selectedFisioterapeuta &&
                <BottomInputsContainer direction='vertical'>
                    <DatePickerContainer>
                        <FisioInputLabel>Data da consulta:</FisioInputLabel>
                        <DatePicker
                            disabled={!isSuccessAgenda}
                            onChange={handleDatePicker}
                            locale={locale}
                            size={'large'}
                            format={'DD/MM/YYYY'}
                            disabledDate={disabledDate} />
                    </DatePickerContainer>

                    {dateSelected &&
                        <DatePickerContainer>
                            <FisioInputLabel>Horário da consulta:</FisioInputLabel>
                            <TimePicker
                                value={timeSelected}
                                hideDisabledOptions={true}
                                minuteStep={30}
                                disabled={!isSuccessAgenda}
                                onChange={(time) => { handleHourPicker(time); }}
                                locale={locale}
                                size={'large'}
                                format={'HH:mm'}
                                placeholder={'Horário'}
                                disabledTime={disabledTime} />
                        </DatePickerContainer>}

                    {timeSelected &&
                        <ButtonContainer>
                            <NextStepButton size="large"
                                loading={isLoadingRequest}
                                onClick={() => sendAppointmentRequest()}>
                                Enviar solicitação de agendamento
                            </NextStepButton>
                        </ButtonContainer>}
                </BottomInputsContainer>}
        </Content>
    );
};

export default DadosConsulta;