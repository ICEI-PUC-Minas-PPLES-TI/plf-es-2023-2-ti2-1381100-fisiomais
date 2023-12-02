import React from 'react';
import {
    FileImageOutlined
} from '@ant-design/icons';

import { CiMedicalClipboard } from 'react-icons/ci'
import { GrSchedule } from 'react-icons/gr'
import { MdPeopleOutline } from 'react-icons/md'
import { AiOutlineBarChart } from 'react-icons/ai'
import { LiaHistorySolid } from 'react-icons/lia'
import { AiOutlineHome } from 'react-icons/ai'
import { FaUserInjured } from 'react-icons/fa'
import { MdHealing } from 'react-icons/md'
import { GiLoveInjection } from 'react-icons/gi'

const SideMenuItensFisio = [
    {
        key: 1,
        icon: <FileImageOutlined />,
        label: 'Mídias',
        route: 'midias',
    },
    {
        key: 3,
        icon: <GrSchedule />,
        label: 'Agenda',
        route: 'agenda',
    },
    // {
    //     key: 6,
    //     icon: <MdPeopleOutline />,
    //     label: 'Cadastro Fisioterapeuta',
    //     route: 'fisioterapeuta',
    // },
    {
        key: 7, 
        icon: <MdPeopleOutline />,
        label: 'Acompanhamento',
        route: 'acompanhamento',
    },
    // {
    //     key: 8, 
    //     icon: <FaUserInjured />,
    //     label: 'Cadastrar Paciente',
    //     route: 'cadastro',
    // },
    {
        key: 9, 
        icon: <MdHealing />,
        label: 'Criar Tratamento',
        route: 'tratamento',
    }
]

export const SideMenuItensPaciente = [
    {
        key: 3,
        icon: <GrSchedule />,
        label: 'Agenda',
        route: 'agenda',
    },
]

export const SideMenuItensAdmin = [
    {
        key: 10,
        icon: <AiOutlineBarChart />,
        label: 'Indicadores',
        route: 'indicadores',
    },
]

export default SideMenuItensFisio;