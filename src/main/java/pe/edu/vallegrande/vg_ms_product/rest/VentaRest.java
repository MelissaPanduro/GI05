package pe.edu.vallegrande.vg_ms_product.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import pe.edu.vallegrande.vg_ms_product.model.VentaModel;
import pe.edu.vallegrande.vg_ms_product.service.VentaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@CrossOrigin("*")
@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaRest {

    private final VentaService ventaService;

    // Obtener todas las ventas
    @GetMapping
    @Operation(summary = "Obtener todas las ventas")
    public Flux<VentaModel> getAllVentas() {
        return ventaService.getAllVentas();
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una venta por ID")
    public Mono<VentaModel> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }

    // Crear una nueva venta
    @PostMapping
    @Operation(summary = "Crear una nueva venta")
    public Mono<VentaModel> createVenta(@RequestBody VentaModel venta) {
        return ventaService.createVenta(venta);
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una venta existente")
    public Mono<VentaModel> updateVenta(@PathVariable Long id, @RequestBody VentaModel venta) {
        return ventaService.updateVenta(id, venta);
    }

    // Eliminar lógicamente una venta
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar lógicamente una venta")
    public Mono<VentaModel> deleteLogicVenta(@PathVariable Long id) {
        return ventaService.deleteLogicVenta(id);
    }

    // Restaurar una venta eliminada
    @PutMapping("/restaurar/{id}")
    @Operation(summary = "Restaurar una venta eliminada")
    public Mono<VentaModel> restoreVenta(@PathVariable Long id) {
        return ventaService.restoreVenta(id);
    }

    // Obtener ventas en un rango de fechas
    @GetMapping("/rango-fechas")
    @Operation(summary = "Obtener ventas en un rango de fechas")
    public Flux<VentaModel> getVentasByDateRange(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin) {
        return ventaService.getVentasByDateRange(inicio, fin);
    }
}
