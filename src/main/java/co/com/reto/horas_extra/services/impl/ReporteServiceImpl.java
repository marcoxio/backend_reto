package co.com.reto.horas_extra.services.impl;

import co.com.reto.horas_extra.entity.Reporte;
import co.com.reto.horas_extra.repository.IReporteRepository;
import co.com.reto.horas_extra.services.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements IReporteService {
    private final IReporteRepository repository;

    @Autowired
    public ReporteServiceImpl(IReporteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reporte> encontrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Reporte guardar(Reporte reporte) {
        return repository.save(reporte);
    }


    @Override
    public List<Reporte> listar() {
        return repository.findAll();
    }

    @Override
    public String validarFecha(Reporte reporte) {
        StringBuilder builder = new StringBuilder();
        builder.append(validarFecha("fechaInicio", reporte.getFechaInicio()));
        builder.append(validarFecha("fechaFin", reporte.getFechaFin()));
        if (reporte.getFechaFin().compareTo(reporte.getFechaInicio()) < 0) {
            builder.append("La fecha de fin no puede ser menor que la de inicio\n");
        }

        return builder.toString();
    }

    private String validarFecha(String nombreCampo, Instant fecha) {
        return fecha == null ? nombreCampo + " - La fecha no puede estar vacia\n" : "";
    }
}
