import {
    MenuFoldOutlined,
    MenuUnfoldOutlined,
} from '@ant-design/icons';
import { CiLogout } from 'react-icons/ci';
import { IoMdNotificationsOutline } from 'react-icons/io';
import { Avatar, Badge, Button, Layout } from 'antd';
import styled from 'styled-components';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { logout } from 'store/currentUser';
const { Header, Content } = Layout;

const PageHeader = ({ collapsed, setCollapsed, colorBgContainer }) => {
    const [loadingLogout, setLoadingLogout] = useState(false);

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const currentUser = useSelector(state => state.currentUser.value);

    if(Object.keys(currentUser.user).length === 0) {
        return null;
    }

    const userIsFisio = currentUser.user.role === 'fisioterapeuta';
    const userIsPaciente = currentUser.user.role === 'paciente';
    const userIsLogged = Object.keys(currentUser.user).length > 0;

    const handleNotifications = () => {
        // TODO - handle notifications
    }

    const handleLogout = () => {
        setLoadingLogout(true);

        dispatch(logout());

        setTimeout(() => {
            setLoadingLogout(false);
        }, 1500)
    }

    if (!userIsLogged) {
        // check if is in the login page or singup page
        const isLoginPage = window.location.pathname === '/login';
        const isSingupPage = window.location.pathname === '/singup';

        if (isLoginPage || isSingupPage) {
            return null;
        }

        // if not, redirect to login page
        navigate('login');
    }

    return (
        <Layout>
            <Header
                style={{
                    padding: 0,
                    background: colorBgContainer,
                }}
            >
                <HeaderOptionsContainer>
                    <Button
                        type="text"
                        icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
                        onClick={() => setCollapsed(!collapsed)}
                        style={{
                            fontSize: '16px',
                            width: 64,
                            height: 64,
                        }}
                    />
                    <UserHeaderOptionsContainer>
                        <LogoutButton onClick={() => navigate(handleLogout())} loading={loadingLogout}>
                            Sair da conta <CiLogout />
                        </LogoutButton>
                    </UserHeaderOptionsContainer>
                </HeaderOptionsContainer>
            </Header>
        </Layout>
    );
}

export default PageHeader;

const HeaderOptionsContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding-right: 20px;
`;

const UserHeaderOptionsContainer = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 10px;
`;

const LogoutButton = styled(Button)`
    background-color: #fff;
    color: #e75e1f;
    border: 0.5px solid #e75e1f;
    font-weight: 350;
    font-size: 15px;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 5px;
    transition: all 0.5s ease-in;
    &:hover{
        background-color: #e75e1f;
        color: #fff !important;
        border: 0.5px solid #fff !important;
    }
`