package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Privilegio;
import com.utp.spring.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolDao extends JpaRepository <Privilegio,Long> {

}
