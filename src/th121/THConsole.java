package th121;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class THConsole extends JFrame implements ActionListener{
	/**
	 * SERIAL VERSION ID = -6113402551063073181L
	 */
	private static final long serialVersionUID = -6113402551063073181L;
	private JTextArea tArea;
	private JScrollPane tScroll;
	private JTextField cField;
	private static THConsole instance;
	
	private THConsole(THApp app){
		super("L.A.N.E.X - THConsole 12.1");
		this.setUndecorated(false);
		tArea = new JTextArea();
		tArea.setLineWrap(true);
		tArea.setEditable(false);
		tScroll = new JScrollPane(tArea);
		tScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		cField = new JTextField();
		cField.addActionListener(this);
		
		
		this.add(tScroll, BorderLayout.CENTER);
		this.add(cField, BorderLayout.SOUTH);
		this.setSize(400, 500);
		this.setVisible(true);
	}
	
	public static THConsole getInstance(THApp app){
		if (instance == null){
			instance = new THConsole(app);
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
