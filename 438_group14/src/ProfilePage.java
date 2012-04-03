import java.awt.MouseInfo;

import javax.swing.JFrame;


public class ProfilePage extends JFrame{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame f = new JFrame("Profile Page");
		ProfilePagePanel profilePanel = new ProfilePagePanel();
		f.add(profilePanel);
		f.setVisible(true);
		f.setLocation(MouseInfo.getPointerInfo().getLocation());
		System.out.println("panel up?");
		f.pack();

	}

	
	

}
