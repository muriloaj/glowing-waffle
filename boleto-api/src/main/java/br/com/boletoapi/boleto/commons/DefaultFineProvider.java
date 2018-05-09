package br.com.boletoapi.boleto.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DefaultFineProvider implements FineProvider {

	@Override
	public boolean isFinable(Date dueDate) {
		Date endOfDay = getEndOfDay();
		return endOfDay.compareTo(dueDate)  < 0 ? true : false; 
	}

	@Override
	public int calculate(int amount, Date dueDate) {
		Date current = getBeginOfDay();
		
		long diff = current.getTime() - dueDate.getTime();
		long diffInDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		double rate = 0;
		
		if(diffInDays >= 10) {
			rate = 0.5 / 100.0;
		} else {
			rate = 1.0 / 100.0;
		}
		
		return (int) (amount * rate * diffInDays);
	}

	
	private Date getEndOfDay() {
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 23, 59, 59);
	    return calendar.getTime();
	}
	private Date getBeginOfDay() {
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 23, 59, 59);
	    return calendar.getTime();
	}
}