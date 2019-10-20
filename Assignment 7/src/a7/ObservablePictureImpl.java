package a7;

import java.util.ArrayList;
import java.util.Iterator;

public class ObservablePictureImpl implements ObservablePicture {
	
	private Picture p;
	private ArrayList<ROIObserverRegion> observers = new ArrayList<ROIObserverRegion>();
	private boolean suspended = false;
	private ArrayList<Coordinate> changed_coordinates = new ArrayList<Coordinate>();
	
	public ObservablePictureImpl(Picture p) {
		this.p=p;
		
	}


	@Override
	public int getWidth() {
		return p.getWidth();
	}

	@Override
	public int getHeight() {
		return p.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return p.getPixel(x,y);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		return p.getPixel(c);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		this.setPixel(new Coordinate(x,y), p);

	}

	@Override
	public void setPixel(Coordinate c, Pixel p) {
		this.p.setPixel(c, p);
		
		for (ROIObserverRegion ob_registered : this.observers) { 
			
				if(suspended) {
					if(changed_coordinates.indexOf(c) == -1) {
						changed_coordinates.add(c);
					}
					
				} else {
				for (Region region : ob_registered.getRegions()) {
					RegionImpl pixelregion = new RegionImpl(c,c);
					
					try {
						Region intersection = pixelregion.intersect(region);
						ob_registered.notify(this, intersection);
					} catch (NoIntersectionException e) {
						
					}
				}
				
				}
			}
		
		

	}

	@Override
	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return p.extract(xoff, yoff, width, height);
	}

	@Override
	public SubPicture extract(Coordinate a, Coordinate b) {
		return p.extract(a, b);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		if( observer == null || r == null) {
			throw new IllegalArgumentException();
		}
		
		ROIObserverRegion observerin = null;
		
		for (ROIObserverRegion ob_registered : this.observers) {
			if(ob_registered.getObserver() == observer) {
				observerin = ob_registered;
				
				break;
			}			
		}
		
		if(observerin != null) {
			observerin.addRegion(r);
		} else {
			observerin = new ROIObserverRegionImpl(observer);
			observerin.addRegion(r);
			observers.add(observerin);
		}

	}

	@Override
	public void unregisterROIObservers(Region r) {
		if( r == null) {
			throw new IllegalArgumentException();
		}
		
		for (
			Iterator<ROIObserverRegion> iter = this.observers.iterator();
			iter.hasNext();
			) { 
			
			for (Region region : iter.next().getRegions()) {
				try {
					//FIXME Does this break?
					region.intersect(r);
					iter.remove();
					break;
					
				} catch (NoIntersectionException e) {
					
				}
			}
			
			
		}

	}

	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		if( observer == null) {
			throw new IllegalArgumentException();
		}
		
		for (
				Iterator<ROIObserverRegion> iter = this.observers.iterator();
				iter.hasNext();				
				) {
			if( observer == iter.next().getObserver()) {
				iter.remove();
			}
		}
		
			

	}

	@Override
	public ROIObserver[] findROIObservers(Region r) {
		
		ArrayList<ROIObserver> _observers = new ArrayList<ROIObserver>(); 
		
		for (
				Iterator<ROIObserverRegion> iter = this.observers.iterator();
				iter.hasNext();				
				) {
			
			ROIObserverRegion it = iter.next();
			
			
			
			for (Region region : it.getRegions()) {
				
				try {
					region.intersect(r);
					_observers.add(it.getObserver());
					
					break;
					
				} catch (NoIntersectionException e) {
					
				}
					
				
			}
		}
		
		return _observers.toArray(new ROIObserver[_observers.size()] );
	}

	@Override
	public void suspendObservable() {
		suspended = true;

	}

	@Override
	public void resumeObservable() {
		suspended = false;
		Region change = null;
		
		for (int i=0; i<changed_coordinates.size(); i++) {	
		RegionImpl pixel_change = new RegionImpl(changed_coordinates.get(i), changed_coordinates.get(i));
		if(change == null) {
			change = pixel_change;
		} else {
		
		change = change.union(pixel_change);
		}}
		
		for (ROIObserverRegion ob_registered : this.observers) { 
		for (Region region : ob_registered.getRegions()) {
			try {
				Region intersection = change.intersect(region);
				ob_registered.notify(this, intersection);
			} catch (NoIntersectionException e) {
				
			}
		}
		}
		changed_coordinates.clear();
	}

}
