package com.itacademy.jd2.vvm.parking.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.CarFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.CarToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.dto.search.CarSearchDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/car")
public class CarController extends AbstractController {

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = CarController.class.getSimpleName() + "_SEACH_DTO";

	public static final String FILE_FOLDER = "d:\\";

	@Autowired
	private ITariffService tariffService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IModelService modelService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private IFotoService fotoService;

	@Autowired
	private CarFromDTOConverter fromDtoConverter;

	@Autowired
	private CarToDTOConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) CarSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final CarFilter filter = new CarFilter();

		if (AuthHelper.hasRole("admin")) {
			// build filter without restrictions

		} else {
			// build filter with\ restriction by logged userId
			Integer loggedUserId = AuthHelper.getLoggedUserId();
			filter.setLoggedUserId(loggedUserId);
		}

		if (searchDto.getNumber() != null) {
			filter.setNumber(searchDto.getNumber());
		}

		prepareFilter(gridState, filter);
		gridState.setTotalCount(carService.getCount(filter));

		final List<ICar> entities = carService.find(filter);
		List<CarDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDto);

		return new ModelAndView("car.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICar newEntity = carService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@RequestParam("file") final MultipartFile file,
			@Valid @ModelAttribute("formModel") final CarDTO formModel, final BindingResult result)
			throws IOException, GeneralSecurityException {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("car.edit", hashMap);
		} else {
			final ICar entity = fromDtoConverter.apply(formModel);
			final IFoto foto;
			// save image
			String name = file.getOriginalFilename();
			if ((name.length() != 0) && (entity.getFoto() == null)) {

				String originalFilename = file.getOriginalFilename(); // to DB
				String contentType = file.getContentType();// to DB
				String uuid = UUID.randomUUID().toString(); // to DB
				System.out.printf("Uploaded file %s", originalFilename);

				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(), StandardCopyOption.REPLACE_EXISTING);

				foto = fotoService.createEntity();

				foto.setLink(uuid);
				fotoService.save(foto);
				entity.setFoto(foto);

			} else if ((name.length() != 0) && (entity.getFoto() != null)) {

				String originalFilename = file.getOriginalFilename(); // to DB
				String contentType = file.getContentType();// to DB
				String uuid = UUID.randomUUID().toString(); // to DB
				System.out.printf("Uploaded file %s", originalFilename);

				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, new File(FILE_FOLDER + uuid).toPath(), StandardCopyOption.REPLACE_EXISTING);

				foto = fotoService.get(entity.getFoto().getId());

				String oldUuid = foto.getLink();
				String namePath = FILE_FOLDER + oldUuid;
				Path path = Paths.get(namePath);
				Files.delete(path);

				foto.setLink(uuid);
				fotoService.save(foto);
				entity.setFoto(foto);

			} else if ((name.length() == 0) && (entity.getFoto() != null)) {

				foto = fotoService.get(entity.getFoto().getId());
				foto.setLink(foto.getLink());
				fotoService.save(foto);
				entity.setFoto(foto);

			}

			carService.save(entity);

			return "redirect:/car";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICar dbModel = carService.getFullInfo(id);
		final CarDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CarDTO dto = toDtoConverter.apply(carService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) throws IOException {

		final CarDTO dto = toDtoConverter.apply(carService.getFullInfo(id));
		Integer fotoId = dto.getFotoId();

		carService.delete(id);
		String uuid = dto.getLink();
		String namePath = FILE_FOLDER + uuid;
		Path path = Paths.get(namePath);
		Files.delete(path);
		fotoService.delete(fotoId);

		return "redirect:/car";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IModel> models = modelService.getAll();
		final List<IUserAccount> users = userAccountService.getAll();
		final List<IFoto> fotos = fotoService.getAll();
		final List<ITariff> tariffs = tariffService.getAll();

		final Map<Integer, String> modelsMap = models.stream()
				.collect(Collectors.toMap(IModel::getId, IModel::getName));
		hashMap.put("modelsChoices", modelsMap);

		final Map<Integer, String> userAccountsMap = users.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getLastName));
		hashMap.put("userAccountsChoices", userAccountsMap);
		final Map<Integer, String> fotosMap = fotos.stream().collect(Collectors.toMap(IFoto::getId, IFoto::getLink));
		hashMap.put("fotosChoices", fotosMap);
		final Map<Integer, String> tariffsMap = tariffs.stream()
				.collect(Collectors.toMap(ITariff::getId, ITariff::getName));
		hashMap.put("tariffsChoices", tariffsMap);

	}

	private CarSearchDTO getSearchDTO(final HttpServletRequest req) {
		CarSearchDTO searchDTO = (CarSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new CarSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}

}
