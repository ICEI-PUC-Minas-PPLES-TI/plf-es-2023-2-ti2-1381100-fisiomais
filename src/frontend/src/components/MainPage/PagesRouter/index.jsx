import { Routes, Route } from 'react-router-dom';

import Home from 'pages/Home';
import Midias from 'pages/Midias';
import Agenda from 'pages/Agenda';
import Exercicios from 'pages/Exercicios';
import Historico from 'pages/Historico';
import Pacientes from 'pages/Pacientes';
import MidiaDetail from 'pages/Midias/MidiaDetail';
import NewMidia from 'pages/Midias/NewMidia';
import Cadastro from 'pages/Cadastro';
import Plano from 'pages/Plano'
import CadastroFisioterapeuta from 'pages/Fisioterapeuta';
import AcompanhamentoVirtual from 'pages/Acompanhar';
//import GerenciamentoPlanos from 'pages/Gerenciamento';

const PagesRouter = () => {
    return ( <
        Routes >
        <
        Route path = "/"
        element = { < Home / > }
        /> <
        Route path = "/midias"
        element = { < Midias / > }
        /> <
        Route path = "/midia/:id"
        element = { < MidiaDetail / > }
        /> <
        Route path = "/midia/criar"
        element = { < NewMidia / > }
        /> <
        Route path = "/agenda"
        element = { < Agenda / > }
        /> <
        Route path = "/exercicios"
        element = { < Exercicios / > }
        /> <
        Route path = "/historico"
        element = { < Historico / > }
        /> <
        Route path = "/pacientes"
        element = { < Pacientes / > }
        /> <
        Route path = "/cadastro"
        element = { < Cadastro / > }
        /> <
        Route path = "/plano"
        element = { < Plano / > }
        /> <
        Route path = "/fisioterapeuta"
        element = { < CadastroFisioterapeuta / > }
        /> <
        Route path = "/acompanhamento"
        element = { < AcompanhamentoVirtual / > }
        /> <
        Route path = "/gerenciamento"
        element = { < AcompanhamentoVirtual / > }
        /> < /
        Routes >
    );
}

export default PagesRouter;