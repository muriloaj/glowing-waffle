package br.com.boletoapi.boleto.commons;

import java.util.Date;

public interface FineProvider {
	
	boolean isFinable(Date DueDate);
	
	int calculate(int amount, Date DueDate);

}
