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
import Tratamento from 'pages/Tratamento'
import CadastroFisioterapeuta from 'pages/Fisioterapeuta';
import AcompanhamentoVirtual from 'pages/Acompanhar';
import Consulta from 'pages/Consulta';
import DadosConsulta from 'pages/Consulta/DadosConsulta';
import Login from 'pages/Login';
import Indicadores from 'pages/Indicadores';
import FisioterapeutaSignup from 'pages/SignUp/FisioterapeutaSignUp';
import PacienteSignup from 'pages/SignUp/PacienteSignUp';

const PagesRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<Agenda />} />
            <Route path="/midias" element={<Midias />} />
            <Route path="/midia/:id" element={<MidiaDetail />} />
            <Route path="/midia/criar" element={<NewMidia />} />
            <Route path="/agenda" element={<Agenda />} />
            <Route path="/exercicios" element={<Exercicios />} />
            <Route path="/historico" element={<Historico />} />
            <Route path="/pacientes" element={<Pacientes />} />
            <Route path="/cadastro" element={<Cadastro />} />
            <Route path="/tratamento" element={<Tratamento />} />
            <Route path="/fisioterapeuta" element={<CadastroFisioterapeuta />} />
            <Route path="/acompanhamento" element={<AcompanhamentoVirtual />} />
            <Route path="/nova-consulta" element={<Consulta />} />
            <Route path='/nova-consulta/dados' element={<DadosConsulta />} />
            <Route path="/signup/fisioterapeuta" element={<FisioterapeutaSignup />} />
            <Route path="/signup/paciente" element={<PacienteSignup />} />
            <Route path='/login' element={<Login />} />
            <Route path='/indicadores' element={<Indicadores />} />
        </Routes>
    );
}

export default PagesRouter;