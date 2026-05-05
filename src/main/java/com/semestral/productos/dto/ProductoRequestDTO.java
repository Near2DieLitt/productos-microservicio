package com.semestral.productos.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.URL;

import com.semestral.productos.model.Categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {

    @NotBlank(message = "El SKU no puede estar vacio")
    private String SKU;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre_prod;
    @NotBlank(message = "La descripcion no puede estar vacia")
    private String desc_prod;
    @NotNull(message = "El precio no puede estar vacio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio_unitario;
    @NotBlank(message = "La URL no puede estar vacia")
    @URL(message = "Por favor, ingresa una dirección URL válida (ej: https://ejemplo.com)")
    private String foto;
    @NotNull(message = "El stock no puede estar vacio")
    @Positive(message = "El stock debe ser mayor a 0")
    private Long stock;
    @NotNull(message = "La categoria no estar vacia")
    private Categoria id_cat;

}
