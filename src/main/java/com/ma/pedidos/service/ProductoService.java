package com.ma.pedidos.service;

import com.ma.pedidos.exceptions.ProductoNotFoundException;
import com.ma.pedidos.model.dto.ProductoDTO;
import com.ma.pedidos.model.entity.ProductoEntity;
import com.ma.pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoDTO getProductoById(String idProducto) {
        ProductoEntity productoEntity = productoRepository.findById(idProducto).orElseThrow(ProductoNotFoundException::new);

        return ProductoDTO.builder()
                .idProducto(productoEntity.getIdProducto())
                .descripcionCorta(productoEntity.getDescripcionCorta())
                .descripcionLarga(productoEntity.getDescripcionLarga())
                .nombre(productoEntity.getNombre())
                .precioUnitario(productoEntity.getPrecioUnitario())
                .build();

    }

    public ProductoDTO createProducto(ProductoDTO producto) {
        ProductoEntity productoEntity = ProductoEntity.builder()
                .descripcionCorta(producto.getDescripcionCorta())
                .descripcionLarga(producto.getDescripcionLarga())
                .nombre(producto.getNombre())
                .precioUnitario(producto.getPrecioUnitario())
                .build();

        ProductoEntity productoEntityResponse = productoRepository.save(productoEntity);

        return ProductoDTO.builder()
                .idProducto(productoEntityResponse.getIdProducto())
                .descripcionCorta(productoEntityResponse.getDescripcionCorta())
                .descripcionLarga(productoEntityResponse.getDescripcionLarga())
                .nombre(productoEntityResponse.getNombre())
                .precioUnitario(productoEntityResponse.getPrecioUnitario())
                .build();

    }

    public ProductoDTO updateProducto(String idProducto, ProductoDTO producto) {
        ProductoEntity productoEntity = productoRepository.findById(idProducto).orElseThrow(ProductoNotFoundException::new);
        AtomicReference<ProductoDTO> productoEntityAtomic = new AtomicReference<>();

        Optional.of(productoEntity).ifPresent(prod -> {
            ProductoEntity productoResponse = productoRepository.save(prod);

            productoEntityAtomic.set(ProductoDTO.builder().nombre(productoResponse.getNombre())
                    .descripcionLarga(productoResponse.getDescripcionLarga())
                    .descripcionCorta(productoResponse.getDescripcionCorta())
                    .precioUnitario(productoResponse.getPrecioUnitario())
                    .idProducto(productoResponse.getIdProducto())
                    .build());
        });

        return productoEntityAtomic.get();

    }

    public Boolean deleteProducto(String idProducto) {
        ProductoEntity productoEntity = productoRepository.findById(idProducto).orElseThrow(ProductoNotFoundException::new);
        AtomicBoolean deleted = new AtomicBoolean(false);
        Optional.of(productoEntity).ifPresent(prod -> {
            productoRepository.delete(prod);
            deleted.set(true);
        });

        return deleted.get();
    }
}
