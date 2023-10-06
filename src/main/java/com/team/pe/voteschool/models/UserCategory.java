package com.team.pe.voteschool.models;

public enum UserCategory {
    CANDIDATO("candidato"),
    ADMIN("admin");

    private String valor;

    UserCategory(String valor) {
        this.valor = valor;
    }

    public  String getValor() {
        return this.valor;
    }
}
