package com.blockshe.nomeplataforma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Gera automaticamente getters, setters, toString(), equals(), hashCode(), e outros métodos utilitários.
@NoArgsConstructor //Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com um argumento para cada campo na classe.
public class UserLogin {

    private String email;

    private String senha;

    private String token;

    private Perfil peril;

}
