package com.app.controllers;

import com.app.models.Seat;
import com.app.models.Showing;
import com.app.models.User;
import com.app.models.dto.BookingAndSeat;
import com.app.services.BookingService;
import com.app.services.SeatService;
import com.app.services.ShowingService;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    ShowingService showingService;

    @Autowired
    SeatService seatService;

    @RequestMapping(value = {"/add", "/add/{id}"})
    private ModelAndView getBookingForm(Map<String, Object> modelMap, @PathVariable Optional<Long> id) {
        if (id.isPresent()) {
            Showing showing = showingService.findOne(id.get());
            if (showing != null) {
                List<Seat> availableSeats = bookingService.findAvailableSeats(id.get());
                modelMap.put("allseats", showing.getCinemahall().getSeats());
                modelMap.put("seats", availableSeats);
                BookingAndSeat bookingAndSeat = new BookingAndSeat();
                bookingAndSeat.getBooking().setShowing(showing);
                modelMap.put("bookingAndSeat", bookingAndSeat);
                return new ModelAndView("/booking/add");
            }
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public ModelAndView processBookingForm(@Valid @ModelAttribute("bookingAndSeat") BookingAndSeat bookingAndSeat, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return new ModelAndView("redirect:/");
        } else {
            User currentUser = userService.getCurrentUserFromContext();
            String listOfSeatsId = result.getFieldValue("seatsId").toString();
            bookingService.bookAFewSeat(listOfSeatsId, currentUser, bookingAndSeat);
            return new ModelAndView("/booking/thanks");
        }
    }

    @InitBinder("bookingAndSeat")
    public void initialiseBinder(WebDataBinder binder) {
        //binder.setAllowedFields("booking", "id");
    }
}
