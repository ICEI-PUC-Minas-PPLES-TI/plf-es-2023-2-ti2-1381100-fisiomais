// App.js
import React, { useState } from 'react';
import { Layout, theme } from 'antd';

import SideBar from 'components/MainPage/SideBar';
import MainContent from 'components/MainPage/MainContent';
import PageHeader from 'components/MainPage/PageHeader';

import { RightSideContainer } from 'style.jsx';

const App = () => {
    const [collapsed, setCollapsed] = useState(false);
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    return (
        <div >
            <Layout hasSider >
                <SideBar collapsed={collapsed} />
                <RightSideContainer className="site-layout" collapsed={collapsed}>
                    <PageHeader collapsed={collapsed} setCollapsed={setCollapsed} colorBgContainer={colorBgContainer} />
                    <MainContent colorBgContainer={colorBgContainer} />
                </RightSideContainer>
            </Layout>
        </div>
    );
};

export default App;