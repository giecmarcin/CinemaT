package com.app.controllers;

import com.app.models.Cinema;
import com.app.models.Seat;
import com.app.models.Showing;
import com.app.models.User;
import com.app.models.dto.BookingAndSeat;
import com.app.services.*;
import com.app.services.impl.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    CinemaService cinemaService;

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

    @RequestMapping(value = {"/all", "/all/{id}"})
    public String getAllBooking(Map<String, Object> modelMap, @PathVariable("id") Optional<Long> id) {
        Cinema cinema = new Cinema();
        modelMap.put("cinema", cinema);
        modelMap.put("cinemas", cinemaService.findAll());
        if (id.isPresent()) {
            cinema = cinemaService.findOne(id.get());
            LocalDate currentDate = LocalDate.now();
            Date date = DateUtils.asDate(currentDate);
            List<Showing> showingsToday = showingService.findAllShowingByIsActiveAndCinemaAndDate(true, cinema, date);
            modelMap.put("showingsToday", showingsToday);
        }
        return "booking/all";
    }

    @RequestMapping(value = {"/all", "/all/{id}"}, method = RequestMethod.POST)
    public ModelAndView searchShowings(@ModelAttribute("cinema") Cinema cinema, BindingResult result) {
        Long idCinema = Long.parseLong(result.getFieldValue("id").toString());
        return new ModelAndView("redirect:/booking/all/" + idCinema);
    }

    @InitBinder("bookingAndSeat")
    public void initialiseBinder(WebDataBinder binder) {
        //binder.setAllowedFields("booking", "id");
    }
}
