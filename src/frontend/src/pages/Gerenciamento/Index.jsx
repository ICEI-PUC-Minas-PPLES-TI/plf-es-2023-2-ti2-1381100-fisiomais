import React from 'react';
import {
    Form,
    Input,
    Button,
    Select,
    message
} from 'antd';

const App = () => {
    const [form] = Form.useForm();
    return ( <
        div >

        <
        div className = "header" >
        <
        img src = "logo1.png"
        width = "330"
        height = "124"
        alt = "hp"
        style = {
            { float: 'left' }
        }
        /div>  <
        p > A central dos fisioterapeutas. < /p>  <
        /div >

        <
        div className = "topnav" >
        <
        a href = "#" > Inicio < /a> <
        a href = "#" > Fale Conosco < /a> <
        a href = "#" > Pergunte a um especialista < /a> <
        a href = "#"
        style = {
            { float: 'right' }
        } > Entrar < /a> < /
        div >

        <
        div className = "row" >
        <
        div className = "leftcolumn" >
        <
        div className = "card" >
        <
        h2 > Plano do paciente 1 < /h2> <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > <
        b href = "#" > Atividade 1 < /b></div >
        <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > < b href = "#" > Atividade 2 < /b></div >
        <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > < b href = "#" > Atividade 3 < /b></div >
        <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > < b href = "#" > Atividade 4 < /b></div >
        <
        div style = {
            { display: 'flex', justifyContent: 'space-around' }
        } >
        <
        div className = "altera"
        style = {
            { height: '40px' }
        } > Alterar plano < /div> <
        div className = "confirma"
        style = {
            { height: '40px' }
        } > Confirmar < /div> < /
        div > <
        /div> <
        div className = "card" >
        <
        h2 > Atividades disponíveis < /h2> <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > Atividade 1 < /div> <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > Atividade 2 < /div> <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > Atividade 3 < /div> <
        div className = "fakeimg"
        style = {
            { height: '40px' }
        } > Atividade 4 < /div> < /
        div > <
        /div> <
        div className = "rightcolumn" >
        <
        div className = "card" >
        <
        h2 > Outros pacientes < /h2> <
        div className = "fakeimg" > < p > < b href = "#" > Paciente1 < /b></p > < /div> <
        div className = "fakeimg" > < p > < b href = "#" > Paciente2 < /b></p > < /div> <
        div className = "fakeimg" > < p > < b href = "#" > Paciente3 < /b></p > < /div> <
        p > Editar outro plano < /p> < /
        div > <
        div className = "card" >
        <
        h3 > Popular Post < /h3> <
        div className = "fakeimg" > < p > Image < /p></div >
        <
        div className = "fakeimg" > < p > Image < /p></div >
        <
        div className = "fakeimg" > < p > Image < /p></div >
        <
        /div> <
        div className = "card" >
        <
        h3 > Follow Me < /h3> <
        p > Some text.. < /p> < /
        div > <
        /div> < /
        div >

        <
        div className = "footer" >
        <
        h2 > Footer < /h2> < /
        div > <
        /div>
    );
}

export  default  App;