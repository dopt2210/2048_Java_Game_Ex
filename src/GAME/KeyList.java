package GAME;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyList implements KeyListener {

	Game game;
	JFrame frame;

	public KeyList(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		playState(code);

	}

	// Với mỗi lần nhấn phim di chuyển sẽ kiểm tra down() và trả về turn() đồng
	// nghĩa với việc lật và đảo board sau mỗi lần, sau đó sẽ tạo bước mới rồi hiển
	// thị lại board, kiểm tra có end thì thực hiện end()
	public void playState(int code) {
		if (!game.ge.run)
			return;

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if (!game.ge.down())
				return;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			game.ge.turn(2);
			if (!game.ge.down()) {
				game.ge.turn(2);
				return;
			}
			game.ge.turn(2);
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			game.ge.turn(1);
			if (!game.ge.down()) {
				game.ge.turn(3);
				return;
			}
			game.ge.turn(3);
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			game.ge.turn(3);
			if (!game.ge.down()) {
				game.ge.turn(1);
				return;
			}
			game.ge.turn(1);
		}

		game.ge.newStep();
		game.ge.FrameShow();
		if (!game.ge.CheckEnd()) {
			game.ge.End();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
