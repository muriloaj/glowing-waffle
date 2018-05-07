package br.com.boletoapi.boleto.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boletoapi.boleto.entities.Boleto;
import br.com.boletoapi.boleto.enums.BoletoStatus;
import br.com.boletoapi.boleto.mappers.BoletoMapper;
import br.com.boletoapi.boleto.repositories.BoletoRepository;
import br.com.boletoapi.boleto.services.exceptions.BankslipRecordNotFoundException;
import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;
import br.com.boletoapi.boleto.services.exceptions.NoBankslipProvidedException;
import br.com.boletoapi.boleto.vos.BoletoVO;
import br.com.boletoapi.commons.UUIDProvider;

@Service
public class DefaultBoletoService implements BoletoService {
	
	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private BoletoMapper boletoMapper;
	
	@Autowired
	private UUIDProvider uuidProvider;

	@Override
	public BoletoVO create(BoletoVO boletoVO) {
		if(boletoVO == null) {
			throw new NoBankslipProvidedException();
		}
			
		return null;
	}

	@Override
	public List<BoletoVO> listAll() {
		Iterable<Boleto> bi = boletoRepository.findAll();
		
		return boletoMapper.mapToVO(bi);
	}

	@Override
	public BoletoVO listById(String id) {
		if(uuidProvider.isValid(id)) {
			UUID uuid = uuidProvider.fromString(id);
			Optional<Boleto> bo = boletoRepository.findById(uuid);
			if(bo.isPresent()) {
				return boletoMapper.mapToVO(bo.get());
			} else {
				throw new BankslipRecordNotFoundException();
			}
		}
		throw new InvalidUUIDFormatException();
	}

	@Override
	public void pay(String id) {
		if(uuidProvider.isValid(id)) {
			UUID uuid = uuidProvider.fromString(id);
			Optional<Boleto> bo = boletoRepository.findById(uuid);
			if(bo.isPresent()) {
				Boleto b = bo.get();
				b.setStatus(BoletoStatus.PAID);
				boletoRepository.save(b);
			} else {
				throw new BankslipRecordNotFoundException();
			}
		} else {
			throw new InvalidUUIDFormatException();
		}
	}

	@Override
	public void cancel(String id) {
		if(uuidProvider.isValid(id)) {
			UUID uuid = uuidProvider.fromString(id);
			Optional<Boleto> bo = boletoRepository.findById(uuid);
			if(bo.isPresent()) {
				Boleto b = bo.get();
				b.setStatus(BoletoStatus.CANCELED);
				boletoRepository.save(b);
			} else {
				throw new BankslipRecordNotFoundException();
			}
		} else {
			throw new InvalidUUIDFormatException();
		}
	}

}
