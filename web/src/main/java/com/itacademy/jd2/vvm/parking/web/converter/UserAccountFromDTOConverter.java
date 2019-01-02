package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setRole(dto.getRole());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());

		return entity;
	}
}
