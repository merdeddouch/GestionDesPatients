package net.primodev.gestion_des_patients_app.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.primodev.gestion_des_patients_app.entities.Patient;
import net.primodev.gestion_des_patients_app.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,@RequestParam(name = "isSick", defaultValue = "") Boolean isSick , @RequestParam(name ="value" ,defaultValue = "") String value,
                        @RequestParam(name = "p", defaultValue = "0")  int page , @RequestParam(name = "s" , defaultValue = "5") int size){


        Page<Patient> Patients;
        if (isSick != null){
            Patients = patientRepository.findByIsSick(isSick,PageRequest.of(page,size));
            // here beacuuse probably there is a value in the bar search so i will set the bar search to ""
            value="";
        }else {
            Patients = patientRepository.findByLastNameContainingOrFirstNameContaining(value,value,PageRequest.of(page,size));
        }

        // get pages
        int[] AllPages = new int[Patients.getTotalPages()];

        // save in the module
        model.addAttribute("ListPatients",Patients.getContent());
        model.addAttribute("pages",AllPages);
        model.addAttribute("currentPage",page);
        model.addAttribute("currentPatientHealth",isSick);
        model.addAttribute("currentValue",value);
//        Patients.forEach(patient -> {
//            System.out.println("patient : "+patient);
//        });
        // return a view
        return "patients";
    }
    @GetMapping("/Delete")
    public String delete(Model model, @RequestParam("id") Long id) {
        // Get the patient we want to delete
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "deletePatient";
        } else {
            return "redirect:/index"; // Redirect to index if patient is not found
        }
    }
    @GetMapping("/deleteValidation")
    public String deleteValidation(@RequestParam(name = "deleteValidation") String confiming ,@RequestParam("id") Long id){
        if (confiming.equals("Confirm")){
            patientRepository.deleteById(id);
        }else{
            return "redirect:/index";
        }
        return "redirect:/index";
    }
    @GetMapping("/addPatient")
    public String addPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "AddPatient";
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model,@RequestParam(name = "id") Long id){
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatient";
    }


    @PostMapping("/savePatientAfEdite")
    public String savePatientAfterEdite(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editPatient";
        }
        if (patient.getIsSick() == null || patient.getIsSick()==false){
            patient.setIsSick(false);
        }
        patientRepository.save(patient);
        return "redirect:/index";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "AddPatient";
        }
        if (patient.getIsSick() == null || patient.getIsSick()==false){
            patient.setIsSick(false);
        }
        patientRepository.save(patient);
        return "redirect:/index";
    }



}
