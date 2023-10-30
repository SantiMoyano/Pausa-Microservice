package pausa.demo.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pausa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pausa;

    private LocalTime hora_ini;

    private LocalTime hora_fin;

    private Long id_viaje;

    public Pausa () {

    }

    public Pausa(LocalTime hora_ini, LocalTime hora_fin, Long id_viaje) {
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.id_viaje = id_viaje;
    }

    public Long getId_pausa() {
        return id_pausa;
    }

    public LocalTime getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(LocalTime hora_ini) {
        this.hora_ini = hora_ini;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Long getId_viaje() {
        return id_viaje;
    }

    public void setId_viaje(Long id_viaje) {
        this.id_viaje = id_viaje;
    }
}
