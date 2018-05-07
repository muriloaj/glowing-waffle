package br.com.boletoapi.boleto.entities;

import static org.junit.Assert.assertSame;

import java.sql.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import br.com.boletoapi.boleto.enums.BoletoStatus;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class BoletoTest {
	
	private Boleto boleto;

	@Before
	public void init() {
		boleto = new Boleto();
	}

	@Test
	public void idTest() {
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");

		boleto.setId(id);

		assertSame(id, boleto.getId());
	}
	
	@Test
	public void dueDateTest() {
		Date d = Date.valueOf("2018-05-04");
		
		boleto.setDueDate(d);
		
		assertSame(d, boleto.getDueDate());
	}
	
	@Test
	public void totalInCentsTest() {
		Integer totalInCents = 5000;
		
		boleto.setTotalInCents(totalInCents);
		
		assertSame(totalInCents, boleto.getTotalInCents());
	}
	
	@Test
	public void statusTest() {
		BoletoStatus status = BoletoStatus.PENDING;
		
		boleto.setStatus(status);;
		
		assertSame(status, boleto.getStatus());
	}
	
	@Test
	public void customerTest() {
		String customer = "customer 1";
		
		boleto.setCustomer(customer);
		
		assertSame(customer, boleto.getCustomer());
	}
	
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(Boleto.class)
			.withPrefabValues(Date.class, Date.valueOf("2018-05-04"),Date.valueOf("2018-04-03"))
			.suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
			.withOnlyTheseFields("id")
			.verify();
	}
	
}
