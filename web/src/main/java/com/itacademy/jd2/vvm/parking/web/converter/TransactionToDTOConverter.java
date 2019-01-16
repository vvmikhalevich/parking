package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.TransactionDTO;

@Component
public class TransactionToDTOConverter implements Function<ITransaction, TransactionDTO> {

	@Override
	public TransactionDTO apply(final ITransaction entity) {
		final TransactionDTO dto = new TransactionDTO();
		dto.setId(entity.getId());
		dto.setValue(entity.getValue());
		dto.setDescription(entity.getDescription());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			dto.set

		}
		// final IUserAccount userAccount = client.getUserAccount();
		// if (userAccount != null) {
		// dto.setClientUserAccountId(userAccount.getId());
		// }

		return dto;
	}

}
