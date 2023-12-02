import React, { useState } from 'react';
import '../../index.css';
import axios from 'axios';
import { useSelector } from 'react-redux';

const CadastroFisioterapeuta = () => {
  const currentUser = useSelector(state => state.currentUser.value);
    const {token} = currentUser;
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

  const [form, setForm] = useState({
    nome: '',
    endereco: '',
    email: '',
    password: '',
    telefone: '',
    controleAutomatico: false,
  });

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  };

  const onFinish = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8081/api/fisioterapeuta', {
        nome: form.nome,
        email: form.email,
        password: form.password,
        telefone: form.telefone,
        endereco: form.endereco,
        controleAutomatico: form.controleAutomatico,
      });
      // console.log('Fisioterapeuta cadastrado:', response.data);
      setForm({
        nome: '',
        endereco: '',
        email: '',
        password: '',
        telefone: '',
        controleAutomatico: false,
      });
    } catch (error) {
      // console.error('Erro ao enviar o formulário:', error);
    }
  };

  const onCancel = () => {
    setForm({
      nome: '',
      endereco: '',
      email: '',
      password: '',
      telefone: '',
      controleAutomatico: false,
    });
  };

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white shadow-md rounded-md">
      <h1 className="text-2xl font-semibold text-gray-800 mb-6">Cadastro de Fisioterapeuta</h1>

      <form onSubmit={onFinish} className="space-y-6">
        <div>
          <label htmlFor="nome" className="block text-sm font-medium text-gray-700">Nome</label>
          <input
            type="text"
            name="nome"
            value={form.nome}
            onChange={handleInputChange}
            required
            pattern="^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+$"
            title="Por favor, insira o nome!"
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md text-gray-700 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label htmlFor="Telefone" className="block text-sm font-medium text-gray-700">Telefone de Contato</label>
          <input
            type="text"
            name="telefone"
            value={form.telefone}
            onChange={handleInputChange}
            required
            title="Por favor, insira o número de celular!"
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md text-gray-700 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label htmlFor="endereco" className="block text-sm font-medium text-gray-700">Endereço</label>
          <textarea
            name="endereco"
            value={form.endereco}
            onChange={handleInputChange}
            rows={4}
            required
            title="Por favor, insira o endereço!"
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md text-gray-700 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <div>
          <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email</label>
          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleInputChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md text-gray-700 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="password" className="block text-sm font-medium text-gray-700">Senha</label>
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={handleInputChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md text-gray-700 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        <div>
          <label htmlFor="controleAutomatico" className="block text-sm font-medium text-gray-700">Controle Automático</label>
          <input
            type="checkbox"
            name="controleAutomatico"
            checked={form.controleAutomatico}
            onChange={handleInputChange}
            className="mt-1"
          />
        </div>
        <div className="flex justify-end gap-x-4">
          <button
            type="button"
            onClick={onCancel}
            className="bg-gray-200 text-gray-800 hover:bg-gray-300 px-4 py-2 rounded-md transition duration-300"
          >
            Cancelar
          </button>
          <button
            type="submit"
            className="bg-blue-500 text-white hover:bg-blue-600 px-4 py-2 rounded-md transition duration-300"
          >
            Confirmar
          </button>
        </div>
      </form>
    </div>
  );
};

export default CadastroFisioterapeuta;
