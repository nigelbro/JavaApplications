package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TypingGui extends Frame implements ActionListener,KeyListener {

	private ArrayList<String> paragraphs = new ArrayList<>();
	private JFrame f;
	private JPanel p;
	private JTextArea t1;
	private JTextArea t2;
	private JButton generate;
	private JLabel timerLabel;
	static Timer timer;
	private JTextField timerField;
	GridBagLayout gl;
	GridBagConstraints gbc;
	private String starttext = new String("Press the generate " + "button to" + " generate a text and type it exactly"
			+ " in the other textbox and press finish");
	
	/**
	 * Scoring Variables
	 */
	private int wpm = 0;
	private int errors = 0;
	private boolean typingStarted = false;
	

	public TypingGui() {
		setUp();
	}

	private void setUp(){
		paragraphs.add("TypingChallenges/challenge1");
		paragraphs.add("TypingChallenges/challenge2");
		paragraphs.add("TypingChallenges/challenge3");
		paragraphs.add("TypingChallenges/challenge4");
		
		f = new JFrame("Typing Help");
		f.setSize(600, 400);
		p = (JPanel) f.getContentPane();
		p.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		gl = new GridBagLayout();
		gbc= new GridBagConstraints();
	
		p.setBackground(Color.YELLOW);
		p.setLayout(gl);
		
		generate = new JButton("Generate Text");
		generate.addActionListener(this);
		generate.setPreferredSize(new Dimension(100,40));
		generate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//Timer label and field to count-down for user
		timerField = new JTextField("60");
		timerField.setBorder(BorderFactory.createEmptyBorder());
		timerField.setEditable(false);
		timerField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		timerField.setBackground(Color.YELLOW);

		timerLabel = new JLabel("Timer:");
		timerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		timerLabel.setForeground(Color.BLACK);
		
		//Text area one for displayed text, second to type generated text.
		
		t1 = new JTextArea(starttext);
		t1.setPreferredSize(new Dimension(600,200));
		t1.setEditable(false);
		t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		t1.setLineWrap(true);
		t1.setWrapStyleWord(true);
		
		t2 = new JTextArea();
		t2.setPreferredSize(new Dimension(600,200));
		t2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		t2.setLineWrap(true);
		t2.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent event) {
				
				
			}

			@Override
			public void insertUpdate(DocumentEvent event) {
				if(!typingStarted) {
					typingStarted = true;
					timer = new Timer();
					TimerTask timerTask = new TimerTask(){
						
						@Override
						public void run(){
							try{
								if(Integer.parseInt(timerField.getText()) <= 30 && Integer.parseInt(timerField.getText()) > 10) timerField.setForeground(Color.ORANGE);;
								if(Integer.parseInt(timerField.getText()) <= 10) timerField.setForeground(Color.RED);
								if(Integer.parseInt(timerField.getText()) == 0){
									timer.cancel();
									t2.setEditable(false);
									calculateResults();
									
								}else{
								timerField.setText(Integer.toString(Integer.parseInt(timerField.getText())-1));
								}
							}catch(NumberFormatException e){
								System.out.println("Cannot convert to int");
							}
						}
					};
					timer.scheduleAtFixedRate(timerTask, 1, 1000);
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent event) {
				
			}	
			
			
			
		});
		t2.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		t2.addKeyListener(this);
		t2.setWrapStyleWord(true);
		
		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 20;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		p.add(timerLabel,gbc);
		
		gbc.weightx = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipady = 20;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(timerField,gbc);
		
		gbc.weightx =1.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth =3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(new JScrollPane(t1),gbc);
	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 0;
		gbc.weightx= 0.0;
		gbc.weighty= 1.0;
		gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc.insets = new Insets(10,100,10,100);
		gbc.gridy = 2;
		gbc.gridx =0;
		p.add(generate, gbc);

		gbc.weightx =1.0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(new JScrollPane(t2),gbc);
		
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	@Override
	public void actionPerformed(ActionEvent action) {
		Object buttonSelected = action.getSource();
		if(buttonSelected == generate){
			Random rand  = new Random();
			try{
				File file = new File(paragraphs.get(rand.nextInt(paragraphs.size())));
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				t1.read(br, null);
			}catch(IOException e){
				e.printStackTrace();
			}
			
			//t1.setText(paragraphs.get(rand.nextInt(paragraphs.size())));
			
		}
	}
	
	private void setWPM(){
		this.wpm++;
	}
	 private int getWPM(){
		 return this.wpm;
	 }
	 
	 private void calculateResults(){
		 String[] textToType = t1.getText().split(" ");
		 String[] textTyped = t2.getText().split(" ");
		 double wpm = (double)getWPM() / ((double)Integer.parseInt(timerField.getText())/60.0);
		 for(int i = 0 ; i < textTyped.length; i++){
			 if(i > textToType.length - 1){
				 break;
			 }else if(!textTyped[i].equals(textToType[i])){
				 setErrorCount();
			 }
		 }
		 t2.setEditable(false);
		 JOptionPane.showMessageDialog(null, "Times Up\nWPM: "+Integer.toString((int)wpm)
		 									+"\nErrors: "+Integer.toString(getErrorCount())+"\nNet WPM: "
		 									+Integer.toString((int)wpm - getErrorCount()));
		 
		 
	 }
	 private void setErrorCount(){
		 this.errors++;
	 }
	 private int getErrorCount(){
		 return this.errors;
	 }
	 private int getNetWPM(){
		 return this.wpm - this.errors;
	 }

	@Override
	public void keyPressed(KeyEvent key) {
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		String[] textToType = t1.getText().split(" ");
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE){
			setWPM();
			
			if(getWPM() == textToType.length){
				timer.cancel();
				calculateResults();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
		
	}

}
