package com.example.oblig3ny;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingKontroller {

    private List<Booking> bookings = new ArrayList<>();

    @GetMapping
    public String visBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking";
    }

    @PostMapping("/sende")
    public String sendeBooking(Booking booking, Model model) {
        bookings.add(booking);
        model.addAttribute("bookings", bookings);
        return "booking";
    }
}