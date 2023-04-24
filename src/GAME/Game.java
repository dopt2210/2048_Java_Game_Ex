package GAME;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DATA.Data;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;

	public final int width = 440;
	public final int height = 300;
	public final int boardNum = 5;

	public Board board = new Board(this);
	public GameExtra ge = new GameExtra(this);
	public Screen screen = new Screen(this);
	KeyList key = new KeyList(this);
	Sound music = new Sound();
	public ArrayList<Data> listData;
	public Dimension D = new Dimension(width, height);

	public int playPoint = 0;
	public long playTime;

	public int state;
	public int openGame = 1;
	public int playGame = 2;

	public Game(JFrame frame) {
		this.setPreferredSize(D);
		JLabel image = new JLabel(new ImageIcon(setIMG("/images/IMG_0525.JPG")));
		image.setPreferredSize(D);
		frame.setContentPane(image);
		frame.setBackground(Color.black);
		frame.setIconImage(setIMG("/images/board.jpg"));
		this.setFocusable(true);
		playTime = System.currentTimeMillis();
		state = openGame;
		this.addKeyListener(key);
		setUp(frame);
		addBoard(frame);
	}

	public void addBoard(JFrame frame) {
		board.openBoard(frame);
		ge.Init();
		 playMusic(1);
	}

	public void setUp(JFrame frame) {
		screen.addButtonNewGame(frame);
		screen.addButtonShowScore(frame);
		screen.addCurrentPoint(frame);
		screen.addButtonQuit(frame);
		screen.addLoad(frame);
		screen.addImg(frame);
	}

	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public Image setIMG(String imgName) {
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(imgName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}
