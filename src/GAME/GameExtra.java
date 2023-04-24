package GAME;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import DAO.DAOData;
import DATA.Data;

public class GameExtra {
	protected int[][] board;
	public boolean run = false;
	String fName = "config.txt";
	File file = new File(fName);

	Game game;

	public GameExtra(Game game) {
		this.game = game;
	}
	
	//Khởi tạo board
	public void Init() {
		board = new int[game.boardNum][game.boardNum];
		game.state = game.playGame;
		run = true;
		newStep();
		FrameShow();
	}

	//Tạo board mới
	public void newStep() {
		int x, y;
		do {
			x = (int) (Math.random() * 100) % game.boardNum;
			y = (int) (Math.random() * 100) % game.boardNum;
		} while (board[x][y] != 0);

		board[x][y] = (int) (Math.random() * 1000) % 1000 < 800 ? 2 : 4;
	}

	//Hiển thị board
	public void FrameShow() {
		for (int i = 0; i < game.boardNum * game.boardNum; i++) {
			int x = i / game.boardNum, y = i % game.boardNum;
			game.board.ChangeBoard(x, y, board[x][y]);
		}
	}

	//Trả về kết quả board
	public int getPointResult() {
		game.playPoint = 0;
		for (int i = 0; i < game.boardNum * game.boardNum; i++)
			game.playPoint += board[i / game.boardNum][i % game.boardNum];
		return game.playPoint;
	}

	//Lưu tiến trình board vào file config.txt
	public void saveBoard() {
		if(game.state == game.playGame) {
			try {
				FileWriter fw = new FileWriter(file);
				try (BufferedWriter bw = new BufferedWriter(fw)) {
					for (int i = 0; i < game.boardNum; i++) {
						for (int j = 0; j < game.boardNum; j++) {
							bw.write("" + board[i][j] + " ");
						}
						bw.newLine();
					}
					bw.close();
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Hiển thị tiến trình board đã lưu ở file config.txt
	public void loadBoard() {
		try {
			Scanner sc = new Scanner(file);
			for (int i = 0; i < game.boardNum; i++) {
				String line = sc.nextLine();
				String[] br = line.split(" ");

				for (int j = 0; j < game.boardNum; j++) {
					board[i][j] = Integer.parseInt(br[j]);
					FrameShow();
					
				}
			}
			if(!CheckEnd()) {
				JOptionPane.showMessageDialog(null,"No step found !!"," Game already end", JOptionPane.WARNING_MESSAGE);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//Xuât thông báo end và các thủ tục khác
	@SuppressWarnings("null")
	public void End() {
		int point = 0;
		String name;
		long time = 0;
		long minute = 0;
		long second = 0;
		for (int i = 0; i < game.boardNum*game.boardNum; i++)
			point += board[i / game.boardNum][i % game.boardNum];
		time = (System.currentTimeMillis() - game.playTime);
		second = time / 1000;
		if (second > 60) {
			minute = second % 60;
			second = time / 60;
		}
		JOptionPane.showMessageDialog(null, "Total point: " + point + " Time: " + minute + "m: " + second + "s",
				"Game Over", JOptionPane.WARNING_MESSAGE);
		do{
		    name = JOptionPane.showInputDialog("Enter your name: ");
		}while(name == null || name.isEmpty());
		Data dt = new Data(name, point, (int) time / 1000);
		DAOData.getINTC().insert(dt);
		game.listData.add(dt);
	}

	//Kiểm tra đã hết game chưa
	public boolean CheckEnd() {
		//Kiểm tra các ô có giống ô bên cạnh hoặc bằng 0 hay không
		//Đầu tiên kiểm tra 9 ô trên trái 
		for (int x = 0; x < (game.boardNum - 1); x++) {
			for (int y = 0; y < (game.boardNum - 1); y++) {
				if (board[x][y] == board[x][y + 1] || board[x][y] == 0)
					return true;
				if (board[x][y] == board[x + 1][y] || board[x][y] == 0)
					return true;
			}
			//Kiểm tra hàng dưới cùng
			if (board[(game.boardNum - 1)][x] == board[(game.boardNum - 1)][x + 1]
					|| board[(game.boardNum - 1)][x] == 0)
				return true;
			//Kiểm tra cột phải cùng
			if (board[x][(game.boardNum - 1)] == board[x + 1][(game.boardNum - 1)]
					|| board[x][(game.boardNum - 1)] == 0)
				return true;
		}
		//Kiểm tra lại ô cuối cùng
		if (board[(game.boardNum - 1)][(game.boardNum - 1)] == 0)
			return true;
		return run = false;
	}

	//Hàm logic thực hiện thuật toán tính toán cài đặt board
	public boolean down() {
		//Kiểm tra nút có được thực hiện
		boolean enable = false;
		for (int x = 0; x < game.boardNum; x++) {
			int t = 0;
			for (int y = (game.boardNum - 1); y >= 0; y--) {
				if (enable)
					break;
				if (t == 0)
					t = board[x][y];
				else {
					if (board[x][y] == 0 || board[x][y] == t)
						enable = true;
					else
						t = board[x][y];
				}
			}
		}
		//Ngược lại thì dồn các ô xuống dưới
		if (!enable)
			return false;
		for (int x = 0; x < game.boardNum; x++)
			for (int y = (game.boardNum - 2); y >= 0; y--)
				if (board[x][y] == 0) {
					for (int t = y; t < (game.boardNum - 1); t++)
						board[x][t] = board[x][t + 1];
					board[x][(game.boardNum - 1)] = 0;
				}
		//Cộng các ô giống nhau 
		for (int x = 0; x < game.boardNum; x++) {
			if (board[x][0] == board[x][1] && board[x][0] != 0) {
				board[x][0] *= 2;
//				board[x][0] *= board[x][0];
				board[x][1] = board[x][2];
				board[x][2] = board[x][3];
				board[x][3] = board[x][4];
				board[x][4] = 0;
			}
			if (board[x][1] == board[x][2] && board[x][1] != 0) {
				board[x][1] *= 2;
//				board[x][1] *= board[x][1];
				board[x][2] = board[x][3];
				board[x][3] = board[x][4];
				board[x][4] = 0;

			} else if (board[x][2] == board[x][3] && board[x][2] != 0) {
				board[x][2] *= 2;
//				board[x][2] *= board[x][2];
				board[x][3] = board[x][4];
				board[x][4] = 0;
			} else if (board[x][3] == board[x][4] && board[x][3] != 0) {
				board[x][3] *= 2;
//				board[x][2] *= board[x][2];
				board[x][4] = 0;
			}
		}
		return true;
	}

	//Thực hiện xoay board 90 độ sang trái
	public void turn(int time) {
		for (int i = 0; i < time; i++) {
			int[][] temp = new int[game.boardNum][game.boardNum];
			for (int x = 0; x < game.boardNum; x++)
				for (int y = 0; y < game.boardNum; y++)
					temp[x][y] = board[y][(game.boardNum - 1) - x];
			board = temp;
		}
	}

}
