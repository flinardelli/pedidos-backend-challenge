package com.ma.pedidos.service;

import com.ma.pedidos.exceptions.ProductoNotFoundException;
import com.ma.pedidos.model.dto.ProductoDTO;
import com.ma.pedidos.model.entity.ProductoEntity;
import com.ma.pedidos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getProductById(){
        ProductoEntity productoEntity = ProductoEntity.builder().build();
        productoEntity .setIdProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
        when(productoRepository.findById(anyString())).thenReturn(Optional.of(productoEntity));

        ProductoDTO productoDTO = productoService.getProductoById("");

        assertNotNull(productoDTO);
        assertEquals(productoEntity.getIdProducto(), productoDTO.getIdProducto());
    }

    @Test
    void getProductById_NotFoundException(){
        ProductoEntity productoEntity = ProductoEntity.builder().build();
        productoEntity .setIdProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31");
        when(productoRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ProductoNotFoundException.class, ()-> productoService.getProductoById(productoEntity.getIdProducto()));
    }

    @Test
    void createProducto() {
        ProductoEntity productoEntity = ProductoEntity.builder()
                .descripcionCorta("Pizza de jamón y morrones")
                .descripcionLarga("Mozzarella, jamón, morrones y aceitunas verdes")
                .nombre("Jamón y morrones")
                .precioUnitario(550.00)
                .build();

        ProductoDTO productoDTO = ProductoDTO.builder()
                .descripcionCorta(productoEntity.getDescripcionCorta())
                .descripcionLarga(productoEntity.getDescripcionCorta())
                .nombre(productoEntity.getNombre())
                .precioUnitario(productoEntity.getPrecioUnitario())
                .build();

        when(productoRepository.save(any())).thenReturn(productoEntity);

        ProductoDTO response = productoService.createProducto(productoDTO);

        productoDTO.setIdProducto(productoEntity.getIdProducto());

        assertNotNull(response);
        assertEquals(productoDTO.getIdProducto(), response.getIdProducto());

    }

    @Test
    void updateProducto() {
        ProductoEntity productoEntity = ProductoEntity.builder()
                .idProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31")
                .descripcionCorta("Pizza de jamón y morrones")
                .descripcionLarga("Mozzarella, jamón, morrones y aceitunas verdes")
                .nombre("Jamón y morrones")
                .precioUnitario(550.00)
                .build();

        ProductoDTO productoDTO = ProductoDTO.builder()
                .idProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31")
                .descripcionCorta("Pizza de jamón y huevo")
                .descripcionLarga("Mozzarella, jamón, huevo y aceitunas verdes")
                .nombre("Jamón y huevo")
                .precioUnitario(560.00)
                .build();

        when(productoRepository.findById(anyString())).thenReturn(Optional.of(productoEntity));
        when(productoRepository.save(any())).thenReturn(productoEntity);

        ProductoDTO response = productoService.updateProducto(productoDTO.getIdProducto(), productoDTO);

        assertNotNull(response);
        assertEquals(productoDTO.getIdProducto(), response.getIdProducto());
        assertNotEquals(productoDTO.getNombre(), response.getNombre());
        assertNotEquals("Jamón y huevo", response.getNombre());
    }

    @Test
    void deleteProducto() {
        ProductoEntity productoEntity = ProductoEntity.builder()
                .idProducto("89efb206-2aa6-4e21-8a23-5765e3de1f31")
                .build();

        when(productoRepository.findById(anyString())).thenReturn(Optional.of(productoEntity));
        doNothing().when(productoRepository).delete(any());

        Boolean response = productoService.deleteProducto(productoEntity.getIdProducto());

        assertTrue(response);

    }

}
