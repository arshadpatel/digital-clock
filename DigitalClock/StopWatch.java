package DigitalClock;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class StopWatch implements ActionListener{

	JPanel panel = new JPanel();
	JButton startBtn = new JButton("START");
	JButton resetBtn = new JButton("RESET");
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_str = String.format("%02d", seconds);
	String minutes_str = String.format("%02d", minutes);
	String hours_str = String.format("%02d", hours);
	
	Font f;
	InputStream myStream;
	
	Timer timer = new Timer(1000,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			elapsedTime+=1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			seconds_str = String.format("%02d", seconds);
			minutes_str = String.format("%02d", minutes);
			hours_str = String.format("%02d", hours);
			timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
		}
	});
	
	StopWatch(){
		
		String filename="Path to digital-7\\Digital Clock\\src\\com\\digitalwatch\\digital-7.ttf"; 
		try {
			myStream = new BufferedInputStream(new FileInputStream(filename));
            f = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
		timeLabel.setBounds(100,100,200,100);
		timeLabel.setFont(f.deriveFont(Font.PLAIN,55));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setForeground(Color.cyan);
		timeLabel.setBackground(Color.black);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		Border border = BorderFactory.createLineBorder(Color.cyan,5);
		timeLabel.setBorder(border);
		
		startBtn.setBounds(100,200,100,50);
		startBtn.setFont(f.deriveFont(Font.PLAIN,20));
		startBtn.setFocusable(false);
		startBtn.addActionListener(this);
		
		resetBtn.setBounds(200,200,100,50);
		resetBtn.setFont(f.deriveFont(Font.PLAIN,20));
		resetBtn.setFocusable(false);
		resetBtn.addActionListener(this);
		
		panel.add(startBtn);
		panel.add(resetBtn);
		panel.add(timeLabel);
		panel.setBackground(Color.black);
		panel.setSize(420,420);
		panel.setLayout(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startBtn) {
			if(started == false) {
				started = true;
				startBtn.setText("STOP");
				start();
			}
			else {
				started = false;
				startBtn.setText("START");
				stop();
			}
		}
		if(e.getSource()==resetBtn) {
			started=false;
			startBtn.setText("START");
			reset();
		}
	}
	
	void start() {
		timer.start();
	}
	
	void stop() {
		timer.stop();
	}
	
	void reset() {
		timer.stop();
		elapsedTime=0;
		seconds=0;
		minutes=0;
		hours=0;
		seconds_str = String.format("%02d", seconds);
		minutes_str = String.format("%02d", minutes);
		hours_str = String.format("%02d", hours);
		timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
	}
}
