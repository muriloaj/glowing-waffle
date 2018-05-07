package br.com.boletoapi.boleto.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.vos.BoletoVO;

public class DefaultBoletoMapper implements BoletoMapper {

	@Override
	public List<BoletoVO> mapToVO(Iterable<Boleto> ib) {
		List<BoletoVO> lista = new ArrayList<>();
		ib.forEach(b -> lista.add(mapToVO(b)));
		
		return lista;
	}

	@Override
	public BoletoVO mapToVO(Boleto boleto) {
		BoletoVO vo = new BoletoVO();
		vo.setCustomer(boleto.getCustomer());
		vo.setDueDate(boleto.getDueDate());
		vo.setId(boleto.getId());
		vo.setStatus(boleto.getStatus());
		vo.setTotalInCents(boleto.getTotalInCents());
		return vo;
	}

	@Override
	public Boleto mapToEntity(BoletoVO boletoVO) {
		Boleto boleto = new Boleto();
		boleto.setCustomer(boletoVO.getCustomer());
		boleto.setDueDate(boletoVO.getDueDate());
		boleto.setId(boletoVO.getId());
		boleto.setStatus(boletoVO.getStatus());
		boleto.setTotalInCents(boletoVO.getTotalInCents());
		return boleto;
	}

}
