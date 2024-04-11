package net.primodev.gestion_des_patients_app.web;


import lombok.AllArgsConstructor;
import net.primodev.gestion_des_patients_app.entities.Patient;
import net.primodev.gestion_des_patients_app.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,@RequestParam(name = "isSick", defaultValue = "") Boolean isSick ,@RequestParam(name = "p", defaultValue = "0")  int page , @RequestParam(name = "s" , defaultValue = "5") int size){
        Page<Patient> Patients;
        if (isSick != null){
            Patients = patientRepository.findByIsSick(isSick,PageRequest.of(page,size));
        }else {
            Patients = patientRepository.findAll(PageRequest.of(page,size));
        }

        // get pages
        int[] AllPages = new int[Patients.getTotalPages()];

        // save in the module
        model.addAttribute("ListPatients",Patients.getContent());
        model.addAttribute("pages",AllPages);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentPatientHealth",isSick);
//        Patients.forEach(patient -> {
//            System.out.println("patient : "+patient);
//        });
        // return a view
        return "patients";

    }

}
