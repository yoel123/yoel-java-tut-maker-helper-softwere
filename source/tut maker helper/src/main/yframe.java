package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class yframe extends JFrame
{
	public int w,h;
	public top_btns tb;
	public forms_panel fp;
	
	public yframe(String title) throws HeadlessException {
		super(title);
		w=900;
		h=500;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		yui();
		setSize(w,h);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void yshow()
	{
		setVisible(true);
	}
	
	public void yui()
	{
		fp = new forms_panel(new GridLayout(0, 1));
		tb = new top_btns(fp);
		
		//add forms panel to scroolpane
		JScrollPane fp_scroll = new JScrollPane(fp,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//vertical scroll 
	
		//add to frame
		add(tb,BorderLayout.NORTH);
		add(fp_scroll,BorderLayout.CENTER);
	}//end yui

}//end yframe
