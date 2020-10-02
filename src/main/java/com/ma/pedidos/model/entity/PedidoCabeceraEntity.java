package com.ma.pedidos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String fechaAlta;
    private double montoTotal;

    @Column(name = "aplico_descuento")
    private Boolean isAplicoDescuento;
    private String estado;
}
