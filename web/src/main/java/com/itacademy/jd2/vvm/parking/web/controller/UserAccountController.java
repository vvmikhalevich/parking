package com.itacademy.jd2.vvm.parking.web.controller;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.UserAccountDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends AbstractController {

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private UserAccountToDTOConverter toDtoConverter;
	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;

	@Autowired
	public UserAccountController(IUserAccountService userAccountService, UserAccountToDTOConverter toDtoConverter,
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
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserAccountFilter filter = new UserAccountFilter();
		prepareFilter(gridState, filter);

		final List<IUserAccount> entities = userAccountService.find(filter);
		List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("userAccount.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		String role = "";
		hashMap.put("formModel", new UserAccountDTO());
		
		 Integer loggedUserId = AuthHelper.getLoggedUserId();
		
		 if ( loggedUserId ==null) {
			 role = "user";
        } 


		loadCommonFormModels(hashMap, role);
		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			String role = "";

			loadCommonFormModels(hashMap, role);
			return "userAccount.edit";
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);

			return "redirect:/"; // generates 302 response with Location="/parking/userAccount"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/userAccount";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		String role = "";
		loadCommonFormModels(hashMap, role);

		return new ModelAndView("userAccount.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		String role = "";
		loadCommonFormModels(hashMap, role);

		return new ModelAndView("userAccount.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap, String role) {

		if (role == "") {
			final List<RoleType> roleTypesList = Arrays.asList(RoleType.values());
			final Map<String, String> roleTypesMap = roleTypesList.stream()
					.collect(Collectors.toMap(RoleType::name, RoleType::name));
			hashMap.put("rolesChoices", roleTypesMap);

		} else {
			final List<RoleType> roleTypesList = Arrays.asList(RoleType.valueOf(role));
			final Map<String, String> roleTypesMap = roleTypesList.stream()
					.collect(Collectors.toMap(RoleType::name, RoleType::name));

			hashMap.put("rolesChoices", roleTypesMap);
		}

	}
}
