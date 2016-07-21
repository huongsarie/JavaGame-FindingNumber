package com.sar.findingnumber;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class NumberManager {
	private ArrayList<Number> arrNum;
	private int n;
	GUI gui= new GUI();
	ContentPanel contentPanel= new ContentPanel();
	ImageManager image= new ImageManager();
	private int maxX=contentPanel.getWPanel()-70-200,
			minX=20,
			minY=125,
			maxY=contentPanel.getHPanel()-50-25-30;
	
	int getN(){
		return n;
	}
	
	void setN(int n){
		this.n=n;
	}
	NumberManager(){
		
	}
	NumberManager(int n) {
		this.n=n;
		initArrayObject();
	}
	
	void initArrayObject(){
		arrNum= new ArrayList<>();
		int i=0;
		while(i<n){
			Random rand= new Random();
			int	x=(int) rand.nextInt(maxX);
			while(x<minX){
				x=rand.nextInt(maxX);
			}
			int y= (int)rand.nextInt(maxY);
			while(y<minY){
				y=rand.nextInt(maxY);
			}
			Number number= new Number(""+i, x, y);
			if(kiemTraDe(arrNum, number)==true){
				arrNum.add(number);
				i++;
			}
		}
		
	}
	
	void drawAllNumber(Graphics2D g){
		int i=0;
//		Iterator<Number> ite= arrNum.iterator();
//		while (!ite.hasNext()){
//			System.out.println(ite.next().getX()+" "+ite.next().getY());
//		}
		if(arrNum.size()!=0)
		while(i<arrNum.size()){
			arrNum.get(i).drawNumber(g);
			i++;
		}
	}
	
	boolean isContain(int x,int y){
		boolean kt=false;
		int i=0;
		while(i<arrNum.size()){
			if(arrNum.get(i++).isInside(x, y)==true){
				kt=true;
				break;
			}
		}
		return kt;
	}
	
	Number getNum(int index){
		if(index>=0 && index<arrNum.size())
			return arrNum.get(index);
		else return null;
	}
	
	int getIndex(int x,int y){
		int k=-1;
		int i=0;
		while(i<arrNum.size()){
			if(arrNum.get(i).isInside(x, y)==true){
				k=i;
				break;
			}
			i++;
		}
		return k;
	}
	
	boolean kiemTraDe(ArrayList<Number> arr,Number num){//de thi false, khong de thi true
		boolean kt= true; // true laf khong bij de
		for(int i=0;i<arr.size();i++){
			if(!isFree(num, arr.get(i))){
				kt=false; //false la bi de
				break;
			}
		}

		return kt;
	}
	//ktra xem 2 so co bi chen len nhau hay khoong?
	boolean isFree(Number num1,Number num2){
		boolean kt= true;//true=khong de
		int kcach=15;
		if((num1.getX()<num2.getX() +num2.w+kcach) && (num2.getX()<=num1.getX())){
			if(((num1.getY()>=num2.getY()) && (num1.getY()<num2.getY() + num2.h+kcach))
					|| ((num1.getY()<=num2.getY()) && (num1.getY() + num1.h+kcach>num2.getY()))){
				kt= false;//de
			}
		}
		else if((num2.getX()<num1.getX() +num1.w+kcach) && (num2.getX()>=num1.getX())){
			if(((num1.getY()>=num2.getY()) && (num1.getY()<num2.getY() + num2.h+kcach))
					|| ((num1.getY()<=num2.getY()) && (num1.getY() + num1.h+kcach>num2.getY()))){
				kt= false;//de
			}
		}
		return kt;
	}
	

}
