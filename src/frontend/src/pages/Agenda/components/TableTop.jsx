import React from "react";

export const TableTop = () => {
  return (
    <thead className="bg-grey-200">
      <tr className="flex justify-between items-center px-4" style={{ height: '48px' }}>
        <th className="flex-1 text-left px-4">Paciente</th>
        <th className="flex-1 text-left px-4">Fisioterapeuta</th>
        <th className="flex-1 text-left px-4">Data e Hora Consulta</th>
        <th className="flex-1 text-left px-4">Status</th>
        <th className="flex-1 text-left px-4">Observações</th>
        <th className="flex-1 text-left px-4">Link Consulta</th>
      </tr>
    </thead>
  );
};
