package com.itacademy.jd2.vvm.parking.web.controller;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.*;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.service.*;
import com.itacademy.jd2.vvm.parking.web.converter.CarFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.CarPutFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.CarToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.dto.search.CarSearchDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/car")
public class CarController extends AbstractController {

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = CarController.class.getSimpleName() + "_SEACH_DTO";

	public static final String FILE_FOLDER = "C:\\Users\\Viktar_Mikhalevich\\Pictures\\";

	@Autowired
	private IEventService eventService;

	@Autowired
	private ITariffService tariffService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IModelService modelService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private IFotoService fotoService;

	@Autowired
	private CarFromDTOConverter fromDtoConverter;

	@Autowired
	private CarToDTOConverter toDtoConverter;

	@Autowired
	private CarPutFromDTOConverter carPutFromDtoConverter;

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
		models.put("placeId", 0);

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

	@RequestMapping(value = "/{id}/put", method = RequestMethod.GET)
	public ModelAndView put(@PathVariable(name = "id", required = true) final Integer id) {
		final CarDTO dto = toDtoConverter.apply(carService.getFullInfo(id));
		Integer userAccountId = dto.getUserAccountId();
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		loadCommonFormModels(hashMap, userAccountId);

		return new ModelAndView("car.put", hashMap);
	}

	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public Object put(@Valid @ModelAttribute("formModel") final CarDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			
			return "redirect:/car";
		} else {

			final IPlace formPlace = carPutFromDtoConverter.apply(formModel);

			Integer carId = formPlace.getCar().getId();
			Integer placeId = formPlace.getId();
			Integer userAccountId = formPlace.getUserAccount().getId();

			final IPlace place = placeService.get(placeId);
			final ICar car = carService.get(carId);
			final IUserAccount user = userAccountService.get(userAccountId);

			place.setCar(car);
			place.setUserAccount(user);
			placeService.save(place);

			final IEvent event = eventService.createEntity();

			event.setCar(car);
			event.setPlace(place);

			Date timeStart = new Date();
			event.setTimeStart(timeStart);

			eventService.save(event);

		}

		return "redirect:/car";
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

	private void loadCommonFormModels(final Map<String, Object> hashMap, Integer userAccountId) {

		PlaceFilter filter = new PlaceFilter();
		filter.setUserAccountId(userAccountId);
		filter.setWithoutCar(true);

		final List<IPlace> places = placeService.find(filter);

		final Map<Integer, String> placesMap = places.stream()
				.collect(Collectors.toMap(IPlace::getId, IPlace::getName));
		hashMap.put("placesChoices", placesMap);
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
