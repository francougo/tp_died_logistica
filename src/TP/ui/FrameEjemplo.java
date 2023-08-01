package TP.ui;

import javax.swing.*;

public class FrameEjemplo extends JFrame{
	
	public static void inicio() {
		FrameEjemplo frame = new FrameEjemplo();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setTitle("Ejemplo");
		frame.pack();
		frame.setSize(400,200);
		frame.setVisible(true);
	}
	

}
