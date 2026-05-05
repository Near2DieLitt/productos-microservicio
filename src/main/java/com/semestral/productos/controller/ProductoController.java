package com.semestral.productos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semestral.productos.dto.ProductoRequestDTO;
import com.semestral.productos.dto.ProductoResponseDTO;
import com.semestral.productos.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService prodService;
    //Creacion de endpoints
    //get (por id)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id_producto){
        return ResponseEntity.ok(prodService.getProductoById(id_producto));
    }

    //get (todos los productos)
    @GetMapping()
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductos() {
        return ResponseEntity.ok(prodService.getAllProductos());
    }

    //post
    @PostMapping("/agregar")
    public ResponseEntity<ProductoResponseDTO>agregarProd(@Valid @RequestBody ProductoRequestDTO prod){
        return ResponseEntity.status(201).body(prodService.saveProducto(prod));
        
    }
    
}
