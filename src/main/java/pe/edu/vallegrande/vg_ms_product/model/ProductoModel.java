package pe.edu.vallegrande.vg_ms_product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("productos") // Mapea la tabla "producto" de la base de datos
public class ProductoModel {

    @Id
    @Column("id_producto") // Mapea la columna "id_producto"
    private Long idProducto;

    @Column("nombre") // Mapea la columna "nombre"
    private String nombre;

    @Column("descripcion") // Mapea la columna "descripcion"
    private String descripcion;

    @Column("unidad_medida") // Mapea la columna "unidad_medida"
    private String unidadMedida;

    @Column("precio_unitario") // Mapea la columna "precio_unitario"
    private BigDecimal precioUnitario;

    @Column("categoria") // Mapea la columna "categoria"
    private String categoria;

    @Column("fecha_creacion") // Mapea la columna "fecha_creacion"
    private LocalDate fechaCreacion;

    @Column("estado") // Mapea la columna "estado"
    private String estado; // Activo o Inactivo
}
