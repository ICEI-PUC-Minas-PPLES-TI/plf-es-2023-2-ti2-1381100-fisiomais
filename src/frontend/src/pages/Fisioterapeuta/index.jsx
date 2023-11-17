import React from 'react';
import {
  Form,
  Input,
  Button,
  message
} from 'antd';

const CadastroFisioterapeuta = () => {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log('Formulário enviado com sucesso:', values);
    message.success('Fisioterapeuta cadastrado com sucesso!');
  };

  const onCancel = () => {
    form.resetFields();
  };

  return (
    <div>
      <div style={{ marginBottom: '20px', marginLeft: '5%' }}>
        <h1>Cadastro Fisioterapeuta</h1>
      </div>

      <div style={{ display: 'flex', justifyContent: 'flex-start', height: 'calc(100vh - 60px)', marginLeft: '5%' }}>

        <Form
          form={form}
          name="cadastroFisioterapeuta"
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
            label="Nome"
            name="nome"
            rules={[
              { required: true, message: 'Por favor, insira o nome!', pattern: /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+$/}
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Celular de Contato"
            name="celular"
            rules={[{ required: true, message: 'Por favor, insira o número de celular!' }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="Endereço"
            name="endereco"
            rules={[{ required: true, message: 'Por favor, insira o endereço!' }]}
          >
            <Input.TextArea rows={4} />
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
            <Button type="primary" htmlType="submit" style={{ backgroundColor: '#0BD980', borderColor: '#0BD980' }}>
              Confirmar
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

export default CadastroFisioterapeuta;
