package net.primodev.gestion_des_patients_app.repository;

import net.primodev.gestion_des_patients_app.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Override
    Page<Patient> findAll(Pageable pageable);

    Page<Patient> findByIsSick(Boolean isSick,Pageable pageable);

    Page<Patient> findByLastNameContainingOrFirstNameContaining(String lastName,String firstname, Pageable pageable);

}
