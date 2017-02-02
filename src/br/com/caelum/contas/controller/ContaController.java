package br.com.caelum.contas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		
		if(result.hasErrors()) {
			return "formulario";
		}
		
		 ContaDAO contaDAO = new ContaDAO();
		 contaDAO.adiciona(conta);
		 System.out.println("Conta adicionada: "  + conta);
		 return "conta-adicionada";
	}
	
	@RequestMapping("listaContas")
	public ModelAndView lista() {
	  ContaDAO dao = new ContaDAO();
	  List<Conta> contas = dao.lista();

	  ModelAndView mv = new ModelAndView("conta/lista");
	  mv.addObject("contas", contas);
	  return mv;
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		ContaDAO dao = new ContaDAO();
		dao.remove(conta);
		return "redirect:listaContas";
	}
}
