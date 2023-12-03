import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux'
import { setCurrentMedia } from '../../store/mediaDetail'
import { Divider, Result, notification } from 'antd';

import TableHeader from './components/TableHeader';
import PlanosTable from './components/PlanosTable';
import axios from 'axios';

// tituloTexto
// conteudoTexto
// dataInicio
// frequencia
// createTime

const columns = [
    {
        title: 'Título Texto',
        dataIndex: 'tituloTexto',
    },
    {
        title: 'Conteúdo Texto',
        dataIndex: 'conteudoTexto',
    },
    {
        title: 'Data Início',
        dataIndex: 'dataInicio',
    },
    {
        title: 'Frequência',
        dataIndex: 'frequencia',
    },
    {
        title: 'Data de criação',
        dataIndex: 'createTime',
    }
];

const SELECTED = true

const Planos = () => {
    const [shortPlanos, setShortPlanos] = useState([]);
    const [deletionStack, setDeletionStack] = useState([]);
    const [deletePlanos, setDeletePlanos] = useState(false);
    const [loadingPlanos, setLoadingPlanos] = useState(true);
    const [loadingDeletion, setLoadingDeletion] = useState(false);
    const currentUser = useSelector(state => state.currentUser.value);

    if (currentUser.user.role !== 'fisioterapeuta') {
        return (
            <Result title="Usuário não tem permissão para acessar essa página"
                subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário">
            </Result>
        )
    }

    const [api, contextHolder] = notification.useNotification();

    const openNotification = (type, title, description) => {
        api[type]({
            message: title,
            description: description,
            duration: 2,
            placement: 'bottomRight',
        });
    };

    const navigate = useNavigate();

    // most recent first
    const orderedData = (data) => {
        return data.sort((a, b) => {
            if (a.createTime > b.createTime) {
                return -1;
            }
            if (a.createTime < b.createTime) {
                return 1;
            }
            return 0;
        }
        );
    }

    const fetchDeletedPlanos = async (ids) => {
        let finalError = {};
        const stringListOfIds = ids.map(item => item).join(',');
        await axios.delete(`${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/planos/${stringListOfIds}`).
            then(response => {
                if (response.status !== 200) {
                    finalError = response;
                }
            }
            ).catch(error => {
                console.log(error)
                finalError = error;
            }).
            finally((error) => {
            });

        return finalError;
    }

    const fetchDeletedPlano = async (id) => {
        let finalError = {};
        await axios.delete(`${import.meta.env.VITE_API_BASE_ROUTE_JSON}/planos/${id}`).
            then(response => {
                if (response.status !== 200) {
                    finalError = response;
                }
            }
            ).catch(error => {
                finalError = error;
            }).
            finally((error) => {
            });

        return finalError;
    }

    const fetchPlanos = async () => {
        setLoadingPlanos(true);
        const apiRoute = process.env.API_TYPE === 'json' ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/planos?fisioterapeuta_id=${currentUser.userId}` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/planos/${currentUser.userId}`;

        await axios.get(apiRoute).
            then(response => {
                const data = response.data.map(plano => {
                    const { id, tituloTexto, conteudoTexto, dataInicio, frequencia, createTime } = plano;

                    const dispatchPlanoData = (id) => (event) => {
                        event.preventDefault();
                        navigate(`/planos/${id}`);
                    }

                    const routeToPlano = (titulo, id) => {
                        return (
                          <a onClick={dispatchPlanoData(id)}>
                            {titulo}
                          </a>
                        );
                    }

                    const titleComponent = routeToPlano(tituloTexto, id);

                    return {
                        key: id,
                        id,
                        tituloTexto: titleComponent,
                        conteudoTexto: conteudoTexto.substring(0, 20) + (conteudoTexto.length > 20 ? '...' : ''),
                        dataInicio: new Date(dataInicio).toLocaleString('pt-BR'),
                        frequencia,
                        createTime: new Date(createTime).toLocaleString('pt-BR')
                    }
                });

                setShortPlanos(orderedData(data));
            }
            ).catch(error => {
                openNotification('error', 'Listar Planos', 'Erro ao listar planos!');
            }).
            finally(() => {
                setLoadingPlanos(false);
            });
    }

    useEffect(() => {
        if (!deletePlanos)
            fetchPlanos();
    }, [deletePlanos]);

    const activateDeletePlanos = () => {
        setDeletePlanos(true);
    }

    const getPageSizeBasedOnScreenSize = () => {
        const width = window.innerWidth;
        if (width <= 1600) {
            return {
                pageSize: 6,
            }
        }
        return {
            pageSize: 10,
        }
    }

    const handlePlanoDeletion = async () => {
        const deleteOneByOne = process.env.API_TYPE === 'json' ? true : false;
        let erroShown = false

        if (deleteOneByOne) {
            console.log(`Deleting one by one: ${deletionStack}`)
            deletionStack.forEach(async element => {
                if (!erroShown) {
                    const resp = await fetchDeletedPlano(element);

                    if (resp.response?.data.message) {
                        find = shortPlanos.find(item => item.id === element);
                        openNotification('error', `Deletar Planos: ${find.titulo.props.children}`, resp.response.data.message);
                        erroShown = true;
                    }
                }
            });
        } else {
            console.log(`Deleting all at once: ${deletionStack}`)
            const resp = await fetchDeletedPlanos(deletionStack);
            if (resp.message) {
                openNotification('error', `Deletar Planos`, resp.response.data.message);
            }
        }

        if (!erroShown) {
            openNotification('success', 'Deletar Planos', 'Planos deletadas com sucesso!');
        }

        setDeletePlanos(false);
        setDeletionStack([]);
    }

    const handleRowSelection = (id, event) => {
        if (event === SELECTED) {
            if (!deletionStack.includes(id)) {
                const newStack = deletionStack;
                newStack.push(id);
                setDeletionStack(newStack);
            }
            return;
        }

        if (deletionStack.includes(id)) {
            const filteredStack = deletionStack.filter(item => item !== id);
            setDeletionStack(filteredStack);
        }
    }

    const cancelDeletion = () => {
        setDeletePlanos(false);
        setDeletionStack([]);
    }
    return (
        <div>
            {contextHolder}
            <TableHeader
                deletePlanos={deletePlanos}
                activateDeletePlanos={activateDeletePlanos}
                cancelDeletion={cancelDeletion}
                handlePlanoDeletion={handlePlanoDeletion}
            />
            <Divider />

            <PlanosTable
                handlePlanoDeletion={handlePlanoDeletion}
                handleRowSelection={handleRowSelection}
                getPageSizeBasedOnScreenSize={getPageSizeBasedOnScreenSize}
                shortPlanos={shortPlanos}
                columns={columns}
                deletePlanos={deletePlanos}
                loadingPlanos={loadingPlanos}
            />
        </div>
    );
};
export default Planos;