// PacienteSignup.jsx
import React, {useEffect} from 'react';
import SignupForm from '../SignUpForm';
import { FullScreenContainer } from 'pages/Login';
import { PageItensSignup } from '../FisioterapeutaSignUp';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const PacienteSignup = () => {
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
        <FullScreenContainer>
            <PageItensSignup>
                <SignupForm userType="patient" />
            </PageItensSignup>
        </FullScreenContainer>
    );
};

export default PacienteSignup;