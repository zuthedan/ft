package de.btu.sst.evs.blatt12.diagramExample;

public interface ITimeFormatter {

    /**
     * Once called, this function will return a String of the current time in a
     * predefined format. The format will be defined by calling the method
     * setTimeFormat(TimeFormat format).
     * 
     * @return
     */
    public String printTime();

    /**
     * Calling this method defines in which format the date will be formated.
     * The parameter of type TimeFormat will defines what the printed time will
     * look like.
     * 
     * @param format
     */
    public void setTimeFormat(TimeFormat format);
}
