// FisioterapeutaSignup.jsx
import React, { useEffect } from 'react';
import SignupForm from '../SignUpForm';
import { FullScreenContainer } from 'pages/Login';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const FisioterapeutaSignup = () => {
    const navigate = useNavigate();
    const currentUser = useSelector(state => state.currentUser.value);

    const noUser = () =>
        currentUser === null ||
        currentUser === undefined ||
        currentUser.user === null ||
        currentUser.user === undefined

    useEffect(() => {

        if (!noUser()) {
            navigate('/');
        }
    }, [noUser]);

    return (
        <FullScreenContainer >
            <PageItensSignup>
                <SignupForm userType="physiotherapist" />
            </PageItensSignup>
        </FullScreenContainer>
    );
};

export default FisioterapeutaSignup;

export const PageItensSignup = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`
