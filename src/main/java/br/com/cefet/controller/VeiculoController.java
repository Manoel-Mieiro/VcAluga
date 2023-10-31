package br.com.cefet.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.VeiculoRepository;
import jakarta.validation.Valid;

@Controller
public class VeiculoController {
	
//	Construção do objeto da interface VeiculoRepository, de forma a usar seus metodos de CRUD
	@Autowired
	private VeiculoRepository veiculoRepository; 
	
	@GetMapping("/veiculos")
	public ModelAndView index() {
		/*
		 * Veiculo fiatPulse = new Veiculo("NEO-2214", Marca.Fiat, "Pulse Drive",
		 * Categoria.Luxo, (float) 121.30, Paletas.Preto, 2024, "Matriz");
		 * fiatPulse.setId(1); Veiculo volkswagenID4 = new Veiculo("MYS-8045",
		 * Marca.Volkswagen, "ID.4", Categoria.Luxo, (float) 6.34, Paletas.Azul, 2022,
		 * "SP"); volkswagenID4.setId(2); List<Veiculo> veiculos =
		 * Arrays.asList(fiatPulse, volkswagenID4);
		 */
		
//		A expressão abaixo é um read
		List<Veiculo> veiculos = this.veiculoRepository.findAll();
		
		ModelAndView mv = new ModelAndView("veiculos/index");
		mv.addObject("veiculos", veiculos);
		
		return mv;
	}
	
	@GetMapping("/veiculos/new")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView("veiculos/new");
		mv.addObject("marcaVeiculo", Marca.values());
		mv.addObject("categoria", Categoria.values());
		mv.addObject("cor", Paletas.values());
		
		return mv;
		
	}
	
	//O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação de dados thymeleaf
	@ModelAttribute(value="requisicaoVeiculo")
	public requisicaoVeiculo getRequisicaoVeiculo() {
		return new requisicaoVeiculo();
	}
	

	@PostMapping("/veiculos")
	/*
	 * @Valid é necessária para validar se os campos foram devidamente preenchidos
	 * conforme DTO.
	 * 
	 * Por isso, adiciona-se um novo parâmetro result do tipo BindingResult para tratar possíveis erros
	 */
	
	public ModelAndView create(@Valid requisicaoVeiculo requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");
			
			ModelAndView mv = new ModelAndView("/veiculos/new");
			//O bloco abaixo recarrega os ENUM do formulário em caso de erro
			mv.addObject("marcaVeiculo", Marca.values());
			mv.addObject("categoria", Categoria.values());
			mv.addObject("cor", Paletas.values());
			return mv;
		}
		else {
		Veiculo veiculo = new Veiculo();
		veiculo = requisicao.toVeiculo();
//		System.out.println();
//		System.out.println(requisicao);
//		System.out.println();
//		System.out.println();
//		System.out.println(veiculo);
//		System.out.println();
		
		//Create do CRUD
		this.veiculoRepository.save(veiculo);
		return new ModelAndView("redirect:/veiculos/" + veiculo.getId());
		}
	}
	
	@GetMapping("/veiculos/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		
		Optional<Veiculo> optional = this.veiculoRepository.findById(id);
		
		if(optional.isPresent()) {
			Veiculo veiculo = optional.get();
			
			ModelAndView mv = new ModelAndView("veiculos/show");
			mv.addObject("veiculo", veiculo);
			return mv;
		}else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
		
		
	} 
}
