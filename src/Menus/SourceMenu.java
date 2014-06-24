package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Utility.HtmlRenderer;

import Components.CMenu;
import Components.CMenuItem;
import Gui.JEditor;
import MenuEvents.SourceMenuEvent;

public class SourceMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	public static CMenuItem command,terminal,renderHtml;

	public SourceMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addActions();
		addToMenu();
		addMenuListener(new SourceMenuEvent());
	}
	
	public void init(){
		command = new CMenuItem("Exec command", "execute a command", 'E', null);
		terminal = new CMenuItem("Open terminal", "open a new terminal", 'O', KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		renderHtml = new CMenuItem("Render Html", "render the text as html", 'R', null);
	}
	
	public void addActions(){
		
		command.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = JOptionPane.showInputDialog(JEditor.frame, "Enter command:", "Exec command", JOptionPane.PLAIN_MESSAGE);
				
				if(text == null){
					return;
				}
				
				String[] command = text.split(" ");
				
				try {
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(JEditor.frame, "An error occured while executing the command. Please check if the command is correct.", "Error executing", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		terminal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Runtime.getRuntime().exec("gnome-terminal");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		renderHtml.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HtmlRenderer r = new HtmlRenderer();
				r.load();
				r.show();
			}
		});
	}
	
	public void addToMenu(){
		add(command);
		add(terminal);
		add(renderHtml);
	}


}
