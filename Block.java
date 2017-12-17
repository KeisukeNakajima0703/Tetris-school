import java.awt.Color;
import java.awt.Graphics;

public class Block {

	public static final int WIDTH = 4;
	public static final int HEIGHT = 4;
	public static final int SIZE = 4;
	public static final int TYPE_I = 0;
	public static final int TYPE_O = 1;
	public static final int TYPE_J = 2;
	public static final int TYPE_L = 3;
	public static final int TYPE_Z = 4;
	public static final int TYPE_S = 5;
	public static final int TYPE_T = 6;
	public static final int TYPE_NUM = 7;

	private Cell[][] _block;

	public Block(int type){
		//配列初期化
		initBlock();
		//ブロックごとに生成
		switch(type){
		case TYPE_I:
			_block[0][1].setCell(Color.CYAN);
			_block[1][1].setCell(Color.CYAN);
			_block[2][1].setCell(Color.CYAN);
			_block[3][1].setCell(Color.CYAN);
			break;
		case TYPE_O:
			_block[1][1].setCell(Color.YELLOW);
			_block[1][2].setCell(Color.YELLOW);
			_block[2][1].setCell(Color.YELLOW);
			_block[2][2].setCell(Color.YELLOW);
			break;
		case TYPE_J:
			_block[1][2].setCell(Color.ORANGE);
			_block[2][2].setCell(Color.ORANGE);
			_block[3][1].setCell(Color.ORANGE);
			_block[3][2].setCell(Color.ORANGE);
			break;
		case TYPE_L:
			_block[1][1].setCell(Color.BLUE);
			_block[2][1].setCell(Color.BLUE);
			_block[3][1].setCell(Color.BLUE);
			_block[3][2].setCell(Color.BLUE);
			break;
		case TYPE_Z:
			_block[1][1].setCell(Color.RED);
			_block[1][2].setCell(Color.RED);
			_block[2][2].setCell(Color.RED);
			_block[2][3].setCell(Color.RED);
			break;
		case TYPE_S:
			_block[1][2].setCell(Color.GREEN);
			_block[1][3].setCell(Color.GREEN);
			_block[2][1].setCell(Color.GREEN);
			_block[2][2].setCell(Color.GREEN);
			break;
		case TYPE_T:
			_block[1][2].setCell(Color.MAGENTA);
			_block[2][1].setCell(Color.MAGENTA);
			_block[2][2].setCell(Color.MAGENTA);
			_block[2][3].setCell(Color.MAGENTA);
			break;
		default:
		}
	}

	public Block() {
		initBlock();
	}

	public void draw(Graphics g, int x, int y){
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				if(_block[i][j].isExist())
				_block[i][j].draw(g,Tetris.BOARD_X + (x+j)*Tetris.CELL_SIZE, Tetris.BOARD_Y + (y+i)*Tetris.CELL_SIZE);
	}

	public Cell getCell(int x, int y){
		return _block[y][x];
	}

	public Block getRotationBlock(){
		Block copy = new Block();
		for(int i = 0; i < Block.HEIGHT; i++)
			for(int j = 0; j < Block.WIDTH; j++)
				copy._block[i][j] = _block[Block.WIDTH - 1 - j][i];
		return copy;
	}
	public void rotation(){
		_block = getRotationBlock()._block;
	}

	private void initBlock(){
		_block = new Cell[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
			_block[i][j] = new Cell();
	}
}
