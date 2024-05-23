package com.utp.spring.models.dto;

import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import lombok.Data;

@Data
public class RegistroDTO {
    private Usuario usuario;
    private Persona persona;
    private Cliente cliente;
}
