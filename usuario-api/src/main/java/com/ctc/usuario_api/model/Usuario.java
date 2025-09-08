package com.ctc.usuario_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Column(name = "login", length = 8, nullable = false)
    private String login;
    @Column(name = "password", length = 8, nullable = false)
    private String password;

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
