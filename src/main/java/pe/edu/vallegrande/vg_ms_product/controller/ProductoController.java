package pe.edu.vallegrande.vg_ms_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_product.model.ProductoModel;
import pe.edu.vallegrande.vg_ms_product.service.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public Flux<ProductoModel> getAllProductos() {
        return productoService.getAllProductos();
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public Mono<ProductoModel> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public Mono<ProductoModel> createProducto(@RequestBody ProductoModel producto) {
        return productoService.createProducto(producto);
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public Mono<ProductoModel> updateProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProducto(id, producto);
    }

    @Operation(summary = "Eliminar lógicamente un producto")
    @DeleteMapping("/logic/{id}")
    public Mono<ProductoModel> deleteLogicProducto(@PathVariable Long id) {
        return productoService.deleteLogicProducto(id);
    }

    @Operation(summary = "Restaurar un producto")
    @PutMapping("/restaurar/{id}")
    public Mono<ProductoModel> restoreProducto(@PathVariable Long id) {
        return productoService.restoreProducto(id);
    }
}
