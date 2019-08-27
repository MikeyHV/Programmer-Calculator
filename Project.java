import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
@SuppressWarnings("serial")
public class Project extends JFrame implements ActionListener,MouseListener{

	JTextField name;
	JTextField prim;
	
	JPanel south;
	JPanel center;
	JPanel north;
	
	private displayPanel display;
	private opPanel ops;
	private numPanel numbers;
	private specialPanel special;
	private boolean neg = false;
	
	static boolean spec = false;
	private static int conv = 10;
	private static boolean zeroed = false;
	
	private static boolean opN = true;
	private static boolean numN = false;
	
	public Project() {
		/******************    SOUTH PANEL SETUP ************************/
		south = new JPanel();
		south.setLayout(new BorderLayout(-2,0));
		add(south, BorderLayout.SOUTH);
		south.setBackground(Color.lightGray);
		
		numbers = new numPanel();
		numbers.setBackground(Color.lightGray);
		south.add(numbers, BorderLayout.CENTER);
		
		special = new specialPanel();
		special.setBackground(Color.lightGray);
		special.setPreferredSize(new Dimension(120, 270));
		south.add(special, BorderLayout.WEST);
		
		ops = new opPanel();
		ops.setBackground(Color.lightGray);
		ops.setPreferredSize(new Dimension(60, 270));
		south.add(ops, BorderLayout.EAST);
		
		south.addMouseListener(this);
		
		/******************    NORTH PANEL SETUP ************************/
		north = new JPanel(new BorderLayout());
		add(north, BorderLayout.CENTER);
		north.setPreferredSize(new Dimension(380, 280));
		display = new displayPanel();
		display.setPreferredSize(new Dimension(300, 300));
		north.add(display, BorderLayout.CENTER);
		north.addMouseListener(this);
		
		addsActionListeners();
		disableAll();
		enableDec();
	}

	public void mousePressed(MouseEvent event) {
		if(((JButton) event.getSource()) == numbers.getOne()) {
			calc("1");
		}else if(((JButton) event.getSource()) == numbers.getDot()) {
			calc(".");
		}else if(((JButton) event.getSource()) == numbers.getTwo()) {
			calc("2");
		}else if(((JButton) event.getSource()) == numbers.getThree()) {
			calc("3");
		}else if(((JButton) event.getSource()) == numbers.getFour()) {
			calc("4");
		}else if(((JButton) event.getSource()) == numbers.getFive()) {
			calc("5");
		}else if(((JButton) event.getSource()) == numbers.getSix()) {
			calc("6");
		}else if(((JButton) event.getSource()) == numbers.getSev()) {
			calc("7");
		}else if(((JButton) event.getSource()) == numbers.getEig()) {
			calc("8");
		}else if(((JButton) event.getSource()) == numbers.getNine()) {
			calc("9");
		}else if(((JButton) event.getSource()) == numbers.getZero()) {
			calc("0");
		}else if(((JButton) event.getSource()) == numbers.getBack()) {
			calc("back");
		}else if(((JButton) event.getSource()) == ops.getPlus()) {
			calc("+");
		}else if(((JButton) event.getSource()) == ops.getSub()) {
			calc("-");
		}else if(((JButton) event.getSource()) == ops.getMult()) {
			calc("*");
		}else if(((JButton) event.getSource()) == ops.getDiv()) {
			calc("÷");
		}else if(((JButton) event.getSource()) == ops.getEqual()) {
			calc("=");
		}else if(((JButton) event.getSource()) == numbers.getCE()) {
			calc("CE");
		}else if(((JButton) event.getSource()) == special.getMod()) {
			calc("%");
		}else if(((JButton) event.getSource()) == numbers.getClear()) {
			display.dClear();
			display.mClear();
			display.dSetText("0");
		}else if(((JButton) event.getSource()) == display.getOct()) {
			display.updateConvOct();
			conv = 8;
		}else if(((JButton) event.getSource()) == display.getHex()) {
			display.updateConvHex();
			conv = 16;
		}else if(((JButton) event.getSource()) == display.getDec()) {
			display.updateConvDec();
			conv = 10;
		}else if(((JButton) event.getSource()) == display.getBin()) {
			display.updateConvBin();
			conv = 2;
		}else if(((JButton) event.getSource()) == ops.getEqual()) {
			calc("=");
		}else if(((JButton) event.getSource()) == special.getA()) {
			calc("A");
		}else if(((JButton) event.getSource()) == special.getB()) {
			calc("B");
		}else if(((JButton) event.getSource()) == special.getC()) {
			calc("C");
		}else if(((JButton) event.getSource()) == special.getD()) {
			calc("D");
		}else if(((JButton) event.getSource()) == special.getE()) {
			calc("E");
		}else if(((JButton) event.getSource()) == special.getF()) {
			calc("F");
		}else if(((JButton) event.getSource()) == special.getLeftPar()) {
			calc("(");
			special.manLeft(1);
		}else if(((JButton) event.getSource()) == special.getRightPar()) {
			calc(")");
			special.manRight(1);
			special.manLeft(-1);
		}else if(((JButton) event.getSource()) == numbers.getPlusMinus()) {
			if(neg == false) {
				neg = true;
			}else { neg = false; }
			
			calc("+-");
			display.setNeg();
			
		}
		
		
		disableAll();
		if(conv == 10) {
			enableDec();
		}else if(conv == 2) {
			enableBin();
		}else if(conv == 8) {
			enableOct();
		}else if(conv == 16) {
			enableHex();
		}
	}
	
