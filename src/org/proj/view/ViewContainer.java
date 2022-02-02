package org.proj.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public abstract class ViewContainer extends JPanel implements ActionListener{
	public abstract void display();

	public abstract void actionPerformed(ActionEvent e);
}
