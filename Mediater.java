import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Mediater {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int ROTATE = 4;

	private Board _board = new Board();
	private Block _block;
	private Block _holder = null;
	private int _x, _y;
	private boolean _turn;
	private boolean _hold;

	public Mediater(){
		_board = new Board();
		_block = createBlock();
		_x = Board.WIDTH / 2;
		_y = 0;
		_turn = false;
		_hold = false;
	}

	public int update(boolean flag){
		int line = 0;
		if(flag){
			if(_board.movable(_block,_x,_y + 1)) _y++;
			else _turn = true;
		}
		if(_turn){
			_board.setBlock(_block, _x, _y);
			line = _board.checkBoard(_y);
			_block = createBlock();
			_x = Board.WIDTH / 2;
			_y = 0;
			_turn = false;
			_hold = false;
		}
		return line;
	}

	public boolean isGameOver(){
		return _board.isGameOver();
	}

	public void draw(Graphics g){
		_board.draw(g);
		_block.draw(g, _x, _y);
		g.setColor(Color.WHITE);
		g.drawRect(Tetris.BOARD_X + Tetris.HOLD_X * Tetris.CELL_SIZE,
					Tetris.BOARD_Y + Tetris.HOLD_Y * Tetris.CELL_SIZE,
					Tetris.CELL_SIZE * Block.SIZE, Tetris.CELL_SIZE * Block.SIZE);//1セルの長さx4
		g.drawString("Hold_block", Tetris.BOARD_X + Tetris.HOLD_X * Tetris.CELL_SIZE, Tetris.BOARD_Y + Tetris.HOLD_Y * Tetris.CELL_SIZE);
		if(_holder != null) _holder.draw(g, Tetris.HOLD_X, Tetris.HOLD_Y);
	}

	//プレイヤーが操作時に呼び出されるメソッド
	public void move(int active){
		switch(active){
		case UP:
			_y = _board.fallDown(_block, _x, _y);
			break;
		case DOWN:
			if(_board.movable(_block, _x, _y + 1)) _y++;
			else _turn = true;
			break;
		case LEFT:
			if(_board.movable(_block, _x - 1, _y)) _x--;
			break;
		case RIGHT:
			if(_board.movable(_block, _x + 1, _y)) _x++;
			break;
		case ROTATE:
			if(_board.movable(_block.getRotationBlock(), _x, _y)) _block.rotation();
			break;
		default:
		}
	}

	public Block createBlock(){
		Random rand = new Random();
		int type = rand.nextInt(Block.TYPE_NUM);
		return new Block(type);
	}

	public void holdBlock() {
		if(_hold) return ;
		_hold = true;
		if(_holder == null){
			_holder = _block;
			_block = createBlock();
		}else{
			Block tmp = _block;
			_block = _holder;
			_holder = tmp;
		}
	}
}
