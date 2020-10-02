package com.ma.pedidos.controller;

import com.ma.pedidos.model.dto.PedidoRequestDTO;
import com.ma.pedidos.model.dto.PedidoResponseDTO;
import com.ma.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> createProducto(@RequestBody PedidoRequestDTO pedido){
        PedidoResponseDTO pedidoDTO = pedidoService.createPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> getProductosByFecha(@RequestParam("fecha") Date fecha){
        PedidoResponseDTO pedidoDTO = pedidoService.getProductosByFecha(fecha);
        return ResponseEntity.ok().body(pedidoDTO);
    }

}
