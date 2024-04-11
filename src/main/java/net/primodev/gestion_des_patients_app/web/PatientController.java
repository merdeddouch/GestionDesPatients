package net.primodev.gestion_des_patients_app.web;


import lombok.AllArgsConstructor;
import net.primodev.gestion_des_patients_app.entities.Patient;
import net.primodev.gestion_des_patients_app.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(){
        List<Patient> Patients = patientRepository.findAll(PageRequest.of(0,3)).getContent();
//        Patients.forEach(patient -> {
//            System.out.println("patient : "+patient);
//        });
        // return a view
        return "patients";
    }
}
