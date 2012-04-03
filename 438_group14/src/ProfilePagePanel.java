import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class ProfilePagePanel extends JPanel implements ActionListener{
	
	private static JPanel contentPane;
	private JLabel titleLabel;

	private JButton editButton;
	private JButton closeButton;

	private final int EXAMPLE = 150;	
	
	private JPanel titleComponent;
	private JPanel dataComponent;
	private JPanel buttonComponent;
	
	private Member testMember = new Member();
	
	
	/**
	 * @param args
	 */
	public ProfilePagePanel() {

		showGui();

	}
	
	public void repaint(){
		
	}

	public void showGui() {
		contentPane = new JPanel(new BorderLayout());
		//Title
		titleLabel = new JLabel("Profile Page.");
		titleComponent = new JPanel();
		titleComponent.add(titleLabel);
		titleComponent.setVisible(true);
		
		contentPane.add(titleComponent, BorderLayout.NORTH);
		
	
		//Buttons

		editButton = new JButton("Edit");
		editButton.addActionListener( this);
		closeButton = new JButton("Close");
		closeButton.addActionListener( this);

		buttonComponent = new JPanel(new GridLayout(1, 2));
		buttonComponent.add(editButton);
		buttonComponent.add(closeButton);

		buttonComponent.setVisible(true);
		contentPane.add(buttonComponent, BorderLayout.SOUTH);
		
		//Data
		this.dataComponent = new JPanel(new GridLayout(8,2));
		
		this.dataComponent.add(new JLabel("Name: "));
		this.dataComponent.add(new JLabel(testMember.getName()));
		
		this.dataComponent.add(new JLabel("ID: "));
		this.dataComponent.add(new JLabel(Integer.toString(testMember.getId())));
		
		this.dataComponent.add(new JLabel("Area Code: "));
		this.dataComponent.add(new JLabel(Integer.toString(testMember.getAreaCode())));
		
		
		this.dataComponent.add(new JLabel("Phone Num: "));
		this.dataComponent.add(new JLabel(Integer.toString(testMember.getPhoneNumber())));
		
		this.dataComponent.add(new JLabel("City: "));
		this.dataComponent.add(new JLabel(testMember.getCity()));
		
		this.dataComponent.add(new JLabel("State: "));
		this.dataComponent.add(new JLabel(testMember.getState()));
		
		this.dataComponent.add(new JLabel("Zipcode: "));
		this.dataComponent.add(new JLabel(Integer.toString(testMember.getZipcode())));
		
		this.dataComponent.add(new JLabel("Motto: "));
		this.dataComponent.add(new JLabel(testMember.getMotto()));
		
		contentPane.add(dataComponent);
		
		
		add(contentPane);
		contentPane.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == editButton) {
			handleEditButton();
		}
		if (e.getSource() == closeButton) {
			handleCloseButton();
		}
	}

	private void handleEditButton(){
		System.out.println("Got edit button!");
	}
	private void handleCloseButton(){
		//System.out.println("handle Clear button");
		System.out.println("Got close button!");
	
	}
	

}
