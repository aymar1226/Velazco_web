package com.utp.spring.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaUsuarioDTO {
    private Long personaId;
    private String personaNombre;
    private Long usuarioId;
    private String usuarioPrivilegio;
    private String usuarioCorreo;
    private String usuarioContrasenia;
    private char estadoUsuario;

    public PersonaUsuarioDTO(Long personaId, String personaNombre, Long usuarioId, String usuarioCorreo, String usuarioContraseña, char estadoUsuario) {
        this.personaId = personaId;
        this.personaNombre = personaNombre;
        this.usuarioId = usuarioId;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioContrasenia = usuarioContrasenia;
        this.estadoUsuario=estadoUsuario;
    }
}
