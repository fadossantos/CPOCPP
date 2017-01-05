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

import br.gov.sp.policiamilitar.cpocpp.business.entities.TipoDocumento;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Feature;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Row;
import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Type;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Variety;
import br.gov.sp.policiamilitar.cpocpp.business.services.TipoDocumentoService;
import br.gov.sp.policiamilitar.cpocpp.business.services.SeedStarterService;
import br.gov.sp.policiamilitar.cpocpp.business.services.VarietyService;


@Controller
public class TipoDocumentoController {


    @Autowired
    private TipoDocumentoService tipoDocumentoService;
      
    
    public TipoDocumentoController() {
        super();
    }

    
    @ModelAttribute("allTipoDocumento")
    public Iterable<TipoDocumento> populateTipoDocumentos() {
        return this.tipoDocumentoService.findAll();
    }
    
    @RequestMapping("/tipoDocumento")
    public String showTipoDocumento(final TipoDocumento tipoDocumento) {
          return "tipoDocumento/tipoDocumento";
    }      
    
    @RequestMapping(value="/tipoDocumento", params={"save"})
    public String saveTipoDocumento(final TipoDocumento tipoDocumento, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "tipoDocumento";
        }
        this.tipoDocumentoService.addOrUpdate(tipoDocumento);
        model.clear();
        return "redirect:/tipoDocumento";
    } 
    
    @RequestMapping(value="/tipoDocumento/remover/{id}")
    public String saveTipoDocumento(@PathVariable Long id) {
        this.tipoDocumentoService.remove(id);
        return "redirect:/tipoDocumento";
    }  


}
