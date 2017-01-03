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

import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Feature;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Row;
import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Type;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Variety;
import br.gov.sp.policiamilitar.cpocpp.business.services.AssuntoService;
import br.gov.sp.policiamilitar.cpocpp.business.services.SeedStarterService;
import br.gov.sp.policiamilitar.cpocpp.business.services.VarietyService;


@Controller
public class AssuntoController {


    @Autowired
    private AssuntoService assuntoService;
      
    
    public AssuntoController() {
        super();
    }

    
    @ModelAttribute("allAssuntos")
    public Iterable<Assunto> populateAssuntos() {
        return this.assuntoService.findAll();
    }
    
    @RequestMapping("/assunto")
    public String showAssunto(final Assunto assunto) {
          return "assunto/assunto";
    }      
    
    @RequestMapping(value="/assunto", params={"save"})
    public String saveAssunto(final Assunto assunto, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "assunto";
        }
        this.assuntoService.addOrUpdate(assunto);
        model.clear();
        return "redirect:/assunto";
    } 
    
    @RequestMapping(value="/assunto/remover/{id}")
    public String saveAssunto(@PathVariable Long id) {
        this.assuntoService.remove(id);
        return "redirect:/assunto";
    }  


}
