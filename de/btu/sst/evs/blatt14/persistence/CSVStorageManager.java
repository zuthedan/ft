package de.btu.sst.evs.blatt14.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * This class is responsible for loading and storing a list of objects
 * implementing the ICSVSerializable interface. Users of this class need take
 * care of the order of loaded and stored items them selves.
 * 
 * @author Mathias Schubanz
 *
 */
public class CSVStorageManager {

    public List<String[]> loadObjects(File file) {
	CSVReader reader;
	try {
	    reader = new CSVReader(new FileReader(file), ';');
	    List<String[]> result = reader.readAll();
//	    Iterator<String[]> iter = result.iterator();
//	    while(iter.hasNext()) {
//		String[] tmp = iter.next();
//		System.err.println(Arrays.toString(tmp));
//	    }
//	    
//	    System.exit(0);
	    reader.close();
	    return result;

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public void storeObjects(File file, List<? extends ICSVSerializable> objectList) {

	CSVWriter writer;
	try {
	    writer = new CSVWriter(new FileWriter(file), ';');
	    // feed in your array (or convert your data to an array)
	    List<String[]> resultList = new ArrayList<>(); 
	    for (ICSVSerializable obj : objectList) {
		resultList.addAll(obj.serializeIncludingAggregates());
	    }		
	    writer.writeAll(resultList);
	    writer.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
