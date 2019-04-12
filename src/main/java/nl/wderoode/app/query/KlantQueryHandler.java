package nl.wderoode.app.query;

import nl.wderoode.app.SimpleDataSource;
import nl.wderoode.app.model.Klant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KlantQueryHandler extends QueryHandler {

    private SimpleDataSource dataSource = new SimpleDataSource();

    public Klant insertKlant(Klant klant) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO klanten (naam, bsn, geboortedatum) VALUES (?, ?, ?);"
            );

            statement.setString(1, klant.getNaam());
            statement.setString(2, klant.getBsn());
            statement.setDate(3, toSqlDate(klant.getGeboortedatum()));
            statement.execute();

            ResultSet result = statement.executeQuery("SELECT LAST_INSERT_ID();");

            while (result.next()) {
                klant.setId(result.getLong(1));
            }

            result.close();
            statement.close();

            return klant;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public Klant selectKlantById(Long klantId) {
        Klant klant = new Klant();

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM klanten WHERE id = ?;"
            );

            statement.setLong(1, klantId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                klant.setId(result.getLong("id"));
                klant.setNaam(result.getString("naam"));
                klant.setBsn(result.getString("bsn"));
                klant.setGeboortedatum(toLocalDate(result.getDate("geboortedatum")));

            }

            result.close();
            statement.close();

            return klant;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean updateKlant(Klant klant) {
        try{
            Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE klanten SET naam = ?, bsn = ?, geboortedatum = ? WHERE id = ?"
            );

            statement.setString(1, klant.getNaam());
            statement.setString(2, klant.getBsn());
            statement.setDate(3, toSqlDate(klant.getGeboortedatum()));
            statement.setLong(4, klant.getId());

            statement.executeUpdate();

            statement.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteKlant(Klant klant) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM klanten WHERE id = ?"
            );

            statement.setLong(1, klant.getId());

            statement.executeUpdate();

            statement.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}