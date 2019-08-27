
import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static void main(String args []) {
		
		JFrame window = new Project();
		window.setTitle("Calculator :-)");
		window.setOpacity(1.0f);
		window.setBackground(Color.lightGray.brighter());
		window.setVisible(true);
		
		
		
		window.pack();
		window.setSize(380, 580);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//important-garbage collector
	}
	
}