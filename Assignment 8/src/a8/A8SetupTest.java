package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A8SetupTest {
	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		
		SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p, "KMP in Namibia");
		PixelInspector pixel_inspector = new PixelInspector(p);
		ImageAdjuster image_adjuster = new ImageAdjuster(p);
		FramePuzzle frame_puzzle = new FramePuzzle(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		
		// This line sets which widget is tested
		top_panel.add(frame_puzzle, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}