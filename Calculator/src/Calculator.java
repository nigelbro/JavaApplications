import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.lang.Math.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.font.*;


public class Calculator extends JFrame implements ActionListener {

	private static final int opacity = 0;
	// this is the calculator panel
	private JPanel panel;
	// these are the function buttons
	 static JButton enterButton, addButton, subButton, divButton, mulButton,
			delButton, plusMinusButton, clButton, powButton, ltpButton,
			rtpButton, decButton, sinButton, cosButton, tanButton, lnButton,
			logButton, sqrtButton, eeButton, onButton, piButton;
	// these are the numbers 0-9
	 static JButton zeroButton, oneButton, twoButton, threeButton, fourButton,
			fiveButton, sixButton, sevenButton, eightButton, nineButton ;
	// this is where the answer and calculations will show
 static JTextArea ansField;

	// this will allow answer to have decimals and also input decimals
	 static double  num1, num2,ln, ans;
 static double plusMinus;
	
 static int addClick = 0, subClick = 0, mulClick = 0, divClick = 0, enterClick = 0,powClick = 0, 
			lnClick = 0, piClick= 0, sinClick= 0,cosClick=0, eeClick=0,tanClick=0,logClick=0,sqrtClick=0;
 static int clearField = 1;
	

	public Calculator() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Texas Instrument");
		setResizable(true);
		setSize(350, 470);
		setLocationRelativeTo(null);
		// the panel is now added to the constructor and visible on run
		panel = new JPanel();
		panel.setSize(350, 470);
		panel.setLayout(null);
		// now it is time to add the buttons
		// furthest column right to left
		enterButton = new JButton("=");
		enterButton.setSize(60, 20);
		enterButton.setLocation(265, 400);
		addButton = new JButton("+");
		addButton.setSize(60, 20);
		addButton.setLocation(265, 375);
		subButton = new JButton("-");
		subButton.setSize(60, 20);
		subButton.setLocation(265, 350);
		mulButton = new JButton("x");
		mulButton.setSize(60, 20);
		mulButton.setLocation(265, 325);
		divButton = new JButton("/");
		divButton.setSize(60, 20);
		divButton.setLocation(265, 300);
		
