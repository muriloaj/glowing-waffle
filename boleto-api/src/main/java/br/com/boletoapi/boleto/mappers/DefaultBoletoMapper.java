package br.com.boletoapi.boleto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.boletoapi.boleto.commons.FineProvider;
import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.vos.BoletoVO;
import br.com.boletoapi.commons.UUIDProvider;

@Component
public class DefaultBoletoMapper implements BoletoMapper {
	
	@Autowired
	private UUIDProvider uuidProvider;
	
	@Autowired
	private FineProvider fineProvider;

	@Override
	public List<BoletoVO> mapToVO(Iterable<Boleto> ib) {
		List<BoletoVO> lista = new ArrayList<>();
		ib.forEach(b -> lista.add(mapToVO(b)));
		
		return lista;
	}

	@Override
	public BoletoVO mapToVO(Boleto boleto) {
		BoletoVO vo = new BoletoVO();
		vo.setId(boleto.getId());
		vo.setCustomer(boleto.getCustomer());
		vo.setDueDate(boleto.getDueDate());
		vo.setStatus(boleto.getStatus());
		vo.setTotalInCents(boleto.getTotalInCents());
		if(fineProvider.isFinable(boleto.getDueDate()))
			vo.setFine(fineProvider.calculate(boleto.getTotalInCents(), boleto.getDueDate()));
		
		return vo;
	}

	@Override
	public Boleto mapToEntity(BoletoVO boletoVO) {
		Boleto boleto = new Boleto();
		boleto.setId(uuidProvider.getUUID());
		boleto.setCustomer(boletoVO.getCustomer());
		boleto.setDueDate(boletoVO.getDueDate());
		boleto.setStatus(boletoVO.getStatus());
		boleto.setTotalInCents(boletoVO.getTotalInCents());
		return boleto;
	}

}
