package com.uce.usuario.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.usuario.data.repository.UsuarioRepository;
import com.uce.usuario.logic.validators.Result;

@Service
public class DeleteUsuarioUserCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

     public Result<Boolean> deleteUser(Integer id) {
        try {
            if (!usuarioRepository.existsById(id)) {
                return Result.failure(new Exception("Usuario no existe"));
            }
            usuarioRepository.deleteById(id);
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }
}
