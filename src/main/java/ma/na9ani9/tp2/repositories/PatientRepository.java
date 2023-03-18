package ma.na9ani9.tp2.repositories;

import ma.na9ani9.tp2.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByMalade(boolean m, PageRequest pageable);
    List<Patient> findByMalade(boolean m);
}