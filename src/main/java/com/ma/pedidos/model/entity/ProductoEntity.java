package com.ma.pedidos.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@Entity
public class ProductoEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String idProducto;

    private String nombre;
    private String descripcionCorta;
    private String descripcionLarga;
    private double precioUnitario;
}
