package co.com.reto.horas_extra.entity;

import co.com.reto.horas_extra.request.ReporteDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "El id del tecnico debe ser definido")
    @Column(name = "idTecnico", nullable = false)
    private String idTecnico;
    @NotBlank(message = "El id del servicio debe ser definido")
    @Column(name = "idServicio", nullable = false)
    private String idServicio;
    @Column(name = "fechaInicio", nullable = false)
    private Instant fechaInicio;
    @Column(name = "fechaFin", nullable = false)
    private Instant fechaFin;

    public Reporte(ReporteDto dto) {
        this.idTecnico = dto.getIdTecnico();
        this.idServicio = dto.getIdServicio();
        this.fechaInicio = Instant.ofEpochMilli(dto.getFechaInicio().getTime());
        this.fechaFin = Instant.ofEpochMilli(dto.getFechaFin().getTime());
    }
}
