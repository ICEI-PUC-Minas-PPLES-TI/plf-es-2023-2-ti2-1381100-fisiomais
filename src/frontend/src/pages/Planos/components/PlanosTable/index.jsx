import { Table, Spin } from 'antd';

const loadingRowsMock = () => {
    let rows = []
    for (let i = 0; i < 6; i++) {
        rows.push({
            key: `loading-${i}`,
            titulo: '.'.repeat(10),
            conteudoTexto: '.'.repeat(120),
            dataInicio: '.'.repeat(20),
            frequencia: '.'.repeat(10),
            createTime: '.'.repeat(20)
        });
    }
    return rows;
}

const PlanosTable = ({ deletePlanos, handleRowSelection, getPageSizeBasedOnScreenSize, shortPlanos, columns, loadingPlanos: loading }) => {
    return (
        <Spin spinning={loading} >
            <Table style={{
                maxHeight: 'calc(100vh - 200px)',
                overflowY: 'auto',
            }}
                rowSelection={deletePlanos ? {
                    type: 'checkbox',
                    onSelect: (record, selected) => {
                        handleRowSelection(record.id, selected);
                    },
                    onSelectAll: (selected, selectedRows) => {
                        for (const row of selectedRows) {
                            handleRowSelection(row.id, selected);
                        }
                    }
                } : null}
                columnTitle="Deletar"
                columns={columns}
                dataSource={loading ? loadingRowsMock() : shortPlanos}
                pagination={getPageSizeBasedOnScreenSize()}
            />
        </Spin>
    );
}

export default PlanosTable;