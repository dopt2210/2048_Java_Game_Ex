package GAME;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board {
	JLabel board[][] = new JLabel[5][5];
	Game game;

	public Board(Game game) {
		this.game = game;
	}
	
	//Mở board
	public void openBoard(JFrame frame) {
		for (int i = 0; i < game.boardNum * game.boardNum; i++) {
			int x = (i / game.boardNum), y = (i % game.boardNum);
			JLabel t = new JLabel("", JLabel.CENTER);
			board[x][y] = t;
			t.setSize(50, 50);
			t.setLocation(10 + x * 55, 15 + y * 55);
			t.setBackground(game.screen.cBack);
			t.setOpaque(true);
			t.setBorder(BorderFactory.createLineBorder(game.screen.cFont));
			t.setFont(new Font("Serif", 1, 20));
			frame.add(t);
		}
	}

	//Thay đổi màu của board sau mỗi bước với x, y là tọa độ ô, d là số trong ô
	public void ChangeBoard(int x, int y, int d) {
		Color c, cl = null;
		int FontSize;
		switch (d) {
		case 0:
			c = game.screen.cBack;
			FontSize = 30;
			break;
		case 2:
			c = new Color(181, 181, 181);
			cl = new Color(0, 0, 0);
			FontSize = 32;
			break;
		case 4:
			c = new Color(255, 182, 193);
			cl = new Color(0, 0, 0);
			FontSize = 32;
			break;
		case 8:
			c = new Color(0, 255, 255);
			cl = new Color(0, 0, 0);
			FontSize = 32;
			break;
		case 16:
			c = new Color(255, 215, 0);
			cl = new Color(0, 0, 0);
			FontSize = 28;
			break;
		case 32:
			c = new Color(127, 255, 0);
			cl = new Color(255, 255, 255);
			FontSize = 28;
			break;
		case 64:
			c = new Color(0, 0, 255);
			cl = new Color(255, 255, 255);
			FontSize = 28;
			break;
		case 128:
			c = new Color(255, 127, 0);
			cl = new Color(255, 255, 255);
			FontSize = 24;
			break;
		case 256:
			c = new Color(255, 0, 0);
			cl = new Color(255, 255, 255);
			FontSize = 24;
			break;
		case 512:
			c = new Color(138, 43, 226);
			cl = new Color(255, 255, 255);
			FontSize = 24;
			break;
		case 1024:
			c = new Color(139, 58, 58);
			cl = new Color(255, 255, 255);
			FontSize = 20;
			break;
		case 2048:
			c = new Color(28, 28, 28);
			cl = new Color(255, 255, 255);
			FontSize = 20;
			break;
		case 4096:

		case 8192:

		default:
			c = new Color(253, 61, 58);
			FontSize = 20;
			break;

		}

		board[x][y].setBackground(c); // Đặt màu nền tương ứng
		board[x][y].setText(d == 0 ? " " : String.valueOf(d)); // Đặt số sẽ được hiển thị trong hộp
		board[x][y].setFont(new Font("Game Over", 1, FontSize)); // Đặt hiệu ứng hiển thị phông chữ và điều chỉnh kích
																	// thước phù hợp
		board[x][y].setForeground(cl); // Đặt màu chữ
	}

}
