package main;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class btns_logic {
	
	private int count_textareas;
	
	private ArrayList<JTextArea> txtfields;
	
	public forms_panel fp;
	
	public btns_logic(forms_panel fp2) {
		super();
		//init arraylist
		txtfields = new ArrayList<JTextArea>();
		fp =fp2;//forms panel refrence
	}

	
	
	//create a text field inside form panel with a title acording to type
	//type is also part of the nput name
	public void add_txt_field(String type,String val) 
	{
		int height = 5;//textarea height
		if(type.equals("title")) {height=2;}//title is a smaller height
		
		//add label
		 JLabel  toptxt= new JLabel(type+": ", JLabel.RIGHT);
		 toptxt.setHorizontalAlignment(JLabel.CENTER);
		 
		//create textarea
		JTextArea tfield = new JTextArea(val,height,50);
		
		
		//add scrollpane and vertical scroll
		JScrollPane scrollPane = new JScrollPane(tfield);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//vertical scroll 
		
		//set textfield name
         tfield.setName(type + count_textareas);//pus count so its a unique name
        
         count_textareas++;//incrament inputs count
         
         //add to forms panel and refresh it
         fp.add(toptxt);
         fp.add(scrollPane);
         fp.revalidate();  // For JDK 1.7 or above.
         //frame.getContentPane().revalidate(); // For JDK 1.6 or below.
         fp.repaint();
         
         //add to arraylist
         txtfields.add(tfield);
         
         //test arraylist (for debug)
         for( JTextArea f : txtfields ) { 
        	   System.out.println( f.getName() ) ;//input name
        	   System.out.println( f.getText());//input value
        	}
         System.out.println( "------------------" ) ;
		
	}//end add_txt_field
	

	
	//convert all forms to html and display in popup form
	public String to_html() 
	{
		String html = "";
	    for( JTextArea f : txtfields )
	    { 
     	   System.out.println( f.getName() ) ;//input name
     	   System.out.println( f.getText());//input value
     	   
     	  if(f.getName().contains("text")) {html +="<p>\n"+f.getText()+"\n</p>\n\n";}
     	  if(f.getName().contains("code")) {html +="<pre><code class='php'>\n"+f.getText()+"\n</code></pre>\n\n";}
     	  if(f.getName().contains("title")) {html +="<h2>\n"+f.getText()+"\n</h2>\n\n";}
     	
	    }//end loop
	    
	    html_window( html);
	    return html;
	}//end to_html
	
	public void html_window(String html) 
	{
		JTextArea textArea = new JTextArea("",30,50);
		textArea.append(html);
		 JOptionPane.showMessageDialog(null, new JScrollPane( textArea), "result",
			        JOptionPane.WARNING_MESSAGE);
	}//end html_window
	
	public void save_arrlist_to_file(String file) 
	{
		ydb_s.write_to_file(file, txtfields);
	}
	
	public void load_arrlist_from_file(String file) 
	{
		//get elements arraylist from file
		ArrayList<JTextArea> txtfields_loaded = (ArrayList<JTextArea>)ydb_s.read_file(file);
		//clear the texfilds 
		txtfields.clear();
		//clear form panel
		fp.removeAll();
		//populate with uoloaded elements
		for( JTextArea f : txtfields_loaded )
		 { 
			String type = f.getName().replaceAll("[\\d.]", "");//regex replace all digits i want only the name
			String val = f.getText();
			//use add text field to add the exact same txt field from save
			add_txt_field( type, val);
			System.out.println(type);
		       
		 }

	}
}
