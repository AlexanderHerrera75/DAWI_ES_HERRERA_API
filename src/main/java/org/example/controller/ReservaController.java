package org.example.controller;

import org.example.model.ReporteDTO;
import org.example.model.Reserva;
import org.example.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Reserva reserva = reservaService.obtenerPorId(id);
        if (reserva == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe esa reserva");
        }
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Reserva reserva) {
        try {
            Reserva nueva = reservaService.registrar(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/reporte")
    public ResponseEntity<?> reporte(@RequestParam(required = false) String fecha,
                                     @RequestParam(required = false) String nombre) {
        List<ReporteDTO> resultado;
        if (fecha != null) {
            resultado = reservaService.reportePorFecha(LocalDate.parse(fecha));
        } else if (nombre != null) {
            resultado = reservaService.reportePorNombre(nombre);
        } else {
            return ResponseEntity.badRequest().body("Debes enviar fecha o nombre");
        }
        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sin resultados");
        }
        return ResponseEntity.ok(resultado);
    }
    public String estado;
}