import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class fileCabinetGUI extends JFrame implements ActionListener{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 250;
	
	private static JButton down;
	private static JButton send;
	private static JButton up;
	private static JButton del;
	
	private static JMenuBar menuBar;
	private static JMenu menu;
	private static JMenuItem exitM;
	private static JMenuItem downM;
	private static JMenuItem upM;
	private static JMenuItem sendM;
	private static JMenuItem delM;
	
	private static JTable table;
	
	private static String[] columns = {"Name", "Size", "Uploaded", "Public"};
	
	private static Object [] [] allFiles = {{"File1", 0.1, "01/01/01", true}, {"File2", 0.2, "02/02/02", false}};
	private static Object[] files;
	
	public fileCabinetGUI(){
		setTitle("File Cabinet");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Top bar
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		downM = new JMenuItem("Download");
		upM = new JMenuItem("Upload");
		sendM = new JMenuItem("Send");
		delM = new JMenuItem("Delete");
		exitM = new JMenuItem("Close");
		
		menu.add(downM);
		menu.add(upM);
		menu.addSeparator();
		menu.add(sendM);
		menu.add(delM);
		menu.addSeparator();
		menu.add(exitM);
		
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
		
		//North
		JLabel title = new JLabel();
		title.setText("<html><font size=+1>File Cabinet</font>");
		add(title, BorderLayout.NORTH);
		
		//Center
		JPanel fileP = new JPanel();
		fileP.setLayout(new BorderLayout());
		table = new JTable(allFiles, columns);
		JScrollPane filePane = new JScrollPane(table);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		fileP.setBorder(loweredetched);
		fileP.add(filePane);
		
		JLabel cap = new JLabel(getCap() + "/1.0 GB  ", JLabel.RIGHT);
		fileP.add(cap, BorderLayout.SOUTH);
		
		add(fileP, BorderLayout.CENTER);
		
		//South
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(1, 4));
		
		down = new JButton("Download");
		down.setMargin(new Insets(2,2,2,2));
		buttonP.add(down);
		
		send = new JButton("Send");
		send.setMargin(new Insets(2,2,2,2));
		buttonP.add(send);
		
		up = new JButton("Upload");
		up.setMargin(new Insets(2,2,2,2));
		buttonP.add(up);
		
		del = new JButton("Delete");
		del.setMargin(new Insets(2,2,2,2));
		buttonP.add(del);
		
		add(buttonP, BorderLayout.SOUTH);
		
		
		
		
	}

	public static void main(String[] args) {
		fileCabinetGUI GUI = new fileCabinetGUI();
		GUI.setVisible(true);

	}
	
	public Object [][] popList(){
		return null;
	}
	
	public String getCap(){
		return "0.3";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
