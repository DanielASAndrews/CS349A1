package combolock;

/**
 * Defines a "lock" as an object that can be locked, unlocked
 * (if you know the combination), and checked to verify whether
 * it is currently locked.
 * 
 * @author bwbecker
 *
 */
public interface ILock extends IModel {
	
	/**
	 * Determine whether the lock is locked or not.
	 * @return True if locked; false otherwise.
	 */
	public boolean isLocked();
	
	/**
	 * Lock this lock if it is currently unlocked.  No change if it is 
	 * already locked.
	 */
	public void lock();
	
	/**
	 * Unlock this lock if it is currently locked and the combination
	 * provided matches the lock's combination.  No change if the
	 * combination does not match or the lock is already unlocked.
	 * @param combo The combination to compare to this lock's combination
	 */
	public void unlock(int[] combo);
	
	/**
	 * @return the length of this lock's combination. 
	 */
	public int comboLength();
}
