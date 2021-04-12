package br.com.itau.ml.produto.imagem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

public class Uploader {
	
	public List<String> enviar(List<MultipartFile> imagens){
		return imagens.stream().map(imagem -> "http://inhai.dev/" + imagem.getOriginalFilename()).collect(Collectors.toList());
	}
	
}