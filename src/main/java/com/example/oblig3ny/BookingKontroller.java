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
    public void lagreBilett(@RequestBody Booking customer) {
        rep.lagreBilett(customer);
    }

    @GetMapping("/hentAlle")
    public List<Booking> hentAlle() {
        return rep.hentAlleBiletter();
    }

    @GetMapping("/hentBilett")
    public Booking hentBilett(int id) {
        return rep.hentBilett(id);
    }

    @PostMapping("/oppdater")
    public void oppdaterBilett(@RequestBody Booking customer) {
        rep.oppdaterBilett(customer);
    }

    @DeleteMapping("/slettBilett")
    public void slettEn(@RequestParam("id") int id) {
        rep.slettBilett(id);
    }
    @DeleteMapping("/slettAlle")
    public void slettAlle() {
        rep.slettAlleBiletter();
    }
    @GetMapping("/sorter")
    public ResponseEntity<List<Booking>> sorter() {
        List<Booking> sortedList = rep.sorterBilletter();
        return ResponseEntity.ok(sortedList);
    }
}
