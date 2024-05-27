package com.utp.spring.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaUsuarioDTO {

    private Long personaId;
    private String personaNombre;
    private Long usuarioId;
    private String usuarioPrivilegio;
    private String usuarioCorreo;
    private String usuarioContrasenia;
    private char estadoUsuario;
}
