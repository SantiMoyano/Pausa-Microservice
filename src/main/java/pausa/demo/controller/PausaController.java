package pausa.demo.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pausa.demo.entity.Pausa;
import pausa.demo.repository.PausaRepository;

@RestController
public class PausaController {

    @Autowired
    private PausaRepository repository;

    @GetMapping
    public List<Pausa> listaPausas() {
        return repository.findAll();
    }

    @GetMapping("/contarPausas/{id_viaje}")
    public long contarPausas(@PathVariable Long id_viaje) {

        List<Pausa> pausas = repository.findAllByIdViaje(id_viaje); 
        long segundosTotales = 0;
        for (Pausa pausa: pausas) {
            long segundos = calcularSegundos(pausa.getHora_ini(), pausa.getHora_fin());
            segundosTotales += segundos;
        }
        return segundosTotales;
    }

    @PostMapping("/{id_viaje}")
    public Pausa empezarPausa(@PathVariable Long id_viaje) {

        Pausa pausa = new Pausa();
        LocalTime horaActual = LocalTime.now();
        pausa.setHora_ini(horaActual);
        pausa.setId_viaje(id_viaje);
        return repository.save(pausa);
    }

    @PutMapping("/{id_viaje}")
    public LocalTime terminarPausa(@PathVariable Long id_viaje) {

        Pausa pausa = repository.findByIdViaje(id_viaje);
        LocalTime horaActual = LocalTime.now();
        pausa.setHora_fin(horaActual);
        if (calcularSegundos(pausa.getHora_ini(), pausa.getHora_fin()) > 10) {
            repository.save(pausa);
            return horaActual;
        } else {
            repository.save(pausa);
            return null;
        }
    }

    public long calcularSegundos(LocalTime hora_ini, LocalTime hora_fin) {
        Duration diferencia = Duration.between(hora_ini, hora_fin);
        return diferencia.getSeconds();
    }
}
