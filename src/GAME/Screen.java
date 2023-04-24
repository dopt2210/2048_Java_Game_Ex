package GAME;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import DAO.DAOData;

public class Screen {
	Game game;
	Font Serif = new Font("Serif", 1, 20);
	Graphics g2;
	Color cBack = new Color(156, 156, 156);
	Color cTran = new Color(0, 0, 0);
	Color cFont = new Color(255, 255, 255);

	public int cmdNum = 0;

	public Screen(Game game) {
		this.game = game;
	}

	public void addScorePanel(JFrame frame) {
		game.listData = DAOData.getINTC().selectAll();
		JDialog dialog = new JDialog();
		String[] columnNames = { "Name", "Point", "Time(s)" };
		Object[][] obj = new Object[game.listData.size()][3];

		for (int i = 0; i < game.listData.size(); i++) {
			obj[i][0] = game.listData.get(i).getName();
			obj[i][1] = game.listData.get(i).getPoint();
			obj[i][2] = game.listData.get(i).getTime();
		}
		JTable tbl = new JTable(obj, columnNames);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tbl.getColumnCount(); i++) {
			tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tbl.setFont(Serif);
		tbl.setRowHeight(40);
		JScrollPane scrollPane = new JScrollPane(tbl);
		dialog.add(scrollPane);
		dialog.setFont(Serif);
		dialog.setTitle("Score");
		dialog.setSize(360, 440);
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}

	public void addButtonNewGame(JFrame frame) {
		JLabel button = new JLabel("New Game", JLabel.CENTER);
		button.setBounds(320, 15, 100, 30);
		button.setBackground(cBack);
		button.setOpaque(true);
		button.setForeground(cFont);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				game.ge.Init();
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(cBack);
			}
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		frame.add(button);
	}

	public void addButtonShowScore(JFrame frame) {
		JLabel button = new JLabel("Score", JLabel.CENTER);
		button.setBounds(320, 55, 100, 30);
		button.setBackground(cBack);
		button.setOpaque(true);
		button.setForeground(cFont);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addScorePanel(frame);
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(cBack);
			}
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		frame.add(button);
	}

	public void addCurrentPoint(JFrame frame) {
		JLabel button = new JLabel("", JLabel.CENTER);
		button.setBounds(320, 95, 100, 30);
		button.setBackground(cBack);
		button.setOpaque(true);
		button.setForeground(cFont);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		button.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				button.setBackground(cBack);
				button.setText("");
			}
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				if (game.state == game.playGame) {
					String text = "";
					text = text.concat("Point " + game.ge.getPointResult());
					button.setText(text);
				}
			}
		});

		frame.add(button);
	}

	public void addButtonQuit(JFrame frame) {
		JLabel button = new JLabel("Quit", JLabel.CENTER);
		button.setBounds(320, 135, 100, 30);
		button.setBackground(cBack);
		button.setOpaque(true);
		button.setForeground(cFont);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				game.ge.saveBoard();
				System.exit(0);
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(cBack);
			}
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		frame.add(button);
	}

	public void addLoad(JFrame frame) {
		JLabel button = new JLabel("Load", JLabel.CENTER);
		button.setBounds(320, 175, 100, 30);
		button.setBackground(cBack);
		button.setOpaque(true);
		button.setForeground(cFont);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				game.ge.loadBoard();
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(cBack);
			}
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.black);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		frame.add(button);
	}

	public void addImg(JFrame frame) {
		JLabel button = new JLabel(new ImageIcon(game.setIMG("/images/logoBrand2.jpg")));
		button.setBounds(320, 220, 100, 56);
		button.setBorder(BorderFactory.createLineBorder(cFont));
		frame.add(button);
	}

	public void setOpacity(JLabel t, float op) {
		Graphics g = t.getGraphics();
		while (g != null) {
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op);
			((Graphics2D) g).setComposite(ac);
			g.setColor(t.getBackground());
			g.fillRect(0, 0, t.getWidth(), t.getHeight());

		}
	}

}
