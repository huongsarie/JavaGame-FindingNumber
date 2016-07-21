package com.sar.findingnumber;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JOptionPane;

class ScoreInfor implements Comparator<ScoreInfor> {
	private String name, str, path;
	private int score;

	ScoreInfor(String str) {
		setName(str);
		setScore(str);
	}

	ScoreInfor() {

	}

	void setString(String str) {
		this.str = str;
	}

	void setName(String str) {
		this.name = str.substring(0, str.indexOf("|"));
	}

	void setScore(String str) {
		try {
			//System.out.println(str.substring(str.lastIndexOf("|")+1));
			score = Integer.parseInt(str.substring(str.lastIndexOf("|")+1));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	String getName() {
		return this.name;
	}

	int getScore() {
		return score;
	}

	@Override
	public int compare(ScoreInfor s1, ScoreInfor s2) {
		if (s1.getScore() > s2.getScore())
			return -1;
		else if (s1.getScore() < s2.getScore())
			return 1;
		else
			return 0;
	}

}

// -- other class
public class HighScoreList {

	private String str, path = "D:/findingnumber-highscore.txt"; // -- nhan
																	// string
																	// chua ten
																	// va diem:
																	// ten|diem
	private File file = new File(path);
	ScoreInfor infor;
	int minScore;
	private ArrayList<ScoreInfor> arrScore ;

	HighScoreList() {
		isExist(file);
		readFile();
	}

	HighScoreList(String str) {
		isExist(file);
		readFile();
		insertList(str);
		writeFile();
		readFile();
	}

	
	ArrayList<ScoreInfor> getArrScore(){
		return arrScore;
	}
	void isExist(File file) {
		boolean kt = true;
		try {
			if (!file.exists()) {
//				System.out.println("File chua ton tai");
				file.createNewFile();
				khoiTao();

				writeFile();

//				System.out.println("Tao thanh cong!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (kt){
//			System.out.println("File da ton tai!");
//			khoiTao();
//			writeFile();
		}

	}
	
	void khoiTao(){
		arrScore = new ArrayList<ScoreInfor>();
		int score = 11000;
		for (int i = 0; i < 10; i++) {
			str = "Bom|" + (score -= 1000);
			infor = new ScoreInfor(str);
			arrScore.add(infor);
		}
	}

	void writeFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			DataOutputStream output = new DataOutputStream(fileOut);
			for (int i = 0; i < 10; i++) {
				String strWrite = arrScore.get(i).getName() + "|"
						+ arrScore.get(i).getScore();
				output.writeBytes(strWrite + "\n");
				//System.out.println(strWrite + "\n");
			}
			output.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Da ghi file");

	}

	void readFile() {
		try {
			if (!file.exists()) {
				JOptionPane.showConfirmDialog(null, "File is not found!");
				return;
			} else {
				FileInputStream fileIn = new FileInputStream(file);
				BufferedReader reader= new  BufferedReader(new  InputStreamReader(fileIn));

				arrScore= new ArrayList<ScoreInfor>();
				String strIn;
				int index=0;
				while ((strIn = reader.readLine()) != null) {
					
					// strIn.split(, arg1)
					ScoreInfor sin= new ScoreInfor(strIn); 
					arrScore.add(sin);
					index++;
					//--demo
					String strWrite = sin.getName() + "|"
							+ sin.getScore();
					//System.out.println(strWrite + "\n");//--demo end
				}
				reader.close();
				fileIn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		minScore=arrScore.get(arrScore.size()-1).getScore();
//		System.out.println("min score in exist file "+minScore);
		//hien lai arrScore
//		Iterator<ScoreInfor> ite= arrScore.iterator();
//		while(ite.hasNext()){
//			System.out.println(ite.next().getScore());
//		}
	}

	void insertList(String str) {
		// -- sap xep giam dan
		if(arrScore.isEmpty()){
			System.out.println("list empty");
		}else{
		String strName = str.substring(0, str.indexOf("|") - 1);
		int strScore = Integer.parseInt(str.substring(str.lastIndexOf("|")+1));
		ScoreInfor inf = new ScoreInfor(str);
		for (int i = 0; i < 10; i++) {
			//System.out.println(arrScore.get(i));
			if (arrScore.get(i).getScore() < strScore) {
				arrScore.add(i, inf);
				arrScore.remove(arrScore.size() - 1);
				break;
			}
		}
		}

	}
}
