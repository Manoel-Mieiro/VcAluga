package br.com.cefet.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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

import br.com.cefet.dto.requisicaoEstacao;
import br.com.cefet.dto.requisicaoManutencao;
import br.com.cefet.dto.requisicaoReserva;
import br.com.cefet.dto.requisicaoVeiculo;
import br.com.cefet.model.Categoria;
import br.com.cefet.model.Cliente;
import br.com.cefet.model.Contrato;
import br.com.cefet.model.Estacao;
import br.com.cefet.model.Funcionario;
import br.com.cefet.model.Marca;
import br.com.cefet.model.Motorista;
import br.com.cefet.model.Paletas;
import br.com.cefet.model.Status;
import br.com.cefet.model.Usuario;
import br.com.cefet.model.Manutencao;
import br.com.cefet.model.Veiculo;
import br.com.cefet.repository.EstacaoRepository;
import br.com.cefet.repository.ManutencaoRepository;
import br.com.cefet.repository.UsuarioRepository;
import br.com.cefet.repository.VeiculoRepository;
import br.com.cefet.service.SessaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ManutencaoController {
	@Autowired
	private ManutencaoRepository manutencaoRepository;
	@Autowired
	private EstacaoRepository estacaoRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SessaoService sessaoService;

	@GetMapping("/manutencoes")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("manutencoes/index");
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		if (cliente != null) {
			List<Manutencao> manutencoes = this.manutencaoRepository.findByStatusAndUsuario("Corrente", cliente);
			mv.addObject("manutencoes", manutencoes);
			mv.addObject("usuario", cliente);
			return mv;
		} else {
			Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
			if (funcionario != null) {
				List<Manutencao> manutencoes = this.manutencaoRepository.findByStatus("Corrente");
				mv.addObject("manutencoes", manutencoes);
				mv.addObject("usuario", funcionario);
				return mv;
			}
		}
		System.out.println("Não há contratos acossiados!");
		return new ModelAndView("redirect:/sessoes");
	}

	@GetMapping("/manutencoes/new")
	public ModelAndView novo(@RequestParam(name = "veiculoId") int veiculoId) {
		Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(veiculoId);
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		if (cliente == null && funcionario == null) {
			System.out.println("Não há sessão associada!");
			return new ModelAndView("redirect:/sessoes");
		}
		if (optionalVeiculo.isPresent()) {
			Veiculo veiculo = optionalVeiculo.get();

			List<Estacao> estacoes = estacaoRepository.findAll(); // Obtém a lista de todas as estações

			ModelAndView mv = new ModelAndView("manutencoes/new");
			mv.addObject("veiculo", veiculo);
			mv.addObject("estacoes", estacoes); // Adiciona a lista de estações ao modelo

			return mv;
		} else {
			System.out.println("Veículo não encontrado.");
			return new ModelAndView("redirect:/veiculos");
		}
	}
	
	
	@GetMapping("/manutencoes/archive")
	public ModelAndView historico() {
		ModelAndView mv = new ModelAndView("manutencoes/archive");
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		if (cliente != null) {
			List<Manutencao> manutencoes = this.manutencaoRepository.findByStatusAndUsuario("Arquivado", cliente);
			
			mv.addObject("cliente", cliente);
			mv.addObject("manutencoes", manutencoes);
			return mv;
			} 
			else {
				Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
				if (funcionario != null) {
					List<Manutencao> manutencoes = this.manutencaoRepository.findByStatus("Arquivado");
					
					mv.addObject("funcionario", funcionario);
					mv.addObject("manutencoes", manutencoes);
					return mv;
			}
			System.out.println("Não há contratos acossiados!");
			return new ModelAndView("redirect:/sessoes");
		}
	}

