package com.uce.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.usuario.data.entities.db.UsuarioEntityDb;
import com.uce.usuario.data.repository.UsuarioRepository;
import com.uce.usuario.logic.validators.Result;

@Service
public class EditUsuarioUserCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<UsuarioEntityDb> updateUser(Integer id,
            String nombre, String apellido, String email) {
        try {
            var usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isEmpty()) {
                return Result.failure(new Exception("Usuario no encontrado con ID: " + id));
            }

            UsuarioEntityDb existingUser = usuarioOptional.get();

            var updatedUser = UsuarioEntityDb.builder()
                    .id(existingUser.getId())
                    .nombreUsuario(nombre)
                    .apellidoUsuario(apellido)
                    .emailUsuario(email)
                    .passwordUsuario(existingUser.getPasswordUsuario())
                    .build();

            var saved = usuarioRepository.save(updatedUser);
            return Result.success(saved);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