	public void calc(String i) {
		if(zeroed == true) {
			display.dClear();
			display.mClear();
			reenableZero();
		}
		
		if(display.dGetText().equals("0") && display.mGetText().equals("")) display.dClear();
		
		if(i.equals("back")) {
			
			if(display.dGetText().length() == 1) {
				display.dClear();
				display.dSetText("0");
			}else {
				display.dSetText(display.dGetText().substring(0, display.dGetText().length()-1));
				display.updateConv();
			}
			
		}else if(i.equals("=")) {//if its equals
			
			if(display.mGetLastChr() == 1 || display.mGetLastChr() == -1) {
				
				if(special.getLeft() == 0) {
					display.dSetText(toUpperString(translateTo(result(display.mGetTranslated(10)))));
					display.mSetText("");
				}
				
			}else {
				if(special.getLeft() == 0) {
					display.dSetText(toUpperString(translateTo(result(display.mGetTranslated(10) + display.dGetText()))));
					display.mSetText("");
				}else {
					String hold = "";
					for(int r = 0; r < special.getLeft(); r++){//appends the appropriate number of left parethesis to the thingy. the string, jsut so it will work
						hold = hold + " )";
					}
					display.dSetText(toUpperString(translateTo(result(display.mGetTranslated(10) + display.dGetText() + hold))));
					display.mSetText("");
				}
				
			}
			display.updateConv();//changes the display, so update
			numN = false;
			
		}else if(i.equals("CE")) {//negative
			display.dClear();
			display.dSetText("0");
			numN = false;
			opN = true;
		}else if(i.equals("+-")) {//negative
//			3 + negate(7 - 6 + 3) + -6 + negate(-3 - 6) * -3
			if(numN == false && display.mGetText().contentEquals("") && display.dGetText().contentEquals("")) {
				display.mSetText("negate( 0 )");//special case
			}else if(display.mGetLastChr() == -1){
				int hold = display.mGetText().length() - (display.mGetPar().length());
				display.mSetText(display.mGetText().substring(0, hold) + " negate" + display.mGetPar() + "");
			}else if(numN == true && neg == true) {
				display.dSetText("-" + display.dGetTextUnCov());
			}else if(numN == true && neg == false) {
				display.dSetText(display.dGetTextUnCov().substring(1));
			}else if(numN == false && !display.dGetText().contentEquals("") && display.mGetText().contentEquals("")) {
				display.mSetText("-" + display.dGetTextUnCov());
				display.dSetText("-" + display.dGetTextUnCov());
			}else if(opN == true && !display.dGetText().contentEquals("")) {
				display.mSetText(display.mGetText() + " negate( " + display.dGetTextUnCov() + " )");
			}
			
		}else if(i.equals("%") || i.equals("+") || i.equals("-") || i.equals("*") || i.equals("÷") || i.equals("(") || i.equals(")")) {
			if(opN == true) {
				// i can just append + 0 to everything
				//otherwise i can gurantee that it only evaluates if there is atleast a 9 + rather than just 9
				if(numN == true && display.mGetText().length() > 3 && (i.equals("+") || i.equals("-") || i.contentEquals(")"))) {//why
					if(i.contentEquals(")")) {
						display.dSetText(result(display.mGetPar() + " )"));
						display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
					}else {
						display.dSetText(result(display.mGetText()));
						display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
					}
					/*
					 * boths of these statements change the display, so i need to update the display
					 */
					display.updateConv();
				}else if((i.equals("+") || i.equals("-") || i.equals("*") || i.equals("÷") || i.equals("%")) && display.mGetLastChr() == 0) {
						display.mSetText(display.mGetText().substring(0, display.mGetText().length() - 2) + " " + i);
				}else if(numN == false && i.equals("(")) {
					display.mSetText(display.mGetText() + " " + i);
					if(display.dGetText().contentEquals("")) display.dSetText("0");
				}else if(numN == false) {
					display.mSetText(display.mGetText() + " " + i);//display.mSetText(display.mGetText() + " " + display.dGetUnCov() + " " + i);
				}
			}else {
				if(numN == true && display.mGetText().length() > 3 && (i.equals("+") || i.equals("-") || i.contentEquals(")"))) {//why
					if(i.contentEquals(")")) {
						display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
						display.dSetText(result(display.mGetPar()));
						display.updateConv();//changes the display, so update
					}else if(numN == true && display.mGetRecent() == false) {
						display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
					}else {
						display.dSetText(result(display.mGetText()));
						display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
						display.updateConv();//changes the display, so update
					}
				}else {
					display.mSetText(display.mGetText() + " " + display.dGetTextUnCov() + " " + i);
				}
			}
			if(display.dGetText().contentEquals("")) display.dSetText("0");
			opN = true;
			numN = false;
			
		}else {//if its a number
			if(numN == true) {
				if(display.dGetText().contentEquals("0")) display.dSetText("");
				display.dSetText(display.dGetTextUnCov() + i);
			}else {
				display.dSetText("");
				display.dSetText(i);
			}
			display.updateConv();//changes the display, so update
			numN = true;
			opN = false;
		}
	}
	
