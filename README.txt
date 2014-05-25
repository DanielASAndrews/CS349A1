
UserID:	d2andrew
Name:	Daniel Andrews

Stuff the TA should know about your assignment.  In particular,
how you solved the problem of keeping the views in sync with
each other.

Input: Through the commandline 1-5 numbers

To keep the views in sync with each other, every time there was a change to the LockModel class (implements ILock)
the method updateAllViews() which kept the views appropriately updated.

I placed my lock/unlock button in it's own view class (View3) because I felt it was more visually appealing
than having two sets of the button for each of the other views (View1 & View2).  So View3 pretty much represents
a controller.
