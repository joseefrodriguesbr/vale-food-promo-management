package br.inatel.pos.dm111.vfp.api.restaurant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.pos.dm111.vfp.api.core.ApiException;
import br.inatel.pos.dm111.vfp.api.core.AppError;
import br.inatel.pos.dm111.vfp.api.restaurant.RestaurantRequest;
import br.inatel.pos.dm111.vfp.api.restaurant.RestaurantResponse;
import br.inatel.pos.dm111.vfp.api.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/valefood/restaurants")
public class RestaurantController
{
	private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

	private final RestaurantRequestValidator validator;

	private final RestaurantService service;

	public RestaurantController(RestaurantRequestValidator validator, RestaurantService service)
	{
		this.validator = validator;
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<RestaurantResponse> postRestaurant(@RequestBody
	RestaurantRequest request, BindingResult bindingResult) throws ApiException
	{
		log.debug("Received request to create a new user...");
		validateRequest(request, bindingResult);
		var response = service.createRestaurant(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private void validateRequest(RestaurantRequest request, BindingResult bindingResult) throws ApiException
	{
		ValidationUtils.invokeValidator(validator, request, bindingResult);
		if (bindingResult.hasErrors())
		{
			var errors = bindingResult.getFieldErrors().stream().map(fe -> new AppError(fe.getCode(), fe.getDefaultMessage())).toList();
			throw new ApiException(HttpStatus.BAD_REQUEST, errors);
		}
	}
}
