package com.example.oblig3ny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
//Setting up the controller
@RestController
public class BookingKontroller {

    @Autowired
    BookingRepository rep;
//postmapping for saving the customer
    @PostMapping("/lagre")
    public void lagreBilett(@RequestBody Booking customer) {
        rep.lagreBilett(customer);
    }
//getmapping for reciving all the tickets in a list
    @GetMapping("/hentAlle")
    public List<Booking> hentAlle() {
        return rep.hentAlleBiletter();
    }
//getmappimg for reciving a single ticket with the id
    @GetMapping("/hentBooking")
    public Booking hentBooking(int id) {
        return rep.hentBooking(id);
    }
//updates the ticket with the customer
    @PostMapping("/oppdater")
    public void oppdaterBilett(@RequestBody Booking customer) {
        rep.oppdaterBilett(customer);
    }
//deletemapping for the single ticket using the id
    @DeleteMapping("/slettBooking")
    public void slettEn(@RequestParam("id") int id) {
        rep.slettBooking(id);
    }
    //deletemaping for all the tickets
    @DeleteMapping("/slettAlle")
    public void slettAlle() {
        rep.slettAlleBiletter();
    }
    //getmapping for the sort
    @GetMapping("/sorter")
    public ResponseEntity<List<Booking>> sorterTickets() {
        List<Booking> sortedList = rep.sorterDatabase();
        return ResponseEntity.ok(sortedList);
    }
}
