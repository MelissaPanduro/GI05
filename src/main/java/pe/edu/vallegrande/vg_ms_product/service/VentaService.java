package pe.edu.vallegrande.vg_ms_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_product.model.VentaModel;
import pe.edu.vallegrande.vg_ms_product.repository.VentaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    /**
     * Obtener todas las ventas.
     * @return Flux<VentaModel> lista de ventas.
     */
    public Flux<VentaModel> getAllVentas() {
        return ventaRepository.findAll();
    }

    /**
     * Obtener una venta por su ID.
     * @param id ID de la venta.
     * @return Mono<VentaModel> venta encontrada o error si no existe.
     */
    public Mono<VentaModel> getVentaById(Long id) {
        return ventaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Venta no encontrada con ID: " + id)));
    }

    /**
     * Crear una nueva venta.
     * @param venta Venta a crear.
     * @return Mono<VentaModel> venta creada.
     */
    public Mono<VentaModel> createVenta(VentaModel venta) {
        return ventaRepository.save(venta);
    }

    /**
     * Actualizar una venta existente.
     * @param id ID de la venta a actualizar.
     * @param venta Datos de la venta actualizados.
     * @return Mono<VentaModel> venta actualizada.
     */
    public Mono<VentaModel> updateVenta(Long id, VentaModel venta) {
        return ventaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Venta no encontrada con ID: " + id)))
                .flatMap(existingVenta -> {
                    
                    existingVenta.setFecha(venta.getFecha());
                    existingVenta.setTotal(venta.getTotal());

                    return ventaRepository.save(existingVenta);
                });
    }

    

    /**
     * Obtener ventas en un rango de fechas.
     * @param inicio Fecha de inicio.
     * @param fin Fecha de fin.
     * @return Flux<VentaModel> ventas dentro del rango de fechas.
     */
    public Flux<VentaModel> getVentasByDateRange(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }

    /**
     * Buscar ventas por total dentro de un rango.
     * @param minTotal Total mínimo.
     * @param maxTotal Total máximo.
     * @return Flux<VentaModel> ventas dentro del rango de totales.
     */
    public Flux<VentaModel> buscarPorRangoDeTotal(BigDecimal minTotal, BigDecimal maxTotal) {
        return ventaRepository.findByTotalBetween(minTotal, maxTotal);
    }
}
