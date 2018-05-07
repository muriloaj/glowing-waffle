package br.com.boletoapi.commons;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;

@Component
public class DefaultUUIDProvider implements UUIDProvider {

	private static final String UUID_REGEX = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

	@Override
	public UUID getUUID() {
		return UUID.randomUUID();
	}

	@Override
	public UUID fromString(String uuid) {
		if(isValid(uuid))
			return UUID.fromString(uuid);
		
		throw new InvalidUUIDFormatException();
	}

	@Override
	public boolean isValid(String uuid) {
		if(uuid != null) {
			if(uuid.matches(UUID_REGEX)) {
				return true;
			}
		}
		return false;
	}
}
