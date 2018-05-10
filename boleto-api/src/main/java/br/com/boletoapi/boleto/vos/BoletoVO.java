package br.com.boletoapi.boleto.vos;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.boletoapi.boleto.enums.BoletoStatus;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BoletoVO {
	
	private UUID id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt_BR")
	private Date dueDate;
	private Integer totalInCents;
	private double fine;
	private BoletoStatus status;
	private String customer;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getTotalInCents() {
		return totalInCents;
	}
	public void setTotalInCents(Integer totalInCents) {
		this.totalInCents = totalInCents;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public BoletoStatus getStatus() {
		return status;
	}
	public void setStatus(BoletoStatus status) {
		this.status = status;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "BoletoVO [id=" + id + ", dueDate=" + dueDate + ", totalInCents=" + totalInCents + ", fine=" + fine
				+ ", status=" + status + ", customer=" + customer + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BoletoVO ))
			return false;
		BoletoVO other = (BoletoVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
