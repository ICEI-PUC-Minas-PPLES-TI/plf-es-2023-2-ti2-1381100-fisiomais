import React from 'react';
import { Form, Input, DatePicker, Button, Select, Checkbox, Table, message } from 'antd';
import { BoldOutlined, ItalicOutlined, UnderlineOutlined, OrderedListOutlined } from '@ant-design/icons';

const Gerenciamento = () => {
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log('Plano de tratamento criado:', values);
    message.success('Plano de tratamento salvo com sucesso!');
  };

  const onCancel = () => {
    form.resetFields();
  };

  return (
    <div>
      <h1>Plano de Tratamento</h1>

      <Form
        form={form}
        name="plano"
        onFinish={onFinish}
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 18 }}
      >
        <Form.Item
          label="Título do Texto"
          name="tituloTexto"
          rules={[{ required: true, message: 'Por favor, insira o título!', max: 100 }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="Conteúdo do Texto"
          name="conteudoTexto"
          rules={[{ required: true, message: 'Por favor, insira o conteúdo!' }]}
        >
          <Input.TextArea rows={4} />
        </Form.Item>

        <Form.Item label="Formatação">
          <Button icon={<BoldOutlined />} />
          <Button icon={<ItalicOutlined />} />
          <Button icon={<UnderlineOutlined />} />
          <Button icon={<OrderedListOutlined />} />
        </Form.Item>

        <Form.Item label="Lista de Mídias">
          <Table
            columns={[
              { title: 'Mídia', dataIndex: 'midia', key: 'midia' },
              { title: 'Seleção', dataIndex: 'selecao', key: 'selecao', render: () => <Checkbox /> },
            ]}
            dataSource={[]}
          />
        </Form.Item>

        <Form.Item label="Ordem das Atividades"></Form.Item>
        <Form.Item
          label="Data de Início"
          name="dataInicio"
          rules={[{ required: true, message: 'Por favor, selecione a data de início!' }]}
        >
          <DatePicker />
        </Form.Item>
        <Form.Item label="Frequência" name="frequencia">
          <Select>
            <Select.Option value="Diário">Diário</Select.Option>
            <Select.Option value="Semanal">Semanal</Select.Option>
            <Select.Option value="Quinzenal">Quinzenal</Select.Option>
            <Select.Option value="Mensal">Mensal</Select.Option>
          </Select>
        </Form.Item>

        <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
          <Button type="primary" htmlType="submit" style={{ backgroundColor: '#0BD980', borderColor: '#0BD980' }}>
            Salvar Cronograma
          </Button>
          <Button style={{ margin: '0 8px' }} onClick={onCancel}>
            Cancelar
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Gerenciamento;