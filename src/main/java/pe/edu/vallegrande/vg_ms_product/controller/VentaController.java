package pe.edu.vallegrande.vg_ms_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_product.model.VentaModel;
import pe.edu.vallegrande.vg_ms_product.service.VentaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping
    public Flux<VentaModel> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @Operation(summary = "Obtener una venta por ID")
    @GetMapping("/{id}")
    public Mono<VentaModel> getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }

    @Operation(summary = "Registrar una nueva venta")
    @PostMapping
    public Mono<VentaModel> createVenta(@RequestBody VentaModel venta) {
        return ventaService.createVenta(venta);
    }

    @Operation(summary = "Actualizar una venta existente")
    @PutMapping("/{id}")
    public Mono<VentaModel> updateVenta(@PathVariable Long id, @RequestBody VentaModel venta) {
        return ventaService.updateVenta(id, venta);
    }

}


    
