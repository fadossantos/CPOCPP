package br.gov.sp.policiamilitar.cpocpp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.sp.policiamilitar.cpocpp.business.entities.ProcessoCPOCPP;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Row;
import br.gov.sp.policiamilitar.cpocpp.business.entities.SeedStarter;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;
import br.gov.sp.policiamilitar.cpocpp.business.entities.DocumentoRelacionado;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Interessado;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Status;
import br.gov.sp.policiamilitar.cpocpp.business.services.AssuntoService;
import br.gov.sp.policiamilitar.cpocpp.business.services.ProcessoCPOCPPService;
import br.gov.sp.policiamilitar.cpocpp.business.services.StatusService;

@Controller
public class ProcessoCPOCPPController {

	@Autowired
	private ProcessoCPOCPPService processoCPOCPPService;

	@Autowired
	private AssuntoService assuntoService;

	@Autowired
	private StatusService statusService;

	public ProcessoCPOCPPController() {
		super();
	}

	@ModelAttribute("allProcessoCPOCPPs")
	public Iterable<ProcessoCPOCPP> populateProcessoCPOCPPs() {
		return this.processoCPOCPPService.findAll();
	}

	@ModelAttribute("allStatus")
	public Iterable<Status> populateStatus() {
		return this.statusService.findAll();
	}

	@ModelAttribute("allAssuntos")
	public Iterable<Assunto> populateAssuntos() {
		return this.assuntoService.findAll();
	}

	@RequestMapping("/processoCPOCPP")
	public String showProcessoCPOCPP(final ProcessoCPOCPP processoCPOCPP) {
		return "processoCPOCPP/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP", params = { "save" })
	public String saveProcessoCPOCPP(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "processoCPOCPP/processoCPOCPP";
		}
		this.processoCPOCPPService.addOrUpdate(processoCPOCPP);
		model.clear();
		return "redirect:/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP", params = { "addInteressado" })
	public String addInteressado(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult) {
		processoCPOCPP.getInteressados().add(new Interessado());
		return "processoCPOCPP/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP", params = { "removeInteressado" })
	public String removeInteressado(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			@RequestParam(value = "removeInteressado", required = false) Integer idInteressado) {
		processoCPOCPP.getInteressados().remove(idInteressado.intValue());
		return "processoCPOCPP/processoCPOCPP";
	}
	
	
	@RequestMapping(value = "/processoCPOCPP", params = { "addDocumentoRelacionado" })
	public String addDocumentoRelacionado(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult) {
		processoCPOCPP.getDocumentosRelacionados().add(new DocumentoRelacionado());
		return "processoCPOCPP/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP", params = { "removeDocumentoRelacionado" })
	public String removeDocumentoRelacionado(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			@RequestParam(value = "removeDocumentoRelacionado", required = false) Integer idDocumentoRelacionado) {
		processoCPOCPP.getDocumentosRelacionados().remove(idDocumentoRelacionado.intValue());
		return "processoCPOCPP/processoCPOCPP";
	}
	

	@RequestMapping(value = "/processoCPOCPP/remover/{id}")
	public String saveProcessoCPOCPP(@PathVariable Long id) {
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPP";
	}

}
