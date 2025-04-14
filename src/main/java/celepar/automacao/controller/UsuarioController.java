package celepar.automacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import celepar.automacao.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;

	}
	// Criar 4 servicos
	// GET para buscar o usuario
	// POST para cadastrar
	// DELETE PaRA EXCLUIR
	// PUT para atualizar

	@GetMapping("/{cpf}")
	public String usuarioServiceGET(@PathVariable String cpf) {
		return usuarioService.buscarUsuario(cpf);

	}

	@PutMapping("/{cpf}")
	public String usuarioServicePUT(@PathVariable String cpf) {

		return usuarioService.atualizarUsuario(cpf);

	}

}
