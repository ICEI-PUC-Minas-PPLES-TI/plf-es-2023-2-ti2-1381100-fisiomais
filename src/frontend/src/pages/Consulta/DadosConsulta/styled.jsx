import { Space } from 'antd';
import Link from 'antd/es/typography/Link';
import 'dayjs/locale/pt-br';
import styled from 'styled-components';

export const FisioInputLabel = styled.span`
    font-weight: 500;
    font-size: 1.2rem;
    margin-right: 8px;
    text-align: center;
`;

export const DatePickerContainer = styled(Space)`
    transition: all 0.5s ease-in;
    margin-bottom: 15px;
`

export const BottomInputsContainer = styled(Space)`
    margin-top: 25px;
    width: 100%;
    justify-content: flex-end;
`;

export const Confirmada = styled.span`
    color: green;
    font-weight: 500;
`;

export const Pendente = styled.span`
    color: orange;
    font-weight: 500;
`;

export const NaoConfirmada = styled.span`
    color: red;
    font-weight: 500;
`;

export const LinkContainer = styled(Link)`
    text-align: 'center';
    display: 'flex';
    align-items: 'center';
    justify-content: 'space-between';
    gap: '5px';
    z-index: 1;
`