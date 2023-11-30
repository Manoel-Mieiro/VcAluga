package br.com.cefet.controller;


import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoNF;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.NF;
import br.com.cefet.repository.ContratoRepository;
import br.com.cefet.repository.NFRepository;
import jakarta.validation.Valid;

@Controller
public class NFController {

	@Autowired
	private NFRepository nfRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	@GetMapping("/nfs")
	public ModelAndView index() {

		List<NF> nfs = this.nfRepository.findAll();
		System.out.println("LIsta de NFS: " + nfs);
		ModelAndView mv = new ModelAndView("nfs/index");
		mv.addObject("nfs", nfs);

		return mv;
	}

	@GetMapping("/nfs/{contratoId}/new")
	public ModelAndView novo(@PathVariable ("contratoId") int contratoId) {
		ModelAndView mv = new ModelAndView("nfs/new");

		// Carregue o Contrato com base no ID
		 Optional<Contrato> contratoOptional = contratoRepository.findById(contratoId);
		 
		    if (contratoOptional.isPresent()) {
		        Contrato contrato = contratoOptional.get();
		        NF nf = new NF();
		        nf.setContrato(contrato);
		        
		        Date inicio = contrato.getReserva().getDataReserva();
		        Date fim = contrato.getReserva().getDataDevolucao();
		        System.out.println("Data Incio - " + inicio);
		        System.out.println("Data Fim - " + fim);
		        Date today = new Date();
		        System.out.println("Today's - " + today);
		        nf.setValorSemImposto(nf.calcularValorSemImposto(inicio, fim));
		        nf.setValorDoImposto(nf.calcularValorImposto(inicio, fim));
		        nf.setValorTotal(nf.calcularTotal(inicio, fim));
		        nf.setDataEmissao(today);
		        nf.setNumeroNF(nf.gerarNumeroNF());
		        this.nfRepository.save(nf);
		        
		        System.out.println("Número da NF gerado: " + nf.getNumeroNF());
		        mv.addObject("nf", nf);
			return mv;
		} else {
			System.out.println("Acesso negado!");
			return new ModelAndView("redirect:/veiculos");
		}

	}

	@ModelAttribute(value = "requisicaoNF")
	public requisicaoNF getRequisicaoNF() {
		return new requisicaoNF();
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
