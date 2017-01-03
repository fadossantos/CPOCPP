package br.gov.sp.policiamilitar.cpocpp.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Status;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Feature;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Row;
import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Type;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Variety;
import br.gov.sp.policiamilitar.cpocpp.business.services.StatusService;
import br.gov.sp.policiamilitar.cpocpp.business.services.SeedStarterService;
import br.gov.sp.policiamilitar.cpocpp.business.services.VarietyService;


@Controller
public class StatusController {


    @Autowired
    private StatusService statusService;
      
    
    public StatusController() {
        super();
    }

    
    @ModelAttribute("allStatus")
    public Iterable<Status> populateStatuss() {
        return this.statusService.findAll();
    }
    
    @RequestMapping("/status")
    public String showStatus(final Status status) {
          return "status/status";
    }      
    
    @RequestMapping(value="/status", params={"save"})
    public String saveStatus(final Status status, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "status";
        }
        this.statusService.addOrUpdate(status);
        model.clear();
        return "redirect:/status";
    } 
    
    @RequestMapping(value="/status/remover/{id}")
    public String saveStatus(@PathVariable Long id) {
        this.statusService.remove(id);
        return "redirect:/status";
    }  


}
