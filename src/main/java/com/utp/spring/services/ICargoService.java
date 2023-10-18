package com.utp.spring.services;

import com.utp.spring.models.entity.Cargo;

import java.util.List;

public interface ICargoService {
    public List<Cargo> findAll();
    public Cargo save(Cargo cargo);
    public Cargo findbyId(Long id);
    public void delete(Long id);
}
