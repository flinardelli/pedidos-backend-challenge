/*package com.ma.pedidos.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Table(name = "pedidos_cabecera")
@Entity
public class PedidoCabeceraEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idPedidoCabecera;
    private String direccion;
    private String email;
    private String telefono;
    private Date horario;
    private Date fechaAlta;
    private Float montoTotal;

    @Column(name = "aplico_descuento")
    private Boolean isAplicoDescuento;
    private String estado;

}*/
