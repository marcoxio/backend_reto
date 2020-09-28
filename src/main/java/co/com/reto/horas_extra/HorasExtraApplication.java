package co.com.reto.horas_extra;

import co.com.reto.horas_extra.entity.Reporte;
import co.com.reto.horas_extra.repository.IReporteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.stream.Stream;

@SpringBootApplication
public class HorasExtraApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorasExtraApplication.class, args);
    }



}
