package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICargoDao extends JpaRepository<Cargo,Long> {
}
