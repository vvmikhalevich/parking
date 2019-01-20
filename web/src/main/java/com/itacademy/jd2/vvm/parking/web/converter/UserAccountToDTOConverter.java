package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelToDTOConverter.class);

	@Override
	public UserAccountDTO apply(final IUserAccount entity) {
		final UserAccountDTO dto = new UserAccountDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		String role = entity.getRole().name();
		dto.setRole(entity.getRole().name());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
