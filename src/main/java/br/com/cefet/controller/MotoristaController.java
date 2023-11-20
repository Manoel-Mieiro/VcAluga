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


import br.com.cefet.dto.requisicaoMotorista;
import br.com.cefet.model.Motorista;
import br.com.cefet.repository.MotoristaRepository;



import jakarta.validation.Valid;

@Controller
public class MotoristaController {


	@Autowired
	private MotoristaRepository motoristaRepository;

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

		return mv;

	}

	@ModelAttribute(value = "requisicaoMotorista")
	public requisicaoMotorista getRequisicaoMotorista() {
		return new requisicaoMotorista();
	}

	@PostMapping("/motoristas")
	public ModelAndView create(@Valid requisicaoMotorista requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			ModelAndView mv = new ModelAndView("/motoristas/new");
			return mv;
		} else {
			Motorista motorista = new Motorista();
			motorista = requisicao.toMotorista();

			// Create do CRUD
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
	public ModelAndView update(@PathVariable Integer idMotorista, @Valid requisicaoMotorista requisicao, BindingResult result) {
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