package com.ma.pedidos.repository;

import com.ma.pedidos.model.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<ProductoEntity, String> {
}
