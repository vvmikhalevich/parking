package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IClientService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.ClientFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.ClientToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.ClientDTO;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController {

	@Autowired
	private IClientService clientService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IModelService modelService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private ITariffService tariffService;

	@Autowired
	private ClientFromDTOConverter fromDtoConverter;

	@Autowired
	private ClientToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final ClientFilter filter = new ClientFilter();

		final List<IClient> entities = clientService.find(filter);
		List<ClientDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("client.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IClient newEntity = clientService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("client.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ClientDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("client.edit", hashMap);
		} else {
			final IClient entity = fromDtoConverter.apply(formModel);

			clientService.save(entity);
			return "redirect:/client";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IClient dbModel = clientService.getFullInfo(id);
		final ClientDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("client.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ClientDTO dto = toDtoConverter.apply(clientService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("client.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		clientService.delete(id);
		return "redirect:/client";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<ICar> cars = carService.getAll();
		final List<IUserAccount> users = userAccountService.getAll();
		final List<ITariff> tariff = tariffService.getAll();

		final Map<Integer, String> carsMap = cars.stream().collect(Collectors.toMap(ICar::getId, ICar::getNumber));
		hashMap.put("carsChoices", carsMap);

		final Map<Integer, String> userAccountsMap = users.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getLastName));
		hashMap.put("userAccountsChoices", userAccountsMap);
		final Map<Integer, String> tariffsMap = tariff.stream()
				.collect(Collectors.toMap(ITariff::getId, ITariff::getName));
		hashMap.put("tariffsChoices", tariffsMap);

	}

}
