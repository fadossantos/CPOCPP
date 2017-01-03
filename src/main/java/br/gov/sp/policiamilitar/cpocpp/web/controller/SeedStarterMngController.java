package br.gov.sp.policiamilitar.cpocpp.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Feature;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Row;
import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Type;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Variety;
import br.gov.sp.policiamilitar.cpocpp.business.services.SeedStarterService;
import br.gov.sp.policiamilitar.cpocpp.business.services.VarietyService;


@Controller
public class SeedStarterMngController {


    @Autowired
    private VarietyService varietyService;
    
    @Autowired
    private SeedStarterService seedStarterService;
    
    
    
    public SeedStarterMngController() {
        super();
    }

    
    
    @ModelAttribute("allTypes")
    public List<Type> populateTypes() {
        return Arrays.asList(Type.ALL);
    }
    
    @ModelAttribute("allFeatures")
    public List<Feature> populateFeatures() {
        return Arrays.asList(Feature.ALL);
    }
    
    @ModelAttribute("allVarieties")
    public Iterable<Variety> populateVarieties() {
        return this.varietyService.findAll();
    }
    
    @ModelAttribute("allSeedStarters")
    public Iterable<SeedStarter> populateSeedStarters() {
        return this.seedStarterService.findAll();
    }
    
    
    
    @RequestMapping("/seedstarter")
    public String showSeedstarters(final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        return "seedstarter";
    }
    
    
    
    @RequestMapping(value="/seedstarter", params={"save"})
    public String saveSeedstarter(final SeedStarter seedStarter, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "seedstarter";
        }
        this.seedStarterService.add(seedStarter);
        model.clear();
        return "redirect:/seedstarter";
    }
    

    
    @RequestMapping(value="/seedstarter", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getRows().add(new Row());
        return "seedstarter";
    }
    
    
    @RequestMapping(value="/seedstarter", params={"removeRow"})
    public String removeRow(
            final SeedStarter seedStarter,
            final BindingResult bindingResult,
            @RequestParam(value = "removeRow", required = false) Integer rowId) {
        seedStarter.getRows().remove(rowId.intValue());
        return "seedstarter";
    }

}
