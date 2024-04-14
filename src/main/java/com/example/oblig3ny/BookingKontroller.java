package com.example.oblig3ny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingKontroller {

    @Autowired
    BookingRepository rep;

    @PostMapping("/lagre")
    public void lagreBilett(@RequestBody Booking kunde) {
        rep.lagreBilett(kunde);
    }

    @GetMapping("/hentAlle")
    public List<Booking> hentAlle() {
        return rep.hentAlleBiletter();
    }


    @DeleteMapping("/slettAlle")
    public void slettAlle() {
        rep.slettAlleBiletter();
    }
}
