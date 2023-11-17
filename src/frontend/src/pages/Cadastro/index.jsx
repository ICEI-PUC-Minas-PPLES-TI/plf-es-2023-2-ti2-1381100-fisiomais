import React from 'react';
import {
  Form,
  Input,
  DatePicker,
  Select,
  Button,
  message
} from 'antd';
import moment from 'moment';

const Cadastro = () => {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log('Formulário enviado com sucesso:', values);
    message.success('Paciente cadastrado com sucesso!');
  };

  const onCancel = () => {
    form.resetFields();
  };

  return (
    <div>
    <div style={{ marginBottom: '20px', marginLeft: '5%' }}>
      <h1>Cadastro Paciente</h1>
    </div>

    <div style={{ display: 'flex', justifyContent: 'flex-start', height: 'calc(100vh - 60px)', marginLeft: '5%' }}>


      <Form
        form={form}
        name="cadastro"
        onFinish={onFinish}
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 18 }}
        initialValues={{
          convenio: 'Não possui',
          dataNascimento: moment(),
        }}
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
            { required: true, message: 'Por favor, insira o nome!', pattern: /^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+$/ }
          ]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="Data de Nascimento"
          name="dataNascimento"
          rules={[{ required: true, message: 'Por favor, selecione a data de nascimento!' }]}
        >
          <DatePicker format="DD/MM/YYYY" />
        </Form.Item>

        <Form.Item
          label="Endereço"
          name="endereco"
          rules={[{ required: true, message: 'Por favor, insira o endereço!' }]}
        >
          <Input.TextArea rows={4} />
        </Form.Item>

        <Form.Item
          label="Convênio"
          name="convenio"
        >
          <Select>
            <Select.Option value="Não possui">Não possui</Select.Option>
            <Select.Option value="Unimed">Unimed</Select.Option>
            <Select.Option value="Porto Seguro">Porto Seguro</Select.Option>
            <Select.Option value="Bradesco Saude">Bradesco Saúde</Select.Option>
            <Select.Option value="Saude Caixa">Saúde Caixa</Select.Option>
          </Select>
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

export default Cadastro;
