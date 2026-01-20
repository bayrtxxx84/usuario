package com.uce.usuario.logic.usercases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.usuario.data.entities.db.UsuarioEntityDb;
import com.uce.usuario.data.repository.UsuarioRepository;
import com.uce.usuario.logic.validators.Result;

@Service
public class ListUsuarioUserCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<List<UsuarioEntityDb>> getAllUsers() {
        try {
            List<UsuarioEntityDb> usuarios = usuarioRepository.findAll();
            return Result.success(usuarios);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

    public Result<UsuarioEntityDb> getUserById(Integer id) {
        try {
            // findById devuelve un Optional
            var usuarioOptional = usuarioRepository.findById(id);

            if (usuarioOptional.isPresent()) {
                return Result.success(usuarioOptional.get());
            } else {
                return Result.failure(new Exception("Usuario con ID " + id + " no encontrado"));
            }
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}

