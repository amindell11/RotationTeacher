package filemanagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.Ostermiller.util.CSVParse;
import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.LabeledCSVParser;

public class Writer {
	public static void TSVWrite(File file,String[][] table){
		if(file!=null){
			CSVPrinter csvp;
			try {
				csvp = new CSVPrinter(new FileWriter(file));
			csvp.changeDelimiter('\t');
				csvp.writeln(table);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(file!=null){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
