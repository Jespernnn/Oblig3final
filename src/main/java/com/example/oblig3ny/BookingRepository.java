package com.example.oblig3ny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import java.util.List;
@Repository
public class BookingRepository {
    @Autowired
    private final JdbcTemplate db;
    public BookingRepository(JdbcTemplate db) {
        this.db = db;
    }

    public void lagreBilett(Booking customer) {
        String sql = "INSERT INTO Booking (film, antall, fornavn, etternavn, telefonnr, epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, customer.getFilm(), customer.getAntall(), customer.getFornavn(), customer.getEtternavn(), customer.getTelefonnr(), customer.getEpost());
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
    public Booking hentBilett(int id) {
        Object[] param = new Object[1];
        param[0] = id;
        String sql = "SELECT * FROM Booking WHERE id=?";
        Booking enBilett = db.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Booking.class));
        return enBilett;
    }

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
            // Execute the update and capture the number of rows affected
            int rowsAffected = db.update(sql,
                    customer.getFilm(),
                    customer.getAntall(),
                    customer.getFornavn(),
                    customer.getEtternavn(),
                    customer.getTelefonnr(),
                    customer.getEpost(),
                    customer.getId());

            // Log the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);

            // Check if no rows were affected and handle the situation
            if (rowsAffected == 0) {
                handleNoRowsUpdated(); // Use a separate method to handle no updates
            }
        } catch (Exception e) {
            // Log and handle exceptions
            System.err.println("An error occurred during the booking update: " + e.getMessage());
        }
    }

    private void handleNoRowsUpdated() {
        System.out.println("No rows were updated. Please check if the ID is correct and exists in the database.");
        // This method can be extended to log this incident, alert admins, etc.
    }
    public void slettBilett(int id) {
        String sql = "DELETE FROM Booking WHERE id=?";
        db.update(sql, id);
    }
    public List<Booking> sorterBilletter() {
        String sql = "SELECT * FROM Booking ORDER BY etternavn ASC";
        return db.query(sql, new BeanPropertyRowMapper<>(Booking.class));
    }
}
