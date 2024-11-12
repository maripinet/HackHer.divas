package com.blockshe.nomeplataforma.repository;

import com.blockshe.nomeplataforma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository //Indicando que a interface é um repositório
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

    public List<User> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}


