package co.com.reto.horas_extra.services;

import co.com.reto.horas_extra.model.CalculoHoras;
import co.com.reto.horas_extra.entity.Reporte;

import java.util.Date;
import java.util.List;

public interface ICalculoService {
    CalculoHoras obtenerCalculoHoras(String idTecnico, int semana);
}
