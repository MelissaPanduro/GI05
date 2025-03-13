package pe.edu.vallegrande.vg_ms_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_product.model.VentaModel;
import pe.edu.vallegrande.vg_ms_product.service.VentaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;


@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestParam Long idProducto, @RequestParam int cantidad) {
        Venta venta = ventaService.registrarVenta(idProducto, cantidad);
        return ResponseEntity.ok(venta);
    }
}
