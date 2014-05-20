package combolock;

public interface IModel {

	/**
	 * Add a view to this model.
	 * @param view
	 */
	public void addView(IView view);
	
	/**
	 * Remove a view from this model.
	 * @param view
	 */
	public void removeView(IView view);
	
	/**
	 * Call the updateView method of all views registered with this model.
	 */
	public void updateAllViews();
}
