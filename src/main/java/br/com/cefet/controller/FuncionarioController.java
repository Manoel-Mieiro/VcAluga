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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cefet.dto.requisicaoCliente;
import br.com.cefet.dto.requisicaoContrato;
import br.com.cefet.dto.requisicaoFuncionario;
import br.com.cefet.dto.requisicaoUsuario;
import br.com.cefet.model.Cargo;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Conta;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Reserva;
import br.com.cefet.model.Usuario;
import br.com.cefet.repository.ClienteRepository;
import br.com.cefet.repository.FuncionarioRepository;
import br.com.cefet.repository.UsuarioRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FuncionarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private SessaoService sessaoService;

//	@GetMapping("/usuarios/clientes")
//	public ModelAndView index() {
//	    List<Cliente> clientes = this.clienteRepository.findAll();
//
//	    ModelAndView mv = new ModelAndView("usuarios/index");
//	    mv.addObject("clientes", clientes);
//
//	    return mv;
//	}

	@GetMapping("/funcionarios/new")
	public ModelAndView novo() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Funcionario usuario = sessaoService.obterFuncionarioDaSessao(session);
		if (usuario == null) {
			System.out.println("Acesso negado!");
			return new ModelAndView("redirect:/sessoes");
		} else if (usuario.getCargo() != Cargo.Gerente) {
			System.out.println("Acesso restrito a gerentes");
			return new ModelAndView("redirect:/sessoes");
		} else {
			ModelAndView mv = new ModelAndView("funcionarios/new");
			mv.addObject("cargo", Cargo.values());
			return mv;
		}

	}
	
	
	@GetMapping("/funcionarios/profile/{sessionId}")
	public ModelAndView profile(@PathVariable String sessionId) {
	    // Obter a sessão
	    HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
	    System.out.println("Session ID de session - " + session);
	    
	    // Verificar se o sessionId corresponde ao da sessão atual
	    if (session.getId().equals(sessionId)) {
	        Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
	        System.out.println("Funcionario: " + funcionario);
	        if (funcionario != null) {
	            ModelAndView mv = new ModelAndView("funcionarios/profile");
	            mv.addObject("funcionario", funcionario);
	            return mv;
	        } else {
	            System.out.println("Funcionario: " + funcionario);
	            return new ModelAndView("redirect:/sessoes");
	        }
	    } else {
	        System.out.println("Sessão indevida");
	        return new ModelAndView("redirect:/sessoes");
	    }
	}


	@ModelAttribute(value = "requisicaoFuncionario")
	public requisicaoFuncionario getRequisicaoFuncionario() {
		return new requisicaoFuncionario();
	}

	@PostMapping("/funcionarios")
	public ModelAndView create(@Valid requisicaoFuncionario requisicao, BindingResult result,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("/funcionarios/new");
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
			mv.addObject("cargo", Cargo.values());
			return mv;
		} else {

			Funcionario funcionario = new Funcionario();
			Usuario usuario = requisicao.getRu().toUsuario();
			System.out.println("USER - " + usuario.getNome() + " " + usuario.getSobrenome());
			funcionario.setNome(usuario.getNome());
			funcionario.setSobrenome(usuario.getSobrenome());
			funcionario.setEmail(usuario.getEmail());
			funcionario.setTipo(Conta.Funcionário);
			funcionario.setSenha(usuario.getSenha());
			funcionario.setTelefone(usuario.getTelefone());
			funcionario.setCpf(usuario.getCpf());
			funcionario = requisicao.toFuncionario(funcionario);

			Optional<Funcionario> valida = funcionarioRepository.findByCpf(funcionario.getCpf());
			System.out.println(valida.isPresent());
		    if(valida.isPresent()) {
		    	System.out.println("Usuário já cadastrado! Reescrevendo formulário.");
		    	mv.addObject("funcionario", funcionario);
		    	mv.addObject("cargo", Cargo.values());
		    	return mv;
		    }
			
			this.funcionarioRepository.save(funcionario);

			
			
//			Integer idUsuario = usuario.getId() - 1;
//			UsuarioController uc = new UsuarioController();
//			uc.delete(idUsuario);
			System.out.println("ID do novo funcionario: " + funcionario.getId());

			mv = new ModelAndView("redirect:/funcionarios/" + funcionario.getId());
			return mv;
		}

	}

	@GetMapping("/funcionarios/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			// Agora que você tem o usuário, obtenha o ID do usuário
			Integer idUsuario = usuario.getId();

			// Use o ID do usuário para buscar o cliente
			Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(idUsuario);

			if (optionalFuncionario.isPresent()) {
				Funcionario funcionario = optionalFuncionario.get();

				ModelAndView mv = new ModelAndView("funcionarios/show");
				mv.addObject("funcionario", funcionario);
				System.out.println("Matrícula: " + funcionario.getmatricula());
				return mv;
			} else {
				System.out.println("Funcionário não encontrado para o usuário com ID: " + idUsuario);
				return new ModelAndView("redirect:/usuarios");
			}
		} else {
			System.out.println("Usuário não encontrado");
			return new ModelAndView("redirect:/usuarios");
		}
	}

//	@GetMapping("/clientes/{id}/edit")
//	public ModelAndView edit(@PathVariable Integer id, requisicaoCliente requisicao, requisicaoUsuario ru) {
//		Optional<Cliente> optional = this.clienteRepository.findById(id);
//		Optional<Usuario> optionalU = this.usuarioRepository.findById(id);
//		
//		if (optional.isPresent()) {
//			System.out.printf("%d%n", id);
//			Cliente cliente = optional.get();
//			Usuario usuario = optionalU.get();
//			System.out.println("USER - " + usuario);
//			requisicao.fromCliente(cliente);
//			ru.fromUsuario(usuario);
//			ModelAndView mv = new ModelAndView("clientes/edit");
////			mv.addObject("usuario", usuario);
//			System.out.println("NOME - " + cliente.getNome());
//			
//			return mv;
//		} else {
//			System.out.println("Registro não consta no banco ou não foi encontrado.");
//			return new ModelAndView("redirect:/usuarios");
//		}
//	}
//
//	@PostMapping("/clientes/{id}")
//	public ModelAndView update(@PathVariable Integer id, @Valid requisicaoCliente requisicao, BindingResult result) {
//		if (result.hasErrors()) {
//			System.out.println("\n**********************Invalid Input Found**************************\n");
//
//			ModelAndView mv = new ModelAndView("clientes/edit");
//
//			return mv;
//		} else {
//			Optional<Cliente> optional = this.clienteRepository.findById(id);
//			if (optional.isPresent()) {
//				Cliente cliente = requisicao.toCliente(optional.get());
//				this.clienteRepository.save(cliente);
//				return new ModelAndView("redirect:/clientes/" + cliente.getId());
//			} else {
//				System.out.println("Registro não consta no banco ou não foi encontrado.");
//				return new ModelAndView("redirect:/usuarios");
//			}
//		}
//	}
//
//	@GetMapping("/clientes/{id}/delete")
//	public String delete(@PathVariable Integer id) {
//		try {
//			this.clienteRepository.deleteById(id);
//			return "redirect:/clientes";
//		} catch (EmptyResultDataAccessException e) {
//			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
//			return "redirect:/clientes";
//		}
//	}
}
