package com.sar.findingnumber;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class ImageManager {

	
	ImageManager(){
		
	}
	
	Image getImageBackGround(){
		return (new ImageIcon(getClass().getResource("/IMAGE/bg-caro1.png")).getImage());
	}
	
	
	
	Image getImageGui(){
		return new ImageIcon(getClass().getResource("/IMAGE/paper1.png")).getImage();
	}
	
	
	Image getCancelIcon(){
		return (new ImageIcon(getClass().getResource("/IMAGE/icon-cancel.png")).getImage());
	}
	
	Image getWinner(){
		return (new ImageIcon(getClass().getResource("/IMAGE/winner.png")).getImage());
	}
	
	Image getMedal(){
		return (new ImageIcon(getClass().getResource("/IMAGE/medal.png")).getImage());
	}
	
	Image getButtonMenu(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonmenu.png")).getImage());
	}
	
	
	Image getContentBackground(){
		return (new ImageIcon(getClass().getResource("/IMAGE/contentbg.png")).getImage());
	}

	Image getHeadPencil(){
//		return (new ImageIcon(getClass().getResource("/IMAGE/head-pencil.png")).getImage());

		return (new ImageIcon(getClass().getResource("/IMAGE/gotbutchi.png")).getImage());
	}
	
	Image getBodyPencil(){
		return (new ImageIcon(getClass().getResource("/IMAGE/body-pencil.png")).getImage());
	}
	
	Image getEndPencil(){
		return (new ImageIcon(getClass().getResource("/IMAGE/end-pencil.png")).getImage());
	}
	
	Image getStar(){
		return (new ImageIcon(getClass().getResource("/IMAGE/star.png")).getImage());
	}
	
	Image getSelected(){
		return (new ImageIcon(getClass().getResource("/IMAGE/selected"+(new Random()).nextInt(3)+".png")).getImage());
	}
	
	Image getSelectedIncorrect(){
		return (new ImageIcon(getClass().getResource("/IMAGE/selectedincorrect.png")).getImage());
	}
	
	Image getVoChi(){
		return (new ImageIcon(getClass().getResource("/IMAGE/vochi.png")).getImage());
	}
	
	Image getImageBack(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonBack.png")).getImage());
	}
	
	Image getImageNext(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonNext.png")).getImage());
	}
	
	Image getImageStart(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonStart.png")).getImage());
	}
	Image getImageGame(){
		return (new ImageIcon(getClass().getResource("/IMAGE/Game.png")).getImage());
	}
	
	Image getImageRestart(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonRestart.png")).getImage());
	}
	
	Image getImageScore(){
		return (new ImageIcon(getClass().getResource("/IMAGE/buttonScore.png")).getImage());
	}
	
	Image getImageInfor(){
		return (new ImageIcon(getClass().getResource("/IMAGE/infor.png")).getImage());
	}
	
	Image getOver(){
		return (new ImageIcon(getClass().getResource("/IMAGE/gameover.png")).getImage());
	}
}
