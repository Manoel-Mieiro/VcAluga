package br.com.cefet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoNF;
import br.com.cefet.model.NF;
import br.com.cefet.repository.NFRepository;
import jakarta.validation.Valid;

@Controller
public class NFController {

	@Autowired
	private NFRepository nfRepository;

	@GetMapping("/nfs")
	public ModelAndView index() {

		List<NF> nfs = this.nfRepository.findAll();
		System.out.println("LIsta de NFS: " + nfs);
		ModelAndView mv = new ModelAndView("nfs/index");
		mv.addObject("nfs", nfs);

		return mv;
	}

	@GetMapping("/nfs/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("nfs/new");

		return mv;

	}

	@ModelAttribute(value = "requisicaoNF")
	public requisicaoNF getRequisicaoNF() {
		return new requisicaoNF();
	}

	@PostMapping("/nfs")
	public ModelAndView create(@Valid requisicaoNF requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			ModelAndView mv = new ModelAndView("/nfs/new");
			return mv;
		} else {
			NF nf = new NF();
			nf = requisicao.toNF();

			// Create do CRUD
			this.nfRepository.save(nf);
			System.out.println("ID da nova Nota Fiscal: " + nf.getIdNF());
			return new ModelAndView("redirect:/nfs/" + nf.getIdNF());
		}
	}

	@GetMapping("/nfs/{idNF}")
	public ModelAndView show(@PathVariable Integer idNF) {

		Optional<NF> optional = this.nfRepository.findById(idNF);

		if (optional.isPresent()) {
			NF nf = optional.get();

			ModelAndView mv = new ModelAndView("nfs/show");
			mv.addObject("nf", nf);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/nf");
		}
	}


	@GetMapping("/nfs/{idNF}/delete")
	public String delete(@PathVariable Integer idNF) {
		try {
			this.nfRepository.deleteById(idNF);
			return "redirect:/nfs";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/nfs";
		}
	}
}
