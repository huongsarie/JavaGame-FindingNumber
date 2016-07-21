package com.sar.findingnumber;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Number {
	private int x,y;//toa do bat dau de ve so
	int w=30,h=25;
	private String snum;
	private Font font= new Font("Bradley Hand ITC", Font.BOLD,22);
	private Graphics g;
	Graphics2D g2d=(Graphics2D)g;
	//private FontMetrics fm= g2d.getFontMetrics(font); //LOI
	private ImageManager image= new ImageManager();
	Image img=null;

	int xnum,ynum;
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	int getNum(){
		return Integer.parseInt(snum);
	}
	
	Number(){
		
	}
	
	Number(String  num,int x, int y){
		snum=num;
		setX(x);
		setY(y);
		
	}
	
	void setImageNull(){
		img= null;
	}
	
	void setSelected(){
			img= image.getSelected();
		
	}
	
	void setIncorrectSelected(){
		img= image.getSelectedIncorrect();
	}
	void drawNumber(Graphics2D g){
		FontMetrics fm= g.getFontMetrics(font);
		xnum=x+w/2-fm.stringWidth(snum)/2;
		ynum=y+fm.getHeight()/2;
		g.setColor(Color.black);
		g.setFont(font);
		g.setStroke(new BasicStroke(0));
		//g.drawRect(x, y, w, h);
		g.drawString(snum, xnum, ynum);
		g.drawImage(img, x-5, y-5, w+10, h+10,null);
	}
	
//	boolean isTouch(int x, int y){
//		boolean kt=false;
//		if(x>=this.x+w && x<=this.x -w && y>=this.y+h && y<=this.y-h){
//			kt=true;
//		}
//		return kt;
//	}
	
	boolean isInside(int x,int y){
		boolean kt=false;
		if(x>=this.x && x<=this.x+w && y>=this.y && y<=this.y+h){
			kt=true;
		}
		return kt;
	}
		
}
