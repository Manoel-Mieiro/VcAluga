package br.com.cefet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cefet.model.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public ModelAndView exibirPerfil(HttpSession session) {
        // Recupera informações do usuário da sessão
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        
        ModelAndView mv = new ModelAndView("/perfil/profile");
        mv.addObject("usuario", usuario);
        return mv;
    }
}
