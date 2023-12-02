// SignupForm.jsx
import React, { useState } from 'react';
import { Form, Input, Button, Select, Space, notification } from 'antd';
import styled from 'styled-components';
import FisiomaisLogo from 'assets/images/logo_stroke_white.svg';
import { useDispatch, useSelector } from 'react-redux';
import { LogoImage } from 'pages/Login/LoginForm';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const { Option } = Select;

const SignupForm = ({ userType }) => {
    const currentUser = useSelector(state => state.currentUser.value);

    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const [fetchStatus, setFetchStatus] = useState('idle');
    const [api, contextHolder] = notification.useNotification();

    const isLoading = fetchStatus === 'loading';
    const isErrored = fetchStatus === 'error';

    const openNotification = (type, title, description) => {
        api[type]({
            message: title,
            description: description,
            duration: 3,
            placement: 'bottomRight',
        });
    };

    const onFinishFailed = (errorInfo) => {
        if (errorInfo === 'Email já cadastrado. Tente realizar o login.' || errorInfo === 'CPF já cadastrado. Tente realizar o login.') {
            api.error({
                message: `Erro ao cadastrar ${userType === 'physiotherapist' ? 'fisioterapeuta' : 'paciente'}`,
                description: errorInfo,
                duration: 5,
                placement: 'bottomRight',
                btn: (
                    <LoginRedictButton type="primary" size="small" onClick={() => navigate('/login')}>
                        Ir para Login
                    </LoginRedictButton>
                ),
            });
        } else {
            openNotification('error', 'Erro ao cadastrar paciente', errorInfo || 'Erro ao cadastrar paciente');
        }
        setFetchStatus('error');
    }

    const singUpPaciente = async (values) => {

        const apiRoute = `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/paciente`;

        const wait = (timeout) => {
            return new Promise(resolve => {
                setTimeout(resolve, timeout);
            });
        }

        const requesBody = {
            nome: values.name,
            email: values.email,
            password: values.password,
            dataNascimento: values.dataNascimento.split('-').reverse().join('/'),
            cpf: values.cpf,
            telefone: values.phone,
            genero: values.genero,
            endereco: values.address,
        }

        await axios.post(apiRoute, requesBody)
            .then(response => {
                form.resetFields();
                setFetchStatus('idle');
                openNotification('success', 'Cadastro realizado com sucesso!', 'Você será redirecionado para a página de login em instantes.');
                wait(2500).then(() => {
                    navigate('/login');
                })
            })
            .catch(error => {
                onFinishFailed(error.response.data.message || 'Erro ao cadastrar paciente')
            });
    }

    const singUpFisioterapeuta = async (values) => {

        const apiRoute = `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/fisioterapeuta`;

        const wait = (timeout) => {
            return new Promise(resolve => {
                setTimeout(resolve, timeout);
            });
        }

        await axios.post(apiRoute, {
            nome: values.name,
            email: values.email,
            password: values.password,
            telefone: values.phone,
            endereco: values.address,
            controleAutomatico: values.automatic,
        })
            .then(response => {
                form.resetFields();
                setFetchStatus('idle');
                openNotification('success', 'Cadastro realizado com sucesso!', 'Você será redirecionado para a página de login em instantes.');
                wait(2500).then(() => {
                    navigate('/login');
                })
            })
            .catch(error => {
                onFinishFailed(error.response.data.message || 'Erro ao cadastrar paciente')
            });
    }

    const onFinish = (values) => {
        setFetchStatus('loading');
        if (userType === 'physiotherapist') {
            singUpFisioterapeuta(values);
        } else {
            singUpPaciente(values);
        }
    };

    return (
        <div>
            {contextHolder}
            <LogoAndTitle style={{ marginBottom: '50px' }}>
                <LogoImage
                    preview={false}
                    width={250}
                    height={250}
                    src={FisiomaisLogo}
                    fallback="data:image/png;base64,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"
                />
                <SignUpTitle>Cadastro de {userType === 'physiotherapist' ? 'Fisioterapeuta' : 'Paciente'}</SignUpTitle>
            </LogoAndTitle>


            <Form
                disabled={isLoading}
                form={form}
                name="signup_form"
                onFinish={onFinish}
            >
                {/* Campos comuns para ambos os tipos de cadastro */}
                <Form.Item
                    name="name"
                    label="Nome"
                    rules={[
                        {
                            required: true,
                            message: 'Por favor, insira seu nome!',
                            max: 100
                        },
                    ]}
                >
                    <Input placeholder="Seu Nome Completo" />
                </Form.Item>

                <Form.Item
                    name="email"
                    label="Email"
                    rules={[
                        {
                            required: true,
                            message: 'Por favor, insira seu email!',
                            max: 255
                        },
                    ]}
                >
                    <Input placeholder="nome@email.com" />
                </Form.Item>

                <Form.Item
                    name="password"
                    label="Senha"
                    rules={[
                        {
                            required: true,
                            message: 'Por favor, insira sua senha!',
                            max: 32
                        },
                    ]}
                >
                    <Input.Password placeholder="Senha" />
                </Form.Item>

                <Form.Item
                    name="address"
                    label="Endereço"
                    rules={[
                        {
                            required: true,
                            message: 'Por favor, insira seu endereço!',
                            max: 200
                        },
                    ]}
                >
                    <Input placeholder="Rua, Número, Bairro, Cidade, Estado" />
                </Form.Item>

                <Form.Item
                    name="phone"
                    label="Telefone de Contato"
                    rules={[
                        {
                            required: true,
                            message: 'Por favor, insira seu telefone!',
                            pattern: new RegExp(/^[0-9]{11}$/),
                        },
                    ]}
                >
                    <NumberInputNoArrows placeholder="31987654321"
                        type='number'
                    />
                </Form.Item>

                {/* Campos específicos para Fisioterapeuta */}
                {userType === 'physiotherapist' &&
                    <>
                        <Form.Item
                            name="automatic"
                            label="Controle Automático"
                            rules={[
                                {
                                    required: true,
                                    message: 'Por favor, selecione uma opção!',
                                },
                            ]}
                            initialValue={false}
                        >
                            <Select>
                                <Option value={true}>Sim</Option>
                                <Option value={false}>Não</Option>
                            </Select>
                        </Form.Item>
                    </>
                }

                {/* Campos específicos para Paciente */}
                {userType === 'patient' &&
                    <>
                        <Form.Item
                            name="dataNascimento"
                            label="Data de Nascimento"
                            rules={[
                                {
                                    required: true,
                                    message: 'Por favor, insira sua data de nascimento!',
                                },
                            ]}
                        >
                            <Input
                                type="date"
                            />
                        </Form.Item>

                        <Form.Item
                            name="cpf"
                            label="CPF"
                            rules={[
                                {
                                    required: true,
                                    message: 'Por favor, insira seu CPF!',
                                    pattern: new RegExp(/^[0-9]{11}$/),
                                    max: 11
                                },
                            ]}
                        >
                            <NumberInputNoArrows placeholder="12345678901"
                                type='number'
                            />
                        </Form.Item>

                        <Form.Item
                            name="genero"
                            label="Gênero"
                            rules={[
                                {
                                    required: true,
                                    message: 'Por favor, selecione uma opção!',
                                },
                            ]}
                            initialValue={'Outro'}
                        >
                            <Select>
                                <Option value="Homem">Homem</Option>
                                <Option value="Mulher">Mulher</Option>
                                <Option value="Outro">Outro</Option>
                            </Select>
                        </Form.Item>
                    </>
                }

                <Form.Item>
                    <CustomSignupButton type="primary" htmlType="submit" loading={isLoading}>
                        Cadastrar
                    </CustomSignupButton>
                </Form.Item>
            </Form>
        </div>
    );
};

export default SignupForm;

const CustomSignupButton = styled(Button)`
    width: 100%;
    background-color: #00c3a5;
    border-color: #00c3a5;
    &:hover {
        background-color: #4ce2cc !important;
        border-color: white !important;
        color: #00c3a5 !important;
    }
`;

const SignUpTitle = styled.h2`
    text-align: center;
    margin-bottom: 50px;
`;

const LogoAndTitle = styled(Space)`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const NumberInputNoArrows = styled(Input)`
    -moz-appearance: textfield;
    &::-webkit-outer-spin-button,
    &::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
`;

const LoginRedictButton = styled(Button)`
    background-color: #00c3a5;
    border-color: #00c3a5;
    &:hover {
        background-color: #4ce2cc !important;
        border-color: white !important;
        color: #00c3a5 !important;
    }
`;