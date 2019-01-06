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

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.service.IClientService;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;
import com.itacademy.jd2.vvm.parking.web.converter.TransactionFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.TransactionToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.TransactionDTO;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController extends AbstractController {
	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private TransactionFromDTOConverter fromDtoConverter;

	@Autowired
	private TransactionToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final TransactionFilter filter = new TransactionFilter();

		final List<ITransaction> entities = transactionService.find(filter);
		List<TransactionDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("transaction.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ITransaction newEntity = transactionService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TransactionDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("transaction.edit", hashMap);
		} else {
			final ITransaction entity = fromDtoConverter.apply(formModel);

			transactionService.save(entity);
			return "redirect:/transaction";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITransaction dbModel = transactionService.getFullInfo(id);
		final TransactionDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TransactionDTO dto = toDtoConverter.apply(transactionService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("transaction.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		transactionService.delete(id);
		return "redirect:/transaction";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IClient> clients = clientService.getAll();

		/*
		 * final Map<Integer, String> brandsMap = new HashMap<>(); for (final IBrand
		 * iBrand : brands) { brandsMap.put(iBrand.getId(), iBrand.getName()); }
		 */

		final Map<Integer, Integer> clientsMap = clients.stream()
				.collect(Collectors.toMap(IClient::getId, IClient::getId));
		hashMap.put("clientsChoices", clientsMap);

	}

}
