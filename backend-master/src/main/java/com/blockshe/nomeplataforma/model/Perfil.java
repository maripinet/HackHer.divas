package com.blockshe.nomeplataforma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.criteria.JpaDerivedFrom;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestResolver;

@Table(name="tbperfil")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
    private Long idPerfil;

    @NotNull
    @Size(min = 10, max = 1000)
    private String descricao;

    @OneToMany
    @JsonIgnoreProperties({"perfil"})
    private Tecnologia tecnologia;

    private Investidor investidor;

    private Empreendedor empreendedor;

}
