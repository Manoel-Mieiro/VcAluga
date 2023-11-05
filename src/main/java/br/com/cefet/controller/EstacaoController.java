package br.com.cefet.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoEstacao;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Status;
import br.com.cefet.repository.EstacaoRepository;
import jakarta.validation.Valid;

@Controller
public class EstacaoController {

	@Autowired
	private EstacaoRepository estacaoRepository;

	@GetMapping("/estacao")
	public ModelAndView index() {

		List<Estacao> estacao = this.estacaoRepository.findAll();

		ModelAndView mv = new ModelAndView("estacao/index");
		mv.addObject("estacao", estacao);

		return mv;
	}

	@GetMapping("/estacao/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("estacao/new");
		mv.addObject("status", Status.values());

		return mv;

	}

	@ModelAttribute(value = "requisicaoEstacao")
	public requisicaoEstacao getRequisicaoEstacao() {
		return new requisicaoEstacao();
	}

	@PostMapping("/estacao")

	public ModelAndView create(@Valid requisicaoEstacao requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("/estacao/new");
			mv.addObject("status", Status.values());
			return mv;
		} else {
			Estacao estacao = new Estacao();
			estacao = requisicao.toEstacao();

			// Create do CRUD
			this.estacaoRepository.save(estacao);
			return new ModelAndView("redirect:/estacao/" + estacao.getId());
		}
	}

	@GetMapping("/estacao/{id}")
	public ModelAndView show(@PathVariable Integer id) {

		Optional<Estacao> optional = this.estacaoRepository.findById(id);

		if (optional.isPresent()) {
			Estacao estacao = optional.get();

			ModelAndView mv = new ModelAndView("estacao/show");
			mv.addObject("veiculo", estacao);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/estacao");
		}
	}

	@GetMapping("/estacao/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, requisicaoEstacao requisicao) {
		Optional<Estacao> optional = this.estacaoRepository.findById(id);

		if (optional.isPresent()) {
			System.out.printf("%d", id);
			Estacao estacao = optional.get();
			requisicao.fromEstacao(estacao);
			ModelAndView mv = new ModelAndView("edicao/edit");
			mv.addObject("status", Status.values());
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/estacao");
		}
	}

	@PostMapping("/estacao/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoEstacao requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("estacao/edit");

			mv.addObject("status", Status.values());
			return mv;
		} else {
			Optional<Estacao> optional = this.estacaoRepository.findById(id);
			if (optional.isPresent()) {
				Estacao estacao = requisicao.toEstacao(optional.get());
				this.estacaoRepository.save(estacao);
				return new ModelAndView("redirect:/estacao/" + estacao.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/estacao");
			}
		}
	}

	@GetMapping("/estacao/{id}/delete")
	public String delete(@PathVariable Integer id) {
		try {
			this.estacaoRepository.deleteById(id);
			return "redirect:/estacao";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/estacao";
		}
	}
}
