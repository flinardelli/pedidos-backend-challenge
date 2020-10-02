package com.ma.pedidos.repository;


import com.ma.pedidos.model.entity.PedidoDetalleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoDetalleRepository extends CrudRepository<PedidoDetalleEntity, String> {

    List<PedidoDetalleEntity> findAllByIdPedidoCabecera(String idPedidoCabecera);
}
