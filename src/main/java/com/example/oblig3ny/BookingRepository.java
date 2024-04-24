package com.example.oblig3ny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
//setting up a booking repository
@Repository
public class BookingRepository {
    @Autowired
    private final JdbcTemplate db;
    public BookingRepository(JdbcTemplate db) {
        this.db = db;
    }
//function for saving a single ticket in the database
    public void lagreBilett(Booking customer) {
        String sql = "INSERT INTO Booking (film, antall, fornavn, etternavn, telefonnr, epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, customer.getFilm(), customer.getAntall(), customer.getFornavn(), customer.getEtternavn(), customer.getTelefonnr(), customer.getEpost());
    }
//recieving all the tickets from the database
    public List<Booking> hentAlleBiletter() {
        String sql = "SELECT * FROM Booking";
        List<Booking> alleBestillinger = db.query(sql, new BeanPropertyRowMapper<>(Booking.class));
        return alleBestillinger;
    }
//deleting all the tickets from the database
    public void slettAlleBiletter() {
        String sql = "DELETE FROM Booking";
        db.update(sql);
    }
    //receiving a single ticket with a parameter
    public Booking hentBooking(int id) {
        Object[] param = new Object[1];
        param[0] = id;
        String sql = "SELECT * FROM Booking WHERE id=?";
        Booking enBilett = db.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Booking.class));
        return enBilett;
    }
//updates the ticket in the database. i had some problems with this so i added a lot of logs.
    public void oppdaterBilett(Booking customer) {
        String sql = "UPDATE Booking " +
                "SET film = ?, " +
                "    antall = ?, " +
                "    fornavn = ?, " +
                "    etternavn = ?, " +
                "    telefonnr = ?, " +
                "    epost = ? " +
                "WHERE id = ?";

        try {
            int rowsAffected = db.update(sql,
                    customer.getFilm(),
                    customer.getAntall(),
                    customer.getFornavn(),
                    customer.getEtternavn(),
                    customer.getTelefonnr(),
                    customer.getEpost(),
                    customer.getId());

            System.out.println("Rows affected: " + rowsAffected);

            if (rowsAffected == 0) {
                handleNoRowsUpdated();
            }
        } catch (Exception e) {
            System.err.println("An error occurred during the booking update: " + e.getMessage());
        }
    }

    private void handleNoRowsUpdated() {
        System.out.println("No rows were updated. Please check if the ID is correct and exists in the database.");
    }
    //deleting a single ticket
    public void slettBooking(int id) {
        String sql = "DELETE FROM Booking WHERE id=?";
        db.update(sql, id);
    }
    //sorts the database
    public List<Booking> sorterDatabase() {
        String sql = "SELECT * FROM Booking ORDER BY etternavn ASC";
        return db.query(sql, new BeanPropertyRowMapper<>(Booking.class));
    }
}
