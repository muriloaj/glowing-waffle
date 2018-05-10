package br.com.boletoapi.boleto.vos;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import br.com.boletoapi.boleto.enums.BoletoStatus;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class BoletoVOTest {
	
	private BoletoVO boletoVO;

	@Before
	public void init() {
		boletoVO = new BoletoVO();
	}

	@Test
	public void idTest() {
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");

		boletoVO.setId(id);

		assertSame(id, boletoVO.getId());
	}
	
	@Test
	public void dueDateTest() {
		Date d = Date.valueOf("2018-05-04");
		
		boletoVO.setDueDate(d);
		
		assertSame(d, boletoVO.getDueDate());
	}
	
	@Test
	public void totalInCentsTest() {
		Integer totalInCents = 5000;
		
		boletoVO.setTotalInCents(totalInCents);
		
		assertSame(totalInCents, boletoVO.getTotalInCents());
	}
	
	@Test
	public void fineTest() {
		double fine = 100.0;
		
		boletoVO.setFine(fine);
		
		assertTrue(fine == boletoVO.getFine());
	}
	
	@Test
	public void statusTest() {
		BoletoStatus status = BoletoStatus.PENDING;
		
		boletoVO.setStatus(status);;
		
		assertSame(status, boletoVO.getStatus());
	}
	
	@Test
	public void customerTest() {
		String customer = "customer 1";
		
		boletoVO.setCustomer(customer);
		
		assertSame(customer, boletoVO.getCustomer());
	}
	
	@Test
	public void equalsTest() {
		EqualsVerifier.forClass(BoletoVO.class)
			.withPrefabValues(Date.class, Date.valueOf("2018-05-04"),Date.valueOf("2018-04-03"))
			.suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
			.withOnlyTheseFields("id")
			.verify();
	}
	

}
