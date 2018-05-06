package br.com.boletoapi.commons;

import java.util.List;

import org.mockito.Mockito;

@SuppressWarnings({"unchecked"})
public class ExtendedMockito extends Mockito {
	public static <T> List<T> mockList(Class<T> clazz) {
		return (List<T>) Mockito.mock(List.class, Mockito.withSettings());
	}
}
