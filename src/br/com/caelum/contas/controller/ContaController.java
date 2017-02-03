package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@Autowired
	private ContaDAO dao;

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {

		if (result.hasErrors()) {
			return "formulario";
		}

		dao.adiciona(conta);
		System.out.println("Conta adicionada: " + conta);
		return "redirect:listaContas";
	}

	@RequestMapping("listaContas")
	public ModelAndView lista() {
		List<Conta> contas = dao.lista();
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", contas);
		return mv;
	}

	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		dao.remove(conta);
		return "redirect:listaContas";
	}

	@RequestMapping("/pagaConta")
	public void paga(Long id, HttpServletResponse response) {
		dao.paga(id);
		response.setStatus(200);
	}
}
