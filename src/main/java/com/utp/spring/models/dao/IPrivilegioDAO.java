package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Privilegio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegioDAO extends CrudRepository<Privilegio,Long> {
}
