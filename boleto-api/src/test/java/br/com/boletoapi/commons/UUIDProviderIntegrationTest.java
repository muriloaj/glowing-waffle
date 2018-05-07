package br.com.boletoapi.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.UUID;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcabi.matchers.RegexMatchers;

import br.com.boletoapi.boleto.services.exceptions.InvalidUUIDFormatException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DefaultUUIDProvider.class})
public class UUIDProviderIntegrationTest {

	@Autowired
	private UUIDProvider uuidProvider;

	@Test
	public void getUUID_called_returnsUUID() {
		UUID uuid = uuidProvider.getUUID();
		assertNotNull(uuid);
		String u = uuid.toString();
		assertEquals(36, u.length());
		assertThat(u, RegexMatchers.matchesPattern(
				"^([a-z]|[\\d]){8}\\-([a-z]|[\\d]){4}\\-([a-z]|[\\d]){4}\\-([a-z]|[\\d]){4}\\-([a-z]|[\\d]){12}$"));
	}
	
	@Test
	public void fromString_called_returnsUUID() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		UUID uuid = uuidProvider.fromString(str);
		
		assertNotNull(uuid);
		String u = uuid.toString();
		assertEquals(36, u.length());
		assertEquals(u, str);
	}
	
	@Test
	public void fromString_called_throws() {
		InvalidUUIDFormatException iufe = null;
		
		try {
			uuidProvider.fromString("aaa-aaa");
		} catch(InvalidUUIDFormatException e ) {
			iufe = e;
		}
		
		assertNotNull(iufe);
	}
	
	@Test
	public void isValid_called_returnsTrue() {
		String str = "8c9f029f-53d2-4f75-8cf5-75a13c4046e3";
		
		boolean result = uuidProvider.isValid(str);
	
		assertTrue(result);
	}
	
	@Test
	public void isValid_called_returnsFalse() {
		String str = "1--asda";
		
		boolean result = uuidProvider.isValid(str);
	
		assertFalse(result);
	}
	
}
