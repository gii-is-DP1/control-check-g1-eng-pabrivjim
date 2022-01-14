package org.springframework.samples.petclinic.vacination;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
    
      
	@Autowired
    private VaccinationService vService;

    @Autowired
    private PetService pService;

    private static final String VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM = "vaccination/createOrUpdateVaccinationForm";
    
    
    @GetMapping(value = "/create")
	public String initCreationForm(ModelMap modelMap) {
		String view = VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM;
	    modelMap.addAttribute("vaccination",new Vaccination());
        modelMap.addAttribute("vaccines", vService.getAllVaccines());
        modelMap.addAttribute("pet", pService.findAllPets());
		return view;
	}
    
    @PostMapping(value = "/create")
	public String processCreationForm(@Valid Vaccination vaccination, BindingResult result, ModelMap modelMap){       
        try {            
            if (result.hasErrors()) {
                if(vaccination.getVaccinatedPet().getType().equals(vaccination.getVaccine().getPetType())){
                   return "vaccination/createOrUpdateVaccinationForm";
                }
                modelMap.addAttribute("vaccination",new Vaccination());
                modelMap.addAttribute("vaccines", vService.getAllVaccines());
                modelMap.addAttribute("pet", pService.findAllPets());
                return "welcome";
                
                
            }else {
                //creating owner, user and authorities
                //vService.save(vaccination);
                modelMap.addAttribute("message", "Product succesfully save");
                return VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM;
            }
        } catch (Exception e) {
            if(e.getCause().equals(new NullPointerException())){
                return "welcome";
            }
            try {            
                if (result.hasErrors()) {
                    if(vaccination.getVaccinatedPet().getType().equals(vaccination.getVaccine().getPetType())){
                       return "vaccination/createOrUpdateVaccinationForm";
                    }
                    modelMap.addAttribute("vaccination",new Vaccination());
                    modelMap.addAttribute("vaccines", vService.getAllVaccines());
                    modelMap.addAttribute("pet", pService.findAllPets());
                    return "welcome";
                    
                    
                }else {
                    //creating owner, user and authorities
                    //vService.save(vaccination);
                    modelMap.addAttribute("message", "Product succesfully save");
                    return VIEWS_VACCINATION_CREATE_OR_UPDATE_FORM;
                }
            } catch (Exception e2) {
                if(vaccination.getVaccinatedPet().getType().equals(vaccination.getVaccine().getPetType())){
                    return "vaccination/createOrUpdateVaccinationForm";
                 }
                 modelMap.addAttribute("vaccination",new Vaccination());
                 modelMap.addAttribute("vaccines", vService.getAllVaccines());
                 modelMap.addAttribute("pet", pService.findAllPets());
                 return "welcome";
            }
        }
    
    }
}
