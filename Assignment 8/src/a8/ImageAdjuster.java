package a8;

import java.awt.BorderLayout;

import java.awt.GridLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ImageAdjuster extends JPanel implements ChangeListener {
	
		private PictureView picture_view;
		private Picture picture;
		private JLabel blurLabel, saturationLabel, brightnessLabel;
		private JSlider blurSlider, saturationSlider, brightnessSlider;
		
		
		public ImageAdjuster(Picture picture) {
			
			//creating new picture view object
			picture_view = new PictureView(picture.createObservable());
			this.picture = picture;
			setLayout(new BorderLayout());
			
			
			//Info panel will have adjustment info in center, info labels to left
			JPanel info = new JPanel();
			info.setLayout(new BorderLayout());
			JPanel adjustmentInfo = new JPanel();
			adjustmentInfo.setLayout(new BorderLayout());
			info.add(adjustmentInfo, BorderLayout.CENTER);
			JPanel infoLabels = new JPanel();
			infoLabels.setLayout(new GridLayout(3,1));	
			info.add(infoLabels, BorderLayout.WEST);
			
			//Don't know if it's redundant to make another picture object???
			picture_view = new PictureView(picture.createObservable());
			add(picture_view, BorderLayout.CENTER);
			
			blurSlider = new JSlider(0, 5, 0);
			blurSlider.setPaintLabels(true);
			blurSlider.setPaintTicks(true);
			blurSlider.setSnapToTicks(true);
			blurSlider.setMajorTickSpacing(1);
			
			
			saturationSlider = new JSlider(-100, 100, 0);
			saturationSlider.setPaintLabels(true);
			saturationSlider.setPaintTicks(true);
			saturationSlider.setMajorTickSpacing(25);
			
			brightnessSlider = new JSlider(-100, 100, 0);
			brightnessSlider.setPaintLabels(true);
			brightnessSlider.setPaintTicks(true);
			brightnessSlider.setMajorTickSpacing(25);
			
			//Sliders compose adjustment info
			adjustmentInfo.add(blurSlider, BorderLayout.NORTH);
			adjustmentInfo.add(saturationSlider, BorderLayout.CENTER);
			adjustmentInfo.add(brightnessSlider, BorderLayout.SOUTH);
			
			blurLabel = new JLabel("Blur:" , SwingConstants.LEFT);
			saturationLabel = new JLabel("Saturation:", SwingConstants.LEFT);
			brightnessLabel = new JLabel("Brightness:", SwingConstants.LEFT);
			
			infoLabels.add(blurLabel);
			infoLabels.add(saturationLabel);
			infoLabels.add(brightnessLabel);
			
			add(info, BorderLayout.SOUTH);
			
			blurSlider.addChangeListener(this);
			saturationSlider.addChangeListener(this);
			brightnessSlider.addChangeListener(this);
			
		}
		
		
		public void stateChanged(ChangeEvent e) {
			
			JSlider slider = (JSlider) e.getSource();
			
			
			if(!slider.getValueIsAdjusting() ) {
			
			Picture blur = this.blur(blurSlider.getValue(), picture);
			Picture brighten = this.brighten(brightnessSlider.getValue(), blur);
			Picture saturate = this.saturate(saturationSlider.getValue(), brighten);
			picture_view.setPicture(saturate.createObservable());
			
			}
			
		}
		
		public Picture blur(int blurscalar, Picture p) {
			Picture blurCopy = new PictureImpl(p.getWidth(),p.getHeight());
			
			if(blurscalar == 0) { return p; }
			
			for(int i=0; i<p.getHeight(); i++) {
				for(int j=0; j<p.getWidth(); j++) {
					
					double count = 0.0;
					double redSum = 0.0;
					double greenSum = 0.0;
					double blueSum = 0.0;
					
					for(int k = -blurscalar; k<=blurscalar; k++) {
						for(int l= -blurscalar; l<=blurscalar; l++) {
							try {
								redSum += p.getPixel(j+l, i+k).getRed();
								greenSum += p.getPixel(j+l, i+k).getGreen();
								blueSum += p.getPixel(j+l, i+k).getBlue();
								count = count+1.0;
								
							} catch (RuntimeException e) {
								
							}
							
						}
					}
					
					
					blurCopy.setPixel(  j, i, new ColorPixel(redSum/count, greenSum/count, blueSum/count)  );
					
					
				}
			}
			
			return blurCopy;
		}
		
		
		public Picture brighten(double brightenscalar, Picture p) {
			Picture brightenCopy = new PictureImpl(p.getWidth(), p.getHeight());
			
			for (int i = 0; i<p.getHeight(); i++) {
				for(int j = 0; j<p.getWidth(); j++) {
					
					if(brightenscalar > 0) {
						brightenCopy.setPixel(j, i, p.getPixel(j, i).lighten(brightenscalar / 100));
					} else {
						brightenCopy.setPixel(j, i, p.getPixel(j, i).darken(-brightenscalar / 100));
					}
					
					
				}
			}
			
			
			return brightenCopy;
			
		}
		
		
		public Picture saturate(double factor, Picture p) {
			Picture saturateCopy = new PictureImpl(p.getWidth(),p.getHeight());
			
			if( factor == 0) { return p;}
			
			for(int i=0; i<p.getHeight(); i++) {
				for(int j=0; j<p.getWidth();j++) {
					double red = p.getPixel(j,i).getRed();
					double green = p.getPixel(j,i).getGreen();
					double blue = p.getPixel(j,i).getBlue();
					double brightness = p.getPixel(j, i).getIntensity();
					
					try {
						if (factor < 0) {
							red = red * (1.0+(factor / 100.0) - brightness * factor /100.0);
							green = green * (1.0+(factor / 100.0) - brightness * factor /100.0);
							blue = blue * (1.0+(factor / 100.0) - brightness * factor /100.0);
							saturateCopy.setPixel(j, i, new ColorPixel(red, green, blue));
							
						}
					} catch (RuntimeException e) {
						continue;
					}
					
					if (factor > 0) {
						double largestValue = Math.max(red, green);
						largestValue = Math.max(largestValue, blue);
						
								
						red = red * (largestValue+(1.0-largestValue)*(factor / 100.0) / largestValue);
						green = green * (largestValue+(1.0-largestValue)*(factor / 100.0) / largestValue);
						blue = blue * (largestValue+(1.0-largestValue)*(factor / 100.0) / largestValue);
						saturateCopy.setPixel(j, i, new ColorPixel(red, green, blue));
					}
				} 
				}
			
			
			return saturateCopy;
			}
}
	
	