	public String result(String s) {// expression evaluation
		Stack<Long> var = new Stack<Long>();
		Stack<Character> op = new Stack<Character>();
		s = resultParser(s);
		for(int i = 0; i < s.length(); i++) {
			if(isNum(s.charAt(i))) {
				String hold = new String("");
				while(i < s.length() && isNum(s.charAt(i))) {
					hold = hold + s.charAt(i++);
				}
				var.push(Long.parseLong(hold));
				i--;
			}else if(s.charAt(i) == '(') {
				if(s.charAt(i + 1) == '-') {
					for(int x = i + 1; x < s.length(); x++) {
						if(s.charAt(x) == ' ') break;
						if(s.charAt(x) == ')') {
							var.push(Long.parseLong(s.substring(i+1,x)));
							i = i + s.substring(i+1,x).length() + 1;
							break;
						}
					}

				}else {
					op.push(s.charAt(i));
				}
			}else if(s.charAt(i) == ')') {
				while (op.peek() != '(') {
	                var.push(solve(op.pop(), var.pop(), var.pop())); 
				}
				op.pop(); 
			}else if(isOp(s.charAt(i))) {
				//ensures that the operator with greatest precendence is taken care of first in the stack
				//while the stack is not empty
				while(!op.empty() && hasPrec(s.charAt(i), op.peek()))
					var.push(solve(op.pop(), var.pop(), var.pop()));
				
				op.push(s.charAt(i));
			}
		}
		while(!op.empty()) {
			var.push(solve(op.pop(), var.pop(), var.pop()));
		}
		return var.pop() + "";
		
	}
	
