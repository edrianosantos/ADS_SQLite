package com.example.thiago.aplicacaobanconoite.model;

import java.io.Serializable;

public class Contato implements Serializable {

    private long _id;
    private String nome;
    private String cpf;
    private String email;

    public Contato() {
    }

    public Contato(long _id, String nome, String cpf, String email) {
        this._id = _id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Contato(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
