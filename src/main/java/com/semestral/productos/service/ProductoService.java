package com.semestral.productos.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.semestral.productos.dto.ProductoResponseDTO;
import com.semestral.productos.model.Productos;
import com.semestral.productos.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Convertimos la lista de entidades a lista de DTOs
    public List<ProductoResponseDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO getProductoById(Long id) {
        Productos producto = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
        return convertToDTO(producto);
    }

    // Recibe entidad (validada en Controller) y devuelve DTO
    public ProductoResponseDTO createProducto(Productos producto) {
        Productos guardado = productoRepository.save(producto);
        return convertToDTO(guardado);
    }

    public ProductoResponseDTO updateProducto(Long id, Productos producto) {
        Productos existente = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
        
        producto.setId_producto(existente.getId_producto());
        Productos actualizado = productoRepository.save(producto);
        return convertToDTO(actualizado);
    }

    // --- Método Helper para mapear ---
    private ProductoResponseDTO convertToDTO(Productos p) {
        return new ProductoResponseDTO(
            p.getId_producto(),
            p.getSKU(),
            p.getNombre_prod(),
            p.getDesc_prod(),
            p.getPrecio_unitario(),
            p.getFoto(),
            p.getStock(),
            p.getId_cat()
        );
    }

    // Los métodos de búsqueda también deberían retornar DTOs
    public List<ProductoResponseDTO> findBySku(String sku) {
        return productoRepository.encontrarProductosPorSku(sku).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NoSuchElementException("Producto no existe con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}