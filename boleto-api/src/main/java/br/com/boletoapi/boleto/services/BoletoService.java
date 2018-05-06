package br.com.boletoapi.boleto.services;

import java.util.List;

import br.com.boletoapi.boleto.vos.BoletoVO;

public interface BoletoService {

	BoletoVO create(BoletoVO boletoVO);

	List<BoletoVO> listAll();

	BoletoVO listById(String id);

	void pay(String id);
	
	void cancel(String id);

}
