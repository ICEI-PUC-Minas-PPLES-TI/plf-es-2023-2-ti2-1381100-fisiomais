import React from 'react';
import axios from 'axios';
import moment from 'moment';
import { Form, Input, DatePicker, Button, Select, Checkbox, Table, message } from 'antd';
import { BoldOutlined, ItalicOutlined, UnderlineOutlined, OrderedListOutlined } from '@ant-design/icons';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';

const CriarPlano = () => {
  const [form] = Form.useForm();
  const [midias, setMidias] = React.useState([]);
  const [midiasSelecionadas, setMidiasSelecionadas] = React.useState([]);
  const [formatacao, setFormatacao] = React.useState();
  const currentUser = useSelector(state => state.currentUser.value);
  const navigate = useNavigate();
  
  const { id } = useParams();

  const loadPlano = () => {
    if(!id) return;

    const isJsonApi = process.env.API_TYPE === 'json';

    const apiRoute = isJsonApi ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/planos/${id}` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/planos/${id}`;
    
    axios.get(apiRoute).then(response => {
      const plano = response.data;

      form.setFieldsValue({
        tituloTexto: plano.tituloTexto,
        conteudoTexto: plano.conteudoTexto,
        dataInicio: moment(plano.dataInicio),
        frequencia: plano.frequencia,
      });

      setMidiasSelecionadas(plano.midias);
      setFormatacao(plano.formatacao);
    }).catch(error => {
      message.error('Erro ao carregar plano!');
    });
  };
  
  const loadMidias = () => {
    const isJsonApi = process.env.API_TYPE === 'json';

    const apiRoute = isJsonApi ?
            `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/midia` :
            `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/midia`;
    
    axios.get(apiRoute, { params: { fisioterapeuta_id: currentUser.userId } }).then(response => {
      const midias = response.data.map(midia => {
        return {
          key: midia.id,
          midia: midia.titulo,
        }
      });
      
      setMidias(midias);
    }).catch(error => {
      message.error('Erro ao carregar mídias!');
    });
  };

  const onFinish = (values) => {
    const planoMock = {
      tituloTexto: values.tituloTexto,
      conteudoTexto: values.conteudoTexto,
      dataInicio: values.dataInicio,
      frequencia: values.frequencia,
      midias: midiasSelecionadas,
      formatacao: formatacao,
      createTime: new Date().toISOString(),
    }

    const plano = {
      tituloTexto: values.tituloTexto,
      conteudoTexto: values.conteudoTexto,
      dataInicio: values.dataInicio,
      frequencia: values.frequencia,
      midias: midiasSelecionadas,
      formatacao: formatacao,
    }

    const isJsonApi = process.env.API_TYPE === 'json';

    const body = isJsonApi ? planoMock : plano;

    if(id) {
      const apiRoute = isJsonApi ?
              `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/planos/${id}` :
              `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/planos/${id}`;

      axios.put(apiRoute, body).then(response => {
        form.resetFields();
        message.success('Plano de tratamento atualizado com sucesso!');
      }).catch(() => {
        message.error('Erro ao atualizar plano de tratamento!');
      }).finally(() => {
        navigate('/planos');
      });
    } else {
      const apiRoute = isJsonApi ?
              `${import.meta.env.VITE_API_BASE_ROUTE_JSON}/planos` :
              `${import.meta.env.VITE_API_BASE_ROUTE_SPRING}/planos`;
  
      axios.post(apiRoute, body).then(response => {
        form.resetFields();
        message.success('Plano de tratamento salvo com sucesso!');
      }).catch(() => {
        message.error('Erro ao criar plano de tratamento!');
      }).finally(() => {
        navigate('/planos');
      });
    };
  }

  const onCancel = () => {
    form.resetFields();
    navigate('/planos')
  };

  useEffect(() => {
    loadMidias();

    if(id) {
      loadPlano();
    }
  }, [id]);

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

        <Form.Item label="Formatação" name="formatacao">
          <Button icon={<BoldOutlined />} onClick={() => setFormatacao('bold')} style={{ borderColor: formatacao === 'bold' ? '#0BD980' : '' }} />
          <Button icon={<ItalicOutlined />} onClick={() => setFormatacao('italic')} style={{ borderColor: formatacao === 'italic' ? '#0BD980' : '' }} />
          <Button icon={<UnderlineOutlined />} onClick={() => setFormatacao('underline')} style={{ borderColor: formatacao === 'underline' ? '#0BD980' : '' }} />
          <Button icon={<OrderedListOutlined />} onClick={() => setFormatacao('orderedList')} style={{ borderColor: formatacao === 'orderedList' ? '#0BD980' : '' }} />
        </Form.Item>

        <Form.Item label="Lista de Mídias">
          <Table
            columns={[
              { title: 'Mídia', dataIndex: 'midia', key: 'midia' },
              { 
                title: 'Seleção', 
                dataIndex: 'selecao', 
                key: 'selecao', 
                render: (_, record, index) => (
                  <Checkbox 
                    checked={midiasSelecionadas.includes(record.key)}
                    onChange={(event) => { 
                      const checked = event.target.checked;
                      const midias = [...midiasSelecionadas, record.key].filter((value) => checked ? value : value !== record.key);
                      setMidiasSelecionadas(midias);
                    }}
                  />
                )
              },
            ]}
            dataSource={midias}
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

export default CriarPlano;