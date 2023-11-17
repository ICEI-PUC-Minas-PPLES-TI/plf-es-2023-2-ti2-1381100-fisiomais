import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux'
import { Menu, Layout } from 'antd';


import FisiomaisLogo from 'assets/images/logo_stroke_white.svg';
import SideMenuItens from './data/menu_itens';
import { setPage } from 'store/currentPage'

const { Sider } = Layout;

const SideBar = ({ collapsed }) => {
    const [defaultSelectedKey, setDefaultSelectedKey] = useState('0');

    const currentUser = useSelector(state => state.currentUser.value);

    if(Object.keys(currentUser.user).length === 0) {
        return null;
    }

    const navigate = useNavigate();
    const dispatch = useDispatch()

    const updateMenuSelection = () => {
        const currentPage = window.location.pathname
        let currentPageKey = 0;
        try {
            currentPageKey = SideMenuItens.find(item => currentPage.includes(item.route)).key;
        } catch (error) {
            currentPageKey = 0;
        }
        setDefaultSelectedKey(currentPageKey.toString());
    }

    useEffect(() => {
        updateMenuSelection();   
    }, []);

    const handleButtonClick = (path, key) => {
        dispatch(setPage(key))
        navigate(path);
        updateMenuSelection()
    };

    const menuItems = SideMenuItens.map((item) => {
        return (
            {
                key: item.key,
                icon: item.icon,
                label: item.label,
                onClick: () => handleButtonClick(item.route, item.key),
            }
        );
    });

    const handleHomeButtonClick = () => {
        if (window.location.pathname !== '/') {
            handleButtonClick('/', 0)
        }
    }

    return (
        <Sider
            style={{
                overflow: 'auto',
                height: '100vh',
                position: 'fixed',
                left: 0,
                top: 0,
                bottom: 0,
            }}
            trigger={null}
            collapsible collapsed={collapsed}
            theme="light"
        >
            <div style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
            }}>
                <img
                    onClick={() => handleHomeButtonClick()}
                    src={FisiomaisLogo}
                    alt="Fisiomais logo"
                    style={{
                        width: collapsed ? 50 : 100,
                        margin: '16px 0',
                        transition: 'all 0.2s',
                        cursor: 'pointer',
                    }}
                />
            </div>

            <Menu
                mode="inline"
                items={menuItems}
                selectedKeys={[defaultSelectedKey]}
            />
        </Sider>
    )
};

export default SideBar;
