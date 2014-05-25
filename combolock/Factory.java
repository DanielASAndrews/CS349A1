package combolock;

import javax.swing.JPanel;
import java.awt.*;

public class Factory {

	/**
	 * Get an unlocked combination lock object (the model).
	 * @param combo  The integer sequence that will unlock the lock.
	 * @return
	 */
	public static ILock getLock(int[] combo) {

        ILock lock = new LockModel(combo);

        return lock;
	}
	
	/**
	 * Get one of the views of the lock model.
	 * @param model
	 * @return
	 */
	public static IView getView1(ILock model) {
        IView viewOne = new View1(model);

        return viewOne;
	}
	
	/**
	 * Get another view of the lock model.
	 * @param model
	 * @return
	 */
	public static IView getView2(ILock model) {
        IView viewTwo = new View2(model);

        return viewTwo;
	}


    /**
     * Get last of the views of the lock model.
     * @param model
     * @return
     */
    public static IView getView3(ILock model) {
        IView viewThree = new View3(model);

        return viewThree;
    }

    /**
     * Get last of the views of the lock model.
     * @param model
     * @return
     */
    public static IView getView4(ILock model) {
        IView viewFour = new View4(model);

        return viewFour;
    }
	
	
	/**
	 * Get the graphical user interface
	 */
	public static JPanel getLockGUI(ILock model) {

        BorderLayout layout = new BorderLayout();

        return new JPanel(layout);
	}
}
