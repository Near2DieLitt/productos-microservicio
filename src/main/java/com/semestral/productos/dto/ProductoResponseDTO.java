package com.semestral.productos.dto;

import java.math.BigDecimal;

import com.semestral.productos.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    private Long id_producto;
    private String SKU;
    private String nombre_prod;
    private String desc_prod;
    private BigDecimal precio_unitario;
    private String foto;
    private Long stock;
    private Categoria id_cat;
}
