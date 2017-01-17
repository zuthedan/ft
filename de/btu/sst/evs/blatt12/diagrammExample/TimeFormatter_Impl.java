package de.btu.sst.evs.blatt12.diagrammExample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter_Impl implements ITimeFormatter {

    private TimeFormat timeFormat;
    private DateTimeFormatter formatter;

    public TimeFormatter_Impl() {
	this(TimeFormat.TWELVE_HOUR_FORMAT);
    }
    
    public TimeFormatter_Impl(TimeFormat format) {
	super();
	this.setTimeFormat(format);
    }

    @Override
    public String printTime() {
	LocalDateTime date = LocalDateTime.now();
	return date.format(formatter);
    }

    @Override
    public void setTimeFormat(TimeFormat format) {
	
	this.timeFormat = format;
	if (this.timeFormat == TimeFormat.TWELVE_HOUR_FORMAT) {
	    formatter = DateTimeFormatter.ofPattern("KK:mm:ss");
	} else {
	    formatter = DateTimeFormatter.ofPattern("kk:mm:ss");
	}
    }

}