	public String resultParser(String s) {// makes sure the expression evaluatable for negatives
		//make sure to append at least 3(?) spaces
		s = " " + s + "  ";
		int loops = s.length() - 1;
		for(int i = 0; i < loops; i++) {
			if(s.charAt(i) == 'n') {
				int subMe = s.substring(s.substring(i).indexOf('(') + i + 1, s.substring(i).indexOf(')') + i).length() + 6;
				String hold = s.substring(s.substring(i).indexOf('(') + i + 1, s.substring(i).indexOf(')') + i);
				if(!hold.contains("(")) {//checks the inside of the negate( ) to make sure nothing in there is invalid
					hold = resultParser("  " + hold + "  ");
				}
				hold = " ((-1) * (" + hold + ")) ";
				s = s.substring(0, i-1) + hold + s.substring(s.substring(i).indexOf(") ") + i + 2);
				
				i = i + subMe + (hold.length() - subMe) - 2;
				loops = s.length() - 1;
			}else if(s.charAt(i) == '-' && isNum(s.charAt(i+1))) {
				String hold = " (" + s.substring(i,i + s.substring(i).indexOf(" ")) + ") ";
				s = s.substring(0, i-1) + hold + s.substring(i + hold.length() - 3);
				
				i = i + 2;
				loops = s.length() - 1;
				
			}
		}
		return s.trim();
	}
	
	public boolean hasPrec(char one, char two) {//checks the second operator
		if(two == '(' || two == ')')
			return false;
		if((one == '÷' || one == '*' || one == '%') && (two == '-' || two == '+'))
			return false;
		return true;
	}
	
	public long solve(char op, long one, long two) {//DOES THE basic calculations for the solve
		if(op == '+') {
			return two + one;
		}else if(op == '-') {
			return two-one;
		}else if(op == '*') {
			return two*one;
		}else if(op == '%') {
			if(one == 0) {
				divByZero();
				display.dSetText("Cannot mod by zero");
				throw new UnsupportedOperationException("Mod by 0");
			}else {
				return two%one;
			}
			
		}else if(op == '÷') {
			if(one == 0) {
				divByZero();
				display.dSetText("Cannot divide by zero");
				throw new UnsupportedOperationException("Divide by 0");
			}else {
				return two/one;
			}
		}
		return 0;
	}
	
	public void disableOps() {
		ops.getDiv().setEnabled(false);
		ops.getPlus().setEnabled(false);
		ops.getMult().setEnabled(false);
		ops.getEqual().setEnabled(false);
		ops.getSub().setEnabled(false);
		
		ops.getDiv().removeMouseListener(this);
		ops.getMult().removeMouseListener(this);
		ops.getPlus().removeMouseListener(this);
		ops.getSub().removeMouseListener(this);
		ops.getEqual().removeMouseListener(this);
	}
	
	public void disableAll() {
		numbers.getOne().setEnabled(false);
		numbers.getTwo().setEnabled(false);
		numbers.getThree().setEnabled(false);
		numbers.getFour().setEnabled(false);
		numbers.getFive().setEnabled(false);
		numbers.getSix().setEnabled(false);
		numbers.getSev().setEnabled(false);
		numbers.getEig().setEnabled(false);
		numbers.getNine().setEnabled(false);
		special.getA().setEnabled(false);
		special.getB().setEnabled(false);
		special.getC().setEnabled(false);
		special.getD().setEnabled(false);
		special.getE().setEnabled(false);
		special.getF().setEnabled(false);
		
		numbers.getOne().removeMouseListener(this);
		numbers.getTwo().removeMouseListener(this);
		numbers.getThree().removeMouseListener(this);
		numbers.getFour().removeMouseListener(this);
		numbers.getFive().removeMouseListener(this);
		numbers.getSix().removeMouseListener(this);
		numbers.getSev().removeMouseListener(this);
		numbers.getEig().removeMouseListener(this);
		numbers.getNine().removeMouseListener(this);
		special.getA().removeMouseListener(this);
		special.getB().removeMouseListener(this);
		special.getC().removeMouseListener(this);
		special.getD().removeMouseListener(this);
		special.getE().removeMouseListener(this);
		special.getF().removeMouseListener(this);
	}

