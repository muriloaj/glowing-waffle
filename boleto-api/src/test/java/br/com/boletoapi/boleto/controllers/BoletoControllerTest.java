package br.com.boletoapi.boleto.controllers;

import static br.com.boletoapi.commons.ExtendedMockito.mockList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.boletoapi.boleto.services.BoletoService;
import br.com.boletoapi.boleto.services.exceptions.BankslipRecordNotFoundException;
import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;
import br.com.boletoapi.boleto.services.exceptions.NoBankslipProvidedException;
import br.com.boletoapi.boleto.services.exceptions.RequiredInformationMissingException;
import br.com.boletoapi.boleto.vos.BoletoVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BoletoController.class})
@SuppressWarnings("rawtypes")
public class BoletoControllerTest {

	
	@Autowired
	private BoletoController boletoController;
	
	@MockBean
	private BoletoService boletoService;
	
	
	@Test
	public void create_called_returnsStatus400() {
		BoletoVO boletoVO = mock(BoletoVO.class);
		when(boletoService.create(boletoVO)).thenThrow(NoBankslipProvidedException.class);
		
		ResponseEntity response = boletoController.create(boletoVO);
		
		verify(boletoService).create(boletoVO);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Bankslip not provided in the request body", response.getBody());
	}
	
	
	@Test 
	public void create_called_returnsStatus422() {
		BoletoVO boletoVO = mock(BoletoVO.class);
		when(boletoService.create(boletoVO)).thenThrow(RequiredInformationMissingException.class);
		
		ResponseEntity response = boletoController.create(boletoVO);
		
		verify(boletoService).create(boletoVO);
		assertNotNull(response);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		assertEquals("Invalid bankslip provided.The possible reasons are:\n" + 
				"○ A field of the provided bankslip was null or with invalid values", response.getBody());
	}
	
	
	@Test
	public void create_called_returnsStatus201() {
		BoletoVO boletoVO = mock(BoletoVO.class);
		
		ResponseEntity response = boletoController.create(boletoVO);
		
		verify(boletoService).create(boletoVO);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Bankslip created", response.getBody());
	}
	
	@Test
	public void listAll_called_returnsStatus200() {
		List<BoletoVO> bvol = mockList(BoletoVO.class);
		when(boletoService.listAll()).thenReturn(bvol);
		
		ResponseEntity response = boletoController.listAll(); 
		
		verify(boletoService).listAll();
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(bvol, response.getBody());
	}
	
	@Test
	public void listById_called_returns400() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		when(boletoService.listById(id)).thenThrow(InvalidUUIDFormatException.class);
		
		ResponseEntity response = boletoController.listById(id);
		
		verify(boletoService).listById(id);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertSame("Invalid id provided - it must be a valid UUID", response.getBody());
	} 
	
	@Test
	public void listById_called_returns404() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		when(boletoService.listById(id)).thenThrow(BankslipRecordNotFoundException.class);
		
		ResponseEntity response = boletoController.listById(id);
		
		verify(boletoService).listById(id);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertSame("Bankslip not found with the specified id", response.getBody());
	} 
	
	@Test
	public void listById_called_returns200() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		BoletoVO bvo = mock(BoletoVO.class);
		when(boletoService.listById(id)).thenReturn(bvo);
		
		ResponseEntity response = boletoController.listById(id);
		
		verify(boletoService).listById(id);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame(bvo, response.getBody());
	} 
	
	@Test
	public void pay_called_resturns200() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		
		ResponseEntity response = boletoController.pay(id);
		
		verify(boletoService).pay(id);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame("Bankslip paid", response.getBody());
	}
	
	@Test
	public void pay_called_resturns404() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		doThrow(BankslipRecordNotFoundException.class).when(boletoService).pay(id);
		
		ResponseEntity response = boletoController.pay(id);
		
		verify(boletoService).pay(id);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertSame("Bankslip not found with the specified id", response.getBody());
	}
	
	@Test
	public void pay_called_resturns400() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		doThrow(InvalidUUIDFormatException.class).when(boletoService).pay(id);
		
		ResponseEntity response = boletoController.pay(id);
		
		verify(boletoService).pay(id);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertSame("Invalid id provided - it must be a valid UUID", response.getBody());
	}
	
	
	@Test
	public void cancel_called_resturns200() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		
		ResponseEntity response = boletoController.cancel(id);
		
		verify(boletoService).cancel(id);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertSame("Bankslip canceled", response.getBody());
	}
	
	@Test
	public void cancel_called_resturns404() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		doThrow(BankslipRecordNotFoundException.class).when(boletoService).cancel(id);
		
		ResponseEntity response = boletoController.cancel(id);
		
		verify(boletoService).cancel(id);
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertSame("Bankslip not found with the specified id", response.getBody());
	}
	
	@Test
	public void cancel_called_resturns400() {
		String id = "95622acc-5108-11e8-9c2d-fa7ae01bbebc";
		doThrow(InvalidUUIDFormatException.class).when(boletoService).cancel(id);
		
		ResponseEntity response = boletoController.cancel(id);
		
		verify(boletoService).cancel(id);
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertSame("Invalid id provided - it must be a valid UUID", response.getBody());
	}
}
