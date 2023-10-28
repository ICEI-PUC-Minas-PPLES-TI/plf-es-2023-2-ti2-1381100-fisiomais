import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux'
import { setCurrentMedia } from '../../store/mediaDetail'
import styled from 'styled-components';
import { Content } from 'antd/es/layout/layout';
import { Divider, Button, Table, ConfigProvider } from 'antd';

// icons
import { MdAdd } from 'react-icons/md';
import { AiOutlineDelete } from 'react-icons/ai';
import { FaCheck } from 'react-icons/fa';
import { MdOutlineCancel } from 'react-icons/md';

import midiasJson from './data/mock-data.json' // TODO - Remover mock data

const columns = [
    {
        title: 'Título',
        dataIndex: 'titulo',
    },
    {
        title: 'Descrição',
        dataIndex: 'descricao',
    },
    {
        title: 'Tipo',
        dataIndex: 'tipo',
    },
    {
        title: 'Data de criação',
        dataIndex: 'created_at',
    }
];

const SELECTED = true

// flex horizontal container for buttons
const ButtonsContainer = styled.div`
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 15px;
`

const CreateMidiaButtonContainer = styled.div`
    .ant-btn-default:hover {
        color: #0BD980 !important;
        border-color: #0BD980 !important;
    }
`

const DeleteMidiaButtonContainer = styled.div`
    .ant-btn-default:hover {
        color: #F95E5A !important;
        border-color: #F95E5A !important;
    }
`

const ConfirmDeleteMidiasButtonContainer = styled.div`
    .ant-btn-default {
        background-color: #F95E5A !important;
        color: white !important;
        border-color: #white !important;
    }

    .ant-btn-default:hover {
        background-color: #fa8c89 !important;
        color: white !important;
        border-color: white !important;
    }
`

const HeadContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;

    h1 {
        font-size: 24px;
        font-weight: 600;
    }
`

const Midias = () => {
    const [shortMidias, setShortMidias] = useState([]);
    const [deletionStack, setDeletionStack] = useState([]);
    const [deleteMidias, setDeleteMidias] = useState(false);

    const navigate = useNavigate();
    const dispatch = useDispatch()

    const data = midiasJson.midias.map(midia => {
        const { id, titulo, descricao, tipo, created_at } = midia;
        const formatedDate = new Date(created_at).toLocaleString('pt-BR');

        const dispatchMidiaData = (id) => (event) => {
            event.preventDefault();

            const midia = midiasJson.midias.find(midia => midia.id === id) ?? undefined;

            dispatch(setCurrentMedia(midia))

            navigate(`/midia/${id}`);
        }

        const routeToMidia = (titulo, id) => {
            return <a
                onClick={dispatchMidiaData(id)}>
                {titulo}
            </a>
        }

        const titleComponent = routeToMidia(titulo, id);

        return {
            key: id,
            id,
            titulo: titleComponent,
            descricao: descricao.substring(0, 50) + '...',
            tipo,
            created_at: formatedDate,
        }
    });

    // most recent first
    const orderedData = data.sort((a, b) => {
        if (a.created_at > b.created_at) {
            return -1;
        }
        if (a.created_at < b.created_at) {
            return 1;
        }
        return 0;
    }
    );

    useEffect(() => {
        setShortMidias(orderedData);
    }, []);

    useEffect(() => {
    }, [deletionStack]);

    const activateDeleteMidias = () => {
        setDeleteMidias(true);
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

    const handleMediaDeletion = () => {
        // FIXME remove from shortMidias
        const filteredMidias = shortMidias.filter(midia => !deletionStack.includes(midia.id));
        setShortMidias(filteredMidias);

        setDeleteMidias(false);
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

    return (
        <>
            <HeadContainer>
                <h1>Midias</h1>

                <ButtonsContainer>
                    {!deleteMidias && (
                        <>
                            <CreateMidiaButtonContainer>
                                <Button
                                    size="large"
                                    icon={<MdAdd />}
                                    onClick={() => navigate('/midia/criar')}
                                >
                                    Criar Mídia
                                </Button>
                            </CreateMidiaButtonContainer>

                            <DeleteMidiaButtonContainer>
                                <Button
                                    size="large"
                                    icon={<AiOutlineDelete />}
                                    onClick={() => activateDeleteMidias()}
                                >
                                    Deletar Mídias
                                </Button>
                            </DeleteMidiaButtonContainer>
                        </>
                    )}

                    {deleteMidias && (
                        <>
                            <Button
                                size="large"
                                icon={<MdOutlineCancel />}
                                onClick={() => {
                                    setDeleteMidias(false)
                                    setDeletionStack([])
                                }}
                            >
                                Cancelar
                            </Button>

                            <ConfirmDeleteMidiasButtonContainer>
                                <Button
                                    size="large"
                                    icon={<FaCheck />}
                                    onClick={() => handleMediaDeletion()}
                                >
                                    Confirmar
                                </Button>
                            </ConfirmDeleteMidiasButtonContainer>
                        </>
                    )}

                </ButtonsContainer>
            </HeadContainer>

            <Divider />

            <div style={{
                maxHeight: 'calc(100vh - 200px)',
                overflowY: 'auto',
            }}>
                <Table
                    rowSelection={deleteMidias ? {
                        type: 'checkbox',
                        onSelect: (record, selected, selectedRows, nativeEvent) => {
                            handleRowSelection(record.id, selected);
                        },
                        onSelectAll: (selected, selectedRows, changeRows) => {
                            for (const row of selectedRows) {
                                handleRowSelection(row.id, selected);
                            }
                        }
                    } : null}
                    columnTitle="Deletar"
                    columns={columns}
                    dataSource={shortMidias}
                    pagination={getPageSizeBasedOnScreenSize()}
                />
            </div>

        </>
    );
};
export default Midias;