package filemanagers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ByteManager {
	public static void HashWrite(String directory, HashMap m)
			throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(directory);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);

		objectOutputStream.writeObject(m);
		objectOutputStream.close();
	}
	

	public static HashMap HashRead(String directory) throws IOException,
			ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(directory);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);

		HashMap m = (HashMap) objectInputStream.readObject();
		objectInputStream.close();
		return m;
	}
	public static File fileRead(String path){
		File file=new File(path);
		try {
			FileInputStream fin = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fin);
			File readObject = (File) ois.readObject();
			return readObject;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;

	}
	public static void fileWrite(String path,File file){
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(file);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
