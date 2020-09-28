package co.com.reto.horas_extra.services;

import co.com.reto.horas_extra.entity.Reporte;

import java.util.List;
import java.util.Optional;

public interface IReporteService {
    Optional<Reporte> encontrarPorId(Long id);
    Reporte guardar(Reporte reporte);
    List<Reporte> listar();
    String validarFecha(Reporte reporte);
}
