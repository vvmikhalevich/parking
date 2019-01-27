package com.itacademy.jd2.vvm.parking.web.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.CarFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.CarToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.TransactionToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;
import com.itacademy.jd2.vvm.parking.web.dto.TransactionDTO;
import com.itacademy.jd2.vvm.parking.web.dto.UserAccountDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/private")
public class PrivateController extends AbstractController {

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private UserAccountToDTOConverter toDtoConverter;
	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;

	@Autowired
	private CarFromDTOConverter carfromDtoConverter;

	@Autowired
	private CarToDTOConverter carToDtoConverter;

	@Autowired
	private TransactionToDTOConverter trToDtoConverter;

	@Autowired
	public PrivateController(IUserAccountService userAccountService, UserAccountToDTOConverter toDtoConverter,
			UserAccountFromDTOConverter fromDtoConverter) {
		super();
		this.userAccountService = userAccountService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final UserAccountFilter filter = new UserAccountFilter();

		final CarFilter carFilter = new CarFilter();

		final TransactionFilter trFilter = new TransactionFilter();

		// TODO!!!!!!!!!!!!!!!!!!!
		
		Integer loggedUserId = AuthHelper.getLoggedUserId();
		
		if (AuthHelper.hasRole("admin")) {
            // build filter without restrictions
        } else {
            // build filter with\ restriction by logged userId
            
            filter.setLoggedUserId(loggedUserId);
        }


		String email = userAccountService.get(loggedUserId).getEmail();

		Integer userAccountId = loggedUserId;
		
		filter.setUserEmail(email);

		carFilter.setUserAccountId(userAccountId);

		trFilter.setUserAccountId(userAccountId);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final List<ICar> carEntities = carService.find(carFilter);
		List<CarDTO> carDtos = carEntities.stream().map(carToDtoConverter).collect(Collectors.toList());

		final List<ITransaction> trEntities = transactionService.find(trFilter);
		List<TransactionDTO> trDtos = trEntities.stream().map(trToDtoConverter).collect(Collectors.toList());

		BigDecimal balance = new BigDecimal("0");

		for (TransactionDTO transactionDTO : trDtos) {

			if (transactionDTO != null) {
				BigDecimal value = transactionDTO.getValue();

				balance = balance.add(value);

			}

		}

		// gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put("gridItems2", carDtos);
		models.put("balance", balance);
		models.put("userAccountId", userAccountId);

		return new ModelAndView("private.list", models);
	}

	@RequestMapping(value = "transaction/{id}", method = RequestMethod.GET)
	public ModelAndView viewTransaction(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn,
			@PathVariable(name = "id", required = true) final Integer id) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserAccountFilter filter = new UserAccountFilter();

		final TransactionFilter trFilter = new TransactionFilter();

		// TODO!!!!!!!!!!!!!!!!!!!

		String email = "admin";

		Integer userAccountId = id;

		filter.setUserEmail(email);

		trFilter.setUserAccountId(userAccountId);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final List<ITransaction> trEntities = transactionService.find(trFilter);
		List<TransactionDTO> trDtos = trEntities.stream().map(trToDtoConverter).collect(Collectors.toList());

		BigDecimal balance = new BigDecimal("0");

		for (TransactionDTO transactionDTO : trDtos) {

			if (transactionDTO != null) {
				BigDecimal value = transactionDTO.getValue();

				balance = balance.add(value);

			}

		}

		gridState.setTotalCount(transactionService.getCount(trFilter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", trDtos);
		models.put("balance", balance);
		models.put("userAccountId", userAccountId);

		return new ModelAndView("privateTransaction.list", models);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		loadCommonFormModels(hashMap);

		return new ModelAndView("userAccount.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final List<RoleType> roleTypesList = Arrays.asList(RoleType.values());
		final Map<String, String> roleTypesMap = roleTypesList.stream()
				.collect(Collectors.toMap(RoleType::name, RoleType::name));
		hashMap.put("rolesChoices", roleTypesMap);

	}
}
