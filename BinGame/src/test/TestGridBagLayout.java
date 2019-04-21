package test;

    import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

    public class TestGridBagLayout
    extends JFrame
    {
      public static void main(String[] args)
      {
	  TestGridBagLayout wnd = new TestGridBagLayout();
        wnd.setVisible(true);
      }

      public TestGridBagLayout()
      {
        super("Test GridBagLayout");
        setBackground(Color.lightGray);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //addWindowListener(new WindowClosingAdapter(true));
        //Layout setzen und Komponenten hinzufügen
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc;
        setLayout(gbl);

        //List hinzufügen
        List list = new List();
        for (int i = 0; i < 20; ++i) {
          list.add("This is item " + i);
        }
        gbc = makegbc(0, 0, 1, 3);
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(list, gbc);
        add(list);
        //Zwei Labels und zwei Textfelder
        for (int i = 0; i < 2; ++i) {
          //Label
          gbc = makegbc(1, i, 1, 1);
          gbc.fill = GridBagConstraints.NONE;
          Label label = new Label("Label " + (i + 1));
          gbl.setConstraints(label, gbc);
          add(label);
          //Textfeld
          gbc = makegbc(2, i, 1, 1);
          gbc.weightx = 100;
          gbc.fill = GridBagConstraints.HORIZONTAL;
          TextField field = new TextField("TextField " + (i +1));
          gbl.setConstraints(field, gbc);
          add(field);
        }
        //Ende-Button
        Button button = new Button("Ende");
        gbc = makegbc(2, 2, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbl.setConstraints(button, gbc);
        add(button);
        //Dialogelemente layouten
        pack();
      }

      private GridBagConstraints makegbc(
        int x, int y, int width, int height)
      {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.insets = new Insets(1, 1, 1, 1);
        return gbc;
      }

    
}
