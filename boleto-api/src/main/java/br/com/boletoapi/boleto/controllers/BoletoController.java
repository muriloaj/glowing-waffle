package br.com.boletoapi.boleto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.boletoapi.boleto.services.BoletoService;
import br.com.boletoapi.boleto.services.exceptions.BankslipRecordNotFoundException;
import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;
import br.com.boletoapi.boleto.services.exceptions.RequiredInformationMissingException;
import br.com.boletoapi.boleto.vos.BoletoVO;


@RestController(value = "bankslipController")
@RequestMapping("/bankslips")
@SuppressWarnings("rawtypes")
public class BoletoController {
	
	private static final String BANKSLIP_PAID = "Bankslip paid";
	private static final String BANKSLIP_CANCELED = "Bankslip canceled";
	private static final String INVALID_UUID_FORMAT = "Invalid id provided - it must be a valid UUID";
	private static final String BANKSLIP_NOT_FOUND = "Bankslip not found with the specified id";
	private static final String BANKSLIP_MISSING_INFORMATION = "Invalid bankslip provided.The possible reasons are:\n" + 
			"○ A field of the provided bankslip was null or with invalid values";
	private static final String BANKSLIP_NOT_PROVIDED = "Bankslip not provided in the request body";
	private static final String BANKSLIP_CREATED = "Bankslip created";
	
	@Autowired
	private BoletoService boletoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody BoletoVO boletoVO) {
		try {
			boletoService.create(boletoVO);
			return ResponseEntity.status(HttpStatus.OK).body(BANKSLIP_CREATED);
		} catch(NoBankslipProvidedException nbspe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BANKSLIP_NOT_PROVIDED);
		} catch(RequiredInformationMissingException rime) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(BANKSLIP_MISSING_INFORMATION);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity listAll() {
		List<BoletoVO> bvol = boletoService.listAll();
		return ResponseEntity.status(HttpStatus.OK).body(bvol);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public ResponseEntity listById(@PathVariable(name = "id") String id) {
		try {
			BoletoVO bvo = boletoService.listById(id);
			return ResponseEntity.status(HttpStatus.OK).body(bvo);
		} catch(InvalidUUIDFormatException iufe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_UUID_FORMAT);	
		} catch(BankslipRecordNotFoundException brnfe) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BANKSLIP_NOT_FOUND);	
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{id}/pay")
	public ResponseEntity pay(@PathVariable(name = "id") String id) {
		try {
			boletoService.pay(id);
			return ResponseEntity.status(HttpStatus.OK).body(BANKSLIP_PAID);
		} catch(InvalidUUIDFormatException iufe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_UUID_FORMAT);	
		} catch(BankslipRecordNotFoundException brnfe) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BANKSLIP_NOT_FOUND);	
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}/cancel")
	public ResponseEntity cancel(@PathVariable(name = "id") String id) {
		try {
			boletoService.cancel(id);
			return ResponseEntity.status(HttpStatus.OK).body(BANKSLIP_CANCELED);
		} catch(InvalidUUIDFormatException iufe) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_UUID_FORMAT);	
		} catch(BankslipRecordNotFoundException brnfe) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BANKSLIP_NOT_FOUND);	
		}
	}
}
