package de.btu.sst.evs.blatt12.classDiagrammExample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter_Impl implements IDateFormatter {

    private DateFormat dateFormat;
    private DateTimeFormatter formatter;

    public DateFormatter_Impl() {
	super();
	this.dateFormat = DateFormat.GERMAN_FORMAT;
	formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public String printDate() {
	LocalDate date = LocalDate.now();
	return date.format(formatter);
    }

    @Override
    public void setDateFormat(DateFormat format) {
	this.dateFormat = format;
	if (this.dateFormat == DateFormat.GERMAN_FORMAT) {
	    formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	} else if (this.dateFormat == DateFormat.GERMAN_FORMAT) {
	    formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
	} else {
	    formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	}
    }

}
