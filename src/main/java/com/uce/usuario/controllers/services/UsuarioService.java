package com.uce.usuario.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uce.usuario.controllers.data.entities.UsuarioDTO;
import com.uce.usuario.logic.usercases.DeleteUsuarioUserCase;
import com.uce.usuario.logic.usercases.EditUsuarioUserCase;
import com.uce.usuario.logic.usercases.GetAllUsuarioUserCase;
import com.uce.usuario.logic.usercases.GetUsuarioByIdUserCase;
import com.uce.usuario.logic.usercases.RegisterUsuarioUserCase;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    @Autowired
    private RegisterUsuarioUserCase registerUsuarioUserCase;

    @Autowired
    private GetAllUsuarioUserCase listUsuarioUserCase;

    @Autowired
    private GetUsuarioByIdUserCase getUsuarioByIdUserCase;

    @Autowired
    private EditUsuarioUserCase editUsuarioUserCase;

    @Autowired
    private DeleteUsuarioUserCase deleteusuarioUseCase;

    @PostMapping(path = "/registro", consumes = "application/json")
    public ResponseEntity<?> registerUsuario(
            @RequestBody UsuarioDTO usuario) {
        var result = registerUsuarioUserCase.execute(
                usuario.getName(),
                usuario.getLastname(),
                usuario.getEmail(),
                usuario.getPassword());
        return result.fold(
                val -> ResponseEntity.ok(val),
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @PostMapping("/editar")
    public ResponseEntity<?> edit(@RequestParam Integer id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email) {
        return editUsuarioUserCase.updateUser(id, nombre, apellido, email).fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listAll() {
        return listUsuarioUserCase.execute().fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.internalServerError().body(ex.getMessage()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> findByIdParam(@RequestParam Integer id) {
        return getUsuarioByIdUserCase.execute(id).fold(
                val -> ResponseEntity.ok(val),
                ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @PostMapping("/eliminar")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        return deleteusuarioUseCase.deleteUser(id).fold(
                val -> ResponseEntity.ok("Usuario eliminado correctamente"),
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
