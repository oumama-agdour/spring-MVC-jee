package com.example.hopital.web;
import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller @AllArgsConstructor public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index (Model model,
                         @RequestParam(name="page",defaultValue = "0")int page ,
                         @RequestParam(name="size",defaultValue = "4") int size,
                         @RequestParam(name="kw",defaultValue = "") String kw
                         )
    {
        Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("ListePatients",pagePatient.getContent());
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentpage",page);
        model.addAttribute("keyword",kw);
       return "patients";
    }
  @GetMapping("/delete")
    public String delete(Long id )
    {
        patientRepository.deleteById(id);
        return "redirect:/index";
    }


}
