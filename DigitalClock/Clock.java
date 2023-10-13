package DigitalClock;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Clock{

	JPanel panel;
	SimpleDateFormat timeFormat;
	SimpleDateFormat dayFormat;
	SimpleDateFormat dateFormat;
	JLabel timeLabel;
	JLabel dayLabel;
	JLabel dateLabel;
	String time;
	String day;
	String date;
	
	Font f;
	InputStream myStream;
	
	Clock(){
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(33, 100, 360, 160); 
		panel.setBackground(Color.black);
		
		String filename = "D:\\Projects\\Java\\digital-clock\\DigitalClock\\digital-7.ttf"; 
		try {
			myStream = new BufferedInputStream(new FileInputStream(filename));
            f = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		timeFormat = new SimpleDateFormat("hh : mm : ss a");
		dayFormat = new SimpleDateFormat("EEEE");
		dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
		
		timeLabel = new JLabel();
		timeLabel.setFont(f.deriveFont(Font.PLAIN,55));
		timeLabel.setForeground(Color.cyan);
		timeLabel.setBackground(Color.black);
		timeLabel.setBounds(5, 5, 350, 50);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);

		dayLabel = new JLabel();
		dayLabel.setFont(f.deriveFont(Font.PLAIN,40));
		dayLabel.setForeground(Color.cyan);
		dayLabel.setBackground(Color.black);
		dayLabel.setBounds(5, 55, 350, 50);
		dayLabel.setOpaque(true);
		dayLabel.setHorizontalAlignment(JTextField.CENTER);
	
		dateLabel = new JLabel();
		dateLabel.setFont(f.deriveFont(Font.PLAIN,30));
		dateLabel.setForeground(Color.cyan);
		dateLabel.setBackground(Color.black);
		dateLabel.setBounds(5, 100, 350, 50);
		dateLabel.setOpaque(true);
		dateLabel.setHorizontalAlignment(JTextField.CENTER);
		
		panel.add(timeLabel);
		panel.add(dayLabel);
		panel.add(dateLabel);
		Border border = BorderFactory.createLineBorder(Color.cyan,5);
		panel.setBorder(border);
	}
	
	public void setTime() {
		while(true) {
			time = timeFormat.format(new Date());
			time = time.toUpperCase();
			timeLabel.setText(time);
			
			day = dayFormat.format(new Date());
			dayLabel.setText(day);

			date = dateFormat.format(new Date());
			dateLabel.setText(date);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
