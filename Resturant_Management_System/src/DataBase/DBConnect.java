package DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static <E> void writeSerialized(ArrayList<E> e, String filePath) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(e);
		out.close();
		fileOut.close();

	}

	public static <E> void writeSerializedObj(E e, String filePath) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(filePath, true);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(e);
		out.close();
		fileOut.close();

	}

	public static <E> ArrayList<E> readSerialized(String filePath) throws IOException, ClassNotFoundException {
		File file = new File(filePath);
		ArrayList<E> e = new ArrayList<E>();
		if (file.exists()) {

			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (ArrayList<E>) in.readObject();
			in.close();
			fileIn.close();

		}
		return e;
	}

	public static <E> E readSerializedObj(String filePath) throws IOException, ClassNotFoundException {
		File file = new File(filePath);
		E e = null;
		if (file.exists()) {

			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (E) in.readObject();
			in.close();
			fileIn.close();

		}
		return e;
	}

}
