package filemanagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.Ostermiller.util.CSVParse;
import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.CSVPrinter;
import com.Ostermiller.util.LabeledCSVParser;

public class CSVManager {
	public CSVManager=new CSV
	private LabeledCSVParser parser;
	public static void TSVWrite(File file,String[][] table){
		if(file!=null&&file.isFile()){
			CSVPrinter csvp = new CSVPrinter(System.out);
			csvp.changeDelimiter('\t');
			try {
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
