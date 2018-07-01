package combine;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import data.Constants;

public class StaticHolder {

	public static AreaPanel area = new AreaPanel(0, 0);
	public static VariablePanel variables = new VariablePanel();
	public static JFrame mainWindow = new JFrame("MainWindow");

	
	public static void main(String[] args) {
		mainWindow.setSize(1200, 800);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.add(area, BorderLayout.CENTER);
		mainWindow.add(variables, BorderLayout.EAST);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		area.initValue(Constants.isTrue);
		mainWindow.setVisible(true);
	}
}
