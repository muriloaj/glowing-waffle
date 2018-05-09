package br.com.boletoapi.boleto.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.boletoapi.boleto.services.exceptions.NoBankslipProvidedException;
import br.com.boletoapi.boleto.services.exceptions.RequiredInformationMissingException;
import br.com.boletoapi.boleto.vos.BoletoVO;

@Component
public class DefaultBoletoValidator implements BoletoValidator{

	@Override
	public void validate(BoletoVO boletoVO) {
		if(boletoVO == null) {
			throw new NoBankslipProvidedException();
		}
		
		String field = null;
		
		if(StringUtils.isEmpty(boletoVO.getCustomer())) {
			field = "customer";
		} else if (boletoVO.getDueDate() == null) {
			field = "dueDate";
		} else if (boletoVO.getTotalInCents() == null) {
			field = "totalInCents";
		} else if (boletoVO.getStatus() == null) {
			field = "status";
		}
		
		if(field != null) {
			throw new RequiredInformationMissingException(field);
		}
		
	}

}
