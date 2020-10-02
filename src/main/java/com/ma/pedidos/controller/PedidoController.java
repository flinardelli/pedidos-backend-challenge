package com.ma.pedidos.controller;

import com.ma.pedidos.model.dto.PedidoRequestDTO;
import com.ma.pedidos.model.dto.PedidoResponseDTO;
import com.ma.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> createProducto(@Valid @RequestBody PedidoRequestDTO pedido){
        PedidoResponseDTO pedidoDTO = pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PedidoResponseDTO>> getProductosByFecha(@RequestParam("fecha")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha){
        List<PedidoResponseDTO> pedidoDTO = pedidoService.getProductosByFecha(fecha);
        return ResponseEntity.ok().body(pedidoDTO);
    }
}
