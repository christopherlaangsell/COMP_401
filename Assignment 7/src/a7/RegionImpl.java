package a7;

public class RegionImpl implements Region {

	private Coordinate c1, c2;
	private int h,w;
	
	
	
	public RegionImpl(Coordinate a, Coordinate b) {
		if (a.getX() < 0 || a.getY() < 0 || b.getX() < 0 || b.getY() < 0) {
			throw new IllegalArgumentException("Illegal arguement.");
		}
		
		c1 = a;
		c2 = b;
		
		if(c1.getX() > c2.getX()) {
			w = c1.getX()-c2.getX();
		} else {
			w = c2.getX()-c1.getX();
		}
		
		if(c1.getY()>c2.getY()) {
			h = c1.getY()-c2.getY();
		} else {
			h = c2.getY()-c1.getY();
		}
		
	}



	@Override
	public Coordinate getUpperLeft() {
		int x = 0;
		int y = 0;
		
		if(c1.getX()>c2.getX() ) {
			 x = c2.getX();
		} else {
			x = c1.getX();
		}
		
		if(c1.getY()<c2.getY()) {
			y=c1.getY();
		} else {
			y = c2.getY();
		}
		
		return new Coordinate(x,y);
		
		
		
	}



	@Override
	public Coordinate getLowerRight() {
		int x = 0;
		int y = 0;
		
		if(c1.getX()<c2.getX() ) {
			 x = c2.getX();
		} else {
			x = c1.getX();
		}
		
		if(c1.getY()>c2.getY()) {
			y=c1.getY();
		} else {
			y = c2.getY();
		}
		
		return new Coordinate(x,y);
		
	}



	@Override
	public int getTop() {
		return getUpperLeft().getY();
	}



	@Override
	public int getBottom() {
		return getLowerRight().getY();
	}



	@Override
	public int getLeft() {
		return getUpperLeft().getX();
	}



	@Override
	public int getRight() {
		return getLowerRight().getX();
	}



	@Override
	public Region intersect(Region other) throws NoIntersectionException {
		if(other==null) {
			throw new NoIntersectionException();
		}
		
		
		
		
		int left,right,bottom,top;
		
		left = Math.max(this.getLeft(), other.getLeft());
		right = Math.min(this.getRight(), other.getRight());
		top = Math.max(this.getTop(), other.getTop());
		bottom = Math.min(this.getBottom(), other.getBottom());
		
		if (this.getBottom() < top || this.getTop() > bottom) {
			throw new NoIntersectionException();
		}
		if (this.getRight()<left || this.getLeft() > right) {
			throw new NoIntersectionException();
		}
		
		return new RegionImpl(new Coordinate(left,top), new Coordinate(right,bottom));	
	}



	@Override
	public Region union(Region other) {
		if(other==null) {
			return this;
		}
		
		int left = Math.min(this.getLeft(), other.getLeft());
		int right = Math.max(this.getRight(), other.getRight());
		int top = Math.min(this.getTop(), other.getTop());
		int bottom = Math.max(this.getBottom(), other.getBottom());
		
		return new RegionImpl(new Coordinate(left, top), new Coordinate(right, bottom));
	}
	
	
	
}