//	 O bloco abaixo cria um objeto requisicaoVeiculo para tratar erro de validação
//	 de dados thymeleaf
	@ModelAttribute(value = "requisicaoManutencao")
	public requisicaoManutencao getRequisicaoManutencao() {
		return new requisicaoManutencao();
	}

	@PostMapping("/manutencoes")
	public ModelAndView create(@Valid requisicaoManutencao requisicao, BindingResult result,
			RedirectAttributes redirectAttributes) {
		Veiculo veiculo = veiculoRepository.findById(requisicao.getVeiculoId()).orElse(null);
		System.out.printf("ID VEICULO - %d%n", requisicao.getVeiculoId());
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();
		Cliente cliente = sessaoService.obterClienteDaSessao(session);
		Funcionario funcionario = sessaoService.obterFuncionarioDaSessao(session);
		Usuario usuario = cliente;
		if (cliente == null) {
			usuario = funcionario;
		}

		int estacaoId = 0;
		try {
			estacaoId = Integer.parseInt(requisicao.getEstacaoId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		Estacao estacao = estacaoRepository.findById(estacaoId).orElse(null);
		System.out.printf("ID ESTACAO - %d%n", estacaoId);
		ModelAndView mv = new ModelAndView("manutencoes/new");

		List<Estacao> estacoes = estacaoRepository.findAll();
		mv.addObject("estacoes", estacoes);
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
			mv.addObject("veiculo", veiculo);
			mv.addObject("estacao", estacao);
			mv.addObject("usuario", usuario);
			mv.addObject("dataEntrada", requisicao.getDataEntrada());
			mv.addObject("dataSaida", requisicao.getDataSaida());
			return mv;
		} else {
			if (veiculo != null && estacao != null && !veiculoRepository.isVeiculoEmManutencao(veiculo.getId())) {
				Manutencao manutencao = new Manutencao();
				mv = new ModelAndView("redirect:/manutencoes/" + manutencao.getIdManutencao());

				manutencao.setEstacao(estacao);
				manutencao.setVeiculo(veiculo);
				manutencao.setUsuario(usuario);
				manutencao.setStatus("Corrente");
				veiculo.setEmManutencao(true);
				estacao.setStatus(Status.Reservado);
				manutencao = requisicao.toManutencao(manutencao);

				System.out.println("Data de entrada recebida: " + manutencao.getDataEntrada());
				System.out.println("Data de saída recebida: " + manutencao.getDataSaida());

				LocalDate localDateReserva = manutencao.getDataEntrada().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();

				if (localDateReserva.isBefore(LocalDate.now())) {
					mv.addObject("estacao", estacao);
					mv.addObject("veiculo", veiculo);
					mv.addObject("dataEntrada", requisicao.getDataEntrada());
					mv.addObject("dataSaida", requisicao.getDataSaida());
					return mv;
				}

				this.manutencaoRepository.save(manutencao);

				return mv;
			} else {
				System.out.println("Não foi possível realizar agendamento.");
				return new ModelAndView("redirect:/veiculos");
			}
		}
	}

	@GetMapping("/manutencoes/{idManutencao}")
	public ModelAndView show(@PathVariable Integer idManutencao) {

		Optional<Manutencao> optional = this.manutencaoRepository.findById(idManutencao);

		if (optional.isPresent()) {
			Manutencao manutencao = optional.get();

			ModelAndView mv = new ModelAndView("manutencoes/show");
			mv.addObject("manutencao", manutencao);
			return mv;
		} else {
			System.out.println("Registro não consta no banco ou não foi encontrado.");
			return new ModelAndView("redirect:/manutencoes");
		}
	}

	@GetMapping("/manutencoes/{idManutencao}/archive")
	public String archive(@PathVariable Integer idManutencao) {
		try {
			Optional<Manutencao> optional = this.manutencaoRepository.findById(idManutencao);
			if (optional.isPresent()) {
				Manutencao manutencao = optional.get();
				Estacao estacao = manutencao.getEstacao();
				Veiculo veiculo = manutencao.getVeiculo();
				// Atualiza o status da estação para 'Livre'
				manutencao.setStatus("Arquivado");
				this.manutencaoRepository.save(manutencao);
				estacao.setStatus(Status.Livre);
				estacaoRepository.save(estacao);
				veiculo.setEmManutencao(false);
				veiculoRepository.save(veiculo);

				return "redirect:/manutencoes";
			} else {
				System.out
						.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
				return "redirect:/manutencoes";
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/manutencoes";
		}
	}

	@GetMapping("/manutencoes/{idManutencao}/delete")
	public String delete(@PathVariable Integer idManutencao) {
		try {
			Optional<Manutencao> optional = this.manutencaoRepository.findById(idManutencao);
			if (optional.isPresent()) {
				Manutencao manutencao = optional.get();
				Estacao estacao = manutencao.getEstacao();
				Veiculo veiculo = manutencao.getVeiculo();
				this.manutencaoRepository.deleteById(idManutencao);

				// Atualiza o status da estação para 'Livre'
				estacao.setStatus(Status.Livre);
				estacaoRepository.save(estacao);
				veiculo.setEmManutencao(false);
				veiculoRepository.save(veiculo);

				return "redirect:/manutencoes";
			} else {
				System.out
						.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
				return "redirect:/manutencoes";
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Registro não consta no banco ou não foi encontrado, portanto não pode ser deletado.");
			return "redirect:/manutencoes";
		}
	}

}