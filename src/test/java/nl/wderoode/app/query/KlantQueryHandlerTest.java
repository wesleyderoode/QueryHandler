package nl.wderoode.app.query;

import static org.junit.Assert.*;

import nl.wderoode.app.model.Klant;

import org.junit.Test;

import java.time.LocalDate;


public class KlantQueryHandlerTest {

    private KlantQueryHandler queryHandler = new KlantQueryHandler();

    @Test
    public void testInsertKlantWithValidKlantReturnsInsertedKlantWithId() {
        Klant klant = new Klant("testklant", "eenbsn", LocalDate.of(1988, 10, 16));

        Klant actual = queryHandler.insertKlant(klant);

        System.out.println(klant);

        assertTrue(actual.getId() > 0);
    }

    @Test
    public void testSelectKlantByIdWithExistingKlantInDatabase() {
        Klant klant = queryHandler.insertKlant(new Klant("testklant", "eenbsn", LocalDate.of(1988, 10, 16)));

        Klant actual = queryHandler.selectKlantById(klant.getId());

        System.out.println(actual);

        assertEquals(klant.getId(), actual.getId());
    }

    @Test
    public void testUpdateKlantWithExistingKlantInDatabase() {
        Klant klant = queryHandler.insertKlant(new Klant("testklant", "eenbsn", LocalDate.of(1988, 10, 16)));

        Klant update = new Klant("Harry", "tweebsn", LocalDate.of(1455, 12, 1));
        update.setId(klant.getId());

        boolean actual = queryHandler.updateKlant(update);

        assertNotEquals(klant, actual);
    }

    @Test
    public void testDeleteKlantWithExistingKlantInDatabase() {
        Klant delete = queryHandler.insertKlant(new Klant("testklant", "eenbsn", LocalDate.of(1988, 10, 16)));

        boolean actual = queryHandler.deleteKlant(delete);

        assertTrue(actual);
    }
}