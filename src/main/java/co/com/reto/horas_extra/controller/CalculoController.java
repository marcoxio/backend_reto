package co.com.reto.horas_extra.controller;

import co.com.reto.horas_extra.services.ICalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RestController
public class CalculoController {

    private final ICalculoService calculoService;

    @Autowired
    public CalculoController(ICalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @GetMapping("calculo/idTecnico/{idTecnico}/semana/{semana}")
    //@RequestParam("semana")
    public ResponseEntity<?> calcularHoras(@PathVariable @NotBlank String idTecnico,
                                           @PathVariable @Min(1) @Max(52) int semana) {
        return ResponseEntity.ok(calculoService.obtenerCalculoHoras(idTecnico, semana));
    }
}
