package br.com.boletoapi.boleto.mappers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.enums.BoletoStatus;
import br.com.boletoapi.boleto.vos.BoletoVO;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DefaultBoletoMapper.class})
public class BoletoMapperTest {
	
	@Autowired
	private BoletoMapper boletoMapper;
	
	
	@Test
	public void mapToVOTest() {
		Boleto b = new Boleto();
		b.setId(UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3"));
		b.setCustomer("Customer 1");
		b.setDueDate(Date.valueOf("2018-04-20"));
		b.setStatus(BoletoStatus.PENDING);
		b.setTotalInCents(5000);
		
		
		BoletoVO vo = boletoMapper.mapToVO(b);
		
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
		vo.setId(UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3"));
		vo.setCustomer("Customer 1");
		vo.setDueDate(Date.valueOf("2018-04-20"));
		vo.setStatus(BoletoStatus.PENDING);
		vo.setTotalInCents(5000);
		
		
		Boleto b = boletoMapper.mapToEntity(vo);
		
		assertSame(vo.getId(), b.getId());
		assertSame(vo.getCustomer(), b.getCustomer());
		assertSame(vo.getDueDate(), b.getDueDate());
		assertSame(vo.getStatus(), b.getStatus());
		assertSame(vo.getTotalInCents(), b.getTotalInCents());
	}


}
