package co.com.reto.horas_extra.controller;

import co.com.reto.horas_extra.entity.Reporte;
import co.com.reto.horas_extra.request.ReporteDto;
import co.com.reto.horas_extra.services.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("reporte")
public class ReporteController {

    private final IReporteService reporteService;

    @Autowired
    public ReporteController(IReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(reporteService.listar());
    }

    @GetMapping("/{idReporte:[0-9]+}")
    public ResponseEntity<?> obtenerPorId(@PathVariable("idReporte") Long idReporte) {
        return reporteService.encontrarPorId(idReporte).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ReporteDto reporte, BindingResult bResultado) {
        if (bResultado.hasErrors()) {
            return new ResponseEntity<>(bResultado.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Reporte r = new Reporte(reporte);
        String retornoValidacion = reporteService.validarFecha(r);
        if (!StringUtils.isEmpty(retornoValidacion)) {
            return new ResponseEntity<>(retornoValidacion, HttpStatus.BAD_REQUEST);
        }
        Reporte reporteBd = reporteService.guardar(r);
        return Optional.ofNullable(reporteBd).map(datos -> ResponseEntity.status(HttpStatus.CREATED).body(datos))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    private ResponseEntity<Object> getNotFoundResponse() {
        return new ResponseEntity<>("No existe el reporte en BD", HttpStatus.NOT_FOUND);
    }

}
