import { Layout, Result } from 'antd';
const { Content } = Layout;

import { MainContentContainer } from './style.jsx'

import PagesRouter from 'components/MainPage/PagesRouter';
import { useSelector } from 'react-redux';
import Login from 'pages/Login/index.jsx';
import styled from 'styled-components';

const MainContent = ({ colorBgContainer }) => {
    const currentUser = useSelector(state => state.currentUser.value);
    const routeParam = window.location.pathname.split('/')[1];

    if (currentUser === null || currentUser === undefined || currentUser.user === null || currentUser.user === undefined) {
        if (routeParam === 'login') {
            return (
                <div>
                    <Login />
                </div>
            );
        }

        return (
            null
            // <FullScreenContainer>
            //     <Result
            //         status={403}
            //         title="Usuário não está logado"
            //         subTitle="Desculpe, ocorreu um erro ao buscar os detalhes de usuário"
            //     />
            // </FullScreenContainer>
        );
    }

    return (
        <MainContentContainer data-color-bg-container={colorBgContainer}>
            <PagesRouter />
        </MainContentContainer>
    );
}

export default MainContent;

const FullScreenContainer = styled.div`
    position: absolute;
    left: 0;
    top: 0;
    height: 100vh;
    width: 100vw;
    display: flex;
    justify-content: center;
    align-items: center;
`