		clButton = new JButton("CC");
		clButton.setSize(60, 20);
		clButton.setLocation(265, 275);
		// next column right to left
		powButton = new JButton("^");
		powButton.setSize(50, 20);
		powButton.setLocation(205, 275);
		rtpButton = new JButton(")");
		rtpButton.setSize(50, 20);
		rtpButton.setLocation(205, 300);
		plusMinusButton = new JButton("+-");
		plusMinusButton.setSize(50, 20);
		plusMinusButton.setLocation(205, 400);
		threeButton = new JButton("3");
		threeButton.setSize(50, 20);
		threeButton.setLocation(205, 375);
		sixButton = new JButton("6");
		sixButton.setSize(50, 20);
		sixButton.setLocation(205, 350);
		nineButton = new JButton("9");
		nineButton.setSize(50, 20);
		nineButton.setLocation(205, 325);
		// next column
		ltpButton = new JButton("(");
		ltpButton.setSize(50, 20);
		ltpButton.setLocation(145, 300);
		decButton = new JButton(".");
		decButton.setSize(50, 20);
		decButton.setLocation(145, 400);
		twoButton = new JButton("2");
		twoButton.setSize(50, 20);
		twoButton.setLocation(145, 375);
		fiveButton = new JButton("5");
		fiveButton.setSize(50, 20);
		fiveButton.setLocation(145, 350);
		eightButton = new JButton("8");
		eightButton.setSize(50, 20);
		eightButton.setLocation(145, 325);
		piButton = new JButton("pi");
		piButton.setSize(50, 20);
		piButton.setLocation(145, 275);
		// next column
		eeButton = new JButton("e^");
		eeButton.setSize(50, 20);
		eeButton.setLocation(85, 300);
		lnButton = new JButton("ln");
		lnButton.setSize(50, 20);
		lnButton.setLocation(85, 275);
		zeroButton = new JButton("0");
		zeroButton.setSize(50, 20);
		zeroButton.setLocation(85, 400);
		oneButton = new JButton("1");
		oneButton.setSize(50, 20);
		oneButton.setLocation(85, 375);
		fourButton = new JButton("4");
		fourButton.setSize(50, 20);
		fourButton.setLocation(85, 350);
		sevenButton = new JButton("7");
		sevenButton.setSize(50, 20);
		sevenButton.setLocation(85, 325);
		// last next column
		delButton = new JButton("Del");
		delButton.setSize(60, 20);
		delButton.setLocation(15, 250);
		sinButton = new JButton("Sin");
		sinButton.setSize(60, 20);
		sinButton.setLocation(15, 275);
		cosButton = new JButton("Cos");
		cosButton.setSize(60, 20);
		cosButton.setLocation(15, 300);
		tanButton = new JButton("Tan");
		tanButton.setSize(60, 20);
		tanButton.setLocation(15, 325);
		logButton = new JButton("Log");
		logButton.setSize(60, 20);
		logButton.setLocation(15, 350);
		sqrtButton = new JButton("\u221a");
		sqrtButton.setSize(60, 20);
		sqrtButton.setLocation(15, 375);
		onButton = new JButton("On");
		onButton.setSize(60, 20);
		onButton.setLocation(15, 400);
		// calculation field
		ansField = new JTextArea();
		ansField.setSize(320, 175);
		ansField.setLocation(12, 23);
		ansField.setEditable(true);
		ansField.setFont(new Font("Arial", Font.BOLD, 24));
		ansField.setLineWrap(true);
		ansField.setForeground(Color.BLACK);
		// adding buttons to pane
		panel.add(enterButton);
		panel.add(addButton);
		panel.add(subButton);
		panel.add(mulButton);
		panel.add(divButton);
		panel.add(powButton);
		panel.add(clButton);
		panel.add(ltpButton);
		panel.add(rtpButton);
		panel.add(eeButton);
		panel.add(delButton);
		panel.add(zeroButton);
		panel.add(oneButton);
		panel.add(twoButton);
		panel.add(threeButton);
		panel.add(fourButton);
		panel.add(fiveButton);
		panel.add(sixButton);
		panel.add(sevenButton);
		panel.add(eightButton);
		panel.add(nineButton);
		panel.add(decButton);
		panel.add(plusMinusButton);
		panel.add(tanButton);
		panel.add(cosButton);
		panel.add(sinButton);
		panel.add(logButton);
		panel.add(lnButton);
		panel.add(onButton);
		panel.add(sqrtButton);
		panel.add(piButton);
		panel.add(ansField);
		add(panel);
		// now its time to add the actionlisteners for each button
		sinButton.addActionListener(this);
		cosButton.addActionListener(this);
		tanButton.addActionListener(this);
		logButton.addActionListener(this);
		lnButton.addActionListener(this);
		sqrtButton.addActionListener(this);
		onButton.addActionListener(this);
		delButton.addActionListener(this);
		eeButton.addActionListener(this);
		piButton.addActionListener(this);
		powButton.addActionListener(this);
		clButton.addActionListener(this);
		divButton.addActionListener(this);
		mulButton.addActionListener(this);
		subButton.addActionListener(this);
		addButton.addActionListener(this);
		enterButton.addActionListener(this);
		ltpButton.addActionListener(this);
		rtpButton.addActionListener(this);
		plusMinusButton.addActionListener(this);
		decButton.addActionListener(this);
		zeroButton.addActionListener(this);
		oneButton.addActionListener(this);
		twoButton.addActionListener(this);
		threeButton.addActionListener(this);
		fourButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		eightButton.addActionListener(this);
		nineButton.addActionListener(this);
		
