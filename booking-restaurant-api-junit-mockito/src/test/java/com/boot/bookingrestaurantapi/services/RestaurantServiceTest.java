package com.boot.bookingrestaurantapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.boot.bookingrestaurantapi.jsons.TurnRest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;

public class RestaurantServiceTest {
    private static final long RESTAURANT_ID=1L;
    private static final String RESTAURANT_NAME="NOMBRE";
    private static final String RESTAURANT_IMAGE="NOMBRE";
    private static final String RESTAURANT_ADDRESS="NOMBRE";
    private static final String RESTAURANT_DESCRIPTION="NOMBRE";
    private static final List<Turn>  TURN_LIST= new ArrayList<>();
    private static final List<Board>  BOARD_LIST= new ArrayList<>();
    private static final List<Reservation>  RESERVATIONS_LIST= new ArrayList<>();
    private static final Restaurant RESTAURANT = new Restaurant();

    private static final List<Restaurant> RESTAURANT_LIST = new ArrayList<>();


    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RESTAURANT.setName(RESTAURANT_NAME);
        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
        RESTAURANT.setImage(RESTAURANT_IMAGE);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setBoards(BOARD_LIST);
        RESTAURANT.setReservations(RESERVATIONS_LIST);
    }

    @Test
    public void getRestaurantByIdTest() throws BookingException{
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
        restaurantService.getRestaurantById(RESTAURANT_ID);
    }

    @Test(expected = BookingException.class)
    public void getRestaurantByIdTestError() throws BookingException{
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurantById(RESTAURANT_ID);
        fail();
    }

    @Test
    public void getRestaurantsTest() throws BookingException{
        final Restaurant restaurant = new Restaurant();
        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
        final List<RestaurantRest> response=restaurantService.getRestaurants();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }
}





