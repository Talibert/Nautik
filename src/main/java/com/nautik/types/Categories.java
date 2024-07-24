package com.nautik.types;

public enum Categories {

    HELICES("Descrição placeholder de Hélices"),
    MOTORES("Descrição placeholder de Motores"),
    ACESSORIOS("Descrição placeholder de acessorios");

    private String descricao;

    Categories(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
