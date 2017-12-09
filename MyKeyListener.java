package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

	private Mediater _mediater;

	public MyKeyListener(Mediater m){
		_mediater = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			_mediater.move(Mediater.UP);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			_mediater.move(Mediater.DOWN);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			_mediater.move(Mediater.LEFT);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			_mediater.move(Mediater.RIGHT);
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			_mediater.move(Mediater.ROTATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
