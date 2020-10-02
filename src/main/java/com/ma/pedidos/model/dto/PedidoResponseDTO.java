package com.ma.pedidos.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
    private String idPedidoCabecera;
    private String direccion;
    private String email;
    private String telefono;
    private String horario;
    private Date fechaAlta;
    private Double montoTotal;
    private Boolean isAplicoDescuento;
    private String estado;
    private List<PedidoDetalleResponseDTO> detalle;
}
