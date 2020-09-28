package co.com.reto.horas_extra.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class ReporteDto {
    @NotBlank(message = "El id del tecnico debe ser definido")
    private String idTecnico;
    @NotBlank(message = "El id del servicio debe ser definido")
    private String idServicio;
    @JsonFormat(locale = "co", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date fechaInicio;
    @JsonFormat(locale = "co", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private Date fechaFin;
}
