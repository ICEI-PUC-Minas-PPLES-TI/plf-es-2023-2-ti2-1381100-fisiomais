package com.fisiomais.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Mes {
    JANEIRO(1, "Janeiro"), FEVEREIRO(2, "Fevereiro"), MARCO(3, "Março"), ABRI(4, "Abril"), MAIO(5, "Maio"),
    JUNHO(6, "Junho"), JUHO(7, "Julho"), AGOSTO(8, "Agosto"), SETEMBRO(9, "Setembro"), OUTUBRO(10, "Outubro"),
    NOVEMBRO(11, "Novembro"), DEZEMBRO(12, "Dezembro");

    private Integer id;
    private String nome;

    public static Mes toEnum(Integer integer) {
        if (integer == null) {
            return null;
        }

        for (Mes mes : Mes.values()) {
            if (integer.equals(mes.getId())) {
                return mes;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + integer);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
