
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class numPanel extends JPanel{
	
	private JButton one, two, three, four, five, six, sev, eig, nine, zero, dot, plusminus;
	JButton div, mult, sub, plus, equal;
	JButton shift, mod, ce, clear, back;
	JButton lsh, rsh, or, xor, not, and, dots, dot2, wo, rd, ms, marrow;
	private JButton word;
	
	
	//second set
	public numPanel() {
//		setLayout(new GridLayout(6, 3, -3, -6));
		GridBagConstraints cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		
		//JButton word = new JButton("WORD");//not useless?
		or = new JButton("Or");
		xor = new JButton("Xor");
		not = new JButton("Not");
		ce = new JButton("CE");
		clear = new JButton("clear");
		back = new JButton("«");
		
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		sev = new JButton("7");
		eig = new JButton("8");
		nine = new JButton("9");
		zero = new JButton("0");
		dot = new JButton(".");
		plusminus = new JButton("+/-");
		
		word = new JButton("WORD");
		ms = new JButton("MS");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1.0;
		cons.insets = new Insets(-2,-3,-1,1);//top left bottom right
		cons.ipady = 12;
		cons.ipadx = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 2;
		add(word, cons);
		
		cons.gridx = 2;
		cons.gridy = 0;
		cons.ipadx = 1;
		cons.gridwidth = 1;
		add(ms, cons);
		
		
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 1;
		add(or, cons);
		cons.gridx = 1;
		cons.gridy = 1;
		cons.ipadx = 0;
		add(xor, cons);
		
		cons.gridx = 2;
		cons.gridy = 1;
		add(not, cons);
		
		cons.gridx = 0;
		cons.gridy = 2;
		add(ce, cons);
		
		cons.gridx = 1;
		cons.gridy = 2;
		add(clear, cons);
		
		cons.gridx = 2;
		cons.gridy = 2;
		add(back, cons);
		
		
		cons.gridx = 0;
		cons.gridy = 3;
		add(sev, cons);
		
		cons.gridx = 1;
		cons.gridy = 3;
		add(eig, cons);
		
		cons.gridx = 2;
		cons.gridy = 3;
		add(nine, cons);
		
		cons.gridx = 0;
		cons.gridy = 4;
		add(four, cons);
		
		cons.gridx = 1;
		cons.gridy = 4;
		add(five, cons);
		
		cons.gridx = 2;
		cons.gridy = 4;
		add(six, cons);
		
		cons.gridx = 0;
		cons.gridy = 5;
		add(one, cons);
		
		cons.gridx = 1;
		cons.gridy = 5;
		add(two, cons);
		
		cons.gridx = 2;
		cons.gridy = 5;
		add(three, cons);
		
		cons.gridx = 0;
		cons.gridy = 6;
		add(plusminus, cons);
		
		cons.gridx = 1;
		cons.gridy = 6;
		add(zero, cons);
		
		cons.gridx = 2;
		cons.gridy = 6;
		add(dot, cons);
		
		word.setBorderPainted(false);
		ms.setBorderPainted(false);
		word.setBackground(Color.LIGHT_GRAY);
		ms.setBackground(Color.LIGHT_GRAY);
		
		two.setBackground(Color.WHITE);
		three.setBackground(Color.WHITE);
		four.setBackground(Color.WHITE);
		five.setBackground(Color.WHITE);
		six.setBackground(Color.WHITE);
		sev.setBackground(Color.WHITE);
		eig.setBackground(Color.WHITE);
		nine.setBackground(Color.WHITE);
		zero.setBackground(Color.WHITE);	
		plusminus.setBackground(Color.WHITE);
		dot.setBackground(Color.WHITE);
		
		or.setBackground(Color.lightGray);
		or.setOpaque(true);
		or.setEnabled(false);
		
		xor.setBackground(Color.lightGray);
		xor.setOpaque(true);
		xor.setEnabled(false);

		not.setBackground(Color.lightGray);
		not.setOpaque(true);
		not.setEnabled(false);
		
		ce.setBackground(Color.lightGray);
		ce.setOpaque(true);
		ce.setEnabled(false);
		
		clear.setBackground(Color.lightGray);
		clear.setOpaque(true);
		clear.setEnabled(false);
		
		back.setBackground(Color.lightGray);
		back.setOpaque(true);
		back.setEnabled(false);
	}
	
	public final JButton getOne() { return one;}
	public final JButton getTwo() { return two;}
	public final JButton getThree() { return three;}
	public final JButton getFour() { return four;}
	public final JButton getFive() { return five;}
	public final JButton getSix() { return six;}
	public final JButton getSev() { return sev;}
	public final JButton getEig() { return eig;}
	public final JButton getNine() { return nine;}
	public final JButton getZero() { return zero;}
	public final JButton getDot() { return dot;}
	public final JButton getCE() { return ce;}
	public final JButton getClear() { return clear;}
	public final JButton getBack() { return back;}
	public final JButton getWord() { return word;}
	public final JButton getPlusMinus() { return plusminus; }
	
	public void wordSetText(String s) { word.setText(s); }
	
}
