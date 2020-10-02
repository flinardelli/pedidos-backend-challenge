package com.ma.pedidos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDetalleRequestDTO {

    private String idPedidoDetalle;
    @NotNull(message = "Falta ingresar la cantidad")
    private Integer cantidad;
    private String producto;
}
