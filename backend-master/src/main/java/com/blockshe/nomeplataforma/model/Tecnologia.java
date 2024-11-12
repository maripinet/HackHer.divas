package com.blockshe.nomeplataforma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbtech")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
    private Long idEtiqueta;

    @NotNull
    private String etiqueta;

    @OneToMany(mappedBy = "tecnologia", cascade = CascadeType.MERGE) // Tecnologia é um, e perfil muitos // Pesquisamos uma tecnoogia e pode retornar vários perfis.
    private List<Perfil> perfil;
}
