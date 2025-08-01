package com.boot.bookingrestaurantapi.services;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.impl.ReservationServiceImpl;

public class ReservationServiceTest {

    private static final String SUCCES_STATUS = "Succes";
    private static final String SUCCES_CODE = "200 OK";
    private static final String OK = "OK";

    private static final String LOCATOR = "BURGUER 2";
    private static final long RESTAURANT_ID = 1L;
    private static final Date DATE = new Date();
    private static final long PERSON = 1L;
    private static final long RESERVATION_ID = 1L;

    private static final String RESTAURANT_NAME = "NOMBRE";
    private static final String RESTAURANT_IMAGE = "NOMBRE";
    private static final String RESTAURANT_ADDRESS = "NOMBRE";
    private static final String RESTAURANT_DESCRIPTION = "NOMBRE";
    private static final List<Turn> TURN_LIST = new ArrayList<>();

    private static final long TURN_ID = 1L;
    private static final String TURN_NAME = "TURNO1";
    private static final Reservation RESERVATION = new Reservation();

    CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
    private static final Restaurant RESTAURANT = new Restaurant();
    private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);

    private static final Turn TURN = new Turn();
    private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);


    private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
    private static final Optional<Restaurant> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
    private static final Optional<Turn> OPTIONAL_TURN_EMPTY = Optional.empty();
    ReservationRest RESERVATION_REST = new ReservationRest();
    @Mock
    private ReservationRespository reservationRespository;

    @Mock
    private TurnRepository turnRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RESTAURANT.setName(RESTAURANT_NAME);
        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
        RESTAURANT.setImage(RESTAURANT_IMAGE);
        RESTAURANT.setTurns(TURN_LIST);

        TURN.setId(TURN_ID);
        TURN.setName(TURN_NAME);
        TURN.setRestaurant(RESTAURANT);

        CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        CREATE_RESERVATION_REST.setPerson(PERSON);
        CREATE_RESERVATION_REST.setTurnId(TURN_ID);
        CREATE_RESERVATION_REST.setDate(DATE);

        RESERVATION.setDate(DATE);
        RESERVATION.setId(RESERVATION_ID);
        RESERVATION.setRestaurant(RESTAURANT);
        RESERVATION.setTurn(TURN_NAME);
        RESERVATION.setPerson(PERSON);
        RESERVATION.setLocator(LOCATOR);


    }


    @Test
    public void getReservationIdTest() throws BookingException {
        Mockito.when(reservationRespository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));
        reservationService.getReservation(RESERVATION_ID);

    }

    @Test(expected = BookingException.class)
    public void getReservationByIdTestError() throws BookingException {
        Mockito.when(reservationRespository.findById(RESERVATION_ID)).thenReturn(Optional.empty());
        reservationService.getReservation(RESERVATION_ID);
        fail();
    }


    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
        Mockito.when(reservationRespository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());
        reservationService.createReservation(CREATE_RESERVATION_REST);

    }

    @Test(expected = BookingException.class)
    public void createReservationFindByIdTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
        reservationService.createReservation(CREATE_RESERVATION_REST);
        fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationFindByIdTurnTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN_EMPTY);
        reservationService.createReservation(CREATE_RESERVATION_REST);
        fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationFindByIdReservationTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(Optional.of(RESERVATION));
        reservationService.createReservation(CREATE_RESERVATION_REST);
        fail();
    }

    @Test(expected = BookingException.class)
    public void createReservationInternalServerTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRespository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
        Mockito.doThrow(Exception.class).when(reservationRespository).save(Mockito.any(Reservation.class));

        reservationService.createReservation(CREATE_RESERVATION_REST);
        fail();
    }


}
















