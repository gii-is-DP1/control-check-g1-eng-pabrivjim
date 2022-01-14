package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vRepo;

    public List<Vaccination> getAll(){
        return vRepo.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return vRepo.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vRepo.findVaccineByName(typeName);
    }
    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
        if(p.getVaccinatedPet().getType().equals(p.getVaccine().getPetType())){
            return vRepo.save(p);       
        }else{
           throw new UnfeasibleVaccinationException();
        }
        
    }

    
}
