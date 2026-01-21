package com.uce.usuario.logic.usercases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.usuario.controllers.data.converters.EntityConverters;
import com.uce.usuario.controllers.data.entities.UsuarioEntityUI;
import com.uce.usuario.data.entities.db.UsuarioEntityDb;
import com.uce.usuario.data.repository.UsuarioRepository;
import com.uce.usuario.logic.validators.Result;

@Service
public class GetAllUsuarioUserCase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<List<UsuarioEntityUI>> execute() {
        List<UsuarioEntityUI> usUI = new ArrayList<UsuarioEntityUI>();
        try {
            List<UsuarioEntityDb> usuarios = usuarioRepository.findAll();
            usuarios.forEach(it -> usUI.add(EntityConverters.usuarioEntityDbToUI(it)));
            return Result.success(usUI);
        } catch (Exception e) {
            return Result.failure(e);
        }
    }

}
