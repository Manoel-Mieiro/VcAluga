package br.com.cefet.controller;

<<<<<<< HEAD
=======

>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
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

import br.com.cefet.dto.requisicaoFilial;
<<<<<<< HEAD
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
=======
import br.com.cefet.model.Filial;
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
import br.com.cefet.repository.FilialRepository;
import jakarta.validation.Valid;

@Controller
public class FilialController {

	@Autowired
	private FilialRepository filialRepository;

	@GetMapping("/filiais")
	public ModelAndView index() {

		List<Filial> filiais = this.filialRepository.findAll();

		ModelAndView mv = new ModelAndView("filiais/index");
		mv.addObject("filiais", filiais);

		return mv;
	}

<<<<<<< HEAD
	@GetMapping("/filiais/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("filiais/new");

		return mv;

	}

=======
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
	@ModelAttribute(value = "requisicaoFilial")
	public requisicaoFilial getRequisicaoFilial() {
		return new requisicaoFilial();
	}
<<<<<<< HEAD

	@PostMapping("/filiais")
	public ModelAndView create(@Valid requisicaoFilial requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			ModelAndView mv = new ModelAndView("/filiais/new");
			return mv;
		} else {
=======
	
	@PostMapping("/filiais")
	public ModelAndView create(@Valid requisicaoFilial requisicao, BindingResult result) {
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
			Filial filial = new Filial();
			filial = requisicao.toFilial();

			// Create do CRUD
			this.filialRepository.save(filial);
			System.out.println("ID da nova filial: " + filial.getId());
			return new ModelAndView("redirect:/filiais/" + filial.getId());
<<<<<<< HEAD
		}
=======
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
	}

	@GetMapping("/filiais/{id}")
	public ModelAndView show(@PathVariable Integer id) {

		Optional<Filial> optional = this.filialRepository.findById(id);

		if (optional.isPresent()) {
			Filial filial = optional.get();

			ModelAndView mv = new ModelAndView("filiais/show");
			mv.addObject("filial", filial);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/filiais");
		}
	}

	@GetMapping("/filiais/{id}/edit")
<<<<<<< HEAD
	public ModelAndView edit(@PathVariable Integer id, requisicaoFilial requisicao) {
		Optional<Filial> optional = this.filialRepository.findById(id);

=======
	public ModelAndView edit(@PathVariable Integer id, requisicaoFilial requisicao){
		Optional<Filial> optional = this.filialRepository.findById(id);
		
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
		if (optional.isPresent()) {
			System.out.printf("%d", id);
			Filial filial = optional.get();
			requisicao.fromFilial(filial);
<<<<<<< HEAD
			ModelAndView mv = new ModelAndView("filiais/edit");
=======
			ModelAndView mv = new ModelAndView("fiiais/edit");
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/filiais");
		}
	}

	@PostMapping("/filiais/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoFilial requisicao, BindingResult result) {
<<<<<<< HEAD
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			ModelAndView mv = new ModelAndView("filiais/edit");
			return mv;
		} else {
=======
>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1
			Optional<Filial> optional = this.filialRepository.findById(id);
			if (optional.isPresent()) {
				Filial filial = requisicao.toFilial(optional.get());
				this.filialRepository.save(filial);
				return new ModelAndView("redirect:/filiais/" + filial.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/filiais");
			}
		}
<<<<<<< HEAD
	}
=======

>>>>>>> e5f9c5b1073de33d9fcd66d82108bb24fb766bf1

	@GetMapping("/filiais/{id}/delete")
	public String delete(@PathVariable Integer id) {
		try {
			this.filialRepository.deleteById(id);
			return "redirect:/filiais";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/filiais";
		}
	}
}
