import React, { useState } from "react";
import PropTypes from 'prop-types';
import { Status } from "../../components/Status/Status";
import { IconMore } from "../../icons/IconMore";
import { IconDelete } from "../../icons/IconDelete";
import { IconEdit } from "../../icons/IconEdit";

export const ContentLine = ({ paciente, fisioterapeuta, dataHora, status, observacoes, linkConsulta }) => {
    const [showIcons, setShowIcons] = useState(false);

    return (
        <tr onMouseEnter={() => setShowIcons(true)} onMouseLeave={() => setShowIcons(false)}>
            <td className="px-4 text-left">{paciente.nome}</td>
            <td className="px-4 text-left">{fisioterapeuta.nome}</td>
            <td className="px-4 text-left">{dataHora}</td>
            <td className="px-4 text-left">
                <Status status={status} />
            </td>
            <td className="px-4 text-left">{observacoes}</td>
            <td className="px-4 text-left">
                <a href={linkConsulta} target="_blank" rel="noopener noreferrer">Link</a>
            </td>
            <td>
                <div className="flex items-center">
                    {showIcons && (
                        <>
                            <IconEdit />
                            <IconDelete />
                        </>
                    )}
                    <IconMore className="ml-2" />
                </div>
            </td>
        </tr>
    );
};

ContentLine.propTypes = {
    paciente: PropTypes.shape({
        nome: PropTypes.string.isRequired,
    }).isRequired,
    fisioterapeuta: PropTypes.shape({
        nome: PropTypes.string.isRequired,
    }).isRequired,
    dataHora: PropTypes.string.isRequired,
    status: PropTypes.string.isRequired,
    observacoes: PropTypes.string,
    linkConsulta: PropTypes.string
};

export default ContentLine;
