import java.awt.Graphics;
import java.util.Random;

public class Mediater {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int ROTATE = 4;

	private Board _board = new Board();
	private Block _block = new Block(Block.TYPE_I);
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

	public void update(boolean flag){
		if(flag){
			if(_board.movable(_block,_x,_y + 1)) _y++;
			else _turn = true;
		}
		if(_turn){
			_board.setBlock(_block, _x, _y);
			_board.checkBoard(_y);
			_block = createBlock();
			_x = Board.WIDTH / 2;
			_y = 0;
			_turn = false;
			_hold = false;
		}
	}

	public void draw(Graphics g){
		_board.draw(g);
		_block.draw(g, _x, _y);
		if(_holder != null) _holder.draw(g, Tetris.HOLD_X, Tetris.HOLD_Y);
	}

	//プレイヤーが操作時に呼び出されるメソッド
	public void move(int active){
		switch(active){
		case UP:
			if(_board.movable(_block, _x, _y - 1)) _y--;
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
