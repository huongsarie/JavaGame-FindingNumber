package com.sar.findingnumber;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//-- W= lay do rong man hinh, H = do dai man hinh
		private final int W=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		private final int H=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		//-- width va height phu thuoc vao ti le cua thiet bi man hinh
		private int wFrame = 1150;
		private int hFrame = 750;
		private ImageIcon icon= new ImageIcon((new ImageManager()).getCancelIcon());
		ImageManager image= new  ImageManager();

		int getWFrame(){
			return wFrame;
		}
		
		int getHFrame(){
			return hFrame;
		}
		
		GUI(){
			
		}
		
		GUI(String title){
			super(title);
			setLayout(new CardLayout());
			setBackground(Color.black);
			setBounds(W/2-wFrame/2, H/2-hFrame/2, wFrame, hFrame);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(new LevelPanel());
			//initComponent();
			setResizable(false);
//			add(panel);
			setVisible(true);
		}
		
		void initComponent(){

			LevelPanel lePanel= new LevelPanel();
			add(lePanel);
			
		}

		
		
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// -- yeu cau xac nhan thoat
			int ok=JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn thoát?", "Thoát game?", 0, 0, icon);
			if(ok==JOptionPane.OK_OPTION){
				System.exit(0);
			}else return;
				
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
