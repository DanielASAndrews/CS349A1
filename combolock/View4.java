package combolock;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cs349 on 24/05/14.
 */
public class View4 extends JPanel implements IView{

    // the model that this view is showing
    private ILock model;

    private JLabel lockState = new JLabel("Locked");

    public View4(ILock lockModel){
        super();

        // set the model
        this.model = lockModel;

        //Needed to set the background colour
        lockState.setOpaque(true);

        lockState.setForeground (Color.white);
        lockState.setBackground(Color.red);

        this.add(lockState);
    }

    @Override
    public void updateView() {

        if (this.model.isLocked()){
            lockState.setText("Locked");
            lockState.setForeground (Color.white);
            lockState.setBackground(Color.red);
        }
        else {
            lockState.setText("Unlocked");
            lockState.setForeground (Color.white);
            lockState.setBackground(Color.green);
        }
    }

}