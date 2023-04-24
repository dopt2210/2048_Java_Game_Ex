package GAME;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("2048 Board");
		frame.setSize(500, 500);
		
		Game game = new Game(frame);
//		game.addButtonNewGame(frame);
//		game.addButtonShowScore(frame);
		frame.add(game);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

//		game.GameOn(frame);
	}
}
