import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class specialPanel extends JPanel {
	
//	JButton one, two, three, four, five, six, sev, eig, nine, zero, dot, plusminus;
//	JButton div, mult, sub, plus, equal;
	private JButton a, b, c, d, e, f, leftPar, rightPar;
	JButton shift, mod, ce, clear, back;
	JButton lsh, rsh, or, xor, not, and, dots, dot2, wo, rd, ms, marrow;
//	
//	JTextField oct, bin, dec, hex;
//	
//	JTextField display;
//	JTextField name;
//	JTextField prim;
//	
//	JPanel south;
//	JPanel center;
//	JPanel north;
//	
//	JPanel operations;
//	JPanel numbers;
//	JPanel special;
	
	private static int left = 0;
	private static int right = 0;
	
	public specialPanel() {
		setLayout(new GridLayout(7, 2, -2, -3));
		//special.setBackground(Color.GRAY);
		a = new JButton("A");
		b = new JButton("B");
		c = new JButton("C");
		d = new JButton("D");
		e = new JButton("E");
		f = new JButton("F");
		leftPar = new JButton("(");
		rightPar = new JButton(")");
		dots = new JButton("⁙");
		dot2 = new JButton("⸬");
		
		shift = new JButton("caps");
		mod = new JButton("MOD");
		lsh = new JButton("Lsh");
		Dimension def = new Dimension(15, 40);
		lsh.setPreferredSize(def);//this isnt very good is it? very bad indeed?
		rsh = new JButton("Rsh");
		
		add(dots);
		add(dot2);
		add(lsh);
		add(rsh);
		add(shift);
		add(mod);
		add(a);
		add(b);
		add(c);
		add(d);
		add(e);
		add(f);
		add(leftPar);
		add(rightPar);
		dots.setBorderPainted(false);
		dot2.setBorderPainted(false);
		dots.setBackground(Color.LIGHT_GRAY);
		dot2.setBackground(Color.LIGHT_GRAY);
		
		lsh.setBackground(Color.lightGray);
		lsh.setOpaque(true);
		lsh.setEnabled(false);
		
		rsh.setBackground(Color.lightGray);
		rsh.setOpaque(true);
		rsh.setEnabled(false);

		shift.setBackground(Color.lightGray);
		shift.setOpaque(true);
		shift.setEnabled(false);
		
		mod.setBackground(Color.lightGray);
		mod.setOpaque(true);
		mod.setEnabled(false);
		
		leftPar.setBackground(Color.lightGray);
		leftPar.setOpaque(true);
		leftPar.setEnabled(false);
		
		rightPar.setBackground(Color.lightGray);
		rightPar.setOpaque(true);
		rightPar.setEnabled(false);
		
//		shift.setBorderPainted(false);
//		mod.setBorderPainted(false);
//		a.setBorderPainted(false);
//		b.setBorderPainted(false);
//		c.setBorderPainted(false);
//		d.setBorderPainted(false);
//		e.setBorderPainted(false);
//		f.setBorderPainted(false);
//		leftPar.setBorderPainted(false);
//		rightPar.setBorderPainted(false);
	}
	
	public final JButton getA() { return a; }
	public final JButton getB() { return b; }
	public final JButton getC() { return c; }
	public final JButton getD() { return d; }
	public final JButton getE() { return e; }
	public final JButton getF() { return f; }
	
	public final int getLeft() { return left; }
	public final int getRight() { return right; }
	
	public final JButton getLeftPar() { return leftPar; }
	public final JButton getRightPar() { return rightPar; }
	public final JButton getMod() { return mod; }
	public final JButton getShift() { return shift; }
	
	/*
	 * only takes 1 or -1. and either decrement or increments the left and right
	 * parentheses counter
	 */
	public void manLeft(int i) {
		if(i == 1) {
			left++;
			leftPar.setText("( " + left);
		}else {
			left--;
			if(left == 0) {
				leftPar.setText("( ");
			}else {
				leftPar.setText("( " + left);
			}
			
		}
		
	}
	
	public void manRight(int i) {
		if(i == 1) {
			right++;
//			rightPar.setText("( " + right);
//		}else if(i < 0 && right > 0){
		}else {
			right--;
//			rightPar.setText("( " + right);
		}
	}
	
	public void buttonSetup() {
		lsh.setBackground(Color.GRAY);
		lsh.setBorderPainted(false);
		lsh.setOpaque(true);
	}

}
