package com.blockshe.nomeplataforma.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tbuser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data // Gera automaticamente getters, setters, toString(), equals(), hashCode(), e outros métodos utilitários.
@NoArgsConstructor //Gera um construtor sem argumentos.
@AllArgsConstructor // Gera um construtor com um argumento para cada campo na classe.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private Long idUser;

    @NotNull(message = "O email é obrigatorio.")
    @Email
    private String email;

    @Size(min = 4, max = 50)
    private String senha;

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @NotNull
    private Date dataNascimento;

    @NotNull
    private Perfil perfil;

}
