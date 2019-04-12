package nl.wderoode.app.query;

import java.sql.Date;
import java.time.LocalDate;

public abstract class QueryHandler {

    protected LocalDate toLocalDate(Date date) {
        return date.toLocalDate();
    }

    public Date toSqlDate(LocalDate date) {
        return new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }
}
