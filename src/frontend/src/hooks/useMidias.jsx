import { useState, useEffect } from 'react'
import axios from 'axios'

const API_ROUTE_BASE = process.env.API_BASE_ROUTE

const fetchMidias = async () => {
    const response = await axios.get(`${API_ROUTE_BASE}/midia`)
        .then(response => response.data)
        // .catch(error => console.log(error))

    return response
}

const requestDeleteMidias = async (ids) => {
    const response = await axios.delete(`${API_ROUTE_BASE}/midia`, { data: { ids } })
        .then(response => response.data)
        // .catch(error => console.log(error))
    return response
}

const reorderByCreationDate = (data) => {
    const orderedData = data.sort((a, b) => {
        if (a.createTime > b.createTime) {
            return -1;
        }
        if (a.createTime < b.createTime) {
            return 1;
        }
        return 0;
    });

    return orderedData
}

const formatShortMidias = (midias) => {
    const data = midias.map(midia => {
        const { id, titulo, descricao, type, createTime } = midia;
        const formatedDate = new Date(createTime).toLocaleString('pt-BR');
        return {
            key: id,
            id,
            titulo,
            descricao: descricao.substring(0, 50) + '...',
            type,
            createTime: formatedDate,
        }
    })
    return data
}


export default function useMidias() {
    const [midias, setMidias] = useState([])
    const [shortMidias, setShortMidias] = useState([])

    useEffect(() => {
        fetchMidias()
            .then(data => {
                setMidias(reorderByCreationDate(data))
                setShortMidias(formatShortMidias(midias))
            })
    })

    const deleteMidias = async (ids) => {
        // request to delete midias
        // if success, update midias and shortMidias

        const deletionOK = await requestDeleteMidias(ids)

        if (deletionOK) {
            const newMidias = midias.filter(midia => !ids.includes(midia.id))
            setMidias(newMidias)
            setShortMidias(formatShortMidias(newMidias))
            return true
        }

        return false
    }

    return {
        midias,
        shortMidias,
        deleteMidias,
    }
}