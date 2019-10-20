package a7;

import java.util.ArrayList;

public class ROIObserverRegionImpl implements ROIObserverRegion {

	
	private ROIObserver o;
	private ArrayList<Region> regions = new ArrayList<Region>();
	
	public ROIObserverRegionImpl(ROIObserver o) {
		this.o = o;
		
	}
	
	
	public void addRegion(Region r) {
		regions.add(r);
	}
	
	public void removeRegion(Region r) {
		regions.remove(r);
	}
	
	public ROIObserver getObserver() {
		return o;
	}
	
	@Override
	public void notify(ObservablePicture picture, Region changed_region) {
		o.notify(picture, changed_region);
		
	}


	@Override
	public Region[] getRegions() {
		return regions.toArray(new Region[regions.size()]);
	}

	
	
	

}
