package br.com.boletoapi.boleto.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boletoapi.boleto.enums.BoletoStatus;
import br.com.boletoapi.boleto.services.exceptions.NoBankslipProvidedException;
import br.com.boletoapi.boleto.services.exceptions.RequiredInformationMissingException;
import br.com.boletoapi.boleto.validator.BoletoValidator;
import br.com.boletoapi.boleto.validator.DefaultBoletoValidator;
import br.com.boletoapi.boleto.vos.BoletoVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DefaultBoletoValidator.class})
public class BoletoValidatorTest {

	
	@Autowired
	private BoletoValidator boletoValidator;
	
	@Test
	public void validate_called_throws() {
		NoBankslipProvidedException nbpe = null;
		
		try {
			boletoValidator.validate(null);
		} catch(NoBankslipProvidedException e) {
			nbpe = e;
		}
		
		assertNotNull(nbpe);
	}
	
	@Test
	public void validate_called_throws1() {
		RequiredInformationMissingException rime = null;
		BoletoVO vo = new BoletoVO();
		
		try {
			boletoValidator.validate(vo);
		} catch(RequiredInformationMissingException e) {
			rime = e;
		}
		
		assertNotNull(rime);
		assertEquals("customer", rime.getMessage());
	}
	
	@Test
	public void validate_called_throws2() {
		RequiredInformationMissingException rime = null;
		BoletoVO vo = new BoletoVO();
		vo.setCustomer("Customer 1");
		
		try {
			boletoValidator.validate(vo);
		} catch(RequiredInformationMissingException e) {
			rime = e;
		}
		
		assertNotNull(rime);
		assertEquals("dueDate", rime.getMessage());
	}
	
	@Test
	public void validate_called_throws3() {
		RequiredInformationMissingException rime = null;
		BoletoVO vo = new BoletoVO();
		vo.setCustomer("Customer 1");
		vo.setDueDate(Date.valueOf("2018-05-01"));
		
		try {
			boletoValidator.validate(vo);
		} catch(RequiredInformationMissingException e) {
			rime = e;
		}
		
		assertNotNull(rime);
		assertEquals("totalInCents", rime.getMessage());
	}
	
	@Test
	public void validate_called_throws4() {
		RequiredInformationMissingException rime = null;
		BoletoVO vo = new BoletoVO();
		vo.setCustomer("Customer 1");
		vo.setDueDate(Date.valueOf("2018-05-01"));
		vo.setTotalInCents(50000);
		
		try {
			boletoValidator.validate(vo);
		} catch(RequiredInformationMissingException e) {
			rime = e;
		}
		
		assertNotNull(rime);
		assertEquals("status", rime.getMessage());
	}
	
	@Test
	public void validate_called_ok() {
		RuntimeException rte = null;
		BoletoVO vo = new BoletoVO();
		vo.setCustomer("Customer 1");
		vo.setDueDate(Date.valueOf("2018-05-01"));
		vo.setTotalInCents(50000);
		vo.setStatus(BoletoStatus.PAID);
		
		try {
			boletoValidator.validate(vo);
		} catch(RequiredInformationMissingException | NoBankslipProvidedException e) {
			rte = e;
		}
		
		assertNull(rte);
	}
}
