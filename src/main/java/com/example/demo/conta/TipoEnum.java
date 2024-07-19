package com.example.demo.conta;

public enum TipoEnum{
    CORRENTE("1"), EMPRESARIAL("2");

    private final String id;

    TipoEnum(String id) {
        this.id = id;
    }

    public String getTipo() {
        return this.id;
    }
    public static TipoEnum fromId(String id) {
        for (TipoEnum tipo : values()) {
            if (tipo.getTipo().equals(id)) {
                return tipo;
            }
        }
        throw new RuntimeException("Tipo invalido");
    }
}
