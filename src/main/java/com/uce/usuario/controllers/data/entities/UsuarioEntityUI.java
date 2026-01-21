package com.uce.usuario.controllers.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntityUI {

    private int id;
    private String name;
    private String lastName;
    private String email;

}
