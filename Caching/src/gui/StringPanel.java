package gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class StringPanel extends JPanel {

	String revealed = "";
	
	public StringPanel(String s) {
		this.setSize(602,22);
		this.revealed = s;
	}
	
	public void paintString(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		String substring,lastChar;
		g2.drawRect(0, 0, 600, 20);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		substring = this.revealed.substring(0, this.revealed.length()-1);
		lastChar = ""+this.revealed.charAt(this.revealed.length()-1);
		g2.drawString(""+substring, 10, 15);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 16));
		Font plain = new Font("TimesRoman", Font.PLAIN,16);
		Font bold = new Font("TimesRoman", Font.BOLD,16);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout tl2 = new TextLayout(""+lastChar, bold, frc);
		FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(substring, g2);
		tl2.draw(g2, (float)rect.getWidth()+5, 15);

		
	}
	
	public void add(char c) {
		this.revealed += c;
		this.repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintString(g);
	}
	
	
}
