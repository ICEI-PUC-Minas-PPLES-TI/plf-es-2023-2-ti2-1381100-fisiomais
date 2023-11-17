import React from 'react';
import {
  Form,
  Input,
  Button,
  Select,
  message
} from 'antd';

const AcompanhamentoVirtual = () => {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log('Formulário de acompanhamento enviado:', values);
    message.success('Acompanhamento registrado com sucesso!');
  };

  const onCancel = () => {
    form.resetFields();
  };

  return (
    <div>
      <div style={{ marginBottom: '20px', marginLeft: '5%' }}>
        <h1>Acompanhamento Virtual</h1>
      </div>

      <div style={{ display: 'flex', justifyContent: 'flex-start', height: 'calc(100vh - 60px)', marginLeft: '5%' }}>

        <Form
          form={form}
          name="acompanhamentoVirtual"
          onFinish={onFinish}
          labelCol={{ span: 6 }}
          wrapperCol={{ span: 18 }}
          style={{ 
            width: '85%', 
            marginTop: '20px', 
            marginBottom: '20px', 
            paddingTop: '10px', 
            paddingBottom: '10px',
            marginLeft: '0px'
          }}
        >
          <Form.Item
            label="Data da Sessão"
            name="dataSessao"
          >
            <Input placeholder="DD/MM/AAAA" />
          </Form.Item>

          <Form.Item
            label="Plataforma de Videoconferência"
            name="plataforma"
          >
            <Select>
              <Select.Option value="Zoom">Zoom</Select.Option>
              <Select.Option value="Skype">Skype</Select.Option>
              <Select.Option value="Teams">Teams</Select.Option>
              <Select.Option value="Google Meet">Google Meet</Select.Option>
            </Select>
          </Form.Item>

          <Form.Item
            label="Recursos Educacionais Fornecidos"
            name="recursos"
          >
            <Input.TextArea rows={4} placeholder="Ex: Vídeos, PDFs, links, etc." />
          </Form.Item>

          <Form.Item
            label="Feedback do Paciente"
            name="feedback"
          >
            <Input.TextArea rows={4} placeholder="Opiniões, sugestões, dúvidas..." />
          </Form.Item>

          <Form.Item
            label="Avaliação do Atendimento"
            name="avaliacao"
          >
            <Select>
              <Select.Option value="Excelente">Excelente</Select.Option>
              <Select.Option value="Bom">Bom</Select.Option>
              <Select.Option value="Regular">Regular</Select.Option>
              <Select.Option value="Ruim">Ruim</Select.Option>
            </Select>
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
            <Button type="primary" htmlType="submit" style={{ backgroundColor: '#0BD980', borderColor: '#0BD980' }}>
              Registrar
            </Button>
            <Button style={{ margin: '0 8px' }} onClick={onCancel}>
              Cancelar
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default AcompanhamentoVirtual;
