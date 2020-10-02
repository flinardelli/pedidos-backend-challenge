package com.ma.pedidos.service;

import com.ma.pedidos.exceptions.ProductoNotFoundException;
import com.ma.pedidos.model.dto.PedidoDetalleResponseDTO;
import com.ma.pedidos.model.dto.PedidoRequestDTO;
import com.ma.pedidos.model.dto.PedidoResponseDTO;
import com.ma.pedidos.model.entity.PedidoCabeceraEntity;
import com.ma.pedidos.model.entity.PedidoDetalleEntity;
import com.ma.pedidos.model.entity.ProductoEntity;
import com.ma.pedidos.repository.PedidoCabeceraRepository;
import com.ma.pedidos.repository.PedidoDetalleRepository;
import com.ma.pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PedidoService {

    private final PedidoCabeceraRepository pedidoCabeceraRepository;

    private final PedidoDetalleRepository pedidoDetalleRepository;

    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoService(PedidoCabeceraRepository pedidoCabeceraRepository, PedidoDetalleRepository pedidoDetalleRepository, ProductoRepository productoRepository) {
        this.pedidoCabeceraRepository = pedidoCabeceraRepository;
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional(rollbackFor = {ProductoNotFoundException.class, NullPointerException.class})
    public PedidoResponseDTO createPedido(PedidoRequestDTO pedido) {
        List<PedidoDetalleEntity> pedidoDetalleEntities = new ArrayList<>();
        AtomicReference<Double> montoTotal = new AtomicReference<>(0.0);
        pedido.getDetalle().forEach( detalle ->{
            ProductoEntity productoEntity = productoRepository.findById(detalle.getProducto()).orElseThrow(ProductoNotFoundException::new);
            PedidoDetalleEntity pedidoDetalleEntity = new PedidoDetalleEntity();
            pedidoDetalleEntity.setCantidad(detalle.getCantidad());
            pedidoDetalleEntity.setPrecioUnitario(productoEntity.getPrecioUnitario());
            pedidoDetalleEntity.setProductoEntity(productoEntity);

            pedidoDetalleEntities.add(pedidoDetalleEntity);
            montoTotal.updateAndGet(v -> v + productoEntity.getPrecioUnitario());
        });

        PedidoCabeceraEntity pedidoCabeceraEntity = PedidoCabeceraEntity.builder()
                .direccion(pedido.getDireccion())
                .email(pedido.getEmail())
                .estado(pedido.getEstado())
                .telefono(pedido.getTelefono())
                .horario(pedido.getHorario())
                .montoTotal(montoTotal.get())
                .build();

        PedidoCabeceraEntity pedidoCabeceraResponse = pedidoCabeceraRepository.save(pedidoCabeceraEntity);
        List<PedidoDetalleEntity> pedidoDetalleResponse = (List<PedidoDetalleEntity>) pedidoDetalleRepository.saveAll(pedidoDetalleEntities);

        PedidoResponseDTO response = this.mappingPedidos(pedidoCabeceraResponse, pedidoDetalleResponse);

        return response;
    }

    private PedidoResponseDTO mappingPedidos(PedidoCabeceraEntity pedidoCabeceraEntity, List<PedidoDetalleEntity> pedidoDetalleEntity){

        List<PedidoDetalleResponseDTO> pedidoDetalleResponseDTOList = new ArrayList<>();
        pedidoDetalleEntity.forEach(detalle -> {
            PedidoDetalleResponseDTO pedidoResponseDTO = PedidoDetalleResponseDTO.builder()
                    .cantidad(detalle.getCantidad())
                    .importe(detalle.getPrecioUnitario())
                    .idPedidoDetalle(detalle.getIdPedidoDetalle())
                    .nombre(detalle.getProductoEntity().getNombre())
                    .producto(detalle.getProductoEntity().getIdProducto())
                    .build();

            pedidoDetalleResponseDTOList.add(pedidoResponseDTO);
        });

        PedidoResponseDTO pedidoDTO = PedidoResponseDTO.builder()
                .idPedidoCabecera(pedidoCabeceraEntity.getIdPedidoCabecera())
                .direccion(pedidoCabeceraEntity.getDireccion())
                .email(pedidoCabeceraEntity.getEmail())
                .estado(pedidoCabeceraEntity.getEstado())
                .horario(pedidoCabeceraEntity.getHorario())
                .fechaAlta(pedidoCabeceraEntity.getFechaAlta())
                .montoTotal(pedidoCabeceraEntity.getMontoTotal())
                .telefono(pedidoCabeceraEntity.getTelefono())
                .detalle(pedidoDetalleResponseDTOList)
                .build();

        return pedidoDTO;

    }

    /*public PedidoResponseDTO getProductosByFecha(Date fecha) {
        List<PedidoCabeceraEntity> pedidoCabeceraEntity = pedidoCabeceraRepository.findByFechaAltaOrderByFechaAltaDesc(fecha);

        Optional.ofNullable(pedidoCabeceraEntity).ifPresent( pedidos -> {
            pedidos.forEach(pedido -> {
                List<PedidoDetalleEntity> pedidoDetalleEntity = pedidoDetalleRepository.findAllByIdPedidoCabecera(pedido.getIdPedidoCabecera());
                pedidoDetalleEntity
            });
        });
    }*/
}
