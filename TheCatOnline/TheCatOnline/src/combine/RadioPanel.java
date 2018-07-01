package combine;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Constants;
import maths.Maths;

public class RadioPanel extends JPanel  implements ActionListener {
	
	protected int varNumber;
	
	protected JRadioButton on,off;
	
	protected ButtonGroup group;
	
	public RadioPanel(int varNumber) {
		this.varNumber = Maths.betweenZeroAndN(varNumber, Constants.maxVariableNumber);
		this.setSize(100, 30);
		this.group = new ButtonGroup();
		this.on = new JRadioButton("on");
		this.on.setBackground(Constants.onColor[this.varNumber]);
		this.on.addActionListener(this);
		this.off = new JRadioButton("off");
		this.off.setBackground(Constants.offColor[this.varNumber]);
		this.off.addActionListener(this);
		this.group.add(this.on);
		this.group.add(this.off);		
		this.setLayout(new FlowLayout());
		this.add(on);
		this.add(off);
		this.setTrue();
		
	}
	
	public boolean getValue() {
		return this.on.isSelected();
	}
	
	public void setTrue() {
		this.on.setSelected(true);
	}
	
	public void setFalse() {
		this.off.setSelected(true);
	}
	
	public int getVarNumber() {
		return this.varNumber;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		boolean value = this.getValue();
		
		int bValue = Constants.isTrue;
		if(value) {
			bValue = Constants.isTrue;
		} else {
			bValue = Constants.isFalse;
		}
		//System.out.println("Huhu");
		StaticHolder.area.setVariable(this.varNumber, bValue);
		StaticHolder.area.repaint();
		if(StaticHolder.area.isTrue()) {
			JOptionPane.showMessageDialog(StaticHolder.mainWindow, "Herzlichen Glückwunsch! Sie haben gewonnen.");
		}
		
	}

}
