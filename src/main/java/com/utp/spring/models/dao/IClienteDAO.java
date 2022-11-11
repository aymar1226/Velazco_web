package com.utp.spring.models.dao;


import com.utp.spring.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {

}
