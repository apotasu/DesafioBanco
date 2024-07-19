package com.example.demo.pessoa;

import com.example.demo.conta.TipoEnum;

public enum PessoaEnum {
    PF("1"), PJ("2");
    private final String id;

    PessoaEnum(String id) {
        this.id = id;
    }

    public String getTipoPessoa() {
        return this.id;
    }
    public static PessoaEnum fromId(String id) {
        for (PessoaEnum tipo : values()) {
            if (tipo.getTipoPessoa().equals(id)) {
                return tipo;
            }
        }
        throw new RuntimeException("Tipo invalido");
    }
}
