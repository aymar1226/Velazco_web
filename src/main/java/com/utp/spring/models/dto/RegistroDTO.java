package com.utp.spring.models.dto;

import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import lombok.Data;

@Data
public class RegistroDTO {
    private String correo;
    private String password;
    private String documento;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String telefono;
    private String direccion;
}
