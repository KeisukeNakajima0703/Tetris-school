package tetris;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

	private Color _color;
	private boolean _exist;

	public Cell(){
		_color = Tetris.backColor;
		_exist = false;
	}
	public void setCell(Color color){
		_color = color;
		_exist = true;
	}

	public void setCell(Cell cell){
		_color = cell._color;
		_exist = cell._exist;
	}
	public boolean isExist(){ return _exist; }

	public void draw(Graphics g, int x, int y) {
		g.setColor(_color);
		g.fillRect(x, y, Tetris.CELL_SIZE, Tetris.CELL_SIZE);
	}

}
