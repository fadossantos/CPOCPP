package br.gov.sp.policiamilitar.cpocpp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.policiamilitar.cpocpp.business.entities.ProcessoCPOCPP;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Assunto;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Historico;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Interessado;
import br.gov.sp.policiamilitar.cpocpp.business.entities.Status;
import br.gov.sp.policiamilitar.cpocpp.business.entities.TipoDocumento;
import br.gov.sp.policiamilitar.cpocpp.business.services.AssuntoService;
import br.gov.sp.policiamilitar.cpocpp.business.services.ProcessoCPOCPPService;
import br.gov.sp.policiamilitar.cpocpp.business.services.StatusService;
import br.gov.sp.policiamilitar.cpocpp.business.services.TipoDocumentoService;
import br.gov.sp.policiamilitar.cpocpp.filepersistence.FilePersistence;



@Controller
public class ProcessoCPOCPPController {

	@Autowired
	private ProcessoCPOCPPService processoCPOCPPService;

	@Autowired
	private AssuntoService assuntoService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private FilePersistence filePersistence;

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

	@ModelAttribute("allTipoDocumentos")
	public Iterable<TipoDocumento> populateTipoDocumentos() {
		return this.tipoDocumentoService.findAll();
	}

	@RequestMapping("/processoCPOCPP")
	public String showProcessoCPOCPP(final ProcessoCPOCPP processoCPOCPP) {
		return "processoCPOCPP/processoCPOCPP";
	}

	// novo processo

