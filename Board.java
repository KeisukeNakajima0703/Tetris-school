import java.awt.Color;
import java.awt.Graphics;

//100行のクラスはゴミ
public class Board {

	public static final int WIDTH = 10 + 2; //両サイドに+1ずつ
	public static final int HEIGHT = 20 + 2;//上下に+1ずつ

	private Cell[][] _board = new Cell[HEIGHT][WIDTH];

	//コンストラクタ
	public Board(){
		initBoard();
	}

	//一気にブロックを下へ設置する
	public int fallDown(Block block, int x, int y){
		int tmp = 0;
		while(movable(block, x, y + tmp + 1)){
			tmp++;
		}
		setBlock(block, x, y + tmp);
		return y + tmp;
	}

	//ゲームオーバー判定
	public boolean isGameOver(){
		boolean result = false;
		for(int i = 1; i < Board.WIDTH - 1; i++)
			if(_board[1][i].isExist()){
				result = true;
				break;
			}
		return result;
	}

	//ブロック設置後、ラインを消すか判断するメソッド
	public int checkBoard(int y){
		int line = 0;
		for(int i = 0; i < Block.HEIGHT ; i++){
			boolean delete = true;
			if(y + i == Board.HEIGHT - 1) break;
			for(int j = 1; j < WIDTH - 1; j++){
				if(!_board[y + i][j].isExist()){
					delete = false;
					break;
				}
			}
			if(delete){
				deleteLine(y + i);
				line++;
			}
		}
		return line;
	}

	//指定した行を消す(上のブロックを下にずらす)
	private void deleteLine(int y){
		for(int i = 0; i < y - 1; i++){
			for(int j = 1; j < Board.WIDTH - 1; j++){
				_board[y - i][j].setCell(_board[y - i - 1][j]);
			}
		}
	}

	//対象ブロックと座標を受け取り、盤上にブロックを固定する
	public void setBlock(Block block, int x, int y){
		for(int i = 0; i < Block.HEIGHT; i++)
			for(int j = 0; j < Block.WIDTH; j++)
				if(block.getCell(j, i).isExist())
					_board[y + i][x + j].setCell(block.getCell(j, i));
	}

	//対象ブロックと座標を元に盤上に存在できるかどうかを返す
	public boolean movable(Block block, int x, int y){
		for(int i = 0; i < Block.HEIGHT; i++)
			for(int j = 0; j < Block.WIDTH; j++)
				if(block.getCell(j,i).isExist())
					if(indexIsOk(x + j, y + i) && _board[y + i][x + j].isExist())
						return false;

		return true;
	}

	//盤面の初期化
	private void initBoard(){
		//配列の初期化
		for(int i = 0; i < HEIGHT; i++)
			for(int j = 0; j < WIDTH; j++)
				_board[i][j] = new Cell();
		//縦壁の作成
		for(int i = 0; i < HEIGHT; i++){
			_board[i][0].setCell(Color.GRAY);
			_board[i][WIDTH - 1].setCell(Color.GRAY);
		}
		//底壁の作成
		for(int i = 0; i < WIDTH; i++)
			_board[HEIGHT - 1][i].setCell(Color.GRAY);
	}

	//指定した座標が存在できるかどうかを返す
	private boolean indexIsOk(int x, int y){
		 return (x >= 0 && x < WIDTH ) && (y >= 0 && y < HEIGHT );
	}

	//描画関数
	public void draw(Graphics g){
		for(int i = 0; i < Board.HEIGHT; i++)
			for(int j = 0; j < Board.WIDTH; j++)
				_board[i][j].draw(g, Tetris.BOARD_X + Tetris.CELL_SIZE * j, Tetris.BOARD_Y + Tetris.CELL_SIZE * i);
	}
}
