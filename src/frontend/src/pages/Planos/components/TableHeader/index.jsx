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

const CreateButtonContainer = styled.div`
    .ant-btn-default:hover {
        color: #0BD980 !important;
        border-color: #0BD980 !important;
    }
`

const DeleteButtonContainer = styled.div`
    .ant-btn-default:hover {
        color: #F95E5A !important;
        border-color: #F95E5A !important;
    }
`

const ConfirmDeleteButtonContainer = styled.div`
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

const TableHeader = ({ deletePlanos, activateDeletePlanos, cancelDeletion, handlePlanoDeletion }) => {
    const navigate = useNavigate();
    
    return (
        <HeadContainer>
            <h1>Planos</h1>

            <ButtonsContainer>
                {!deletePlanos && (
                    <>
                        <CreateButtonContainer>
                            <Button
                                size="large"
                                icon={<MdAdd />}
                                onClick={() => navigate('/planos/criar')}
                            >
                                Criar Plano
                            </Button>
                        </CreateButtonContainer>

                        <DeleteButtonContainer>
                            <Button
                                size="large"
                                icon={<AiOutlineDelete />}
                                onClick={() => activateDeletePlanos()}
                            >
                                Deletar Planos
                            </Button>
                        </DeleteButtonContainer>
                    </>
                )}

                {deletePlanos && (
                    <>
                        <Button
                            size="large"
                            icon={<MdOutlineCancel />}
                            onClick={() => cancelDeletion()}
                        >
                            Cancelar
                        </Button>

                        <ConfirmDeleteButtonContainer>
                            <Button
                                size="large"
                                icon={<FaCheck />}
                                onClick={() => handlePlanoDeletion()}
                            >
                                Confirmar
                            </Button>
                        </ConfirmDeleteButtonContainer>
                    </>
                )}

            </ButtonsContainer>
        </HeadContainer>
    );
}

export default TableHeader;