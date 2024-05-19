package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Privilegio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolDao extends JpaRepository <Privilegio,Long> {
}
