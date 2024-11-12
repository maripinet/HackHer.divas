package com.blockshe.nomeplataforma.repository;

import com.blockshe.nomeplataforma.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

    public Optional<Tecnologia> findByEtiqueta(String etiqueta);

    public List<Tecnologia> findAllByEtiquetaContainingIgnoreCase(@Param("etiqueta") String etiqueta);

}
