import java.awt.Graphics;
import java.util.LinkedList;

public class BlockList {

	private LinkedList<Block> _blockList;
	private Mediater _mediater;
	private static final int LIST_NUM = 5;

	public BlockList(Mediater m){
		_mediater = m;
		_blockList = new LinkedList<Block>();
		for(int i = 0; i < LIST_NUM; i++)
			_blockList.offer(m.createBlock());
	}

	public Block getNextBlock(){
		_blockList.offer(_mediater.createBlock());
		return _blockList.poll();
	}

	public void draw(Graphics g){

	}
}
