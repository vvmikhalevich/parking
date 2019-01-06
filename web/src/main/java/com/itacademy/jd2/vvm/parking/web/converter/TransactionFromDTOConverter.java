package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.service.IClientService;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;
import com.itacademy.jd2.vvm.parking.web.dto.TransactionDTO;

@Component
public class TransactionFromDTOConverter implements Function<TransactionDTO, ITransaction> {

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IClientService clientService;

	@Override
	public ITransaction apply(final TransactionDTO dto) {
		final ITransaction entity = transactionService.createEntity();
		entity.setId(dto.getId());
		entity.setValue(dto.getValue());
		entity.setDescription(dto.getDescription());

		final IClient client = clientService.createEntity();
		client.setId(dto.getClientId());

		entity.setClient(client);

		return entity;
	}
}
