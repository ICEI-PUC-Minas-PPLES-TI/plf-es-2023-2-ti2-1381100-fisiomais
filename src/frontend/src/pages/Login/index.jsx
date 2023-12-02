import { Content } from 'antd/es/layout/layout';
import React from 'react';
import LoginForm from './LoginForm';
import styled from 'styled-components';

const Login = () => {
    return (
        <FullScreenContainer>
            <LoginForm />
        </FullScreenContainer>
    )
};

export default Login;

export const FullScreenContainer = styled.div`
    position: absolute;
    left: 0;
    top: 0;
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;
`