package celepar.automacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import celepar.automacao.entity.CidadaoEntity;
import celepar.automacao.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public String buscarUsuario(String cpf) {

		List<CidadaoEntity> cidadaoEntityLista = usuarioRepository.buscarUsuarioRepository(cpf);
		if (cidadaoEntityLista != null && cidadaoEntityLista.size() > 0) {

			return "Cidadão: " + cidadaoEntityLista.get(0).getNome();

		} else {

			return "Usuário não encontrado com o CPF informado.";

		}

		// Optional<CidadaoEntity> cidadaoEntity = usuarioRepository.findById((long)
		// 1511);

		// cidadaoEntity.get().setNmBairroContato("ATUBA");
		// usuarioRepository.save(cidadaoEntity.get());

		// return "Select do Usuário " + cidadaoEntity.get().getNome();

	}

	public String atualizarUsuario(String cpf) {
		
		List<CidadaoEntity> cidadaoEntityLista = usuarioRepository.buscarUsuarioRepository(cpf);
		
		CidadaoEntity cidadaoEntity = cidadaoEntityLista.get(0);
		
		//Atualização do Bairro do Usuário
		cidadaoEntity.setNmBairroContato("BACACHERI");
		cidadaoEntity.setComplementoLogradouroContato("SOBRADO 02");
		
		usuarioRepository.save(cidadaoEntity);
		
		return "Novo bairro atualizado: " + cidadaoEntityLista.get(0).getNmBairroContato();

	}

}
