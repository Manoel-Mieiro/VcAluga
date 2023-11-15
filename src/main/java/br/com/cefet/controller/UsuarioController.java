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

import br.com.cefet.dto.requisicaoUsuario;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Usuario;
import br.com.cefet.repository.FuncionarioRepository;
import br.com.cefet.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;


	@GetMapping("/usuarios")
	public ModelAndView index() {

		List<Usuario> usuarios = this.usuarioRepository.findAll();

		ModelAndView mv = new ModelAndView("usuarios/index");
		mv.addObject("usuarios", usuarios);

		return mv;
	}

	@GetMapping("/usuarios/new")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("usuarios/new");
		mv.addObject("tipo", Conta.values());

		return mv;

	}

	@ModelAttribute(value = "requisicaoUsuario")
	public requisicaoUsuario getRequisicaoUsuario() {
		return new requisicaoUsuario();
	}
	
	@PostMapping("/usuarios")
	public ModelAndView create(@Valid requisicaoUsuario requisicao, BindingResult result) {
	    if (result.hasErrors()) {
	        System.out.println("\n**********************Invalid Input Found**************************\n");
	        ModelAndView mv = new ModelAndView("/usuarios/new");
	        mv.addObject("tipo", Conta.values());
	        return mv;
	    } else {
	        // Verifica se o tipo é Funcionario
	        if (requisicao.getTipo() == Conta.Funcionario) {
	            // Se for Funcionario, cria um Funcionario e preenche seus atributos específicos
	            Funcionario funcionario = new Funcionario();
	            funcionario = requisicao.toFuncionario(); // Método para preencher dados comuns de Usuario
	            funcionario.setMatricula(requisicao.getMatricula());
	            funcionario.setCargo(requisicao.getCargo());
	            funcionario.setFilial(requisicao.getFilial());

	            this.funcionarioRepository.save(funcionario); 

	            System.out.println("ID do novo funcionário: " + funcionario.getId());
	            return new ModelAndView("redirect:/usuarios/" + funcionario.getId());
	        } else {
	            Usuario usuario = new Usuario();
	            usuario = requisicao.toUsuario(); 

	            this.usuarioRepository.save(usuario);

	            System.out.println("ID do novo usuário: " + usuario.getId());
	            return new ModelAndView("redirect:/usuarios/" + usuario.getId());
	        }
	    }
	}


	@GetMapping("/usuarios/{id}")
	public ModelAndView show(@PathVariable Integer id) {

		Optional<Usuario> optional = this.usuarioRepository.findById(id);

		if (optional.isPresent()) {
			Usuario usuario = optional.get();

			ModelAndView mv = new ModelAndView("usuarios/show");
			mv.addObject("usuario", usuario);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/usuarios");
		}
	}

	@GetMapping("/usuarios/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, requisicaoUsuario requisicao) {
		Optional<Usuario> optional = this.usuarioRepository.findById(id);

		if (optional.isPresent()) {
			System.out.printf("%d", id);
			Usuario usuario = optional.get();
			requisicao.fromUsuario(usuario);
			ModelAndView mv = new ModelAndView("usuarios/edit");
			mv.addObject("tipo", Conta.values());
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/usuarios");
		}
	}

	@PostMapping("/usuarios/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoUsuario requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("usuarios/edit");

			mv.addObject("tipo", Conta.values());
			return mv;
		} else {
			Optional<Usuario> optional = this.usuarioRepository.findById(id);
			if (optional.isPresent()) {
				Usuario usuario = requisicao.toUsuario(optional.get());
				this.usuarioRepository.save(usuario);
				return new ModelAndView("redirect:/usuarios/" + usuario.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/usuarios");
			}
		}
	}

	@GetMapping("/usuarios/{id}/delete")
	public String delete(@PathVariable Integer id) {
		try {
			this.usuarioRepository.deleteById(id);
			return "redirect:/usuarios";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/usuarios";
		}
	}
}
