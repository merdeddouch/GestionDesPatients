package net.primodev.gestion_des_patients_app;

import net.primodev.gestion_des_patients_app.entities.Patient;
import net.primodev.gestion_des_patients_app.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GestionDesPatientsAppApplication implements CommandLineRunner {



    //inject the Patient Dependency
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionDesPatientsAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        patientRepository.save(new Patient(null,"FATNAOUI","Hamza",new Date(),true));
//        patientRepository.save(new Patient(null,"ELHARACH","AbdElAtai",new Date(),false));
//        patientRepository.save(new Patient(null,"LMHAFAD","Yhia",new Date(),false));
//        patientRepository.save(new Patient(null,"NASSER","fatiha",new Date(),true));
//        patientRepository.save(new Patient(null,"ILAA","Dina",new Date(),false));

//  to test the function
//        patientRepository.findByIsSick(true, PageRequest.of(0, 4)).getContent().forEach(patient -> {
//            System.out.println(patient);
//        });
    }

    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
