package br.com.itau.ml.compra;

@RestController
@RequestMapping("/compra")
@Validated
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	
	
}