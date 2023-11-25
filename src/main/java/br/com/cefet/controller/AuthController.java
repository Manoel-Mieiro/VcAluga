package br.com.cefet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.model.Cliente;
import br.com.cefet.model.Funcionario;
import br.com.cefet.service.AuthService;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/sessoes")
    public ModelAndView showLoginForm() {
    	ModelAndView mv = new ModelAndView("sessoes/login");
        return mv; 
    }

    @PostMapping("/sessoes")
    public ModelAndView processLogin(@RequestParam String cpf, @RequestParam String senha, @RequestParam String tipo) {
    	System.out.println("Nao cai");
        ModelAndView mv = new ModelAndView("/veiculos");
        if ("cliente".equals(tipo)) {
            System.out.println("Cai no cliente");
            Cliente cliente = authService.autenticarCliente(cpf, senha);
            if (cliente != null) {
                // Lógica para login bem-sucedido como cliente
                return new ModelAndView("redirect:/veiculos");
            }
        } else if ("funcionario".equals(tipo)) {
        	System.out.println("Cai no funça");
            Funcionario funcionario = authService.autenticarFuncionario(cpf, senha);
            if (funcionario != null) {
                // Lógica para login bem-sucedido como funcionário
                return new ModelAndView("redirect:/funcionarios");
            }
        }

        System.out.println("Credenciais inválidas!");
        return mv;
    }
}
