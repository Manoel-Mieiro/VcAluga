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

	private String nome;

	private String sobrenome;

	private String email;

	private String tipo;

	private String senha;

	private String telefone;

	private String cpf;

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
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView("clientes/new");
		return mv;

	}

	@ModelAttribute(value = "requisicaoCliente")
	public requisicaoCliente getRequisicaoCliente() {
		return new requisicaoCliente();
	}

	@PostMapping("/clientes")
	public ModelAndView create(@Valid requisicaoCliente requisicao, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("/clientes/new");
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

			Cliente cliente = new Cliente();
			Usuario usuario = requisicao.getRu().toUsuario();
			System.out.println("USER - " + usuario.getNome() + " " + usuario.getSobrenome());
			cliente.setNome(usuario.getNome());
			cliente.setSobrenome(usuario.getSobrenome());
			cliente.setEmail(usuario.getEmail());
			cliente.setTipo(usuario.getTipo());
			cliente.setSenha(usuario.getSenha());
			cliente.setTelefone(usuario.getTelefone());
			cliente.setCpf(usuario.getCpf());
		    cliente = requisicao.toCliente(cliente);

			this.clienteRepository.save(cliente);

//			Integer idUsuario = usuario.getId() - 1;
//			UsuarioController uc = new UsuarioController();
//			uc.delete(idUsuario);
			System.out.println("ID do novo cliente: " + cliente.getId());

			mv = new ModelAndView("redirect:/clientes/" + cliente.getId());
			return mv;
		}

	}

	@GetMapping("/clientes/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			// Agora que você tem o usuário, obtenha o ID do usuário
			Integer idUsuario = usuario.getId();

			// Use o ID do usuário para buscar o cliente
			Optional<Cliente> optionalCliente = clienteRepository.findById(idUsuario);

			if (optionalCliente.isPresent()) {
				Cliente cliente = optionalCliente.get();

				ModelAndView mv = new ModelAndView("clientes/show");
				mv.addObject("cliente", cliente);
				return mv;
			} else {
				System.out.println("Cliente não encontrado para o usuário com ID: " + idUsuario);
				return new ModelAndView("redirect:/usuarios");
			}
		} else {
			System.out.println("Usuário não encontrado");
			return new ModelAndView("redirect:/usuarios");
		}
	}

	@GetMapping("/clientes/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, requisicaoCliente requisicao, requisicaoUsuario ru) {
		Optional<Cliente> optional = this.clienteRepository.findById(id);
		Optional<Usuario> optionalU = this.usuarioRepository.findById(id);
		
		if (optional.isPresent()) {
			System.out.printf("%d%n", id);
			Cliente cliente = optional.get();
			Usuario usuario = optionalU.get();
			System.out.println("USER - " + usuario);
			requisicao.fromCliente(cliente);
			ru.fromUsuario(usuario);
			ModelAndView mv = new ModelAndView("clientes/edit");
//			mv.addObject("usuario", usuario);
			System.out.println("NOME - " + cliente.getNome());
			
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/usuarios");
		}
	}

	@PostMapping("/clientes/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoCliente requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("\n**********************Invalid Input Found**************************\n");

			ModelAndView mv = new ModelAndView("clientes/edit");

			return mv;
		} else {
			Optional<Cliente> optional = this.clienteRepository.findById(id);
			if (optional.isPresent()) {
				Cliente cliente = requisicao.toCliente(optional.get());
				this.clienteRepository.save(cliente);
				return new ModelAndView("redirect:/clientes/" + cliente.getId());
			} else {
				System.out.println("Registro não consta no banco ou não foi encontrado.");
				return new ModelAndView("redirect:/usuarios");
			}
		}
	}

	@GetMapping("/clientes/{id}/delete")
	public String delete(@PathVariable Integer id) {
		try {
			this.clienteRepository.deleteById(id);
			return "redirect:/clientes";
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/clientes";
		}
	}
}
