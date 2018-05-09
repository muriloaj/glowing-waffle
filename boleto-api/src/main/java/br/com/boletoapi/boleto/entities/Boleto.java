package br.com.boletoapi.boleto.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.boletoapi.boleto.enums.BoletoStatus;

@Entity
@Table
public class Boleto implements Serializable {
	
	private static final long serialVersionUID = 3013317892962750034L;
	
	@Id
	private UUID id;
	private Date dueDate;
	private Integer totalInCents;
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
		return "Boleto [id=" + id + ", dueDate=" + dueDate + ", totalInCents=" + totalInCents + ", status=" + status
				+ ", customer=" + customer + "]";
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
		if (!(obj instanceof Boleto ))
			return false;
		Boleto other = (Boleto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
