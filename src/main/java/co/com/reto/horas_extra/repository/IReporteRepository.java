package co.com.reto.horas_extra.repository;

import co.com.reto.horas_extra.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Repository
@Transactional
public interface IReporteRepository extends JpaRepository<Reporte, Long> {
    @Query("SELECT r FROM Reporte r WHERE r.fechaInicio >= ?1 AND r.fechaFin <= ?2")
    List<Reporte> obtenerReportesEntreFechas(Instant fechaInicio, Instant fechaFin);
}
