package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PixelInspector extends JPanel implements MouseListener {
		private PictureView picture_view;
		private Picture picture;
		//private double xValue, yValue, redValue, blueValue, greenValue, brightnessValue;
		private JLabel xLabel, yLabel, redLabel, greenLabel, blueLabel, brightnessLabel;
		
		public PixelInspector(Picture picture) {
			this.picture = picture;
			setLayout(new BorderLayout());
			
			JPanel pixelInfo = new JPanel();
			pixelInfo.setLayout(new GridLayout(6,1));
			
			
			picture_view = new PictureView(picture.createObservable());
			picture_view.addMouseListener(this);
			add(picture_view, BorderLayout.CENTER);
			
			redLabel = new JLabel("Red:", SwingConstants.LEFT);
			greenLabel = new JLabel("Green:", SwingConstants.LEFT);
			blueLabel = new JLabel("Blue:", SwingConstants.LEFT);
			xLabel = new JLabel("X:", SwingConstants.LEFT);
			yLabel = new JLabel("Y:", SwingConstants.LEFT);
			brightnessLabel = new JLabel("brightness:", SwingConstants.LEFT);
			
			
			pixelInfo.add(xLabel);
			pixelInfo.add(yLabel);
			pixelInfo.add(redLabel);
			pixelInfo.add(greenLabel);
			pixelInfo.add(blueLabel);
			pixelInfo.add(brightnessLabel);
			
			add(pixelInfo, BorderLayout.WEST);
			
		}
		
		public void mouseClicked(MouseEvent pixel) {
			int x = pixel.getX();
			int y = pixel.getY();
			Pixel p = picture_view.getPicture().getPixel(x,y);
			double red = p.getRed();
			double green = p.getGreen();
			double blue = p.getBlue();
			double brightness = p.getIntensity();
			
			red = Math.floor(red * 100) / 100;
			green = Math.floor(green * 100) / 100;
			blue = Math.floor(blue * 100) / 100;
			brightness = Math.floor(brightness * 100) / 100;
			
			xLabel.setText("X:" + Integer.toString(x));
			yLabel.setText("Y:" + Integer.toString(y));
			redLabel.setText("Red:" + Double.toString(red));
			greenLabel.setText("Green:" + Double.toString(green));
			blueLabel.setText("Blue:" + Double.toString(blue));
			brightnessLabel.setText("brightness:" + Double.toString(brightness));
			
		}
		
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
			
			
			
			
			
			
		

}
