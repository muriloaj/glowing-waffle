package br.com.boletoapi.boleto.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.boletoapi.boleto.entities.Boleto;

public interface BoletoRepository extends CrudRepository<Boleto, UUID> {

}
