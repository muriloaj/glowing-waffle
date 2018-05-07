package br.com.boletoapi.boleto.mappers;

import java.util.List;

import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.vos.BoletoVO;

public interface BoletoMapper {

	List<BoletoVO> mapToVO(Iterable<Boleto> ib);
	
	BoletoVO mapToVO(Boleto boleto);

	Boleto mapToEntity(BoletoVO boletoVO);

}
