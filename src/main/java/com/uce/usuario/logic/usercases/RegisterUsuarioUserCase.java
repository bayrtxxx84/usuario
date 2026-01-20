package com.uce.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.usuario.data.entities.db.UsuarioEntityDb;
import com.uce.usuario.data.repository.UsuarioRepository;
import com.uce.usuario.logic.validators.Result;

@Service
public class RegisterUsuarioUserCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<UsuarioEntityDb> registerUser(
            String nombre,
            String apellido,
            String email,
            String password,
            String passwordConfirmar) {

        Result<UsuarioEntityDb> result;

        // 1. Validaciones básicas de negocio
        if (!password.equals(passwordConfirmar)) {
            return Result.failure(new Exception("Las contraseñas no coinciden"));
        }

        try {
            var usuarioBuilder = UsuarioEntityDb.builder()
                    .nombreUsuario(nombre)
                    .apellidoUsuario(apellido)
                    .emailUsuario(email)
                    .passwordUsuario(password)                    
                    .passwordConfirmar(passwordConfirmar);

            var usuario = usuarioBuilder.build();

            var usuarioSaved = usuarioRepository.save(usuario);
            
            result = Result.success(usuarioSaved);

        } catch (Exception e) {
            result = Result.failure(e);
        }

        return result;
    }

}
