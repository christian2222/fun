package data;

import java.awt.*;

import javax.swing.*;


public class Constants {
	/**
	 * Die boolschen Werte werden als integer deklariert, um später noch neue "Zustände" angeben zu können.
	 * 
	 */
	public static final int isTrue = 1;
	public static final int isFalse = 0;
	public static final int isUndefined = -1;
	
	public static final boolean isNegated = true;
	public static final boolean notNegated = false;
	
	/**
	 * maximale Werte für das Programm
	 */
	public static final int maxVariableNumber = 16;
	public static final int maxLiteralNumber = 90;
	public static final int maxDisjClauseNumber = 30;
	
	public static final Color orange = new Color(200,100,0);
	public static final Color magenta = new Color(200,200,0);
	public static final Color cyan = new Color(0,200,200);
	
	public static final Color standColor = Color.BLACK;
	

	public static final Color greenOn = new Color(0,204,0);
	public static final Color greenOff = new Color(0,85,0);
	public static final Color cyanOn = new Color(0,221,221);
	public static final Color cyanOff = new Color(0,136,119);
	public static final Color blueOn = new Color(0,0,255);
	public static final Color blueOff = new Color(51,102,221);
	public static final Color grayOn = new Color(136,136,136);
	public static final Color grayOff = new Color(68,68,68);
	public static final Color violetOn = new Color(85,68,187);
	public static final Color violetOff = new Color(51,51,119);
	public static final Color lilaOn = new Color(187,68,255);
	public static final Color lilaOff = new Color(153,0,255);
	public static final Color redOn = new Color(204,0,0);
	public static final Color redOff = new Color(204,51,51);
	public static final Color pinkOn = new Color(255,34,170);
	public static final Color pinkOff = new Color(255,153,170);
	public static final Color yellowOn = new Color(210,210,0);
	public static final Color yellowOff = new Color(255,255,136);
	public static final Color orangeOn = new Color(255,68,0);
	public static final Color orangeOff = new Color(255,136,68);
	public static final Color goldOn = new Color(255,119,00);
	public static final Color goldOff = new Color(218,165,32);
	public static final Color brownOn = new Color(153,51,0);
	public static final Color brownOff = new Color(153,102,34);
	public static final Color skyOn = new Color(119,170,204);
	public static final Color skyOff = new Color(170,238,204);
	public static final Color yellowgreenOn = new Color(154,205,50);
	public static final Color yellowgreenOff = new Color(172,239,83);
	public static final Color naviOn = new Color(0,0,128);
	public static final Color naviOff = new Color(68,68,224);
	public static final Color darkpinkOn = new Color(136,0,136);
	public static final Color darkpinkOff = new Color(187,119,170);
	
	/**
	 * definiert onColor und offColor arrays; dadurch ist der Zugriff auf die Farben relativ einfach
	 */
	public static final Color[] onColor = {
		greenOn,
		cyanOn,
		blueOn,
		grayOn,
		violetOn,
		lilaOn,
		redOn,
		yellowOn,
		orangeOn,
		pinkOn,
		goldOn,
		brownOn,
		skyOn,
		yellowgreenOn,
		naviOn,
		darkpinkOn
										};
	
	public static final Color[] offColor = {
		greenOff,
		cyanOff,
		blueOff,
		grayOff,
		violetOff,
		lilaOff,
		redOff,
		yellowOff,
		orangeOff,
		pinkOff,
		goldOff,
		brownOff,
		skyOff,
		yellowgreenOff,
		naviOff,
		darkpinkOff

										};
	
	/**
	 * anonyme Klasse, um ein JFrame zu initialisieren
	 * 
	 */
	public static JFrame testFrame = new JFrame("testFrame") {
		public void paint(Graphics g) {
			for(int i = 0; i < onColor.length; i++) {
				g.setColor(onColor[i]);
				g.fillRect(20+30*i, 20, 20, 20);
				g.setColor(offColor[i]);
				g.fillRect(20+30*i, 42, 20, 20);
			}

			g.drawString("onLength: "+onColor.length, 100, 300);
			g.drawString("offLength: "+offColor.length, 100, 340);
		}
	};
	
	/**
	 * Die Instanzierung über die anonyme Klasse hat schon etliche Voreinstellungen getroffen.
	 * Darum müssen wir mit dem Objekt [testFrame] nichts weiter machen,als es anzuzeigen und als Hauptprogramme zu behandeln. 
	 */
	public static void drawPalette() {
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(640,480);
		testFrame.setVisible(true);
	}
	
	/**
	 * mit [drawPalette] zusammenlegbar
	 * @param args keine Bedeutung
	 */
	public static void main(String args[]) {
		drawPalette();
	}
	
	
}
