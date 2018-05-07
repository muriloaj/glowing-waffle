package br.com.boletoapi.commons;

import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

@SuppressWarnings({"unchecked"})
public class ExtendedMockito extends Mockito {
	public static <T> List<T> mockList(Class<T> clazz) {
		return (List<T>) Mockito.mock(List.class, Mockito.withSettings());
	}
	
	public static <T> Iterable<T> mockIterable(Class<T> clazz) {
		return (Iterable<T>) Mockito.mock(Iterable.class, Mockito.withSettings());
	}
	
	public static <T> Optional<T> mockOptional(Class<T> clazz) {
		return (Optional<T>) Mockito.mock(Optional.class, Mockito.withSettings());
	}
}
