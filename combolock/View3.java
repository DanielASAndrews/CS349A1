package combolock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cs349 on 24/05/14.
 */
public class View3 extends JPanel implements IView{

    // the model that this view is showing
    private ILock model;

    private JButton configureLock = new JButton("Unlock");

    public View3(ILock lockModel){
        super();

        // set the model
        this.model = lockModel;

        //For re-locking the lock
        this.configureLock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (model.isLocked()){
                    model.unlock(((LockModel) model).getEnteredCombo());
                }
                else {
                    model.lock();
                    ((LockModel)model).resetEnteredCombo();
                }

            }
        });

        this.add(configureLock);

    }

    @Override
    public void updateView() {

        if(this.model.isLocked()){
            this.configureLock.setText("Unlock");
        }
        else {
            this.configureLock.setText("Lock");
        }

    }

}