	public boolean isOp(char c) {
		if(c == '÷' || c == '*' || c == '+' || c == '-' || c == '%') return true;
		return false;
	}
	
	public boolean isNum(char c) {
		if(c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
			return true;
		}
		return false;
	}

	public String translateTo(String i) {
		String hold = "";
		if(conv == 16) {
			hold = Long.toHexString(Long.valueOf(i));
		}else if(conv == 8) {
			hold = Long.toOctalString(Long.valueOf(i));
		}else if(conv == 2) {
			hold = Long.toBinaryString(Long.valueOf(i));
		}else {
			return i;
		}
		return hold;
	}
	
	public String toUpperString(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'a' || s.charAt(i) == 'b' || s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'e' || s.charAt(i) == 'f') {
				s = s.substring(0, i) + (s.charAt(i) + "").toUpperCase() + s.substring(i + 1);
			}
		}
		return s;
	}
	
	public void enableBin() {
		numbers.getOne().setEnabled(true);
		
		numbers.getOne().addMouseListener(this);
	}
	
	public void enableOct() {
		enableBin(); // no need for one
		
		numbers.getTwo().setEnabled(true);
		numbers.getThree().setEnabled(true);
		numbers.getFour().setEnabled(true);
		numbers.getFive().setEnabled(true);
		numbers.getSix().setEnabled(true);
		numbers.getSev().setEnabled(true);
		
		numbers.getTwo().addMouseListener(this);
		numbers.getThree().addMouseListener(this);
		numbers.getFour().addMouseListener(this);
		numbers.getFive().addMouseListener(this);
		numbers.getSix().addMouseListener(this);
		numbers.getSev().addMouseListener(this);
	}
	
	public void enableDec() {
		enableOct(); // no need for 1-7
		
		numbers.getEig().setEnabled(true);
		numbers.getNine().setEnabled(true);
		
		numbers.getEig().addMouseListener(this);
		numbers.getNine().addMouseListener(this);
	}
	
	public void enableHex() {
		enableDec(); // no need for 1-9
		
		special.getA().setEnabled(true);
		special.getB().setEnabled(true);
		special.getC().setEnabled(true);
		special.getD().setEnabled(true);
		special.getE().setEnabled(true);
		special.getF().setEnabled(true);
		
		special.getA().addMouseListener(this);
		special.getB().addMouseListener(this);
		special.getC().addMouseListener(this);
		special.getD().addMouseListener(this);
		special.getE().addMouseListener(this);
		special.getF().addMouseListener(this);
	}
	
	public void enableOps() {
		ops.getDiv().setEnabled(true);
		ops.getPlus().setEnabled(true);
		ops.getMult().setEnabled(true);
		ops.getEqual().setEnabled(true);
		ops.getSub().setEnabled(true);
		
		ops.getDiv().addMouseListener(this);
		ops.getMult().addMouseListener(this);
		ops.getPlus().addMouseListener(this);
		ops.getSub().addMouseListener(this);
		ops.getEqual().addMouseListener(this);
	}
	
	public void addsActionListeners() {
		numbers.getOne().addMouseListener(this);
		numbers.getTwo().addMouseListener(this);
		numbers.getThree().addMouseListener(this);
		numbers.getFour().addMouseListener(this);
		numbers.getFive().addMouseListener(this);
		numbers.getSix().addMouseListener(this);
		numbers.getSev().addMouseListener(this);
		numbers.getEig().addMouseListener(this);
		numbers.getNine().addMouseListener(this);
		numbers.getZero().addMouseListener(this);
		numbers.getDot().addMouseListener(this);
		numbers.getClear().addMouseListener(this);
		numbers.getCE().addMouseListener(this);
		numbers.getBack().addMouseListener(this);
		
		ops.getMult().addMouseListener(this);
		ops.getDiv().addMouseListener(this);
		ops.getSub().addMouseListener(this);
		ops.getPlus().addMouseListener(this);
		ops.getEqual().addMouseListener(this);
		
		display.getBin().addMouseListener(this);
		display.getHex().addMouseListener(this);
		display.getDec().addMouseListener(this);
		display.getOct().addMouseListener(this);
		
		special.getRightPar().addMouseListener(this);
		special.getLeftPar().addMouseListener(this);
		
		special.getMod().addMouseListener(this);
		
		numbers.getPlusMinus().addMouseListener(this);
		
	}
	
	public void divByZero() {
		ops.getPlus().setBackground(Color.gray);
		ops.getDiv().setBackground(Color.gray);
		ops.getEqual().setBackground(Color.gray);
		ops.getSub().setBackground(Color.gray);
		ops.getMult().setBackground(Color.gray);
		ops.getAnd().setBackground(Color.gray);
		ops.getPlus().removeMouseListener(this);
		ops.getDiv().removeMouseListener(this);
		ops.getEqual().removeMouseListener(this);
		ops.getSub().removeMouseListener(this);
		ops.getMult().removeMouseListener(this);
		ops.getAnd().removeMouseListener(this);
		ops.getMarrow().setBackground(Color.gray);
		
		numbers.ms.setBackground(Color.gray);
		numbers.or.setBackground(Color.gray);
		numbers.xor.setBackground(Color.gray);
		numbers.not.setBackground(Color.gray);
		
		special.getLeftPar().setBackground(Color.gray);
		special.getRightPar().setBackground(Color.gray);
		special.getShift().setBackground(Color.gray);
		special.getMod().setBackground(Color.gray);
		special.lsh.setBackground(Color.gray);
		special.rsh.setBackground(Color.gray);
		special.dots.setBackground(Color.gray);
		special.dot2.setBackground(Color.gray);
		special.getLeftPar().removeMouseListener(this);
		special.getRightPar().removeMouseListener(this);
		special.getShift().removeMouseListener(this);
		special.getMod().removeMouseListener(this);
		
		display.dClear();
		display.mClear();
		
		zeroed = true;
		
		display.getDisplay().setEditable(false);
		display.getMain().setEditable(false);
	}
	
	public void reenableZero() {
		ops.getPlus().setBackground(Color.lightGray);
		ops.getDiv().setBackground(Color.lightGray);
		ops.getEqual().setBackground(Color.lightGray);
		ops.getSub().setBackground(Color.lightGray);
		ops.getMult().setBackground(Color.lightGray);
		ops.getAnd().setBackground(Color.lightGray);
		ops.getPlus().addMouseListener(this);
		ops.getDiv().addMouseListener(this);
		ops.getEqual().addMouseListener(this);
		ops.getSub().addMouseListener(this);
		ops.getMult().addMouseListener(this);
		ops.getAnd().addMouseListener(this);
		ops.getMarrow().setBackground(Color.lightGray);
		
		numbers.ms.setBackground(Color.lightGray);
		numbers.or.setBackground(Color.lightGray);
		numbers.xor.setBackground(Color.lightGray);
		numbers.not.setBackground(Color.lightGray);
		
		special.getLeftPar().setBackground(Color.lightGray);
		special.getRightPar().setBackground(Color.lightGray);
		special.getShift().setBackground(Color.lightGray);
		special.getMod().setBackground(Color.lightGray);
		special.lsh.setBackground(Color.lightGray);
		special.rsh.setBackground(Color.lightGray);
		special.dots.setBackground(Color.lightGray);
		special.dot2.setBackground(Color.lightGray);
		special.getLeftPar().addMouseListener(this);
		special.getRightPar().addMouseListener(this);
		special.getShift().addMouseListener(this);
		special.getMod().addMouseListener(this);
		
		display.dClear();
		display.mClear();
		
		zeroed = false;
		
		display.getDisplay().setEditable(true);
		display.getMain().setEditable(true);
	}
	
	public void actionPerformed(ActionEvent event) {//event driven programming
	}
	//action performed is a method required for aciton listener
	
	public void mouseExited(MouseEvent event) {
		
	}
	
	public void mouseClicked(MouseEvent event) {//mouse clicked is a class
	}
	//sends event to mouseclicked to the class

	public void mouseEntered(MouseEvent event) {
		
	}
	
	public void mouseReleased(MouseEvent event) {
		
	}
	/*action listener interface must implement 
	 * 
	 * action listener mouse thingy must implement those 7~ functions
	 */
}
