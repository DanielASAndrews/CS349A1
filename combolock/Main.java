package combolock;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		ILock lock = Factory.getLock(new int[]{1, 2, 3});

		JPanel gui = Factory.getLockGUI(lock);
		JFrame f = new JFrame("Combo Lock");
		f.setContentPane(gui);
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
