package pausa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pausa.demo.entity.Pausa;

public interface PausaRepository extends JpaRepository<Pausa, Long> {

    @Query("SELECT p FROM Pausa p WHERE p.id_viaje = :id_viaje")
    List<Pausa> findAllByIdViaje(Long id_viaje);

    @Query("SELECT p FROM Pausa p WHERE p.id_viaje = :id_viaje AND p.hora_fin is NULL")
    Pausa findByIdViaje(Long id_viaje);
    
}
