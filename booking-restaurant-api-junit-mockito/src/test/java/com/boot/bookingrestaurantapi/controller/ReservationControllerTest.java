package com.boot.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.bookingrestaurantapi.controllers.ReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;

public class ReservationControllerTest {

    private static final String SUCCES_STATUS="Succes";
    private static final String SUCCES_CODE="200 OK";
    private static final String OK="OK";

    private static final String LOCATOR="BURGUER 2";
    private static final long RESTAURANT_ID=1L;
    private static final Date DATE=new Date();
    private static final long PERSON=1L;
    private static final long TURN=1L;

    private static final long RESERVATION_ID=1L;
    CreateReservationRest CREATE_RESERVATION_REST=new CreateReservationRest();

    ReservationRest RESERVATION_REST=new ReservationRest();

    @Mock
    private ReservationService reservationService;
    @InjectMocks
    private ReservationController reservationController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        CREATE_RESERVATION_REST.setDate(DATE);
        CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        CREATE_RESERVATION_REST.setTurnId(TURN);
        CREATE_RESERVATION_REST.setPerson(PERSON);


        RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        RESERVATION_REST.setPerson(PERSON);
        RESERVATION_REST.setLocator(LOCATOR);
        RESERVATION_REST.setTurnId(TURN);
        RESERVATION_REST.setDate(DATE);
    }
    @Test
    public void getReservationByIdTest() throws BookingException{
        Mockito.when(reservationService.getReservation(RESERVATION_ID)).thenReturn(RESERVATION_REST);
        final BookingResponse<ReservationRest> response=reservationController.getReservationById(RESERVATION_ID);
        assertEquals(response.getStatus(), SUCCES_STATUS);
        assertEquals(response.getMessage(), OK);
    }

    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
        final BookingResponse<String> response=reservationController.createReservation(CREATE_RESERVATION_REST);
        assertEquals(response.getStatus(), SUCCES_STATUS);
        assertEquals(response.getMessage(), OK);
        assertEquals(response.getData(), LOCATOR);


    }



}







