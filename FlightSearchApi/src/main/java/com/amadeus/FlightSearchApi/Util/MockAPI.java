package com.amadeus.FlightSearchApi.Util;

import com.amadeus.FlightSearchApi.Request.FlightRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockAPI {
    public static List<FlightRequest> generateMockFlightInfoList(int size) {
        List<FlightRequest> mockFlightList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            FlightRequest flightInfo = new FlightRequest();


            flightInfo.setDepartureAirportId(random.nextInt(8) + 1);
            flightInfo.setArrivalAirportId(random.nextInt(8) + 1);

            // departureTime ve arrivalTime rastgele tarihler oluşturulur
            flightInfo.setDepartureTime(LocalDateTime.now().plusDays(random.nextInt(30))); // 30 gün içinde rastgele bir tarih
            flightInfo.setArrivalTime(flightInfo.getDepartureTime().plusHours(random.nextInt(24))); // 24 saat içinde rastgele bir tarih

            // price rastgele bir ondalıklı sayı oluşturulur
            flightInfo.setPrice(random.nextDouble() * 1000); // 0 ile 1000 arasında rastgele bir ondalıklı sayı

            mockFlightList.add(flightInfo);
        }

        return mockFlightList;
    }
}
