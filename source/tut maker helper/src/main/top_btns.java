package main;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;



public class top_btns extends JPanel implements ActionListener
{
	private JButton add_txt,add_code,add_title,yresult,save,load;
	
	private JFileChooser fc = new JFileChooser();
	
	public forms_panel fp;
	
	public btns_logic bl;
	
	public top_btns(forms_panel fp2)
	{
		fp = fp2;//set fp so we can manipulate it with btns click action
		
		//init btn logic object (i wanted to seperate view from logic as much as i can)
		bl = new btns_logic(fp);
		
		//init btns
		add_txt = new JButton("add_txt");
		add_code = new JButton("add_code");
		add_title = new JButton("add_title");
		yresult = new JButton("yresult");
		save = new JButton("save");
		load = new JButton("load");
		
		//set all events (espacialy fo click) to this (pross in actionPerformed method)
		add_txt.addActionListener(this);
		add_code.addActionListener(this);
		add_title.addActionListener(this);
		yresult.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		
		//float all btns left and add border
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createEtchedBorder());
		
		//add btns to panel
		add(add_txt);
		add(add_code);
		add(add_title);
		add(yresult);
		add(save);
		add(load);
		
	
	}//end constractor
	
	
	
	
	//handle click event
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton click = (JButton) e.getSource();//get btn clicked var name
		//handle clicked btns
		if(click == add_txt){bl.add_txt_field("text","");}
		if(click == add_code){bl.add_txt_field("code","");}
		if(click == add_title){bl.add_txt_field("title","");}
		if(click == yresult)
		{
			bl.to_html() ;
		}
		
		if(click == save)
		{
			
			
			 int returnVal = fc.showSaveDialog(save);

		        if (returnVal == JFileChooser.APPROVE_OPTION) 
		        {
		            File file = fc.getSelectedFile();
		            System.out.println("Opening: " + file.getAbsolutePath());
		            
		            String file_name = file.getAbsolutePath();
		            
		            if(!file_name.contains(".dat")) {file_name +=".dat";}//if no dat extantion add it
		            
		            bl.save_arrlist_to_file(file_name);
		            
					//FileWriter fw = new FileWriter(file.getAbsoluteFile()+".txt");
		        }
		}
		
		if(click == load)
		{
			int returnVal2 = fc.showOpenDialog(load);

	        if (returnVal2 == JFileChooser.APPROVE_OPTION) 
	        {
	        	File file = fc.getSelectedFile();
	        	System.out.println("Opening: " + file.getAbsolutePath());
	        	bl.load_arrlist_from_file(file.getAbsolutePath());
	        }
		}
		
		
	}//end actionPerformed
	

}
