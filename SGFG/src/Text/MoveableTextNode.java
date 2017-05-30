package Text;

import Sprite.IMoveable;

public abstract class MoveableTextNode extends TextNode implements IMoveable{
	protected final int SPEED = 1;
	
	@Override
	public void move() {
		this.y -= SPEED;
		if(this.y  < 0) {
			setVisible(false);
		}
	}
}
