import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';

const AcompanhamentoVirtual = () => {
  const currentUser = useSelector((state) => state.currentUser.value);
  const { token } = currentUser;

  const [form, setForm] = useState({
    dataSessao: '',
    plataforma: '',
    recursos: '',
    feedback: '',
    avaliacao: '',
  });

  const handleInputChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // console.log(form)
    try {
      const url = 'http://localhost:8081/api/acompanhamento';
      const response = await axios.post(url, form, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      // console.log('Formulário de acompanhamento enviado:', response.data);
      setForm({
        dataSessao: '',
        plataforma: '',
        recursos: '',
        feedback: '',
        avaliacao: '',
      });
    } catch (error) {
      // console.error('Erro ao enviar o formulário:', error);
    }
  };

  const handleCancel = () => {
    setForm({
      dataSessao: '',
      plataforma: '',
      recursos: '',
      feedback: '',
      avaliacao: '',
    });
  };

  return (
    <div className="p-5">
      <div className="mb-5">
        <h1 className="text-xl font-bold">Acompanhamento Virtual</h1>
      </div>

      <form onSubmit={handleSubmit} className="space-y-6">
        <div className="flex flex-col">
          <label htmlFor="dataSessao" className="text-sm font-medium text-gray-700">Data da Sessão</label>
          <input
            type="date"
            name="dataSessao"
            value={form.dataSessao}
            onChange={handleInputChange}
            className="mt-1 p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
            placeholder="DD/MM/AAAA"
          />
        </div>

        <div className="flex flex-col">
          <label htmlFor="plataforma" className="text-sm font-medium text-gray-700">Plataforma de Videoconferência</label>
          <select
            name="plataforma"
            value={form.plataforma}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 bg-white rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="Zoom">Zoom</option>
            <option value="Skype">Skype</option>
            <option value="Teams">Teams</option>
            <option value="Google Meet">Google Meet</option>
          </select>
        </div>

        <div className="flex flex-col">
          <label htmlFor="recursos" className="text-sm font-medium text-gray-700">Recursos Educacionais Fornecidos</label>
          <textarea
            name="recursos"
            value={form.recursos}
            onChange={handleInputChange}
            rows={4}
            className="mt-1 p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
            placeholder="Ex: Vídeos, PDFs, links, etc."
          />
        </div>

        <div className="flex flex-col">
          <label htmlFor="feedback" className="text-sm font-medium text-gray-700">Feedback do Paciente</label>
          <textarea
            name="feedback"
            value={form.feedback}
            onChange={handleInputChange}
            rows={4}
            className="mt-1 p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
            placeholder="Opiniões, sugestões, dúvidas..."
          />
        </div>

        <div className="flex flex-col">
          <label htmlFor="avaliacao" className="text-sm font-medium text-gray-700">Avaliação do Atendimento</label>
          <select
            name="avaliacao"
            value={form.avaliacao}
            onChange={handleInputChange}
            className="mt-1 block w-full p-2 border border-gray-300 bg-white rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="Excelente">Excelente</option>
            <option value="Bom">Bom</option>
            <option value="Regular">Regular</option>
            <option value="Ruim">Ruim</option>
          </select>
        </div>

        <div className="flex justify-end space-x-4">
          <button type="button" onClick={handleCancel} className="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
            Cancelar
          </button>
          <button type="submit" className="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
            Registrar
          </button>
        </div>
      </form>
    </div>
  );
};

export default AcompanhamentoVirtual;
