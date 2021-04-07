package br.com.itau.ml.categoria;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public void PostCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
		Categoria categoria = categoriaForm.converter(categoriaRepository);
		categoriaRepository.save(categoria);
	}
	
	@GetMapping
	public List<CategoriaDto> GetAll(){
		List<Categoria> categorias = categoriaRepository.findAll();
		return CategoriaDto.converter(categorias);
	}
	
}
