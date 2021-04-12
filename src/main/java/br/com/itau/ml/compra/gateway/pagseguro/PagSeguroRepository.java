package br.com.itau.ml.compra.gateway.pagseguro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagSeguroRepository extends JpaRepository<PagSeguro, Long>{
	
	Optional<PagSeguro> findByCompraId(Long compraId);
}