		//I will now tell the program what to do when buttons are pressed starting with numbers
	}

	public void actionPerformed(ActionEvent e){	
			//This will be the zero button
			if(e.getSource() == zeroButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("0");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "0");
						}else{
							ansField.setText(ansField.getText() + "0");
						}
					}	
				}	
			}
			//end of zero button
			//This will be the one button
			if(e.getSource() == oneButton){
				if(ansField.getText().length() <= 20){
					if( clearField == 1){
						ansField.setText("1");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "1");
						}else{
							ansField.setText(ansField.getText() + "1");
						}
						
					}	
				}	
			}
			//end of one button
			//This will be the two button
			if(e.getSource() == twoButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("2");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "2");
						}else{
							ansField.setText(ansField.getText() + "2");
						}
					}	
				}	
			}
			//end of two button
			//This will be the three button
			if(e.getSource() == threeButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("3");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "3");
						}else{
							ansField.setText(ansField.getText() + "3");
						}
					}	
				}	
			}
			//end of three button
			//This will be the four button
			if(e.getSource() == fourButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("4");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "4");
						}else{
							ansField.setText(ansField.getText() + "4");
						}
					}	
				}	
			}
			//end of four button
			//This will be the five button
			if(e.getSource() == fiveButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("5");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "5");
						}else{
							ansField.setText(ansField.getText() + "5");
						}
					}	
				}	
			}
			//end of five button
			//This will be the six button
			if(e.getSource() == sixButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("6");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "6");
						}else{
							ansField.setText(ansField.getText() + "6");
						}
					}	
				}	
			}
			//end of six button
			//This will be the seven button
			if(e.getSource() == sevenButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("7");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "7");
						}else{
							ansField.setText(ansField.getText() + "7");
						}
					}	
				}	
			}
			//end of seven button
			//This will be the eight button
			if(e.getSource() == eightButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("8");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "8");
						}else{
							ansField.setText(ansField.getText() + "8");
						}
					}	
				}	
			}
			//end of eight button
			//This will be the nine button
			if(e.getSource() == nineButton){
				if(ansField.getText().length() <= 20){
					if(clearField == 1){
						ansField.setText("9");
						clearField = 0;
					}else{
						if(ansField.getText().startsWith("0") && ansField.getText().length() == 1){
							ansField.setText(ansField.getText() +"."+ "9");
						}else{
							ansField.setText(ansField.getText() + "9");
						}
					}	
				}	
			}
			//end of nine button
			
			
			//DECIMAL BUTTON
			if(e.getSource() == decButton){
				if(ansField.getText().length() ==  0 ){
						
						ansField.setText("0" + ".");
						clearField = 0;
				}else if(ansField.getText().length() ==  1){
					ansField.setText(ansField.getText() + ".");
					clearField = 0;
				}
					
			}
			
			
			
			//END OF DECIMAL BUTTON
			//CLEARBUTTON
			if(e.getSource() == clButton){
				if(ansField.getText().length() > 0){
					
						ansField.setText("0");
						clearField = 1;
				}
					
			}
			//END OF CLEARBUTTON
			
			//DEL BUTTON
			if(e.getSource() == delButton){
				if(ansField.getText().length() > 1){
						int length = ansField.getText().length();
						String str = ansField.getText().substring(0, length-1);
						ansField.setText(str);
						clearField = 0;
				}
					
			}
			//END OF DELETE BUTTON 
			
			//ADD BUTTON
			if(e.getSource() == addButton){
				addClick = 1;
				 num1 = Double.parseDouble(String.valueOf(ansField.getText()));
				 ansField.setText("");
				 clearField = 1;
			 }	 
			//END OF ADD BUTTON
			//SUBTRACT BUTTON
			if(e.getSource() == subButton){
					subClick = 1;
				 num1 = Double.parseDouble(String.valueOf(ansField.getText()));
				 ansField.setText("");
				 clearField = 1;
			 }	 
			//END OF SUBTRACT BUTTON
			//MULTIPLY BUTTON
			if(e.getSource() == mulButton){
					mulClick = 1;
				 num1 = Double.parseDouble(String.valueOf(ansField.getText()));
				 ansField.setText("");
				 clearField = 1;
			 }	 
			//END OF MULTIPLY BUTTON
			//DIVIDE BUTTON
			if(e.getSource() == divButton){
					divClick = 1;
				 num1 = Double.parseDouble(String.valueOf(ansField.getText()));
				 ansField.setText("");
				 clearField = 1;
			 }	 
			//END OF DIVIDE BUTTON
			//LN BUTTON
			if(e.getSource() == lnButton){
				if(ansField.getText().length() >0){
					lnClick = 1;
					ansField.setText("ln("+ansField.getText()+")");
					clearField =1;
				}
			 }	 
			//END OF LN BUTTON
			//POWER BUTTON
			if(e.getSource() == powButton){
				if(ansField.getText().length() >0){
					powClick = 1;
				 num1 = Double.parseDouble(String.valueOf(ansField.getText()));
				 ansField.setText("");
				 clearField = 1;
				}
			 }	 
			//END OF POWER BUTTON
			//PI BUTTON
			if(e.getSource() == piButton){
					piClick = 1;
					if(ansField.getText().length() == 0){
				 num1 = Math.PI;
				 ansField.setText(String.valueOf(num1));
				 clearField = 1;
					}
					
			 }	 
			//END OF PI BUTTON
			//E^ BUTTON
			
			if(e.getSource() == eeButton){
				eeClick = 1;
				if(ansField.getText().length() > 0){
					num1 = Math.E;
					ansField.setText("e^"+ansField.getText());
					clearField = 1;
				}
				
			}	 
			
			//END OF E^BUTTON
			//SIN BUTTON
			
			if(e.getSource() == sinButton){
				sinClick = 1;
				if(ansField.getText().length() > 0){
					ansField.setText("sin("+ansField.getText()+")");
					clearField = 1;
				}
				
			}	 
			
			//END OF SIN BUTTON
			//COS BUTTON
			
			if(e.getSource() == cosButton){
				cosClick = 1;
				if(ansField.getText().length() > 0){
					ansField.setText("cos("+ansField.getText()+")");
					clearField = 1;
				}
				
			}	 
			
			//END OF COS BUTTON
			//TAN BUTTON
			
			if(e.getSource() == tanButton){
				tanClick = 1;
				if(ansField.getText().length() > 0){
					ansField.setText("tan("+ansField.getText()+")");
					clearField = 1;
				}
				
			}	 
			
			//END OF TAN BUTTON
			//SQRT BUTTON
			
			if(e.getSource() == sqrtButton){
				sqrtClick = 1;
				if(ansField.getText().length() > 0){
					num1 = Double.parseDouble(String.valueOf(ansField.getText()));
					ansField.setText(String.valueOf(num1));
					clearField = 1;
				}
				
			}	 
			
			//END OF SQRT BUTTON
			//LOG BUTTON
			if(e.getSource() == logButton){
				if(ansField.getText().length() >0){
					logClick = 1;
					ansField.setText("log("+ansField.getText()+")");
					clearField =1;
				}
			 }	 
			
			//END OFLOG BUTTON
			
			//ENTER BUTTON
			if(e.getSource() == enterButton){
				
				
				if(addClick == 1){
					num2 = Double.parseDouble(String.valueOf(ansField.getText()));
					ans = num2;
					ansField.setText(String.valueOf(ans));
					clearField = 1;
					addClick = 0;
				}
				if(subClick == 1){
					num2 = Double.parseDouble(String.valueOf(ansField.getText()));
					ans = num1 - num2;
					ansField.setText(String.valueOf(ans));
					clearField = 1;
					subClick = 0;
				}
				if(mulClick == 1){
					num2 = Double.parseDouble(String.valueOf(ansField.getText()));
					ans = num1 * num2;
					ansField.setText(String.valueOf(ans));
					clearField = 1;
					mulClick = 0;
				}
				if(divClick == 1){
					num2 = Double.parseDouble(String.valueOf(ansField.getText()));
					ans = num1 / num2;
					ansField.setText(String.valueOf(ans));
					clearField = 1;
					divClick = 0;
				}
				if(lnClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(3, length -1);
					num2 = Double.parseDouble(str);
					num2 = Math.log(num2);
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					lnClick = 0;
				}
				if(powClick == 1){
					num2 = Double.parseDouble(String.valueOf(ansField.getText()));
					ansField.setText(String.valueOf(Math.pow(num1, num2)));
					powClick =0;
					clearField = 1;
					
				}
				if(eeClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(2, length);
					num2 = Double.parseDouble(str);
					ansField.setText(String.valueOf(Math.pow(num1, num2)));
					clearField = 1;
					eeClick = 0;
				}
				if(sinClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(4, length -1);
					num2 = Double.parseDouble(str);
					num2 = Math.sin(num2);
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					sinClick = 0;
				}
				if(cosClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(4, length -1);
					num2 = Double.parseDouble(str);
					num2 = Math.cos(num2);
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					cosClick = 0;
				}
				if(tanClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(4, length -1);
					num2 = Double.parseDouble(str);
					num2 = Math.tan(num2);
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					tanClick = 0;
				}
				if(sqrtClick ==1){
						
					num2 = Double.parseDouble(String.valueOf(Math.sqrt(num1)));
					
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					sqrtClick = 0;
				}
				if(logClick ==1){
					int length = ansField.getText().length();
					String str = ansField.getText().substring(4, length -1);
					num2 = Double.parseDouble(str);
					num2 = Math.log10(num2);
					ansField.setText(String.valueOf(num2));
					clearField = 1;
					logClick = 0;
				}
			

			}
			
			
			
			//END OF ENTER BUTTON
	 }
}

