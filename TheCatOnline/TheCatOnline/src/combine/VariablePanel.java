package combine;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data.Constants;

public class VariablePanel extends JPanel {

	public RadioPanel[] radios;
	
	public VariablePanel() {
		this.radios = new RadioPanel[Constants.maxVariableNumber];
		for(int i = 0; i < Constants.maxVariableNumber; i++) {
			this.radios[i] = new RadioPanel(i);
		}
		this.setSize(102,500);
		this.setLayout(new GridLayout(0,1));
		for(int i = 0; i < Constants.maxVariableNumber; i++) {
			this.add(this.radios[i]);
		}
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Test");
		window.setSize(300, 800);
		window.add(new VariablePanel());
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
