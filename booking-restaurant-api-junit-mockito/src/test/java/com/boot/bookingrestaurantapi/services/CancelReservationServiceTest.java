package com.boot.bookingrestaurantapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {

    private static final String RESTAURANT_NAME = "NOMBRE";
    private static final String RESTAURANT_IMAGE = "NOMBRE";
    private static final String RESTAURANT_ADDRESS = "NOMBRE";
    private static final String RESTAURANT_DESCRIPTION = "NOMBRE";

    private static final List<Turn> TURN_LIST = new ArrayList<>();

    private static final String LOCATOR = "BURGUER 2";
    private static final long RESTAURANT_ID = 1L;
    private static final Date DATE = new Date();
    private static final long PERSON = 1L;
    private static final long RESERVATION_ID = 1L;
    private static final String TURN_NAME = "TURNO1";
    private static final Restaurant RESTAURANT = new Restaurant();
    private static final Reservation RESERVATION = new Reservation();
    private static final String RESERVATION_DELETED = "LOCATOR_DELETED";
    @Mock
    private ReservationRespository reservationRespository;

    @InjectMocks
    private CancelReservationServiceImpl cancelReservationService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RESTAURANT.setName(RESTAURANT_NAME);
        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setAddress(RESTAURANT_ADDRESS);
        RESTAURANT.setDescription(RESTAURANT_DESCRIPTION);
        RESTAURANT.setImage(RESTAURANT_IMAGE);
        RESTAURANT.setTurns(TURN_LIST);

        RESERVATION.setDate(DATE);
        RESERVATION.setId(RESERVATION_ID);
        RESERVATION.setRestaurant(RESTAURANT);
        RESERVATION.setTurn(TURN_NAME);
        RESERVATION.setPerson(PERSON);
        RESERVATION.setLocator(LOCATOR);
    }

    @Test(expected = BookingException.class)
    public void deleteReservationInternalServerErrorTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.doThrow(Exception.class).when(reservationRespository).deleteByLocator(LOCATOR);
        final String response = cancelReservationService.deleteReservation(LOCATOR);
        assertEquals(response, RESERVATION_DELETED);
        fail();
    }

    @Test(expected = BookingException.class)
    public void deleteReservationFindByIdLocatorErrorTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        final String response = cancelReservationService.deleteReservation(LOCATOR);
        assertEquals(response, RESERVATION_DELETED);
        fail();
    }


    @Test
    public void deleteReservationTest() throws BookingException {
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        final String response = cancelReservationService.deleteReservation(LOCATOR);
        assertEquals(response, RESERVATION_DELETED);
    }

}
