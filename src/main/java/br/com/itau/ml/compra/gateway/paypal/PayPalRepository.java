package br.com.itau.ml.compra.gateway.paypal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPalRepository extends JpaRepository<PayPal, Long>{
	
	Optional<PayPal> findByCompraId(Long compraId);
	
}
