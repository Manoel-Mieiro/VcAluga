package br.com.cefet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoMotorista;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Motorista;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.MotoristaRepository;
import br.com.cefet.service.AuthService;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MotoristaController {

	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private SessaoService sessaoService;

	@GetMapping("/motoristas")
	public ModelAndView index() {

		List<Motorista> motoristas = this.motoristaRepository.findAll();

		ModelAndView mv = new ModelAndView("motoristas/index");
		mv.addObject("motoristas", motoristas);

		return mv;
	}

	@GetMapping("/motoristas/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("motoristas/new");
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		
		if (cliente == null) {
			System.out.println("Cliente nulo!");
			return new ModelAndView("redirect:/sessoes");
		}
		
		mv.addObject("cliente", cliente);
		
		return mv;

	}

	@ModelAttribute(value = "requisicaoMotorista")
	public requisicaoMotorista getRequisicaoMotorista() {
		return new requisicaoMotorista();
	}

//	@PostMapping("/motoristas")
//	public ModelAndView create(@Valid requisicaoMotorista requisicao, BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println("\n**********************Invalid Input Found**************************\n");
//			ModelAndView mv = new ModelAndView("/motoristas/new");
//			return mv;
//		} else {
//			Motorista motorista = new Motorista();
//			motorista = requisicao.toMotorista();
//
//			// Create do CRUD
//			this.motoristaRepository.save(motorista);
//			System.out.println("ID do novo motorista: " + motorista.getIdMotorista());
//			return new ModelAndView("redirect:/motoristas/" + motorista.getIdMotorista());
//		}
//	}

	@PostMapping("/motoristas")
	public ModelAndView create(@Valid requisicaoMotorista requisicao, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("/motoristas/new");
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		System.out.println("Cliente logado: " + cliente.getNome() + " " + cliente.getSobrenome());
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			// Percorre os erros de campo (field errors)
						for (FieldError error : result.getFieldErrors()) {
							System.out.println("Field: " + error.getField());
							System.out.println("Message: " + error.getDefaultMessage());

							// Adicione a mensagem de erro ao RedirectAttributes se necessário
							redirectAttributes.addFlashAttribute("error", error.getDefaultMessage());
						}

						// Percorre os erros globais
						for (ObjectError error : result.getGlobalErrors()) {
							System.out.println("Object: " + error.getObjectName());
							System.out.println("Message: " + error.getDefaultMessage());

							// Adicione a mensagem de erro ao RedirectAttributes se necessário
							redirectAttributes.addFlashAttribute("error", error.getDefaultMessage());
						}
			return mv;
		} else {
				Motorista motorista = requisicao.toMotorista();
				motorista.setCliente(cliente);
				motorista = requisicao.toMotorista(motorista);

				this.clienteRepository.save(cliente);
				this.motoristaRepository.save(motorista);

				System.out.println("ID do novo motorista: " + motorista.getIdMotorista());
				return new ModelAndView("redirect:/motoristas/" + motorista.getIdMotorista());
		}

	}

	@GetMapping("/motoristas/{idMotorista}")
	public ModelAndView show(@PathVariable Integer idMotorista) {

		Optional<Motorista> optional = this.motoristaRepository.findById(idMotorista);

		if (optional.isPresent()) {
			Motorista motorista = optional.get();

			ModelAndView mv = new ModelAndView("motoristas/show");
			mv.addObject("motorista", motorista);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/motoristas");
		}
	}

	@GetMapping("/motoristas/{idMotorista}/edit")
	public ModelAndView edit(@PathVariable Integer idMotorista, requisicaoMotorista requisicao) {
		Optional<Motorista> optional = this.motoristaRepository.findById(idMotorista);

		if (optional.isPresent()) {
			System.out.printf("%d", idMotorista);
			Motorista motorista = optional.get();
			requisicao.fromMotorista(motorista);
			ModelAndView mv = new ModelAndView("motoristas/edit");
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/motoristas");
		}
	}

	@PostMapping("/motoristas/{idMotorista}")
	public ModelAndView update(@PathVariable Integer idMotorista, @Valid requisicaoMotorista requisicao,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			ModelAndView mv = new ModelAndView("motoristas/edit");
			return mv;
		} else {
			Optional<Motorista> optional = this.motoristaRepository.findById(idMotorista);
			if (optional.isPresent()) {
				Motorista motorista = requisicao.toMotorista(optional.get());
				this.motoristaRepository.save(motorista);
				return new ModelAndView("redirect:/motoristas/" + motorista.getIdMotorista());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/motoristas");
			}
		}
	}

	@GetMapping("/motoristas/{idMotorista}/delete")
	public String delete(@PathVariable Integer idMotorista) {
		try {
			this.motoristaRepository.deleteById(idMotorista);
			return "redirect:/motoristas";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/motoristas";
		}
	}
}