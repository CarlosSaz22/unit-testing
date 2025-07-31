package com.boot.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.controllers.RestaurantController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.springframework.http.HttpStatus;

public class RestaurantControllerTest {
	private static final long RESTAURANT_ID=1L;
	private static final String RESTAURANT_NAME="NOMBRE";
	private static final String RESTAURANT_ADDRESS="NOMBRE";
	private static final String RESTAURANT_DESCRIPTION="NOMBRE";


	private static final String RESTAURANT_IMAGE="NOMBRE";


	private static final String SUCCES_STATUS="Succes";
	private static final String SUCCES_CODE="200 OK";
	private static final String OK="OK";
	private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();

	private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();

	private static final List<TurnRest>  TURN_RESTS = new ArrayList<>();

	@Mock
	private RestaurantService restaurantService;

	@InjectMocks
	RestaurantController restaurantController;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		RESTAURANT_REST.setName(RESTAURANT_NAME);
		RESTAURANT_REST.setId(RESTAURANT_ID);
		RESTAURANT_REST.setAddress(RESTAURANT_ADDRESS);
		RESTAURANT_REST.setDescription(RESTAURANT_DESCRIPTION);
		RESTAURANT_REST.setImage(RESTAURANT_IMAGE);
		RESTAURANT_REST.setTurns(TURN_RESTS);
	}

	@Test
	public void getRestaurantByIdTest() throws BookingException{
		Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
		final BookingResponse<RestaurantRest> response=restaurantController.getRestaurantById(RESTAURANT_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST);
	}

	@Test
	public void getRestaurantsTest() throws BookingException{
		Mockito.when(restaurantService.getRestaurants()).thenReturn(RESTAURANT_REST_LIST);
		final BookingResponse<List<RestaurantRest>> response=restaurantController.getRestaurants();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_LIST);
	}



}
