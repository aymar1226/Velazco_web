package com.utp.spring.services;

import com.utp.spring.models.dao.IDetalleOrdenDAO;
import com.utp.spring.models.entity.DetalleOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
	
	@Autowired
	private IDetalleOrdenDAO detalleOrdenDAO;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenDAO.save(detalleOrden);
	}

}
