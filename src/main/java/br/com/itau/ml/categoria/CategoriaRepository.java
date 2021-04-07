package br.com.itau.ml.categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Optional<Categoria> findByNome(String nomeCategoriaMae);

}
