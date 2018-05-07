package br.com.boletoapi.boleto.services;

import static br.com.boletoapi.commons.ExtendedMockito.mockIterable;
import static br.com.boletoapi.commons.ExtendedMockito.mockList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.enums.BoletoStatus;
import br.com.boletoapi.boleto.mappers.BoletoMapper;
import br.com.boletoapi.boleto.repositories.BoletoRepository;
import br.com.boletoapi.boleto.services.exceptions.BankslipRecordNotFoundException;
import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;
import br.com.boletoapi.boleto.services.exceptions.NoBankslipProvidedException;
import br.com.boletoapi.boleto.vos.BoletoVO;
import br.com.boletoapi.commons.UUIDProvider;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DefaultBoletoService.class})
public class BoletoServiceTest {
	
	@Autowired
	private BoletoService boletoService;
	
	@MockBean
	private BoletoRepository boletoRepository;
	
	@MockBean
	private BoletoMapper boletoMapper;
	
	@MockBean
	private UUIDProvider uuidProvider;
	
	@Test
	public void create_calledWithNullVO_throws() {
		NoBankslipProvidedException ex = null;
		
		try {
			boletoService.create(null);
		} catch(NoBankslipProvidedException e) { 
			ex = e;
		}
		
		assertNotNull(ex);
	}
	
	@Test
	public void listAll_called() {
		Iterable<Boleto> ib = mockIterable(Boleto.class);
		when(boletoRepository.findAll()).thenReturn(ib);
		List<BoletoVO> bol = mockList(BoletoVO.class);
		when(boletoMapper.mapToVO(ib)).thenReturn(bol);
		
		List<BoletoVO> listAll = boletoService.listAll();
		
		verify(boletoRepository).findAll();
		verify(boletoMapper).mapToVO(ib);
		assertSame(bol, listAll);
	}
	
	@Test
	public void listById_calledWithInvalidUUID_throws() {
		String str = "1111";
		when(uuidProvider.isValid(str)).thenReturn(false);
		InvalidUUIDFormatException iufe = null;
		
		try {
			boletoService.listById(str);
		} catch(InvalidUUIDFormatException e) {
			iufe = e;
		}
		
		verify(uuidProvider).isValid(str);
		assertNotNull(iufe);
	}
	
	@Test
	public void listById_calledWithINonExistingUUID_throws() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Optional<Boleto> bo = Optional.empty();
		when(boletoRepository.findById(id)).thenReturn(bo);
		BankslipRecordNotFoundException brnfe = null;
		
		try {
			boletoService.listById(str);
		} catch(BankslipRecordNotFoundException e) {
			brnfe = e;
		}
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		assertNotNull(brnfe);
	}
	
	@Test
	public void listById_called_returnOK() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Boleto b = mock(Boleto.class);
		Optional<Boleto> bo = Optional.of(b);
		when(boletoRepository.findById(id)).thenReturn(bo);
		BoletoVO bvo = mock(BoletoVO.class);
		when(boletoMapper.mapToVO(b)).thenReturn(bvo);
		
		
		BoletoVO bvo1 = boletoService.listById(str);
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		verify(boletoMapper).mapToVO(b);
		assertSame(bvo, bvo1);
	}

	@Test
	public void pay_calledWithInvalidUUID_throws() {
		String str = "1111";
		when(uuidProvider.isValid(str)).thenReturn(false);
		InvalidUUIDFormatException iufe = null;
		
		try {
			boletoService.pay(str);
		} catch(InvalidUUIDFormatException e) {
			iufe = e;
		}
		
		verify(uuidProvider).isValid(str);
		assertNotNull(iufe);
	}
	
	@Test
	public void pay_calledWithINonExistingUUID_throws() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Optional<Boleto> bo = Optional.empty();
		when(boletoRepository.findById(id)).thenReturn(bo);
		BankslipRecordNotFoundException brnfe = null;
		
		try {
			boletoService.pay(str);
		} catch(BankslipRecordNotFoundException e) {
			brnfe = e;
		}
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		assertNotNull(brnfe);
	}
	
	@Test
	public void pay_called_returnOK() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Boleto b = mock(Boleto.class);
		Optional<Boleto> bo = Optional.of(b);
		when(boletoRepository.findById(id)).thenReturn(bo);
		
		boletoService.pay(str);
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		verify(b).setStatus(BoletoStatus.PAID);
		verify(boletoRepository).save(b); 
	}
	
	@Test
	public void cancel_calledWithInvalidUUID_throws() {
		String str = "1111";
		when(uuidProvider.isValid(str)).thenReturn(false);
		InvalidUUIDFormatException iufe = null;
		
		try {
			boletoService.cancel(str);
		} catch(InvalidUUIDFormatException e) {
			iufe = e;
		}
		
		verify(uuidProvider).isValid(str);
		assertNotNull(iufe);
	}
	
	@Test
	public void cancel_calledWithINonExistingUUID_throws() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Optional<Boleto> bo = Optional.empty();
		when(boletoRepository.findById(id)).thenReturn(bo);
		BankslipRecordNotFoundException brnfe = null;
		
		try {
			boletoService.cancel(str);
		} catch(BankslipRecordNotFoundException e) {
			brnfe = e;
		}
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		assertNotNull(brnfe);
	}
	
	@Test
	public void cancel_called_returnOK() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		when(uuidProvider.isValid(str)).thenReturn(true);
		UUID id = UUID.fromString("8c9f029f-53d2-4f75-8cf5-75a13c4046e3");
		when(uuidProvider.fromString(str)).thenReturn(id);
		Boleto b = mock(Boleto.class);
		Optional<Boleto> bo = Optional.of(b);
		when(boletoRepository.findById(id)).thenReturn(bo);
		
		boletoService.cancel(str);
		
		verify(uuidProvider).isValid(str);
		verify(uuidProvider).fromString(str);
		verify(boletoRepository).findById(id);
		verify(b).setStatus(BoletoStatus.CANCELED);
		verify(boletoRepository).save(b); 
	}
	
}
