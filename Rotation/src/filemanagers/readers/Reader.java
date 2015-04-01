package filemanagers.readers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class Reader {
	private LabeledCSVParser parser;
	private File file;
	public Reader(File file) {
		this.file=file;
		if(file!=null&&file.isFile()){
			try {
				parser=new LabeledCSVParser(new CSVParser(new FileReader(file)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<String> readToList(String label) throws IOException {
		int c=parser.getLabelIdx(label);
		return null;
	}
	public String[][] TSVRead(File file) throws IOException{
		return parser.getAllValues();
	}
}
