/*
package com.ma.pedidos.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Table(name = "pedidos_detalle")
@Entity
public class PedidoDetalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String idPedidoDetalle;
    private String nombre;
    private String descripcionCorta;
    private String descripcionLarga;
    private Float precioUnitario;

    @OneToOne
    private PedidoCabeceraEntity pedidoCabeceraEntity;

    @OneToMany(mappedBy = "idProducto")
    private List<ProductoEntity> productoEntities;
}
*/
