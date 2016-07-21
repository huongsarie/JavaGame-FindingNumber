package com.sar.findingnumber;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ContentPanel extends JPanel implements Runnable,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private static final long serialVersionUID = 1L;
	GUI gui = new GUI(); // -- goi khoi tao GUI de muon cac thuoc tinh da set
							// trong GUI
	// protected final int w=1000, h=800;
	private int w = gui.getWFrame(), h = gui.getHFrame();
	private ImageManager image = new ImageManager();
	private int level;
	private HighScoreList highScore= new HighScoreList();
	private JButton btReStart,btScore;
	private int nho;// bien nho dung de nho button cuoi cung
	private HighScoreList fileHighScore = new HighScoreList();
	
	private int  timeSecond;
	private float perSecond,stepSecond; // do dai timebar va tong thoi gian choi
										// 1 level
	private Image imgHeadPen = image.getHeadPencil(), imgBodyPen = image
			.getBodyPencil(), imgEndPen = image.getEndPencil(); // call pencil and detail.
	private int wPen, hPen = imgBodyPen.getHeight(null); // wPen=time of a game, ve time bar
	private boolean isTrue, isEnd,isStart,isFinish;
	private Image imgWin = image.getWinner(), imgGO= image.getOver();
	private int w_imgWin = imgWin.getWidth(null), h_imgWin = imgWin
			.getHeight(null), w_imgGO=imgGO.getWidth(null),h_imgGO=imgGO.getHeight(null);
	private int wi = 1000, hi = 1000;
	private Score score = new Score();
	private long timeEnd = 0, timeStart = 0, currentS = 0 ; 
	private int star=10;
	private int indexLastFalse=-1;
	private NumberManager numManager;
	private int vochi=0;
	private MouseAdapter mouseClick = new MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			
			int x=e.getX();
			int y=e.getY();
			int indexClick= numManager.getIndex(x, y);
			if(indexClick>=0 && isEnd== false){
				if(indexClick==nho){
					if(indexLastFalse>=nho){
						numManager.getNum(indexLastFalse).setImageNull();
					}
					nho++;
					numManager.getNum(indexClick).setSelected();
					isTrue=true;
					
					//tinh diem
					if (timeStart != 0) {
						timeEnd = System.currentTimeMillis();
						currentS = (long) ((100000-level*5) / (1 + (timeEnd - timeStart)));
						timeStart = System.currentTimeMillis();
						score.setCurrentScore(currentS);
					}
				}
				else if(indexClick>nho){
					numManager.getNum(indexClick).setIncorrectSelected();
					if(indexLastFalse>nho)
						numManager.getNum(indexLastFalse).setImageNull();	
					indexLastFalse=indexClick;
					isTrue=false;
					star-=1;
				}
				if(nho==numManager.getN()){
					isEnd=true;
				}
			}
			repaint();
		}
	};
	
	

	Thread th = new Thread(this);

	int getWPanel(){
		return w;
	}
	
	int getHPanel(){
		return h;
	}

	void setTimeSecond(int timeSecond) {
		this.timeSecond = timeSecond;
	}

	int getTimeSecond() {
		return timeSecond;
	}


	void setLevel(int level) {
		this.level = level;
	}

	void setIsStart(boolean isStart){
		this.isStart=isStart;
	}
	ContentPanel(){
		
	}
	ContentPanel(int level) {
		setLayout(null);
		setBounds(0, 0, w, h);
		setLevel(level);
		setOpaque(false); // lam nen cua panel trong suot
		initComponent(level);
		khoiTao(level);		
		setVisible(true);

		addMouseListener(mouseClick);
		th.start();

		khoiTao(level);		
		timeStart = System.currentTimeMillis();
		
	}

	void khoiTao(int level){
		currentS=0;
		score= new Score();
		isStart=true;
		nho = 0;
		star=10;
		isTrue = false;
		isEnd = false;
		isFinish=false;
		numManager= new NumberManager(level);
		wPen = timeSecond=500;//(int) Math.sqrt(level)*4;
		perSecond=(float)timeSecond/(level*4+100);
		stepSecond=0;
		wi = w_imgWin / 100;
		hi = h_imgWin / 100;
	}
	
	private void initComponent(int level) {
		
		ImageIcon icon= new ImageIcon(image.getImageRestart());
		int xL=this.getWidth()-200, yL=200,
				wL=icon.getIconWidth(),hL=icon.getIconHeight();
		btReStart= new JButton();
		btReStart.setBorderPainted(false);
		btReStart.setContentAreaFilled(false);
		btReStart.setIcon(icon);
		btReStart.setBounds(xL, yL, wL, hL);
		add(btReStart);
		btReStart.addActionListener(this);
		
		btScore = new  JButton();
		btScore.setForeground(Color.black);
		icon= new ImageIcon(image.getImageScore());
		btScore.setIcon(icon);
		btScore.setBorderPainted(false);
		btScore.setContentAreaFilled(false);
		btScore.setHorizontalTextPosition(SwingConstants.CENTER);
		//btScore.setHorizontalAlignment(SwingConstants.CENTER);
		btScore.setBounds(xL, yL+50+icon.getIconHeight(),icon.getIconWidth(), icon.getIconHeight());
		add(btScore);
		btScore.addActionListener(this);
	}

	
	
	private void addScoreList(long score) {//score la tong diem khi tro choi ket thuc
		if (isEnd == true) {
			ArrayList<ScoreInfor> arrS = fileHighScore.getArrScore();
			for(int i=0;i<arrS.size();i++) {
				if (score > arrS.get(i).getScore()) {
					String name =JOptionPane.showInputDialog("Type your name:");
					String str=name+"|"+score;		
					fileHighScore= new HighScoreList(str);
					break;
				}
			}
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		backGround(g2d);
		drawLabelGame(g2d);
		isFinish=false;
		if(isStart==true){
		g2d.drawImage(image.getContentBackground(), 5, 5, w-200-5,h-30-10, null);
		numManager.drawAllNumber(g2d);
		
		// --ve timeBar - tinh thoi gian choi game
		drawTimePencil(g2d);
		drawVoButChi(g2d);
		numManager.drawAllNumber(g2d);

		drawStar(g2d, star);
		if(nho == numManager.getN() || wPen<=0 || star<=0){
				isEnd = true;
				
				if (nho == numManager.getN() ) {
					// neu thang
					drawScore(g2d, score.totalScore(wPen, star, level));
					if (wi <= imgWin.getWidth(null)
							&& hi <= imgWin.getHeight(null)){
						drawWinner(g2d);
					}
//					if (wi >= imgWin.getWidth(null)
//							&& hi >= imgWin.getHeight(null)) {
//						addScoreList(score.totalScore(wPen, star, level));
//					}

				}else
				if ( (wPen <= 0)|| (star <= 0)) {
					// neu thua
					if (wi <= imgGO.getWidth(null)
							&& hi <= imgGO.getHeight(null))
						drawGameOver(g2d);
					drawScore(g2d, score.totalScore(wPen, star, level));
				} 
				if (isFinish==true) {
					addScoreList(score.totalScore(wPen, star, level));
				}
				
			}else {
				drawScore(g2d, score.getCurrentScore());
			}
		}
	}

	private void drawLabelGame(Graphics2D g){
		Image img= image.getImageGame();
		int wI=img.getWidth(null)/2,
				hI=img.getHeight(null)/2;
		g.drawImage(img, this.getWidth()/2-wI/2, 20, wI, hI, null);
	}

	private void backGround(Graphics2D g) {
		Image imgBackground = image.getImageGui();
		int w_bg = imgBackground.getWidth(null);
		int h_bg = imgBackground.getHeight(null);
		for (int j = 0; j <= h / h_bg; j++) {
			for (int i = 0; i <= w / w_bg; i++) {
				g.drawImage(imgBackground, i * w_bg, j * h_bg, null);
			}

		}

	}
	
	void drawTimePencil(Graphics2D g) {
		int xPen=100;
		g.drawImage(imgBodyPen, xPen + imgHeadPen.getWidth(null), 27, wPen,hPen, null);

		g.drawImage(imgHeadPen, xPen+10, 17, null);
		g.drawImage(imgEndPen, xPen + imgHeadPen.getWidth(null) + wPen, 27, null);
	}
	
	void drawVoButChi(Graphics2D g){
		Image img= image.getVoChi();
		int xchi=150-vochi;
		int ychi=17-vochi;
		g.drawImage(img, xchi ,ychi, null);
		g.drawImage(img, xchi-vochi, ychi-vochi, null);
		g.drawImage(img, xchi-vochi*3, ychi, null);
		if(xchi<=0|| ychi<=0){
			vochi=0;
		}
	}
	void drawWinner(Graphics2D g) {
		g.drawImage(imgWin, (w-200) / 2 - wi / 2, h / 2 - hi / 2, wi, hi, null);
		if (wi >= w_imgWin|| hi>=h_imgWin)
			isFinish = true;
	}
	
	void drawGameOver(Graphics2D g){
		g.drawImage(imgGO, (w-200) / 2 - wi / 2, h / 2 - hi / 2, wi, hi, null);
		if (wi >= w_imgGO || hi>=h_imgGO)
			isFinish = true;
	}
	void drawStar(Graphics2D g,int star){
		g.drawImage(image.getStar(), 10, 20, 70, 70, null);
		g.setFont(new Font("Showcard Gothic",Font.BOLD,30));
		g.setColor(Color.decode("#ffe400"));
		g.drawString(""+star, 10+70, 20+50);
	}
	
	void drawScore(Graphics2D g,long score){
		int wS=this.getWidth()-150-200;
		g.setFont(new Font("MV Boli",Font.BOLD,26));
		g.setColor(Color.red);
		g.drawString("SCORE",wS , 40);
		String str=""+score;
		g.drawString(str, wS, 80);
	}

	@Override
	public void run() {
		while (isStart==true && isEnd == false && isFinish==false) {

			if (nho <= numManager.getN()) {
				vochi += 2;
				if (isTrue == false) {
					stepSecond += perSecond;

					if (stepSecond >= 2) {
						wPen -= 3;
						stepSecond = 0;
					}
					if (stepSecond > 1) {
						wPen -= 2;
						stepSecond = 0;
					}
					if (Math.abs(1 - stepSecond) <= 0.3) {
						wPen -= 1;
						stepSecond = 0;
					}

				} else if (isTrue = true) {
					isTrue = false;
					if (wPen <= timeSecond + 1)
						wPen += 2;
				}
			}
//			if (isFinish==true) {
//				
//			}
			repaint();
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(isEnd==true && isFinish ==false){
			wi += 2;
			hi += 2;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	void drawCorrect(Graphics2D g,int x,int y){
		Image img= image.getSelected();
		g.drawImage(img, x, y,60,50, null);
	}
	void drawIncorrect(Graphics2D g,int x,int y){
		Image img= image.getSelectedIncorrect();
		g.drawImage(img, x, y,60,50, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btReStart){
			int ok=JOptionPane.showConfirmDialog(null, "Do you want restart this level?", "Restart?", 0, 0, null);
			if(ok==JOptionPane.OK_OPTION){
				khoiTao(level);
				isFinish=false;
				repaint();
			}
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
