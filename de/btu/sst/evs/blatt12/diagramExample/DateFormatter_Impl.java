package de.btu.sst.evs.blatt12.diagramExample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter_Impl implements IDateFormatter {

    private DateFormat dateFormat;
    private DateTimeFormatter formatter;

    public DateFormatter_Impl() {
	this(DateFormat.GERMAN_FORMAT);
    }

    public DateFormatter_Impl(DateFormat format) {
	super();
	this.setDateFormat(format);
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
	} else if (this.dateFormat == DateFormat.BRITISH_FORMAT) {
	    formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	} else {
	    formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	}
    }

}
