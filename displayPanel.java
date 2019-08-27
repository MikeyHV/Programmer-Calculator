import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class displayPanel extends JPanel{
	
	private JButton oct, bin, dec, hex;
	
	JTextField display;
	JTextField name;
	JTextField main;
	
	private boolean hexD = false, decD = true, binD = false, octD = false;

	public displayPanel() {
		GridBagConstraints cons = new GridBagConstraints();
		setLayout(new GridBagLayout());
		name = new JTextField("");
		Font prog = name.getFont().deriveFont(Font.PLAIN, 22f);
//		Font prog = new Font("SansSerif", Font.PLAIN, 200);
		name.setFont(prog);
		name.setText(" \u2630    PROGRAMMER");
		cons.ipady = 30;
		cons.weightx = 0.1;
		cons.weighty = 0.75;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 6;
		cons.gridx = 0;
		cons.gridy = 0;
		
		//name.setBorder(new EmptyBorder(new Insets(15, 25, 15, 25)));
		add(name, cons);
		name.setEditable(false);
		name.setBackground(getBackground());
		
		main = new JTextField("");//primary, on top
		Font prog3 = name.getFont().deriveFont(Font.PLAIN, 15f);
		main.setFont(prog3);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 0.1;
		cons.ipady = 30;
		cons.ipadx = 100;
		cons.gridwidth = 6;
		cons.gridx = 0;
		cons.gridy = 1;
		main.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//prim.setBorder(new EmptyBorder(new Insets(15, 25, 15, 25)));
		main.setBackground(getBackground());
		add(main, cons);
		
		display = new JTextField("0"); //secondary, on bottom
		Font prog2 = name.getFont().deriveFont(Font.PLAIN, 23f);
		display.setFont(prog2);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 0.1;
		cons.ipady = 40;
		cons.ipadx = 100;
		cons.gridwidth = 6;
		cons.gridx = 0;
		cons.gridy = 2;
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		
		display.setBackground(getBackground());
		//display.setBorder(new EmptyBorder(new Insets(15, 25, 15, 25)));
		add(display, cons);
		
		
		bin = new JButton("  BIN 0");
		bin.setHorizontalAlignment(SwingConstants.LEFT);
		bin.setBorderPainted(false);
		dec = new JButton("▮ DEC 0");
		dec.setHorizontalAlignment(SwingConstants.LEFT);
		dec.setBorderPainted(false);
		oct = new JButton("  OCT 0");
		oct.setHorizontalAlignment(SwingConstants.LEFT);
		oct.setBorderPainted(false);
		hex = new JButton("  HEX 0");
		hex.setHorizontalAlignment(SwingConstants.LEFT);
		hex.setBorderPainted(false);
		cons.weighty = 0;
		cons.weightx = 0.0;
		//cons.anchor = GridBagConstraints.PAGE_END;
		cons.insets = new Insets(-2,0,-2,0);//top left bottom right
		cons.gridx = 0;
		cons.ipady = 4;
		cons.gridwidth = 6;
		
		cons.gridy = 3;
		add(hex, cons);
		cons.gridy = 4;
		add(dec, cons);
		cons.gridy = 5;
		add(oct, cons);
		cons.gridy = 6;
		add(bin, cons);
		
		setBackground(Color.lightGray);
		
		display.setBackground(getBackground());
		main.setBackground(getBackground());
		name.setBackground(getBackground());
		
	
		
	}
	
	
	public final JButton getBin() { return bin; }
	public final JButton getHex() { return hex; }
	public final JButton getDec() { return dec; }
	public final JButton getOct() { return oct; }
	
	/*
	 * returns the last parenthesis expression
	 */
	public String mGetPar() {
		String s = main.getText() + " ";
		int right = 0;
		int left = 0;
		int rightInd = 0;
		
		int leftInd = 0;
		for(int i = s.length()-1; i > 0; i--) {
			if(s.charAt(i) == ')') {
				right++;
				if(i > rightInd) {
					rightInd = i;
				}
				
			}
			if(s.charAt(i) == '(') {
				left++;
				leftInd = i;
				if(left == right) {
					return s.substring(leftInd, rightInd + 1);
				}
			}
		}
		return s.substring(0, s.length() - 1);
	}
	/*
	 * returns the last character
	 * 
	 * 1 if it is a number
	 * 0 if it is an operator thats is not a parenthesis
	 */
	public int mGetLastChr() {
		String s = main.getText();
		for(int i = s.length()-1; i > 0; i--) {
			if(s.charAt(i) != ' ') {
				if(isNum(s.charAt(i))) {
					return 1;
				}else if(s.charAt(i) == ')') {
					return -1;
				}else if(isOp(s.charAt(i))) {
					return 0;
				}
			}
		}
		return 0;
	}
	
	/*
	 * returns the most recent expression -- 
	 * only 1 + 3 or 2 * 6; 1 op 2 vals
	 */
	public boolean mGetRecent() {
		String s = main.getText();
		int num = 0;
		int op = 0;
		for(int i = s.length()-1; i > 0; i--) {
			if(isNum(s.charAt(i))) {
				num++;
			}
			if(isOp(s.charAt(i))) {
				op++;
			}
			if(s.charAt(i) == '(') {
				if(num == op + 1) {
					return true;
				}else {
					return false;
				}
			}
		}
		return false;
	}
	
	/*
	 * wtf does this do
	 * returns the last set of numbers
	 */
	public String mGetLast() {
		String s = mGetText();
		
		if(s.contentEquals("")) s = "0";
		int last = 0;
		int first = 0;
		if(s.length() == 1) return s;
		for(int i = s.length()-1; i > 0; i--) {
			if(s.charAt(i)==')') {
				last = i;
				System.out.println(s.charAt(i));
				for(int r = i-1; r > 0; r--) {
					if(s.charAt(r) == ' '){
						first = r;
						break;
					}
				}
				break;
			}
			
			
			if(isNum(s.charAt(i))){
				last = i;
				for(int r = i-1; r > 0; r--) {
					if(s.charAt(r) == ' '){
						first = r;
						break;
					}
				}
				break;
			}
		}
		return s.substring(first, last+1);
	}
	
	/*
	 * returns the text in decimal format
	 * always takes the conversion into account, translating the nubmer form whatever to binary
	 */
	public String dGetText() {
		if(display.getText().equals("")) return "";
		String s = display.getText().trim();
		for(int i = 0; i < s.length()-1; i++) if(s.charAt(i) == ' ') s = s.substring(0,i) + s.substring(i+1);
		if(hexD == true) {
//			return display.getTex
//			return dec.getText().substring(7);
			return Long.parseUnsignedLong(s, 16) + "";
		}else if(octD == true) {
			return Long.parseUnsignedLong(s, 8) + "";
		}else if(binD == true) {
			return Long.parseUnsignedLong(s, 2) + "";
		}
		return display.getText();
	}
	
	public void setNeg() {
		if(dGetText().contentEquals("")) display.setText("0");
		dec.setText(dec.getText().substring(0, 6) + dGetText());
		bin.setText(bin.getText().substring(0, 6) + textSpacer(Long.toBinaryString(Long.valueOf(dGetText())), 5).trim());
		hex.setText(hex.getText().substring(0, 6) + textSpacer(toUpperString(Long.toHexString(Long.valueOf(dGetText()))),5).trim());
		oct.setText(oct.getText().substring(0, 6) + textSpacer(Long.toOctalString(Long.valueOf(dGetText())), 4).trim());
	}
	
	public String textSpacer(String s, int q) {
		s = s.trim();
		for(int i = 0; i < s.length()-1; i++) if(s.charAt(i) == ' ') s = s.substring(0,i) + s.substring(i+1);
		if(q == 5) {
			for(int i = 0; i < s.length()-1; i++) if(i % 5 == 0) s = s.substring(0,i) + " " + s.substring(i);
		}else {
			
			for(int i = 3; i < s.length(); i++) {
				if(i % 4 == 0) {
					s = s.substring(0,i) + " " + s.substring(i);
				}
			}
			s = s.substring(0, 1) + " " + s.substring(1);
		}
		
		return s;
	}
	
	public String dGetTextUnCov() {
		if(display.getText().contentEquals("0")) return "";
		return display.getText();
	}
	
	/*
	 * this takes the main, and translates it from the bin, hex, oct or dec to any other value, depending on the conv paramater
	 * conv is 2, 8, or 16. it translates from the global boolean, which tells what it was, to the conv, telling ti what it shoudl be
	 *
	 * what i want to translate it from is the boolean
	 * what i want to translate it to is the conv-- the parameter
	 */
	public String mGetTranslated(int conv) {
		String s = main.getText();
		int index = 0;
		String hold = new String("");
		s = s + "   ";
		for(int i = 0; i < s.length(); i++) {
			if(isNum(s.charAt(i))) {
				index = i;
				while(i < s.length() && isNum(s.charAt(i)))
					hold = hold + (s.charAt(i++));
			}//hold now holds a string of integers. in 99 * 33 in the first iteration hold will contain 99 only
			if(hold.length() != 0 && (s.charAt(i) == ' ' || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '÷' || s.charAt(i) == '*')) {
				String subMe = new String(hold);
				/* hold is just an integer
				 * first translate hold to decimal from whatever it was previously, i know from the global boolean
				 * i then translate hold to whatever this function requires it to be
				 */
				if(octD == true) {// this section of code turns hold to its decimal value
					hold = Long.parseUnsignedLong(hold, 8) + "";
				}else if(binD == true) {
					hold = Long.parseUnsignedLong(hold, 2) + "";
				}else if(hexD == true) {
					hold = Long.parseUnsignedLong(hold, 16) + "";
				}
				
				// turnign it into the string it needs to be
				
				/*
				 * converting from bigger to smaller(hex to bin) i need to subtract
				 * converting from smaller to bigger(bin to hex) i need to add
				 */
				if(conv == 2) {
					s = s.substring(0, index) + Long.toBinaryString(Long.valueOf(hold)) + s.substring(i); //adding the converted string into the og string
					i = i + Long.toBinaryString(Long.valueOf(hold)).length() - subMe.length(); //adding the length of the newly converted string to the thingy cus yeah
				}else if(conv == 8) {
					s = s.substring(0, index) + Long.toOctalString(Long.valueOf(hold)) + s.substring(i); //adding the converted string into the og string
					i = i + Long.toOctalString(Long.valueOf(hold)).length() - subMe.length(); //adding the length of the newly converted string to the thingy cus yea
				}else if(conv == 16) {
					s = s.substring(0, index) + toUpperString(Long.toHexString(Long.valueOf(hold))) + s.substring(i); //adding the converted string into the og string
					i = i + (Long.toHexString(Long.valueOf(hold)).length() - subMe.length()); //adding the length of the newly converted string to the thingy cus yeah
				}else if (conv == 10) {
					s = s.substring(0, index) + hold + s.substring(i); //adding the converted string into the og string
					i = i + (hold.length() - subMe.length());
					
				}
				hold = "";
			}
		}
		return s.trim() + " ";
	}
	
	public void updateConvBin() {
		dec.setText("  DEC " + dec.getText().substring(6));
		bin.setText("▮ BIN " + textSpacer(bin.getText().substring(6),5));
		oct.setText("  OCT " + textSpacer(oct.getText().substring(6),4).trim());
		hex.setText("  HEX " + toUpperString(textSpacer(hex.getText().substring(6),5)));
		
		//TODO CHANGE MAIN FOR ME
		
		display.setText(Long.toBinaryString(Long.valueOf(dGetText())));
		mSetText(mGetTranslated(2));
		
		//the main and display are now officialy translated to binary values
		hexD = false;
		decD = false;
		binD = true;
		octD = false;
		
	}
	
	public void updateConvHex() {
		
		dec.setText("  DEC " + dec.getText().substring(6));
		bin.setText("  BIN " + textSpacer(bin.getText().substring(6),5));
		oct.setText("  OCT " + textSpacer(oct.getText().substring(6),4).trim());
		hex.setText("▮ HEX " + toUpperString(textSpacer(hex.getText().substring(6),5)));
		
		display.setText(toUpperString(Long.toHexString(Long.valueOf(dGetText()))));
		mSetText(toUpperString(mGetTranslated(16)));
		
		hexD = true;
		decD = false;
		binD = false;
		octD = false;
	}
	
	public void updateConvDec() {
		dec.setText("▮ DEC " + dec.getText().substring(6));
		bin.setText("  BIN " + textSpacer(bin.getText().substring(6),5));
		oct.setText("  OCT " + textSpacer(oct.getText().substring(6),4).trim());
		hex.setText("  HEX " + toUpperString(textSpacer(hex.getText().substring(6),5)));
		display.setText(dGetText());
		mSetText(mGetTranslated(10));
		
		hexD = false;
		decD = true;
		binD = false;
		octD = false;
	}
	
	public void updateConvOct() {
		dec.setText("  DEC " + dec.getText().substring(6));
		bin.setText("  BIN " + textSpacer(bin.getText().substring(6),5));
		hex.setText("  HEX " + toUpperString(textSpacer(hex.getText().substring(6),5)));
		oct.setText("▮ OCT " + textSpacer(oct.getText().substring(6),4).trim());
		
		display.setText(Long.toOctalString(Long.valueOf(dGetText().trim())));
		mSetText(mGetTranslated(8));
		
		hexD = false;
		decD = false;
		binD = false;
		octD = true;
	}
	
	public void updateConv() {
		if(octD == true) {
			bin.setText("  BIN " + textSpacer(Long.toBinaryString(Long.valueOf(dGetText())),5));
			hex.setText("  HEX " + textSpacer(toUpperString(Long.toHexString(Long.valueOf(dGetText()))),5));
			oct.setText("▮ OCT " + textSpacer(Long.toOctalString(Long.valueOf(dGetText())),4).trim());
			dec.setText("  DEC " + dGetText());
		}else if(binD == true) {
			bin.setText("▮ BIN " + textSpacer(Long.toBinaryString(Long.valueOf(dGetText())),5));
			hex.setText("  HEX " + textSpacer(toUpperString(Long.toHexString(Long.valueOf(dGetText()))),5));
			oct.setText("  OCT " + textSpacer(Long.toOctalString(Long.valueOf(dGetText())),4).trim());
			dec.setText("  DEC " + dGetText());
		}else if(hexD == true) {
			bin.setText("  BIN " + textSpacer(Long.toBinaryString(Long.valueOf(dGetText())),5));
			hex.setText("▮ HEX " + textSpacer(toUpperString(Long.toHexString(Long.valueOf(dGetText()))),5));
			oct.setText("  OCT " + textSpacer(Long.toOctalString(Long.valueOf(dGetText())),4).trim());
			dec.setText("  DEC " + dGetText());
		}else if(decD == true){
			bin.setText("  BIN " + textSpacer(Long.toBinaryString(Long.valueOf(dGetText())),5).trim());
			hex.setText("  HEX " + textSpacer(toUpperString(Long.toHexString(Long.valueOf(dGetText()))),5));
			oct.setText("  OCT " + textSpacer(Long.toOctalString(Long.valueOf(dGetText())),4).trim());
			dec.setText("▮ DEC " + dGetText());
		}
	}
	
	/*
	 * does this need to clean
	 */
	public void dClear() {
		display.setText(null);
		display.setText("");
		if(dGetText().equals("0")) dSetText("0");
		if(octD == true) {
			bin.setText("  BIN " + 0);
			hex.setText("  HEX " + 0);
			oct.setText("▮ OCT " + 0);
			dec.setText("  DEC " + 0);
		}else if(binD == true) {
			bin.setText("▮ BIN " + 0);
			hex.setText("  HEX " + 0);
			oct.setText("  OCT " + 0);
			dec.setText("  DEC " + 0);
		}else if(hexD == true) {
			bin.setText("  BIN " + 0);
			hex.setText("▮ HEX " + 0);
			oct.setText("  OCT " + 0);
			dec.setText("  DEC " + 0);
		}else if(decD == true) {
			bin.setText("  BIN " + 0);
			hex.setText("  HEX " + 0);
			oct.setText("  OCT " + 0);
			dec.setText("▮ DEC " + 0);
		}
	}
	
	public boolean mGetOps() {
		String m = mGetText();
		for(int i = 0; i < m.length(); i++)
			if(m.charAt(i) == '+' || m.charAt(i) == '-' || m.charAt(i) == '÷' || m.charAt(i) == '*') return false;
		return true;
	}
	
	/*
	 * would i need to parse through this to do the stuff? or would the number here already be in their necessary form?
	 * i bleive it would already be in the necessary form, regardless this shouldnt be a translated value
	 */
	public String mGetText() {
		/*
		if(hexD == true) {
			return Long.parseUnsignedLong(display.getText(), 6) + "";
		}else if(octD == true) {
			return Long.parseUnsignedLong(display.getText(), 8) + "";
		}else if(binD == true) {
			return Long.parseUnsignedLong(display.getText(), 2) + "";
		}*/
		return main.getText();
	}
	
	/*
	 * sets the text to what is given to it. isnt adding but changing
	 * so : clear it, set main as the passed String
	 */
	public void mSetText(String s) {
		mClear();
		main.setText(s);
	}
	
	public boolean isNum(char c) {
		if(c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
			return true;
		}
		return false;
	}
	
	public boolean isOp(char c) {
		if(c == '÷' || c == '*' || c == '+' || c == '-' || c == '%') return true;
		return false;
	}
	
	
	public void mClear() {
		main.setText(null);
		main.setText("");
	}
	
	public void dSetText(String s) {
		display.setText(null);
		display.setText(s);
	}
	
	public String toUpperString(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'a' || s.charAt(i) == 'b' || s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'e' || s.charAt(i) == 'f') {
				s = s.substring(0, i) + (s.charAt(i) + "").toUpperCase() + s.substring(i + 1);
			}
		}
		return s;
	}

	public JTextField getDisplay() { return display; }
	public JTextField getMain() { return main; }
	
}
