package org.example.service;

import org.example.model.ReporteDTO;
import org.example.model.Reserva;
import org.example.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva registrar(Reserva reserva) {
        return reservaRepository.save(reserva);

    }
    public List<Reserva> buscarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFchReserva(fecha);
    }
    public List<Reserva> buscarPorNombre(String nombre) {
        return reservaRepository.findByNomClienteContainingIgnoreCase(nombre);
    }
    public List<ReporteDTO> reportePorFecha(LocalDate fecha) {
        return mapear(reservaRepository.findByFchReserva(fecha));
    }

    public List<ReporteDTO> reportePorNombre(String nombre) {
        return mapear(reservaRepository.findByNomClienteContainingIgnoreCase(nombre));
    }

    private List<ReporteDTO> mapear(List<Reserva> lista) {
        List<ReporteDTO> resultado = new ArrayList<>();
        for (Reserva r : lista) {
            resultado.add(new ReporteDTO(
                    r.numReserva, r.fchReserva.toString(), r.nomCliente,
                    r.destino.nomDestino, r.destino.preDestino, r.estado
            ));
        }
        return resultado;
    }
}