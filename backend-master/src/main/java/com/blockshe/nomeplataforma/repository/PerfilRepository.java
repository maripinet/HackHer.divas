package com.blockshe.nomeplataforma.repository;

import com.blockshe.nomeplataforma.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    public List<Perfil> findAllByTecnologiaContainingIgnoreCase(@Param("tecnologia") String tecnologia);

    public Optional<Perfil> findByTecnologia(String tecnologia);
}
