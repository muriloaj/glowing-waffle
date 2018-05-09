package br.com.boletoapi.boleto.mappers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boletoapi.boleto.commons.FineProvider;
import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.enums.BoletoStatus;
import br.com.boletoapi.boleto.vos.BoletoVO;
import br.com.boletoapi.commons.UUIDProvider;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DefaultBoletoMapper.class})
public class BoletoMapperTest {
	
	@Autowired
	private BoletoMapper boletoMapper;
	
	@MockBean
	private UUIDProvider uuidProvider;
	
	@MockBean
	private FineProvider fineProvider;
	
	@Test
	public void mapToVOTest() {
		Boleto b = new Boleto();
		b.setId(UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3"));
		b.setCustomer("Customer 1");
		b.setDueDate(Date.valueOf("2018-04-20"));
		b.setStatus(BoletoStatus.PENDING);
		b.setTotalInCents(5000);
		when(fineProvider.isFinable(b.getDueDate())).thenReturn(true);
		when(fineProvider.calculate(b.getTotalInCents(), b.getDueDate())).thenReturn(100);
		
		BoletoVO vo = boletoMapper.mapToVO(b);
		
		verify(fineProvider).isFinable(Date.valueOf("2018-04-20"));
		verify(fineProvider).calculate(b.getTotalInCents(), b.getDueDate());
		assertSame(b.getId(), vo.getId());
		assertSame(b.getCustomer(), vo.getCustomer());
		assertSame(b.getDueDate(), vo.getDueDate());
		assertSame(b.getStatus(), vo.getStatus());
		assertSame(b.getTotalInCents(), vo.getTotalInCents());
	}
	
	@Test
	public void mapToVOListTest() {
		List<Boleto> list = new ArrayList<>();
		Boleto b = new Boleto();
		b.setId(UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3"));
		b.setCustomer("Customer 1");
		b.setDueDate(Date.valueOf("2018-04-20"));
		b.setStatus(BoletoStatus.PENDING);
		b.setTotalInCents(5000);
		
		list.add(b);
		
		List<BoletoVO> vol = boletoMapper.mapToVO(list);
		
		assertNotNull(vol);
		assertThat(vol, hasSize(1));
		
		BoletoVO vo = vol.get(0);
		
		assertSame(b.getId(), vo.getId());
		assertSame(b.getCustomer(), vo.getCustomer());
		assertSame(b.getDueDate(), vo.getDueDate());
		assertSame(b.getStatus(), vo.getStatus());
		assertSame(b.getTotalInCents(), vo.getTotalInCents());
	}
	
	
	@Test
	public void mapToEntityTest() {
		BoletoVO vo = new BoletoVO();
		vo.setCustomer("Customer 1");
		vo.setDueDate(Date.valueOf("2018-04-20"));
		vo.setStatus(BoletoStatus.PENDING);
		vo.setTotalInCents(5000);
		UUID id= UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.getUUID()).thenReturn(id);
		
		
		Boleto b = boletoMapper.mapToEntity(vo);
		
		verify(uuidProvider).getUUID();
		assertNotNull(b.getId());
		assertEquals(id, b.getId());
		assertSame(vo.getCustomer(), b.getCustomer());
		assertSame(vo.getDueDate(), b.getDueDate());
		assertSame(vo.getStatus(), b.getStatus());
		assertSame(vo.getTotalInCents(), b.getTotalInCents());
	}


}
