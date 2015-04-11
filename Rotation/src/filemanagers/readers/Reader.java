package filemanagers.readers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		int c=parser.getLabelIdx(label);
		List<String> list=new ArrayList<String>();
		for(int x=0;x<table.length;x++){
			list.add(table[x][c]);
		}
		System.out.println("sig reader:"+list);
		return list;
	}
	public String[][] TSVRead(File file) throws IOException{
		return parser.getAllValues();
	}
}
