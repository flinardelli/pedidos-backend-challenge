package com.ma.pedidos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDetalleResponseDTO {

    private String idPedidoDetalle;
    private Integer cantidad;
    private String producto;
    private String nombre;
    private double importe;

}
