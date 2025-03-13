package pe.edu.vallegrande.vg_ms_product.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import pe.edu.vallegrande.vg_ms_product.model.ProductoModel;
import pe.edu.vallegrande.vg_ms_product.service.ProductoService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@CrossOrigin("*")
@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoRest {

    private final ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public Flux<ProductoModel> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Mono<ProductoModel> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public Mono<ProductoModel> createProducto(@RequestBody ProductoModel producto) {
        return productoService.createProducto(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Mono<ProductoModel> updateProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProducto(id, producto);
    }

    // Eliminar un producto de forma lógica
    @DeleteMapping("/{id}")
    public Mono<ProductoModel> deleteLogicProducto(@PathVariable Long id) {
        return productoService.deleteLogicProducto(id);
    }

    // Restaurar un producto (cambiar su estado a "activo")
    @Operation(summary = "Restaurar un producto")
    @PutMapping("/restaurar/{id}")
    public Mono<ProductoModel> restoreProducto(@PathVariable Long id) {
        return productoService.restoreProducto(id);
    }
}

