package br.com.boletoapi.boleto.validator;

import br.com.boletoapi.boleto.vos.BoletoVO;

public interface BoletoValidator {

	void validate(BoletoVO boletoVO);

}
