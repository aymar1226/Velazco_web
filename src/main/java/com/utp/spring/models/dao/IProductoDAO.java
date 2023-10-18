package com.utp.spring.models.dao;

import com.utp.spring.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductoDAO extends JpaRepository<Producto,Long>{

    /*@Query("SELECT i FROM Producto p INNER JOIN Categoria c on p.idcategoria=c.idcategoria WHERE" + " CONCAT(c.idcategoria)" +" LIKE %?1%")
    public List<Producto> findAll(String palabraClave);*/

    @Query("SELECT p FROM Producto p JOIN p.categoria c WHERE c.id = :categoriaID")
    List<Producto> findAll(@Param("categoriaID") Long categoriaID);

}
