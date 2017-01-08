package br.gov.sp.policiamilitar.cpocpp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;
import br.gov.sp.policiamilitar.cpocpp.business.services.AssuntoService;


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
