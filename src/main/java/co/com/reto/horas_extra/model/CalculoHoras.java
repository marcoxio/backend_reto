package co.com.reto.horas_extra.model;

import lombok.Data;

@Data
public class CalculoHoras {
    private int horasNormales;
    private int horasNocturnas;
    private int horasDominicales;
    private int horasNormalesExtra;
    private int horasDominicalesExtra;
    private int horasNocturnasExtra;
}
