package br.com.cefet.controller;


import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoCliente;
import br.com.cefet.dto.requisicaoContrato;
import br.com.cefet.dto.requisicaoUsuario;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Usuario;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Controller
public class ClienteController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

//	@GetMapping("/usuarios/clientes")
//	public ModelAndView index() {
//	    List<Cliente> clientes = this.clienteRepository.findAll();
//
//	    ModelAndView mv = new ModelAndView("usuarios/index");
//	    mv.addObject("clientes", clientes);
//
//	    return mv;
//	}


	@GetMapping("/clientes/new")
	public ModelAndView novo(@RequestParam(name = "idUsuario") int idUsuario) {
	    Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
	    ModelAndView mv = new ModelAndView("clientes/new");

	    if (optionalUsuario.isPresent()) {
	    	Usuario usuario = optionalUsuario.get();
	        System.out.println("Usuario de ID: " + usuario.getId());
	        
//	        requisicaoCliente requisicao = new requisicaoCliente();
//	        requisicao.setUsuario(usuario);
	        
//	        mv.addObject("requisicaoCliente", requisicao);
	        mv.addObject("usuario", usuario);
	        System.out.println("Usuario: " + usuario);
	        return mv;
	    } else {
	        System.out.println("Usuario não encontrado.");
	        return new ModelAndView("redirect:/veiculos");
	    }
	}


	@ModelAttribute(value = "requisicaoCliente")
	public requisicaoCliente getRequisicaoCliente() {
		return new requisicaoCliente();
	}
	
	@PostMapping("/clientes")
	public ModelAndView create(@Valid requisicaoCliente requisicao, BindingResult result, RedirectAttributes redirectAttributes) {
		int usuarioId = requisicao.getUsuario().getId();
        System.out.println("USER ID = " + usuarioId);
	    if (result.hasErrors()) {
	        System.out.println("\n**********************Invalid Input Found**************************\n");

	        ModelAndView mv = new ModelAndView("/clientes/new");
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

//	    	int usuarioId = requisicao.getUsuario().getId();

	        // Obtenha o usuário do banco de dados com base no ID
	        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

	        if (optionalUsuario.isPresent()) {
	        	Usuario usuario = optionalUsuario.get();
	        	
	            // Crie um novo cliente e configure seus atributos
	            Cliente cliente = requisicao.toCliente();
	            cliente.setNome(usuario.getNome());
	            cliente.setSobrenome(usuario.getSobrenome());
	            cliente.setEmail(usuario.getEmail());
	            
	            

	            // Salve o cliente no banco de dados
	            this.clienteRepository.save(cliente);

	            System.out.println("ID do novo cliente: " + cliente.getId());

	            // Redirecione para a página do cliente recém-criado
	            return new ModelAndView("redirect:/clientes/" + cliente.getId());
	        } else {
	            // Usuário não encontrado, redirecione para uma página apropriada
	            return new ModelAndView("redirect:/veiculos"); // ou outra página adequada
	        }
	    }
	}


	@GetMapping("/clientes/{id}")
	public ModelAndView showCliente(@PathVariable Integer id) {
	    Optional<Cliente> optional = this.clienteRepository.findById(id);

	    if (optional.isPresent()) {
	        Cliente cliente = optional.get();

	        ModelAndView mv = new ModelAndView("clientes/show");
	        
	        mv.addObject("cliente", cliente);
	        return mv;
	    } else {
	        System.out.println("Registro de cliente não consta no banco ou não foi encontrado.");
	        return new ModelAndView("redirect:/usuarios");
	    }
	}

//	@GetMapping("/usuarios/{id}/edit")
//	public ModelAndView edit(@PathVariable Integer id, requisicaoUsuario requisicao) {
//		Optional<Usuario> optional = this.usuarioRepository.findById(id);
//
//		if (optional.isPresent()) {
//			System.out.printf("%d", id);
//			Usuario usuario = optional.get();
//			requisicao.fromUsuario(usuario);
//			ModelAndView mv = new ModelAndView("usuarios/edit");
//			mv.addObject("tipo", Conta.values());
//			return mv;
//		} else {
//			System.out.println("Registro não consta no banco ou não foi encontrado.");
//			return new ModelAndView("redirect:/usuarios");
//		}
//	}
//
//	@PostMapping("/usuarios/{id}")
//	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoUsuario requisicao, BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println("\n**********************Invalid Input Found**************************\n");
//
//			ModelAndView mv = new ModelAndView("usuarios/edit");
//
//			mv.addObject("tipo", Conta.values());
//			return mv;
//		} else {
//			Optional<Usuario> optional = this.usuarioRepository.findById(id);
//			if (optional.isPresent()) {
//				Usuario usuario = requisicao.toUsuario(optional.get());
//				this.usuarioRepository.save(usuario);
//				return new ModelAndView("redirect:/usuarios/" + usuario.getId());
//			} else {
//				System.out.println("Registro não consta no banco ou não foi encontrado.");
//				return new ModelAndView("redirect:/usuarios");
//			}
//		}
//	}
//
//	@GetMapping("/usuarios/{id}/delete")
//	public String delete(@PathVariable Integer id) {
//		try {
//			this.usuarioRepository.deleteById(id);
//			return "redirect:/usuarios";
//		} catch (EmptyResultDataAccessException e) {
//			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
//			return "redirect:/usuarios";
//		}
//	}
}
