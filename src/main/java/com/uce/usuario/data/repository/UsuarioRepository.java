package com.uce.usuario.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uce.usuario.data.entities.db.UsuarioEntityDb;

public interface UsuarioRepository extends JpaRepository<UsuarioEntityDb, Integer> {

        

}
