package de.btu.sst.evs.blatt10.drawlingLib.shapes;

public interface IFigure {

    public Point getCenter();

    public void setCenter(Point point);

    /**
     * Diese Methode prüft, ob ein übergebener Punkt point innerhalb der durch
     * das Objekt beschriebenen Figur liegt. Die X- und Y-Koordinate des Punktes
     * sind in dem übergegeben Objekt vom Typ Point enthalten.
     * 
     * @param point
     *            : Point
     * @return : boolean -- enthält die geometrische Figur besagten Punkt?
     */
    public boolean contains(Point point);

    /**
     * Diese Methode prüft, ob ei- ne übergegebene rechteckige Fläche (parallel
     * zur X bzw. Y-Achse ausgerichtet) komplett von der durch das Objekt
     * beschriebenen Fläche umgeben wird. Der Parameter topleft vom Typ Point
     * reflektiert den linken oberen Punkt des Rechtecks. Die Parameter width
     * und height beinhalten die Werte für die Breite
     * 
     * @param topLeft
     *            : Point
     * @param width
     *            : double
     * @param height
     *            : double
     * @return : boolean -- Ist das Rechteck in der geometrischen Figur komplett
     *         enthalten?
     */
    public boolean contains(Point topLeft, double width, double height);

    public Point[] getVertices();

    /**
     * Die Methode prüft, ob eine übergegebene rechteckige Fläche (parallel zur
     * X bzw. Y-Achse ausgerichtet) nur teilweise von der durch das Objekt
     * beschriebenen Fläche umgeben wird. Wird das Rechteck komplett durch die
     * Fläche umschlossen, so gibt die Methode false zurück. Der Parameter
     * topleft vom Typ Point reflektiert den linken oberen Punkt des Rechtecks.
     * Die Parameter width und height beinhalten die Werte für die Breite und
     * die Höhe. Die Höhe ist parallel zur Y-Achse angegeben und die Breite
     * parallel zur X-Achse.
     * 
     * @param topLeft
     *            : Point
     * @param width
     *            : double
     * @param height
     *            : double
     * @return : boolean -- Ist das Rechteck in der geometrischen Figur
     *         teilweise enthalten?
     */
    public boolean intersects(Point topLeft, double width, double height);

    public double getArea();

    public double getPerimeter();

}
