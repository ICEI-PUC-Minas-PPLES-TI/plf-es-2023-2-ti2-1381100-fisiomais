import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { Button } from 'antd';

// icons
import { MdAdd, MdOutlineCancel } from 'react-icons/md';
import { AiOutlineDelete } from 'react-icons/ai';
import { FaCheck } from 'react-icons/fa';

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
        border-color: white !important;
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

const TableHeader = ({ deleteMidias, activateDeleteMidias, cancelDeletion, handleMediaDeletion }) => {
    const navigate = useNavigate();
    
    return (
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
                            onClick={() => cancelDeletion()}
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
    );
}

export default TableHeader;