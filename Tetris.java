import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

//

public class Tetris extends JPanel implements Runnable{

	public static final int WIDTH = 1000; //画面横幅
	public static final int HEIGHT = 1000; //画面縦幅
	public static final int CELL_SIZE = 20; //1マスの一辺の長さ
	public static final int BOARD_X = 100;
	public static final int BOARD_Y = 100;
	public static final int HOLD_X = Board.WIDTH + 2;
	public static final int HOLD_Y = 0;
	public static final Color backColor = Color.BLACK;
	public static final int DOWN_INTERVAL = 10;
	private Mediater game;
	private long time;
	private int score = 0;
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
		int counter = 1;
		time = System.currentTimeMillis();
		while(!game.isGameOver()){
			score += game.update(counter%DOWN_INTERVAL == 0) * 100;
			counter++;
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
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf((System.currentTimeMillis() - time)/1000)+"秒", 50, 50);
		g.drawString("score:"+String.valueOf(score), 100, 50);
		game.draw(g);
	}

}
