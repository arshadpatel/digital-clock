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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class TimerT implements ActionListener {

	JPanel panel = new JPanel();
	
	JButton startBtn = new JButton("START");
	JButton deleteBtn = new JButton("DELETE");
	JLabel timeLabel = new JLabel();
	
	JSpinner hrs_spinner = new JSpinner();
	JSpinner mint_spinner = new JSpinner();
	JSpinner sec_spinner = new JSpinner();
	
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
			if(((hours==0)&&(minutes ==0))&&(seconds==0)) {
				delete();
			}else {
				elapsedTime-=1000;
				hours = (elapsedTime/3600000);
				minutes = (elapsedTime/60000) % 60;
				seconds = (elapsedTime/1000) % 60;
				seconds_str = String.format("%02d", seconds);
				minutes_str = String.format("%02d", minutes);
				hours_str = String.format("%02d", hours);
				sec_spinner.setValue(seconds);
				mint_spinner.setValue(minutes);
				hrs_spinner.setValue(hours);
			}
		}
	}); 
	
	TimerT(){
		String filename="Path to digital-7\\Digital Clock\\src\\com\\digitalwatch\\digital-7.ttf"; 
		try {
			myStream = new BufferedInputStream(new FileInputStream(filename));
            f = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		hrs_spinner.setFont(f.deriveFont(Font.PLAIN,55));
		Border border = BorderFactory.createLineBorder(Color.cyan,5);
		hrs_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		hrs_spinner.setBounds(105, 100, 70, 60);
		hrs_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		hrs_spinner.getEditor().getComponent(0).setBackground(Color.black);
		hrs_spinner.setBorder(BorderFactory.createBevelBorder(2));
		hrs_spinner.setOpaque(true);
		hrs_spinner.setBorder(border);
		
		mint_spinner.setFont(f.deriveFont(Font.PLAIN,55));
		mint_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		mint_spinner.setBounds(175, 100, 70, 60);
		mint_spinner.getEditor().getComponent(0).setBackground(Color.black);
		mint_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		mint_spinner.setBorder(BorderFactory.createBevelBorder(2));
		mint_spinner.setOpaque(true);
		mint_spinner.setBorder(border);
		
		sec_spinner.setFont(f.deriveFont(Font.PLAIN,55));
		sec_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		sec_spinner.setBounds(245, 100, 70, 60);
		sec_spinner.setBorder(BorderFactory.createBevelBorder(2));
		sec_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		sec_spinner.getEditor().getComponent(0).setBackground(Color.black);
		sec_spinner.setOpaque(true);
		sec_spinner.setBorder(border);
		
		startBtn.setBounds(160,200,100,50);
		startBtn.setFont(f.deriveFont(Font.PLAIN,20));
		startBtn.setFocusable(false);
		startBtn.addActionListener(this);

		panel.add(startBtn);
		panel.add(deleteBtn);
		panel.add(hrs_spinner);
		panel.add(mint_spinner);
		panel.add(sec_spinner);
		panel.setLayout(null);
		panel.setSize(420,420);
		panel.setBackground(Color.black);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startBtn) {
			if(started == false) {
				started = true;
				start();
			}
			else {
				started = false;
				startBtn.setText("RESUME");
				pause();
			}
		}
		if(e.getSource()==deleteBtn) {
			started=false;
			startBtn.setText("START");
			delete();
		}
	}
	
	void start() {
		seconds = (int) sec_spinner.getValue();
		minutes = (int) mint_spinner.getValue();
		hours = (int) hrs_spinner.getValue();
		elapsedTime = seconds*1000 + minutes*60000 +hours*3600000 ;
		timer.start();
		startBtn.setText("PAUSE");
		startBtn.setBounds(200,200,100,50);
		panel.add(deleteBtn);
		deleteBtn.setBounds(100,200,100,50);
		deleteBtn.setFont(f.deriveFont(Font.PLAIN,20));
		deleteBtn.setFocusable(false);
		deleteBtn.addActionListener(this);
	}
	
	void pause() {
		timer.stop();
	}
	
	void resume() {
		timer.start();
	}
	
	void delete() {
		started=false;
		startBtn.setText("START");
		timer.stop();
		elapsedTime=0;
		seconds=0;
		minutes=0;
		hours=0;
		seconds_str = String.format("%02d", seconds);
		minutes_str = String.format("%02d", minutes);
		hours_str = String.format("%02d", hours);
		hrs_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		hrs_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		hrs_spinner.getEditor().getComponent(0).setBackground(Color.black);
		
		mint_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		mint_spinner.getEditor().getComponent(0).setBackground(Color.black);
		mint_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		
		sec_spinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		sec_spinner.getEditor().getComponent(0).setForeground(Color.cyan);
		sec_spinner.getEditor().getComponent(0).setBackground(Color.black);
		
		startBtn.setBounds(160,200,100,50);
		deleteBtn.setBounds(160,200,100,50);
		panel.remove(deleteBtn);
	}
}
