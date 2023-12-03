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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoEstacao;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Status;
import br.com.cefet.repository.EstacaoRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EstacaoController {

	@Autowired
	private EstacaoRepository estacaoRepository;

	
	@Autowired
	private SessaoService sessaoService;
	
	@GetMapping("/estacoes")
	public ModelAndView index() {

		List<Estacao> estacoes = this.estacaoRepository.findAll();
		
		ModelAndView mv = new ModelAndView("estacoes/index");
		mv.addObject("estacoes", estacoes);

		return mv;
	}

	@GetMapping("/estacoes/new")
	public ModelAndView novo() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		if (funcionario == null) {
			System.out.println("Acesso negado!");
			return new ModelAndView("redirect:/estacoes");
		}
		ModelAndView mv = new ModelAndView("estacoes/new");
		mv.addObject("status", Status.values());

		return mv;

	}

	@ModelAttribute(value = "requisicaoEstacao")
	public requisicaoEstacao getRequisicaoEstacao() {
		return new requisicaoEstacao();
	}
	
	@PostMapping("/estacoes")
	public ModelAndView create(@Valid requisicaoEstacao requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("/estacoes/new");
			mv.addObject("status", Status.values());
			return mv;
		} else {
			Estacao estacao = new Estacao();
			estacao = requisicao.toEstacao();

			// Create do CRUD
			this.estacaoRepository.save(estacao);
			System.out.println("ID da nova estação: " + estacao.getId());
			return new ModelAndView("redirect:/estacoes/" + estacao.getId());
		}
	}

	@GetMapping("/estacoes/{id}")
	public ModelAndView show(@PathVariable Integer id) {

		Optional<Estacao> optional = this.estacaoRepository.findById(id);

		if (optional.isPresent()) {
			Estacao estacao = optional.get();

			ModelAndView mv = new ModelAndView("estacoes/show");
			mv.addObject("estacao", estacao);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/estacoes");
		}
	}

	@GetMapping("/estacoes/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, requisicaoEstacao requisicao) {
		Optional<Estacao> optional = this.estacaoRepository.findById(id);

		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		if (funcionario == null) {
			System.out.println("Acesso negado!");
			return new ModelAndView("redirect:/estacoes");
		}
		
		if (optional.isPresent()) {
			System.out.printf("%d", id);
			Estacao estacao = optional.get();
			requisicao.fromEstacao(estacao);
			ModelAndView mv = new ModelAndView("estacoes/edit");
			mv.addObject("status", Status.values());
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/estacoes");
		}
	}

	@PostMapping("/estacoes/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoEstacao requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("estacoes/edit");

			mv.addObject("status", Status.values());
			return mv;
		} else {
			Optional<Estacao> optional = this.estacaoRepository.findById(id);
			if (optional.isPresent()) {
				Estacao estacao = requisicao.toEstacao(optional.get());
				this.estacaoRepository.save(estacao);
				return new ModelAndView("redirect:/estacoes/" + estacao.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/estacoes");
			}
		}
	}

	@GetMapping("/estacoes/{id}/delete")
	public String delete(@PathVariable Integer id) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		if (funcionario == null) {
			System.out.println("Acesso negado!");
			return "redirect:/estacoes";
		}
		try {
			this.estacaoRepository.deleteById(id);
			return "redirect:/estacoes";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/estacoes";
		}
	}
}
