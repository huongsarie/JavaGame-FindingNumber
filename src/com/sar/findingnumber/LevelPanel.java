package com.sar.findingnumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class LevelPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */

	GUI gui = new GUI(); 
	private int w = gui.getWFrame(), h = gui.getHFrame();
	private static final long serialVersionUID = 1L;
	private JButton btBack,btNext, btScore,btStart,btInf;
	private JTextArea txtLevel;
	private Font font1= new Font("Bradley Hand ITC",Font.BOLD,30);
	private HighScoreList highScore;
	ImageManager image= new  ImageManager();
	private int level;
	private String str="";
	boolean isStart=false;
	
	LevelPanel(){
		setLayout(null);
		setOpaque(false);
		setBounds(0, 0, w, h);
		initComponent();
		setVisible(true);
	}
	
	int getLevelNum(String str){
		int t=-1;
		try{
			t=Integer.parseInt(str);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,"Error!!");
		}
		return t;
	}
	void initComponent(){
		ImageIcon icon= new ImageIcon(image.getImageBack());
		int xL=300, yL=this.getHeight()/2-icon.getIconHeight()/2,
				wL=icon.getIconWidth(),hL=icon.getIconHeight();
		btBack= new JButton();
		btBack.setBorderPainted(false);
		btBack.setContentAreaFilled(false);
		btBack.setIcon(icon);
		btBack.setBounds(xL, yL, wL, hL);
		add(btBack);
		btBack.addActionListener(this);
		
		
		txtLevel= new JTextArea();
		txtLevel.setFont(font1);
		txtLevel.setBounds(xL+wL+20, yL, wL, hL);
		txtLevel.setText("100");
		add(txtLevel);
		
		btNext= new JButton();
		icon= new ImageIcon(image.getImageNext());
		btNext.setBorderPainted(false);
		btNext.setContentAreaFilled(false);
		btNext.setIcon(icon);
		btNext.setBounds(xL+wL*2+20*2, yL, wL, hL);
		add(btNext);
		btNext.addActionListener(this);
		
		btStart= new JButton();
		icon= new ImageIcon(image.getImageStart());
		btStart.setBorderPainted(false);
		btStart.setContentAreaFilled(false);
		btStart.setIcon(icon);
		btStart.setBounds(xL+wL*3+20*3, yL, wL, hL);
		add(btStart);
		btStart.addActionListener(this);
		
		btScore = new  JButton();
		btScore.setForeground(Color.black);
		icon= new ImageIcon(image.getImageScore());
		btScore.setIcon(icon);
		btScore.setBorderPainted(false);
		btScore.setContentAreaFilled(false);
		btScore.setHorizontalTextPosition(SwingConstants.CENTER);
		//btScore.setHorizontalAlignment(SwingConstants.CENTER);
		btScore.setBounds(w-icon.getIconWidth()-30, 100+30+icon.getIconHeight(),icon.getIconWidth(), icon.getIconHeight());
		add(btScore);
		btScore.addActionListener(this);
		
		btInf= new JButton();
		icon= new ImageIcon(image.getImageInfor());
		btInf.setBorderPainted(false);
		btInf.setContentAreaFilled(false);
		btInf.setIcon(icon);
		btInf.setBounds(10, yL-50, icon.getIconWidth(), icon.getIconHeight());
		add(btInf);
		btInf.addActionListener(this);
		
	}
	void setIsStart(boolean isStart){
		this.isStart=isStart;
	}
	boolean getIsStart(){
		return isStart;
	}
	void setLevel(int level){
		this.level=level;
	}
	int getLevel(){
		return level;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btInf){
		String str= "Hương Sarie:\nYou click sequence from 0 to number to get best score!";
		JOptionPane.showMessageDialog(null,str , "Infor",0,null);
		}
				
		if(e.getSource()==btBack){
			String a=txtLevel.getText();
			if(getLevelNum(a) >10)
				txtLevel.setText((getLevelNum(a)-1)+"");
		}
		if(e.getSource()==btNext){
			String a=txtLevel.getText();
			if(getLevelNum(a)<150)
				txtLevel.setText((getLevelNum(a)+1)+"");
		}
		if(e.getSource()==btStart){
			int t=getLevelNum(txtLevel.getText());
			if(t<10 || t>150){
				JOptionPane.showMessageDialog(null, "10 <= number <= 150","Warn",0,null);
			}
			else{
			this.removeAll();
			ContentPanel panel= new ContentPanel(t);
			add(panel);
			this.setVisible(true);}
		}
		if(e.getSource()==btScore){
			highScore= new HighScoreList();
			ArrayList<ScoreInfor> arrScoreInfors= highScore.getArrScore();
			Iterator<ScoreInfor> ite=arrScoreInfors.iterator();
			String strArr="";
			int i=1;
			while(ite.hasNext()){
				ScoreInfor sc= ite.next();
				strArr+=i+++" ----- "+sc.getName()+" ------- "+sc.getScore()+"\n";
				
			}
			ImageIcon icon=new ImageIcon(image.getMedal());
			JOptionPane.showMessageDialog(null, strArr, "Best score", 0,icon);
		}
		
	}

}
