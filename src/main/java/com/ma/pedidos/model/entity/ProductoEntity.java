package com.ma.pedidos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@Entity
public class ProductoEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String idProducto;

    private String nombre;
    private String descripcionCorta;
    private String descripcionLarga;
    private double precioUnitario;

    public ProductoEntity(String nombre, String descripcionCorta, String descripcionLarga, double precioUnitario) {
        this.idProducto = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga ;
        this.precioUnitario = precioUnitario;
    }
}
