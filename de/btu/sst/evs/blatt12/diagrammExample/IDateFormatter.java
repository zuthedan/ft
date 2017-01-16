package de.btu.sst.evs.blatt12.diagrammExample;

public interface IDateFormatter {

    /**
     * Once called, this function will return a String of the current date in a
     * predefined format. The format will be defined by calling the method
     * setDateFormat(DateFormat format).
     * 
     * @return
     */
    public String printDate();

    /**
     * Calling this method defines in which format the time will be formated.
     * The parameter of type TimeFormat will defines what the printed time will
     * look like.
     * 
     * @param format
     */
    public void setDateFormat(DateFormat format);

}
