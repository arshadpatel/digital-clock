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
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame implements ActionListener{
	
	JFrame frame;
	Clock c;
	StopWatch ct;
	TimerT tt;
	
	JButton btn1;
	JButton btn2;
	Boolean flag1 =true;
	Boolean flag2 =true;
	Boolean init = true;
	
	Font f;
	InputStream myStream;
	
	MainFrame(){
		
		String filename = "D:\\Projects\\Java\\digital-clock\\DigitalClock\\digital-7.ttf";  // Relative path to the font file

		try {
			myStream = new BufferedInputStream(new FileInputStream(filename));
            f = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		c = new Clock();
		ct = new StopWatch();
		tt = new TimerT();
		
		frame = new JFrame();
		frame.add(c.panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clock");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black);
		frame.setSize(420,420);
		frame.setLayout(null);
		
		btn1 = new JButton("TIMER");
		btn2 = new JButton("STOPWATCH");
			
		btn1.setBounds(90,260,120,50);
		btn1.setFont(f.deriveFont(Font.PLAIN,20));
		btn1.setFocusable(false);
		btn1.addActionListener(this);
			
		btn2.setBounds(210,260,120,50);
		btn2.setFont(f.deriveFont(Font.PLAIN,20));
		btn2.setFocusable(false);
		btn2.addActionListener(this);
			
		frame.add(btn1);
		frame.add(btn2);
		frame.setVisible(true);
		c.setTime();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(flag1&&flag2) {
			if(e.getSource()==btn1) {
				flag1=false;
				flag2=false;
				btn1.setText("STOPWATCH");
				btn2.setText("CLOCK");
				frame.remove(c.panel);
				frame.remove(ct.panel);
				frame.add(tt.panel);
				frame.setSize(420,421);
			}
			if(e.getSource()==btn2) {
				flag1=true;
				flag2=false;
				btn1.setText("CLOCK");
				btn2.setText("TIMER");
				frame.remove(c.panel);
				frame.remove(tt.panel);
				frame.add(ct.panel);
				frame.setSize(421,421);
			}
		}
		
		else if(!(flag1)&&!(flag2)) {
			if(e.getSource()==btn1) {
				flag1=true;
				flag2=false;
				btn1.setText("CLOCK");
				btn2.setText("TIMER");
				frame.remove(c.panel);
				frame.remove(tt.panel);
				frame.add(ct.panel);
				frame.setSize(421,421);
			}
			if(e.getSource()==btn2) {
				flag1=true;
				flag2=true;
				btn1.setText("TIMER");
				btn2.setText("STOPWATCH");
				frame.remove(ct.panel);
				frame.remove(tt.panel);
				frame.add(c.panel);
				frame.setSize(421,420);
			}
		}
		
		else if((flag1)&&!(flag2)) {
			if(e.getSource()==btn1) {
				flag1=true;
				flag2=true;
				btn1.setText("TIMER");
				btn2.setText("STOPWATCH");
				frame.remove(ct.panel);
				frame.remove(tt.panel);
				frame.add(c.panel);
				frame.setSize(421,420);
			}
			if(e.getSource()==btn2) {
				flag1=false;
				flag2=false;
				btn1.setText("STOPWATCH");
				btn2.setText("CLOCK");
				frame.remove(ct.panel);
				frame.remove(c.panel);
				frame.add(tt.panel);
				frame.setSize(420,421);
			}
		}		
	}
}
