package br.com.cefet.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.model.Categoria;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Veiculo;

@Controller
public class VeiculoController {
	@GetMapping("/veiculos")
	public ModelAndView index() {
		Veiculo fiatPulse = new Veiculo("NEO-2214", Marca.Fiat, "Pulse Drive", Categoria.Luxo, (float) 121.30, Paletas.Preto, 2024, "Matriz");
		fiatPulse.setId(1);
		Veiculo volkswagenID4 = new Veiculo("MYS-8045", Marca.Volkswagen, "ID.4", Categoria.Luxo, (float) 6.34, Paletas.Azul, 2022, "SP");
		volkswagenID4.setId(2);
		List<Veiculo> veiculos = Arrays.asList(fiatPulse, volkswagenID4);
		
		ModelAndView mv = new ModelAndView("veiculos/index");
		mv.addObject("veiculos", veiculos);
		
		return mv;
	}
}