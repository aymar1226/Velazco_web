package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Persistence;

@Repository
public interface IPersonaDao extends JpaRepository<Persona,Long> {

    @Query("SELECT COUNT(c) FROM Persona c WHERE c.dni = :dni")
    Long countByDNI(@Param("dni") String dni);
}
