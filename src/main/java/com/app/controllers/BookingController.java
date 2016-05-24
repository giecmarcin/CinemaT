package com.app.controllers;

import com.app.models.Booking;
import com.app.models.Seat;
import com.app.models.Showing;
import com.app.services.BookingService;
import com.app.services.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    ShowingService showingService;

    @RequestMapping(value = {"/add", "/add/{id}"})
    private ModelAndView getBookingForm(Map<String, Object> modelMap, @PathVariable Optional<Long> id) {
        System.out.println(id.get());
        if (id.isPresent()) {
            Showing showing = showingService.findOne(id.get());
            if (showing != null) {
                Booking booking = new Booking();
                List<Seat> availableSeats = bookingService.findAvailableSeats(id.get());
                modelMap.put("allseats", showing.getCinemahall().getSeats());
                modelMap.put("seats", availableSeats);
                modelMap.put("booking", booking);
                return new ModelAndView("/booking/add");
            }
        }
        return new ModelAndView("redirect:/");
    }
}
