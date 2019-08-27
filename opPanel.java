import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class opPanel extends JPanel{
	private JButton div, mult, sub, plus, equal, marrow, and;
	

	public opPanel() {
		setLayout(new GridLayout(7, 0, 0, -3));
		
		div = new JButton("÷");
		mult = new JButton("*");
		sub = new JButton("-");
		plus = new JButton("+");
		equal = new JButton("=");
		marrow = new JButton("M˙");
		Dimension def = new Dimension(15, 40);
		
		and = new JButton("And");
		and.setPreferredSize(def);
		marrow.setBorderPainted(false);
		
		marrow.setBackground(Color.LIGHT_GRAY);
		
		div.setBackground(Color.lightGray);
		div.setOpaque(true);
		div.setEnabled(false);
		
		mult.setBackground(Color.lightGray);
		mult.setOpaque(true);
		mult.setEnabled(false);
		
		plus.setBackground(Color.lightGray);
		plus.setOpaque(true);
		plus.setEnabled(false);
		
		sub.setBackground(Color.lightGray);
		sub.setOpaque(true);
		sub.setEnabled(false);
		
		equal.setBackground(Color.lightGray);
		equal.setOpaque(true);
		equal.setEnabled(false);
		
		and.setBackground(Color.lightGray);
		and.setOpaque(true);
		and.setEnabled(false);
		
		add(marrow);
		add(and);
		add(div);
		add(mult);
		add(sub);
		add(plus);
		add(equal);
	}
	
	public void disableOps() {
		div.setEnabled(false);
		plus.setEnabled(false);
		mult.setEnabled(false);
		equal.setEnabled(false);
		sub.setEnabled(false);
	}
	
	public final JButton getAnd() { return and; }
	public final JButton getDiv() { return div; }
	public final JButton getMult() { return mult; }
	public final JButton getSub() { return sub; }
	public final JButton getPlus() { return plus; }
	public final JButton getEqual() { return equal; }
	public final JButton getMarrow() { return marrow; }
	
	
}
