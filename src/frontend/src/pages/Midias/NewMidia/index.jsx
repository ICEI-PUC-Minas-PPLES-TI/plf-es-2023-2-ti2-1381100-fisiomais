import React, { useState } from 'react';
import axios from 'axios';
import { InboxOutlined } from '@ant-design/icons';
import styled from 'styled-components';
import { Form, Input, Button, Select, message, Upload, Divider, Tooltip, Space, notification } from 'antd';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const { Dragger } = Upload;
const { Option } = Select;

const DraggerContainer = styled.div`
    margin: 0 auto;
    width: 500px;
`;

const SubmitMidiaButtonContainer = styled.div`
    .ant-btn-default {
        color: white !important;
        border-color: white !important;
        background-color: #0BD980 !important;
    }

    .ant-btn-default:hover {
        background-color: #70f5bb !important;
    }
`

const NewMidia = () => {
    const [loadCreateMidia, setLoadCreateMidia] = useState(false);
    const [form] = Form.useForm();
    const [api, contextHolder] = notification.useNotification();

    const navigate = useNavigate();

    const currentUser = useSelector(state => state.currentUser.value);
    const { token } = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    if (currentUser.user.role !== 'fisioterapeuta') {
        return (
            <Result title="Usuário não tem permissão para acessar essa página"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        )
    }

    const openNotification = (type, title, description) => {
        api[type]({
            message: title,
            description: description,
            duration: 2,
            placement: 'bottomRight',
        });
    }

    const onFinish = (values) => {
        setLoadCreateMidia(true)
        handleMidiaCreation(values);
    };

    const handleMidiaCreation = (newMidia) => {
        setLoadCreateMidia(true);
        // FIXME: add id, fisioterapeuta_id and createTime to newMidia
        const mockNewMidia = {
            // id: 1,
            fisioterapeuta_id: 1,
            titulo: newMidia.titulo,
            descricao: newMidia.descricao,
            type: newMidia.type,
            createTime: new Date().toISOString(),
        }

        const newMidiaOfficial = {
            fisioterapeutaId: currentUser.user.id,
            type: newMidia.type,
            linkArquivo: newMidia.linkArquivo,
            titulo: newMidia.titulo,
            descricao: newMidia.descricao,
        }

        const body = process.env.API_TYPE === 'json' ? mockNewMidia : newMidiaOfficial;

        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/midia` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/midia`;

        // console.log(body)


        axios.post(apiRoute, body).
            then(response => {
                if (response.status !== 201) {
                    openNotification('error', 'Erro ao criar mídia!', response.message);
                }
            }
            ).catch(error => {
                openNotification('error', 'Erro ao criar mídia!', error.message);
            }).
            finally(() => {
                openNotification('success', 'Sucesso ao criar mídia!', 'Mídia criada com sucesso!');
                setLoadCreateMidia(false);
                navigate('/midias');
            });
    }

    const fileDraggerProps = {
        accept: '.mp4, .gif, .jpg, .png',
        name: 'file',
        multiple: false,
        maxCount: 1,
        rules: [
            {
                required: false,
            },
        ],
        action: 'https://run.mocky.io/v3/435e224c-44fb-4773-9faf-380c5e6a2188',
        onChange(info) {
            const { status } = info.file;
            if (status !== 'Carregando...') {
                // console.log(info.file, info.fileList);
            }
            if (status === 'Finalizado') {
                message.success(`${info.file.name} Arquivo carregado com sucesso.`);
            } else if (status === 'error') {
                message.error(`${info.file.name} Erro ao carregar arquivo.`);
            }
        },
        onDrop(e) {
            // console.log('Dropped files', e.dataTransfer.files);
        },
    };

    const isvalidUrl = (url) => {
        try {
            new URL(url);
            return true;
        } catch (error) {
            return false;
        }
    }

    const notifyErrorField = (errorInfo) => {
        const { errorFields } = errorInfo;
        const message = errorFields[0].errors[0];
        const name = errorFields[0].name[0];

        openNotification('error', `Erro ao criar mídia!`, message);

        const input = document.getElementById("media-form_" + name)
        input.focus();
    }


    return (
        <>
            {contextHolder}
            <h1>Cadastrar Mídia</h1>
            <Form
                form={form}
                name="media-form"
                onFinish={onFinish}
                onFinishFailed={notifyErrorField}
                labelCol={{ span: 6 }}
                wrapperCol={{ span: 15 }}
                disabled={loadCreateMidia}
            >
                <Form.Item label="Tipo" name="type" required rules={[{ required: true, message: "Por favor, selecione o type" }]}>
                    <Select >
                        <Option value="Video">Video</Option>
                        <Option value="GIF">GIF</Option>
                        <Option value="Imagem">Imagem</Option>
                    </Select>
                </Form.Item>


                <Form.Item label="Título" name="titulo" rules={[{ required: true, message: 'Por favor, digite um título' }]} >
                    <Input size='large' />
                </Form.Item>

                <Form.Item label="Descrição" name="descricao" rules={[{ required: true, message: 'Por favor, digite uma descrição' }]}>
                    <Input.TextArea rows={10} placeholder="Tamanho máximo de 1000 caracteres" maxLength={1000} />
                </Form.Item>

                <Divider />

                <Space>
                    <Tooltip
                        title="Apenas utilize um link ou selecione um arquivo"
                        color={'#0bd980'}
                        key={'#0bd980'}
                        placement='right'
                    >
                        <h1 style={{
                            cursor: 'help'
                        }}>Arquivo</h1>
                    </Tooltip>
                </Space>

                <Form.Item label="Link Arquivo"
                    name="linkArquivo"
                    rules={[{
                        required: true,
                        message: 'Por favor digite um link válido',
                        type: 'url',
                    }]}
                    validateTrigger={['onBlur', 'onChange']}
                >
                    <Input size='large' />
                </Form.Item>

                {/* <DraggerContainer >
                    <Dragger {...fileDraggerProps}
                        style={{
                            width: '500px',
                        }}>
                        <p className="ant-upload-drag-icon">
                            <InboxOutlined />
                        </p>
                        <p className="ant-upload-text">Clique ou arraste o arquivo para esta área para fazer upload</p>
                        <p className="ant-upload-hint">
                            Suporte para upload único. Tipos de arquivos: .mp4, .gif, .jpg, .png
                        </p>
                    </Dragger>
                </DraggerContainer> */}

                <Form.Item style={{
                    textAlign: 'center',
                    margin: '20px auto 0 auto',
                    display: 'flex',
                    justifyContent: 'center',
                }}>
                    <SubmitMidiaButtonContainer>
                        <Button htmlType="submit" size='large' loading={loadCreateMidia}>
                            Finalizar
                        </Button>
                    </SubmitMidiaButtonContainer>
                </Form.Item>
            </Form>
        </>
    );
};
export default NewMidia;