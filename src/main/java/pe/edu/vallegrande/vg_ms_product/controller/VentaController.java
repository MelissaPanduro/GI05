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
