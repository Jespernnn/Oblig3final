package com.example.oblig3ny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class BookingRepository {
    @Autowired
    private JdbcTemplate db;
    public void lagreBilett(Booking kunde) {
        String sql = "INSERT INTO Booking (film, antall, fornavn, etternavn, telefonnr, epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, kunde.getFilm(), kunde.getAntall(), kunde.getFornavn(), kunde.getEtternavn(), kunde.getTelefonnr(), kunde.getEpost());
    }

    public List<Booking> hentAlleBiletter() {
        String sql = "SELECT * FROM Booking";
        List<Booking> alleBestillinger = db.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        return alleBestillinger;
    }

    public void slettAlleBiletter() {
        String sql = "DELETE FROM Booking";
        db.update(sql);
    }

}
