package com.ma.pedidos.controller;

import com.ma.pedidos.model.dto.ProductoDTO;
import com.ma.pedidos.exceptions.ProductoNotFoundException;
import com.ma.pedidos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("/{idProducto}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable(name = "idProducto") String idProducto){
        ProductoDTO productoDTO = productoService.getProductoById(idProducto);
        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO producto){
        ProductoDTO productoDTO = productoService.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO);
    }

    @PutMapping("/{idProducto}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable(name = "idProducto") String idProducto, @RequestBody ProductoDTO producto){
        ProductoDTO productoDTO = productoService.updateProducto(idProducto, producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDTO);
    }

    @DeleteMapping("/{idProducto}")
    @ResponseBody
    public ResponseEntity deleteProducto(@PathVariable(name = "idProducto") String idProducto){
        Boolean productoDTO = productoService.deleteProducto(idProducto);

        if (productoDTO) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
