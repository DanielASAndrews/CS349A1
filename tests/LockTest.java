package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import combolock.*;

public class LockTest {

	@Test
	public void testLockConstruction() {
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		assertNotNull(lock);
		assertFalse(lock.isLocked());
	}

	@Test
	public void testLockLocking() {
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		lock.lock();
		assertTrue(lock.isLocked());
	}

	@Test
	public void testUnlockGoodCombo() {
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		lock.lock();
		lock.unlock(new int[] { 1, 2, 3 });
		assertFalse(lock.isLocked());
	}


	@Test
	public void testUnlockWrongCombo() {
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		lock.lock();
		lock.unlock(new int[] { 3, 2, 1 });
		assertTrue(lock.isLocked());
	}

	@Test
	public void testUnlockedStaysUnlocked() {
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		lock.unlock(new int[] { 3, 2, 1 });
		assertFalse(lock.isLocked());
	}


	@Test
	public void testViewNotifications() {
		class TestView implements  IView {
			int counter = 0;
			public void updateView() {
				this.counter++;
			}			
		}
		TestView testView = new TestView();
		
		ILock lock = Factory.getLock(new int[] { 1, 2, 3 });
		lock.addView(testView);
		lock.addView(testView);
		lock.addView(testView);
		assertEquals(testView.counter, 3);
		lock.lock();
		assertEquals(testView.counter, 6);		
	}
}
