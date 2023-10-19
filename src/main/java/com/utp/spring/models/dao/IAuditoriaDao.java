package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditoriaDao extends JpaRepository<Auditoria,Long> {
}
