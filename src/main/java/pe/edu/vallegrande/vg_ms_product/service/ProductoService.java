package pe.edu.vallegrande.vg_ms_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_product.model.ProductoModel;
import pe.edu.vallegrande.vg_ms_product.repository.ProductoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtener todos los productos activos.
     * @return Flux<ProductoModel> lista de productos activos.
     */
    public Flux<ProductoModel> getAllProductos() {
        return productoRepository.findAll(); // Sin filtro
    }
    /**
     * Obtener un producto por su ID.
     * @param id ID del producto.
     * @return Mono<ProductoModel> producto encontrado o error si no existe.
     */
    public Mono<ProductoModel> getProductoById(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)));
    }

    /**
     * Crear un nuevo producto.
     * @param producto Producto a crear.
     * @return Mono<ProductoModel> producto creado.
     */
    public Mono<ProductoModel> createProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualizar un producto existente.
     * @param id ID del producto a actualizar.
     * @param producto Datos del producto actualizado.
     * @return Mono<ProductoModel> producto actualizado.
     */
    public Mono<ProductoModel> updateProducto(Long id, ProductoModel producto) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)))
                .flatMap(existingProducto -> {
                    existingProducto.setNombre(producto.getNombre());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setUnidadMedida(producto.getUnidadMedida());
                    existingProducto.setPrecioUnitario(producto.getPrecioUnitario());
                    existingProducto.setCategoria(producto.getCategoria());
                    existingProducto.setEstado(producto.getEstado());
                    return productoRepository.save(existingProducto);
                });
    }

    /**
     * Eliminar un producto de forma lógica (cambiar su estado a "inactivo").
     * @param id ID del producto a eliminar.
     * @return Mono<ProductoModel> producto actualizado como "inactivo".
     */
    public Mono<ProductoModel> deleteLogicProducto(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)))
                .flatMap(producto -> {
                    producto.setEstado("inactivo");
                    return productoRepository.save(producto);
                });
    }

    /**
     * Restaurar un producto eliminado (cambiar su estado a "activo").
     * @param id ID del producto a restaurar.
     * @return Mono<ProductoModel> producto restaurado.
     */
    public Mono<ProductoModel> restoreProducto(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)))
                .flatMap(producto -> {
                    if ("inactivo".equalsIgnoreCase(producto.getEstado())) {
                        producto.setEstado("activo");
                        return productoRepository.save(producto);
                    } else {
                        return Mono.error(new RuntimeException("El producto no está inactivo, no se puede restaurar"));
                    }
                });
    }

    /**
     * Buscar productos por estado.
     * @param estado Estado del producto ("activo" o "inactivo").
     * @return Flux<ProductoModel> productos con el estado especificado.
     */
    public Flux<ProductoModel> buscarPorEstado(String estado) {
        return productoRepository.findByEstado(estado);
    }

    /**
     * Buscar productos por rango de precios.
     * @param minPrecio Precio mínimo.
     * @param maxPrecio Precio máximo.
     * @return Flux<ProductoModel> productos en el rango de precios.
     */
    public Flux<ProductoModel> buscarPorRangoDePrecios(BigDecimal minPrecio, BigDecimal maxPrecio) {
    return productoRepository.findByPrecioUnitarioBetween(minPrecio, maxPrecio);
}

    /**
     * Buscar productos por coincidencia parcial en el nombre.
     * @param nombre Parte del nombre del producto.
     * @return Flux<ProductoModel> productos con coincidencias.
     */
    public Flux<ProductoModel> buscarPorNombre(String nombre) {
        return productoRepository.findByDescripcionIgnoreCase(nombre);
    }
}
