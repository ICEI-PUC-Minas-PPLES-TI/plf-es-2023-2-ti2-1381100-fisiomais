import React from 'react';
import {
    FileImageOutlined
} from '@ant-design/icons';

import { CiMedicalClipboard } from 'react-icons/ci'
import { GrSchedule } from 'react-icons/gr'
import { MdPeopleOutline } from 'react-icons/md'
import { LiaHistorySolid } from 'react-icons/lia'
import { AiOutlineHome } from 'react-icons/ai'

const SideMenuItens = [
    // {
    //     key: 0,
    //     icon: <AiOutlineHome />,
    //     label: 'Inicio',
    //     route: '',
    // },
    {
        key: 1,
        icon: <FileImageOutlined />,
        label: 'Mídias',
        route: 'midias',
    },
    // {
    //     key: 2,
    //     icon: <CiMedicalClipboard />,
    //     label: 'Exercicios',
    //     route: 'exercicios',
    // },
    {
        key: 3,
        icon: <GrSchedule />,
        label: 'Agenda',
        route: 'agenda',
    },
    // {
    //     key: 4,
    //     icon: <MdPeopleOutline />,
    //     label: 'Pacientes',
    //     route: 'pacientes',
    // },
    // {
    //     key: 5,
    //     icon: <LiaHistorySolid />,
    //     label: "Histórico",
    //     route: "historico",
    // },
    {
        key: 6,
        icon: <MdPeopleOutline />,
        label: 'Cadastro Fisioterapeuta',
        route: 'fisioterapeuta',
    },
    {
        key: 7, 
        icon: <MdPeopleOutline />,
        label: 'Acompanhamento',
        route: 'acompanhamento',
    }
]

export default SideMenuItens;