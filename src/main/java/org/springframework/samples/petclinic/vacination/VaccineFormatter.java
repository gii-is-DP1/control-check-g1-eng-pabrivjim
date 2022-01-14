package org.springframework.samples.petclinic.vacination;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormatter implements Formatter<Vaccine>{

    
    private final VaccinationService vService;

    
    public VaccineFormatter(VaccinationService vService) {
        this.vService = vService;
    }


    @Override
    public String print(Vaccine object, Locale locale) {
        return object.getName();
    }

    @Override
    public Vaccine parse(String text, Locale locale) throws ParseException {
        Vaccine p = vService.getVaccine(text);

		if(p == null){
            throw new ParseException("type not found: " + text, 0);
		}else{
            return p;
        }
    }
    
}
