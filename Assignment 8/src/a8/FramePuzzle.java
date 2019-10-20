package a8;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzle extends JPanel implements MouseListener, KeyListener {
	
	private Picture picture;
	Picture[][] subpic_array;
	
	private PictureView picture_view;
	private PictureView white_tile;
	private PictureView[][] pic_view_array;
	
	int a = 0;
	int b = 0;
	int x = 4;
	int y = 4;
	int sub_width;
	int sub_height;
	
	public FramePuzzle(Picture p) {
		this.picture = p;
		setLayout( new GridLayout(5, 5, 2, 2));
		
		//creating observable
		picture_view = new PictureView(p.createObservable());
		
		subpic_array = new Picture[5][5];
		pic_view_array = new PictureView[5][5];
		sub_height = p.getHeight() / 5;
		sub_width = p.getWidth() / 5;
		
		
		
		for (int i=0; i<p.getHeight(); i+= sub_height) {
			b=0;
			
			for(int j=0; j<p.getWidth(); j+= sub_width) {
				SubPicture square = p.extract(j,i,sub_width, sub_height);
				subpic_array[b][a] = square;
				picture_view = new PictureView(square.createObservable());
				
				
				
				pic_view_array[b][a] = picture_view;
				b++;
			}
			a++;
		}
		
		
		pic_view_array[4][4].setPicture(new PictureImpl(sub_width,sub_height).createObservable());
		white_tile = pic_view_array[4][4];
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				pic_view_array[j][i].addMouseListener(this);
				add(pic_view_array[j][i]);
			}
		}
		
		addKeyListener(this);
		setFocusable(true);
		
	}
	
//for using the arrows
	
	public void keyReleased(KeyEvent e) {
		//Left
		if (e.getKeyCode() == 37) {
			if (x > 0 ) {
				switchTiles(pic_view_array[x-1] [y]);
				x -= 1;
			} else {
				System.out.println("no chance");
			}
		}
		
		//Up
		
		if( e.getKeyCode() == 38) {
			if (y > 0) {
				switchTiles(pic_view_array[x][y - 1]);
				y -=1;
				
			} else {
				System.out.println("out of bounds");
			}
		}
		
		//Right
		if(e.getKeyCode() == 39) {
			if (x < pic_view_array[0].length-1 ) {
				switchTiles(pic_view_array[x+1][y]);
				x += 1;
			} else {
				System.out.println("no chance");
			}
			
		}
		// Down
		if(e.getKeyCode() == 40) {
			if (y < pic_view_array.length - 1) {
				switchTiles(pic_view_array[x][y + 1]);
				y += 1;
			} else {
				System.out.println("no chance");
			}
		}	
	}
	
	public void switchTiles(PictureView p1) {
		for( int i = 0; i<pic_view_array.length; i++) {
			for (int j = 0; j<pic_view_array[0].length; j++) {
				if (pic_view_array[i][j] == p1 ) {
					white_tile.setPicture(pic_view_array[i][j].getPicture());
					white_tile = pic_view_array[i][j];
					pic_view_array[i][j].setPicture(new PictureImpl (sub_width, sub_height).createObservable());
					repaint();
				}
			}
		}
	}
		//for the mouse
	public void mouseClicked(MouseEvent e) {
		int x = e.getComponent().getX();
		int y = e.getComponent().getY();
		x = x / sub_width;			
		y = y / sub_height;
			
			
		if ( x == white_tile.getX()) {
			if(y < white_tile.getY()) {
				for(int i = white_tile.getY() - sub_height; i>= y; i-=sub_height) {
					switchTiles(pic_view_array[x/sub_width][i/ sub_height]);
				}
			} else {
					for(int i= white_tile.getY() + sub_height; i<= y; i+= sub_height) {
						switchTiles(pic_view_array[x/sub_width][ i/sub_height]);
					}
			}	
		}
			
		if(y == white_tile.getY()) {
			if(x < white_tile.getX()) {
				for(int i = white_tile.getX()-sub_width; i>=x; i -= sub_width) {
					switchTiles(pic_view_array[i/ sub_width][ y/ sub_height]);
				}
			} else {
				for ( int i = white_tile.getX()+sub_width; i<=x; i+= sub_width) {
					switchTiles(pic_view_array[i/sub_width] [ y/ sub_height]);
				}
			}
		}
	}
	
	//End
			
		public void keyPressed(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}

}
