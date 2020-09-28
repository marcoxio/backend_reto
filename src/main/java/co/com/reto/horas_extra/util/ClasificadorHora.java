package co.com.reto.horas_extra.util;

import co.com.reto.horas_extra.model.CalculoHoras;
import co.com.reto.horas_extra.entity.Reporte;

import java.time.DayOfWeek;
    import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;

public class ClasificadorHora {
    private static final int INI_DIURNA = 7;
    private static final int INI_NOCTURNA = 20;

    public CalculoHoras clasificarHoras(CalculoHoras ch, Reporte reporte) {
        Instant ini = reporte.getFechaInicio();
        Instant fin = reporte.getFechaFin();
        calcularHorasSemana(ch, ini, fin);
            ch.setHorasDominicales(ch.getHorasDominicales() + calcularHorasDominicales(ini, fin));
        return ch;
    }

    // Diurnas 7AM - 8 PM - Nocturnas 8 PM - 7 AM
    private void calcularHorasSemana(CalculoHoras ch, Instant ini, Instant fin) {
        DayOfWeek diaIni = ini.atOffset(ZoneOffset.UTC).getDayOfWeek();
        DayOfWeek diaFin = fin.atOffset(ZoneOffset.UTC).getDayOfWeek();
        // inicio y fin no son el mismo dia
        if (diaIni.getValue() != diaFin.getValue()) {

        } else if (!DayOfWeek.SUNDAY.equals(diaIni)) {
            calculoHorasEnMismoDia(ch, ini, fin);
        }
    }

    private void calculoHorasEnMismoDia(CalculoHoras ch, Instant ini, Instant fin) {
        int horaInicio = ini.atOffset(ZoneOffset.UTC).getHour();
        int horaFin = fin.atOffset(ZoneOffset.UTC).getHour();
        sumarHorasNormalesMismoDia(ch, horaInicio, horaFin);
        sumarHorasNocturnasMismoDia(ch, horaInicio, horaFin);
    }

    private void sumarHorasNormalesMismoDia(CalculoHoras ch, int ini, int fin) {
        int newFin = fin == 0? 24 : fin;
        newFin = newFin > INI_NOCTURNA? INI_NOCTURNA : newFin;
        if (ini <= INI_DIURNA && newFin > INI_DIURNA) {
            ch.setHorasNormales(ch.getHorasNormales() + (newFin - INI_DIURNA));
        } else if (ini > INI_DIURNA && newFin > INI_DIURNA) {
            ch.setHorasNormales(ch.getHorasNormales() + (newFin - ini));
        }
    }

    private void sumarHorasNocturnasMismoDia(CalculoHoras ch, int ini, int fin) {
        int newFin = fin == 0? 24 : fin;
        if (ini < INI_DIURNA) {
            if (newFin <= INI_DIURNA) {
                ch.setHorasNocturnas(ch.getHorasNocturnas() + (newFin - ini));
            } else if (newFin > INI_NOCTURNA) {
                ch.setHorasNocturnas(ch.getHorasNocturnas() + (INI_DIURNA - ini));
                ch.setHorasNocturnas(ch.getHorasNocturnas() + (newFin - INI_NOCTURNA));
            }
        } else if (ini >= INI_NOCTURNA) {
            ch.setHorasNocturnas(ch.getHorasNocturnas() + (newFin - ini));
        }
    }

    // Dominicales
    public int calcularHorasDominicales(Instant ini, Instant fin) {
        int dominicales = 0;
        DayOfWeek diaIni = ini.atOffset(ZoneOffset.UTC).getDayOfWeek();
        DayOfWeek diaFin = fin.atOffset(ZoneOffset.UTC).getDayOfWeek();
        // inicio y fin no son el mismo dia
        if (diaIni.getValue() != diaFin.getValue()) {
            // inicio empieza el domingo y fin no es domingo
            if (DayOfWeek.SUNDAY.equals(diaIni) && !DayOfWeek.SUNDAY.equals(diaFin)) {
                dominicales = calcularInicioDominical(ini, diaIni);
            } else if (!DayOfWeek.SUNDAY.equals(diaIni) && DayOfWeek.SUNDAY.equals(diaFin)) {
                dominicales = calcularFinDominical(fin, diaFin);
            }
        } else if (DayOfWeek.SUNDAY.equals(diaIni)) {
            Duration duration = Duration.between(ini, fin);
            dominicales = (int) duration.toHours();
        }
        return dominicales;
    }



    private int calcularInicioDominical(Instant ini, DayOfWeek diaIni) {
        int dominicales;
        Instant tmp = Instant.from(ini);
        tmp = tmp.atOffset(ZoneOffset.UTC).withHour(0).withMinute(0).withNano(0)
                .withDayOfMonth(tmp.atOffset(ZoneOffset.UTC).getDayOfMonth() + 1).toInstant();
        Duration duration = Duration.between(ini, tmp);
        dominicales = (int) duration.toHours();
        return dominicales;
    }

    private int calcularFinDominical(Instant fin, DayOfWeek diaIni) {
        int dominicales;
        Instant tmp = Instant.from(fin);
        tmp = tmp.atOffset(ZoneOffset.UTC).withHour(0).withMinute(0).withNano(0).toInstant();
        Duration duration = Duration.between(tmp, fin);
        dominicales = (int) duration.toHours();
        return dominicales;
    }


}
