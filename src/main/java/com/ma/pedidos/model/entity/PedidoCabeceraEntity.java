package com.ma.pedidos.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos_cabecera")
@Entity
public class PedidoCabeceraEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String idPedidoCabecera;
    private String direccion;
    private String email;
    private String telefono;
    private String horario;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    private double montoTotal;

    @Column(name = "aplico_descuento")
    private Boolean isAplicoDescuento;
    private String estado;
}
