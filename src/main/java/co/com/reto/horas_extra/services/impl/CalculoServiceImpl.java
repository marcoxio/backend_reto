package co.com.reto.horas_extra.services.impl;

import co.com.reto.horas_extra.model.CalculoHoras;
import co.com.reto.horas_extra.repository.IReporteRepository;
import co.com.reto.horas_extra.services.ICalculoService;
import co.com.reto.horas_extra.entity.Reporte;
import co.com.reto.horas_extra.util.ClasificadorHora;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class CalculoServiceImpl implements ICalculoService {

    @Value("${calculadora.anio.actual}")
    private Integer anioActual;

    private final IReporteRepository repository;

    @Autowired
    public CalculoServiceImpl(IReporteRepository repository) {
        this.repository = repository;
    }

    @Override
    public CalculoHoras obtenerCalculoHoras(String idTecnico, int semana) {
        Calendar cld = obtenerFechaXSemana(semana);
        log.info("fecha en la semana es: {}", cld.getTime());
        // ajusta a primer dia como domingo
        Instant fechaIni = Instant.ofEpochMilli(ajustarDiaSemana(cld, 1).getTime().getTime());
        // ajusta a ultimo dia domingo (sin horas)
        Instant fechaFin = Instant.ofEpochMilli(ajustarDiaSemana(cld, 8).getTime().getTime());
        log.info("FechaInicio = {}, FechaFin = {}", fechaIni, fechaFin);
        CalculoHoras ch = new CalculoHoras();
        List<Reporte> reportes = repository.obtenerReportesEntreFechas(fechaIni, fechaFin);
        for (Reporte reporte : reportes) {
            new ClasificadorHora().clasificarHoras(ch, reporte);
        }
        return ch;
    }

    private Calendar obtenerFechaXSemana(int semana) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, anioActual);
        cld.set(Calendar.WEEK_OF_YEAR, semana);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld;
    }

    // ajusta a primer dia como domingo
    private Calendar ajustarDiaSemana(Calendar cld, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cld.getTime());
        cal.add(Calendar.DAY_OF_WEEK, dias);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
