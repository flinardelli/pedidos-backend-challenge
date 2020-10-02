package com.ma.pedidos.model.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    private String idPedidoCabecera;

    @NotNull(message = "La dirección no puede estar nula")
    private String direccion;

    @Email(message = "El email ingresado no es correcto. Revise que el formato e intentelo nuevamente.")
    private String email;
    private String telefono;
    private String horario;
    private String fechaAlta;
    private Double montoTotal;
    private Boolean isAplicoDescuento;
    private String estado;

    @Valid
    private List<PedidoDetalleRequestDTO> detalle;
}
