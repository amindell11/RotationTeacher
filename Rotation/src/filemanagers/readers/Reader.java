package filemanagers.readers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class Reader {
	private LabeledCSVParser parser;
	private File file;
	String[][] table;
	public Reader(File file) {
		this.file=file;
		if(file!=null&&file.isFile()){
			try {
				parser=new LabeledCSVParser(new CSVParser(new FileReader(file)));
				parser.changeDelimiter('\t');
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				table=TSVRead(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<String> readToList(String label) throws IOException {
		List<String> asList = Arrays.asList(parser.getLabels());
		int c=parser.getLabelIdx(label);
		return Arrays.asList(table[c]);
	}
	public String[][] TSVRead(File file) throws IOException{
		return parser.getAllValues();
	}
}
