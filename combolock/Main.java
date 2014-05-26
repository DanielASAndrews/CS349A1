package combolock;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;

public class Main {

	public static void main(String[] args) {

        int[] combination = new int[args.length];

        for (int i=0; i<args.length; i++){
            combination[i] = Integer.parseInt(args[i]);
        }

        // create Model and initialize it
		ILock lock = Factory.getLock(combination);
        lock.lock();

        // create Views, tell it about model (and controller)
        IView viewOne = Factory.getView1(lock);
        IView viewTwo = Factory.getView2(lock);
        IView viewThree = Factory.getView3(lock);
        IView viewFour = Factory.getView4(lock);

        // tell Model about Views.
        lock.addView(viewOne);
        lock.addView(viewTwo);
        lock.addView(viewThree);
        lock.addView(viewFour);

        // create the window
		JPanel gui = Factory.getLockGUI(lock);

        JFrame f = new JFrame("Combo Lock");
		f.setContentPane(gui);

        // Use border layout for the Views
        f.getContentPane().add(new JPanel(), BorderLayout.EAST);
        f.getContentPane().add((View1) viewOne, BorderLayout.WEST);
        f.getContentPane().add((View2)viewTwo, BorderLayout.CENTER);
        f.getContentPane().add((View3)viewThree, BorderLayout.SOUTH);
        f.getContentPane().add((View4) viewFour, BorderLayout.NORTH);

        //Set min-max window dimensions
        f.setMinimumSize(new Dimension(355, 290));
        f.setMaximumSize(new Dimension(400, 300));

        f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
