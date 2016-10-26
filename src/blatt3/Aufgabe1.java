import java.util.Scanner;
import java.lang.Math;

public class Aufgabe1 {
    public static void main(String[] args) {

	// Menueausgabe:
	System.out.println("=============================================================\n"
		+ "Waehlen Sie aus den folgenden Optionen:\n" 
		+ "1 : Berechnung der Flaeche eines Rechteck\n"
		+ "2 : Berechnung der Flaeche eines rechtwinkligen Dreiecks\n"
		+ "3 : Berechnung der Flaeche eines Kreises\n" 
		+ "4 : Berechnung des Volumen eines Kegels\n"
		+ "5 : Berechnung der Mantelflaeche eines Kegelstumpfs\n"
		+ "6 : Berechnung der Funktion f aus der Aufgabenstellung\n" 
		+ "7 : Programm beenden\n"
		+ "=============================================================");

	switch (readInt()) { // Reaktion auf Auswahl
	case 1: // Flaeche - Rechteck
	case 2: // Flaeche - rechtwinkliges Dreieck
	case 3: // Flaeche - Kreis
	case 4: // Volumen - Kegel
	case 5: // Mantelflaeche - Kegelstumpf
	case 6: // Funktion f aus Aufgabenstellung
	case 7: // Programm beenden:
	default:
	}
    }

    /** 
     * =====================================================================
     * ========================== HILFSFUNKTIONEN ==========================
     * =====================================================================
     * 
     * Nutzen Sie die folgenden beiden folgenden Funktionen um Werte einzulesen! 
     *  
     */    
    private static double readDouble() { /** Double-Wert einlesen */
	try {
	    Scanner scan = new Scanner(System.in);
	    double input = scan.nextDouble();
	    return (input < 0) ? 0 : input;
	} catch (Exception e) {
	    System.out.println("Es ist ein Fehler beim Einlesen aufgetreten.");
	    return 0;
	}
    }

    private static int readInt() { /** Int-Wert einlesen */
	try {
	    Scanner sc = new Scanner(System.in);
	    int input = sc.nextInt();
	    return (input < 0) ? 0 : input;
	} catch (Exception e) {
	    System.out.println("Es ist ein Fehler beim Einlesen aufgetreten.");
	    return 0;
	}
    }
}