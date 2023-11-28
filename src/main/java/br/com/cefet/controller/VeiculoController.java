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

import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Filial;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.FilialRepository;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.Valid;

@Controller
public class VeiculoController {

//	Construção do objeto da interface VeiculoRepository, de forma a usar seus metodos de CRUD
	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private FilialRepository filialRepository;

	@GetMapping("/veiculos")
	public ModelAndView index() {
//		List<Veiculo> veiculos = this.veiculoRepository.findByStatus("Disponível");
		List<Veiculo> veiculos = this.veiculoRepository.findByStatusAndEmManutencao("Disponível", false);
		ModelAndView mv = new ModelAndView("veiculos/index");
		mv.addObject("veiculos", veiculos);

		return mv;
	}

	@GetMapping("/veiculos/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("veiculos/new");
		List<Filial> filiais = filialRepository.findAll();
		mv.addObject("filiais", filiais);
		mv.addObject("marcaVeiculo", Marca.values());
		mv.addObject("categoria", Categoria.values());
		mv.addObject("cor", Paletas.values());

		return mv;

	}

	@ModelAttribute(value = "requisicaoVeiculo")
	public requisicaoVeiculo getRequisicaoVeiculo() {
		return new requisicaoVeiculo();
	}

	@PostMapping("/veiculos")
	public ModelAndView create(@ModelAttribute requisicaoVeiculo requisicao, BindingResult result) {
		ModelAndView mv = new ModelAndView("/veiculos/new");

		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			List<Filial> filiais = filialRepository.findAll();
			mv.addObject("filiais", filiais);
			mv.addObject("marcaVeiculo", Marca.values());
			mv.addObject("categoria", Categoria.values());
			mv.addObject("cor", Paletas.values());
			return mv;
		} else {
			Filial filial = filialRepository.findById(requisicao.getBranchId()).orElse(null);
				System.out.printf("ID FILIAL - %d%n", requisicao.getBranchId());
				Veiculo veiculo = new Veiculo();
				veiculo.setBranch(filial);
				veiculo = requisicao.toVeiculo(veiculo);
				veiculo.setStatus("Disponível");
				veiculo.setEmManutencao(false);

				this.veiculoRepository.save(veiculo);
				System.out.println("ID do veículo: " + veiculo.getId());

				return new ModelAndView("redirect:/veiculos/" + veiculo.getId());
		}
	}

	@GetMapping("/veiculos/{id}")
	public ModelAndView show(@PathVariable Integer id) {

		Optional<Veiculo> optional = this.veiculoRepository.findById(id);

		if (optional.isPresent()) {
			Veiculo veiculo = optional.get();

			ModelAndView mv = new ModelAndView("veiculos/show");
			mv.addObject("veiculo", veiculo);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}

	@GetMapping("/veiculos/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, requisicaoVeiculo requisicao) {
		Optional<Veiculo> optional = this.veiculoRepository.findById(id);

		if (optional.isPresent()) {
			System.out.printf("%d", id);
			Veiculo veiculo = optional.get();
			requisicao.fromVeiculo(veiculo);
			ModelAndView mv = new ModelAndView("veiculos/edit");
			List<Filial> filiais = filialRepository.findAll(); // Substitua filialRepository pelo nome correto do seu
																// repositório
			mv.addObject("filiais", filiais);
			mv.addObject("marcaVeiculo", Marca.values());
			mv.addObject("categoria", Categoria.values());
			mv.addObject("cor", Paletas.values());
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}

	@PostMapping("/veiculos/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoVeiculo requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("veiculos/edit");
			mv.addObject("marcaVeiculo", Marca.values());
			mv.addObject("categoria", Categoria.values());
			mv.addObject("cor", Paletas.values());
			List<Filial> filiais = filialRepository.findAll(); 
			mv.addObject("filiais", filiais);
			System.out.println("if");
			return mv;
		} else {
			System.out.println("else");
			Optional<Veiculo> optional = this.veiculoRepository.findById(id);
			if (optional.isPresent()) {
				Veiculo veiculo = requisicao.toVeiculo(optional.get());
				this.veiculoRepository.save(veiculo);
				return new ModelAndView("redirect:/veiculos/" + veiculo.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/veiculos");
			}
		}
	}

	@GetMapping("/veiculos/{id}/delete")
	public String delete(@PathVariable Integer id) {
		try {
			this.veiculoRepository.deleteById(id);
			return "redirect:/veiculos";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/veiculos";
		}
	}
}