	@RequestMapping(value = "/processoCPOCPP", params = { "save" })
	public String saveProcessoCPOCPP(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			final ModelMap model) throws IOException {
		ProcessoCPOCPP pr = this.processoCPOCPPService.findOne(processoCPOCPP.getIdSecCom());
		if (pr != null) {
			bindingResult.addError(new ObjectError("idSecCom",
					"Processo de numero " + processoCPOCPP.getIdSecCom() + " j� existe na base de dados"));
		}
		if (bindingResult.hasErrors()) {
			return "processoCPOCPP/processoCPOCPP";
		}

		for (Historico dr : processoCPOCPP.getHistoricos()) {
			if (dr.getDocumentoRelacionado().getFile() != null) {
				dr.getDocumentoRelacionado()
						.setCaminhoArquivoSalvo(filePersistence.getCaminhoArquivo() + File.separator
								+ String.valueOf(processoCPOCPP.getIdSecCom()) + File.separator
								+ dr.getDocumentoRelacionado().getNomeDocumentoRelacionado());
				filePersistence.salvaArquivo(dr.getDocumentoRelacionado().getFile(),
						String.valueOf(processoCPOCPP.getIdSecCom()),
						dr.getDocumentoRelacionado().getNomeDocumentoRelacionado());
			}
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

	@RequestMapping(value = "/processoCPOCPP", params = { "addHistorico" })
	public String addHistorico(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult) {
		
		processoCPOCPP.getHistoricos().add(new Historico());
		return "processoCPOCPP/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP", params = { "removeHistorico" })
	public String removeHistorico(final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			@RequestParam(value = "removeHistorico", required = false) Integer idHistorico) {
		processoCPOCPP.getHistoricos().remove(idHistorico.intValue());
		return "processoCPOCPP/processoCPOCPP";
	}

	@RequestMapping(value = "/processoCPOCPP/remover/{id}")
	public String removeProcessoCPOCPP(@PathVariable Long id) throws IOException {
		ProcessoCPOCPP pr = this.processoCPOCPPService.findOne(id);
		if (pr != null) {
			if (pr.getHistoricos() != null) {
				this.filePersistence.deletaDiretorio(String.valueOf(pr.getIdSecCom()));
			}
		}
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPP";
	}

	// Edicao modal

	@RequestMapping(value = "/processoCPOCPP/{origem}/editar/{id}/{parametroPesquisado}")
	public ModelAndView editaProcessoCPOCPP(@PathVariable Long id, ProcessoCPOCPP pr, @PathVariable String origem,
			@PathVariable String parametroPesquisado) {
		pr = this.processoCPOCPPService.findOne(id);
		List<Historico> listDocRel = new ArrayList<Historico>();
		for (Historico docrel : pr.getHistoricos()) {
			listDocRel.add(docrel);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("processoCPOCPP/processoCPOCPPEdicao");
		mav.addObject("listaDocumentos", listDocRel);
		pr.getHistoricos().clear();
		mav.addObject("origem", origem);
		mav.addObject("processoCPOCPP", pr);
		mav.addObject("parametroPesquisado", parametroPesquisado);
		return mav;
	}

	@RequestMapping(value = "/processoCPOCPP/{origem}/{parametroPesquisado}", params = { "save" })
	public String saveEditedProcessoCPOCPP(@PathVariable String origem, @PathVariable String parametroPesquisado,
			final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult, final ModelMap model)
			throws IOException {
		if (bindingResult.hasErrors()) {
			return "redirect:/processoCPOCPP" + origem + "/" + parametroPesquisado;
		}
		ProcessoCPOCPP pr = this.processoCPOCPPService.findOne(processoCPOCPP.getIdSecCom());
		for (Historico dr : pr.getHistoricos()) {
			processoCPOCPP.getHistoricos().add(dr);
		}

		for (Historico dr : processoCPOCPP.getHistoricos()) {
			if (dr.getDocumentoRelacionado().getFile() != null) {
				dr.getDocumentoRelacionado()
						.setCaminhoArquivoSalvo(filePersistence.getCaminhoArquivo() + File.separator
								+ String.valueOf(processoCPOCPP.getIdSecCom()) + File.separator
								+ dr.getDocumentoRelacionado().getNomeDocumentoRelacionado());
				filePersistence.salvaArquivo(dr.getDocumentoRelacionado().getFile(),
						String.valueOf(processoCPOCPP.getIdSecCom()),
						dr.getDocumentoRelacionado().getNomeDocumentoRelacionado());
			}
		}
		this.processoCPOCPPService.addOrUpdate(processoCPOCPP);
		model.clear();
		return "redirect:/processoCPOCPP" + origem + "/" + parametroPesquisado;
	}

	@RequestMapping(value = "/processoCPOCPP/{origem}/{parametroPesquisado}", params = { "addHistorico" })
	public ModelAndView addEditedHistorico(@PathVariable String origem,
			@PathVariable String parametroPesquisado, final ProcessoCPOCPP processoCPOCPP,
			final BindingResult bindingResult) {
		processoCPOCPP.getHistoricos().add(new Historico());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("processoCPOCPP/processoCPOCPPEdicao");
		mav.addObject("origem", origem);
		mav.addObject("processoCPOCPP", processoCPOCPP);
		mav.addObject("parametroPesquisado", parametroPesquisado);
		return mav;
	}

	@RequestMapping(value = "/processoCPOCPP/{origem}/{parametroPesquisado}", params = { "removeHistorico" })
	public ModelAndView removeEditedDocumentoRelacionado(@PathVariable String origem,
			@PathVariable String parametroPesquisado, final ProcessoCPOCPP processoCPOCPP,
			final BindingResult bindingResult,
			@RequestParam(value = "removeHistorico", required = false) Integer idHistorico) {
		processoCPOCPP.getHistoricos().remove(idHistorico.intValue());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("processoCPOCPP/processoCPOCPPEdicao");
		mav.addObject("origem", origem);
		mav.addObject("processoCPOCPP", processoCPOCPP);
		mav.addObject("parametroPesquisado", parametroPesquisado);
		return mav;
	}

	@RequestMapping(value = "/processoCPOCPP/{origem}/{parametroPesquisado}", params = { "addInteressado" })
	public ModelAndView addEditedInteressado(@PathVariable String origem, @PathVariable String parametroPesquisado,
			final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult) {
		processoCPOCPP.getInteressados().add(new Interessado());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("processoCPOCPP/processoCPOCPPEdicao");
		mav.addObject("origem", origem);
		mav.addObject("processoCPOCPP", processoCPOCPP);
		mav.addObject("parametroPesquisado", parametroPesquisado);
		return mav;
	}

	@RequestMapping(value = "/processoCPOCPP/{origem}/{parametroPesquisado}", params = { "removeInteressado" })
	public ModelAndView removeEditedInteressado(@PathVariable String origem, @PathVariable String parametroPesquisado,
			final ProcessoCPOCPP processoCPOCPP, final BindingResult bindingResult,
			@RequestParam(value = "removeInteressado", required = false) Integer idInteressado) {
		processoCPOCPP.getInteressados().remove(idInteressado.intValue());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("processoCPOCPP/processoCPOCPPEdicao");
		mav.addObject("origem", origem);
		mav.addObject("processoCPOCPP", processoCPOCPP);
		mav.addObject("parametroPesquisado", parametroPesquisado);
		return mav;
	}

	// busca por nome

	@RequestMapping(value = "/processoCPOCPPPorNomeInteressado/remover/{id}/{parametroPesquisado}")
	public String removeProcessoCPOCPPPorNomeInteressado(@PathVariable Long id,
			@PathVariable String parametroPesquisado) {
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPPPorNomeInteressado/" + parametroPesquisado;
	}

	@RequestMapping("/processoCPOCPPPorNomeInteressado")
	public String showProcessoCPOCPPPorNomeInteressado(
			@RequestParam(value = "nomeInteressado", required = false) String nomeInteressado, final ModelMap model) {
		model.addAttribute("listaProcessosPorNomeInteressado",
				this.processoCPOCPPService.findByNomeInteressado(nomeInteressado));
		model.addAttribute("parametroPesquisado", nomeInteressado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorNomeInteressado";
	}

	@RequestMapping("/processoCPOCPPPorNomeInteressado/{parametroPesquisado}")
	public String showProcessoCPOCPPPorNomeInteressadoGet(@PathVariable String parametroPesquisado,
			final ModelMap model) {
		model.addAttribute("listaProcessosPorNomeInteressado",
				this.processoCPOCPPService.findByNomeInteressado(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorNomeInteressado";
	}

	// busca por re

	@RequestMapping(value = "/processoCPOCPPPorReInteressado/remover/{id}/{parametroPesquisado}")
	public String removeProcessoCPOCPPPorReInteressado(@PathVariable Long id, @PathVariable Long parametroPesquisado) {
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPPPorReInteressado/" + parametroPesquisado;
	}

	@RequestMapping("/processoCPOCPPPorReInteressado")
	public String showProcessoCPOCPPPorReInteressado(
			@RequestParam(value = "parametroPesquisado", required = false) Long parametroPesquisado,
			final ModelMap model) {
		model.addAttribute("listaProcessosPorReInteressado",
				this.processoCPOCPPService.findByReInteressado(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorReInteressado";
	}

	@RequestMapping("/processoCPOCPPPorReInteressado/{parametroPesquisado}")
	public String showProcessoCPOCPPPorReInteressadoGet(@PathVariable Long parametroPesquisado, final ModelMap model) {
		model.addAttribute("listaProcessosPorReInteressado",
				this.processoCPOCPPService.findByReInteressado(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorReInteressado";
	}

	// busca por status

	@RequestMapping(value = "/processoCPOCPPPorStatus/remover/{id}/{parametroPesquisado}")
	public String removeProcessoCPOCPPPorStatus(@PathVariable Long id, @PathVariable Long parametroPesquisado) {
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPPPorStatus/" + parametroPesquisado;
	}

	@RequestMapping("/processoCPOCPPPorStatus")
	public String showProcessoCPOCPPPorStatus(
			@RequestParam(value = "parametroPesquisado", required = false) Long parametroPesquisado,
			final ModelMap model) {
		model.addAttribute("listaProcessosPorStatus", this.processoCPOCPPService.findByIdStatus(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorStatus";
	}

	@RequestMapping("/processoCPOCPPPorStatus/{parametroPesquisado}")
	public String showProcessoCPOCPPPorStatusGet(@PathVariable Long parametroPesquisado, final ModelMap model) {
		model.addAttribute("listaProcessosPorStatus", this.processoCPOCPPService.findByIdStatus(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorStatus";
	}

	// busca por IdSecCom

	@RequestMapping(value = "/processoCPOCPPPorIdSecCom/remover/{id}/{parametroPesquisado}")
	public String removeProcessoCPOCPPPorIdSecCom(@PathVariable Long id, @PathVariable Long parametroPesquisado) {
		this.processoCPOCPPService.remove(id);
		return "redirect:/processoCPOCPPPorIdSecCom/" + parametroPesquisado;
	}

	@RequestMapping("/processoCPOCPPPorIdSecCom")
	public String showProcessoCPOCPPPorIdSecCom(
			@RequestParam(value = "parametroPesquisado", required = false) Long parametroPesquisado,
			final ModelMap model) {
		model.addAttribute("listaProcessosPorIdSecCom", this.processoCPOCPPService.findByIdSecCom(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorIdSecCom";
	}

	@RequestMapping("/processoCPOCPPPorIdSecCom/{parametroPesquisado}")
	public String showProcessoCPOCPPPorIdSecComGet(@PathVariable Long parametroPesquisado, final ModelMap model) {
		model.addAttribute("listaProcessosPorIdSecCom", this.processoCPOCPPService.findByIdSecCom(parametroPesquisado));
		model.addAttribute("parametroPesquisado", parametroPesquisado);
		System.out.println(model.toString());
		return "processoCPOCPP/processoCPOCPPPorIdSecCom";
	}

}
