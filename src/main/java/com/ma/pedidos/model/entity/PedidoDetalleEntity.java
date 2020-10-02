package com.ma.pedidos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Table(name = "pedidos_detalle")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalleEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String idPedidoDetalle;
    private Integer cantidad;
    private double precioUnitario;

    @OneToOne
    @JoinColumn(name = "idPedidoCabecera")
    private PedidoCabeceraEntity pedidoCabeceraEntity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProducto")
    private ProductoEntity productoEntity;
}
