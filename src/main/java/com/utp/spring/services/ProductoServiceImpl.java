package com.utp.spring.services;

import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final Path productImagesDirectory = Paths.get("mediafiles//");


    @Autowired
    private IProductoDAO IProductoDAO;

    @Override
    public List<Producto> findAll() {
        return IProductoDAO.findAll();
    }

    @Override
    public Producto save(Producto producto) {

        IProductoDAO.save(producto);
        return producto;
    }



    @Override
    public void delete(Long id) {
        Optional<Producto> producto=IProductoDAO.findById(id);
        if(producto.isPresent()){
            Producto productoAEliminar = producto.get();
            productoAEliminar.setEstado('0');
            IProductoDAO.save(productoAEliminar);
        }
    }

    @Override
    public Optional<Producto> findbyId(Long id) {
        return IProductoDAO.findById(id);
    }



    @Override
    public void update(Producto producto) {
        IProductoDAO.save(producto);
    }

    public List<Producto> listAll(Long categoriaID){
        if(categoriaID !=null){
            return IProductoDAO.findAll(categoriaID);
        }
        return IProductoDAO.findAll();
    }

    //Cargar imagen
    @Override
    public Resource loadProductImage(String imageName) throws MalformedURLException {
        Path imagePath = productImagesDirectory.resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("No se pudo cargar la imagen del producto: " + imageName);
        }
    }

    @Override
    public String findProductImageById(Long productoId) {
        String imageName=IProductoDAO.findProductImageById(productoId);
        return imageName;
    }
}
