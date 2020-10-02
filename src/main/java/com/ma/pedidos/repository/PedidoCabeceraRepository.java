package com.ma.pedidos.repository;

import com.ma.pedidos.model.entity.PedidoCabeceraEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PedidoCabeceraRepository extends CrudRepository<PedidoCabeceraEntity, String> {

    List<PedidoCabeceraEntity> findByFechaAltaOrderByFechaAltaDesc(Date fecha);
}
