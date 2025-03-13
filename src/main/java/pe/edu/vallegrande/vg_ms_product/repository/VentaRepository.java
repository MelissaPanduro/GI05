package pe.edu.vallegrande.vg_ms_product.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.vg_ms_product.model.VentaModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public interface VentaRepository extends ReactiveCrudRepository<VentaModel, Long> {

    /**
     * Buscar ventas por estado ("activo" o "inactivo").
     * @param estado estado de la venta
     * @return Flux<VentaModel> ventas que coinciden
     */
    Flux<VentaModel> findByEstado(String estado);

    /**
     * Buscar ventas dentro de un rango de fechas.
     * @param inicio fecha de inicio
     * @param fin fecha de fin
     * @return Flux<VentaModel> ventas en el rango de fechas
     */
    Flux<VentaModel> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Buscar ventas por ID del producto.
     * @param idProducto ID del producto
     * @return Flux<VentaModel> ventas que incluyen este producto
     */
    Flux<VentaModel> findByIdProducto(Long idProducto);

    /**
     * Buscar ventas cuyo total esté dentro de un rango.
     * @param minTotal monto mínimo
     * @param maxTotal monto máximo
     * @return Flux<VentaModel> ventas dentro del rango
     */
    Flux<VentaModel> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);
}
