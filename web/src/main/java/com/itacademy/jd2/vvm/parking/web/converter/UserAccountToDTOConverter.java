package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
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
		dto.setRole(entity.getRole());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		try {
			final Set<IPlace> places = entity.getPlaces();
			if (places != null) {
				dto.setPlaceIds(places.stream().map(IPlace::getId).collect(Collectors.toSet()));
				;
			}
		} catch (final Exception e) {
			LOGGER.warn("ignore conversion of 'places' collection because of:" + e.getMessage());
		}

		return dto;
	}

}
