package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adiciona(Conta conta) {
		 ContaDAO contaDAO = new ContaDAO();
		 contaDAO.adiciona(conta);
		 System.out.println("Conta adicionada: "  + conta);
		 return "conta-adicionada";
	}
}
