package combolock;

import javax.swing.JPanel;

public class Factory {

	/**
	 * Get an unlocked combination lock object (the model).
	 * @param combo  The integer sequence that will unlock the lock.
	 * @return
	 */
	public static ILock getLock(int[] combo) {
		return null;
	}
	
	/**
	 * Get one of the views of the lock model.
	 * @param model
	 * @return
	 */
	public static IView getView1(ILock model) {
		return null;
	}
	
	/**
	 * Get another view of the lock model.
	 * @param model
	 * @return
	 */
	public static IView getView2(ILock model) {
		return null;
	}
	
	
	/**
	 * Get the graphical user interface
	 */
	public static JPanel getLockGUI(ILock model) {
		return new JPanel();
	}
}
