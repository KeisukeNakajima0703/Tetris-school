package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JPanel implements Runnable{

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final int CELL_SIZE = 30;
	public static final int BOARD_X = 100;
	public static final int BOARD_Y = 100;
	public static final Color backColor = Color.BLACK;
	public static final int DOWN_INTERVAL = 10;
	private Mediater game;

	public Tetris(){
		setPreferredSize(new Dimension(Tetris.WIDTH, Tetris.HEIGHT));
		setBackground(backColor);
		setFocusable(true);

		game = new Mediater();
		MyKeyListener mkl = new MyKeyListener(game);
		addKeyListener(mkl);
		Thread gameLoop = new Thread(this);
		gameLoop.start();
	}

	public static void main(String[] args) {
		JFrame tetris = new JFrame("Tetris");
		tetris.getContentPane().add(new Tetris());
		tetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetris.pack();
		tetris.setVisible(true);
	}

	@Override
	public void run() {
		int counter = 0;
		while(true){
			counter++;
			game.update(counter % Tetris.DOWN_INTERVAL == 0);
			repaint();
			try{
				Thread.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(backColor);
		g.fillRect(0, 0, Tetris.WIDTH, Tetris.HEIGHT);
		game.draw(g);
	}

}
