package pe.edu.vallegrande.vg_ms_product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("ventas") // Mapea la tabla "ventas" de la base de datos
public class VentaModel {

    @Id
    @Column("id_venta") // Mapea la columna "id_venta"
    private Long idVenta;

    @Column("id_producto") // Mapea la columna "id_producto"
    private Long idProducto;

    @Column("cantidad") // Mapea la columna "cantidad"
    private Integer cantidad;

    @Column("precio_unitario") // Mapea la columna "precio_unitario"
    private BigDecimal precioUnitario;

    @Column("total") // Mapea la columna "total"
    private BigDecimal total;

    @Column("fecha") // Mapea la columna "fecha"
    private LocalDateTime fecha;
}
