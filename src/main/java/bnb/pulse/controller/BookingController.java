package bnb.pulse.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bnb.pulse.model.Booking;
import bnb.pulse.model.User;
import bnb.pulse.service.BookingService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public String createBooking(
            @RequestParam("propertyId") int propertyId,
            @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate,
            @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDate,
            HttpSession session, Model model) {

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Booking booking = bookingService.createBooking(propertyId, currentUser.getId(), checkinDate, checkoutDate);
        if (booking == null) {
            model.addAttribute("error", "La proprietà non è disponibile per le date selezionate.");
            return "redirect:/property/" + propertyId;
        }

        return "redirect:/property/" + propertyId;
    }
}
