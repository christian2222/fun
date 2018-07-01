package combine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TimeTimer extends Canvas {

	public static String time = "";
	public int min = 5;
	public int sec = 0;
	public int x = 100;
	public int y = 100;
	public JLabel timeLabel;
	
	public TimeTimer() {
		super();
		this.min = 2;
		this.sec = 0;
	}
	
	public TimeTimer(int min, int sec,int x, int y, JLabel label) {
		this.min = min;
		this.sec = sec;
		this.x = x;
		this.y = y;
		this.timeLabel = label;
	}
	
	
	
	public int getMin() {
		return min;
	}

	public int getSec() {
		return sec;
	}

	public static void main(String[] args) {
		TimeTimer tt = new TimeTimer(0,10,100,100, new JLabel());
		tt.countDownText();
		System.out.println("Finished");
		
	}
	
	public void paint(Graphics g) {
		g.drawString(min+":"+sec, x, y);
	}
	
	public void countDownText() {
		while(min >= 0 && sec >= 0) {
			if(sec == 0) {
				System.out.println(min+":"+sec+"0");
				min--;
				sec = 60 -1;
			}
			else {
				if(sec < 10) {
					System.out.println(min+":0"+sec);
				}
				else {
					System.out.println(min+":"+sec);
				}
				sec--;
			}
			waiting(1000);
		}
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void countDownGraphics() {
		while(min >= 0 && sec >= 0) {
			this.timeLabel.setText(time);
			if(sec == 0) {
				time = min+":0"+sec;
				this.repaint();
				min--;
				sec = 60 -1;
			}
			else {
				if(sec < 10) {
					time = min+":0"+sec;
				}
				else {
					time = min+":"+sec;
				}
				sec--;
			}
			waiting(1000);
		}
	}

	
	public static void waiting(int ms) {
		Thread timeThread = new Thread();
		//System.out.println("Hello");
		try {
			timeThread.sleep(ms);
		}catch (Exception e) {
			//System.out.println("Error while waiting...");
		}
		//System.out.println("Goodbye");
		
	}
}
