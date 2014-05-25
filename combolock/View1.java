package combolock;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.*;

/**
 * Created by cs349 on 21/05/14.
 */
public class View1 extends JPanel implements IView {

    // the model that this view is showing
    private ILock model;

    // the view's main user interface
    //private ArrayList<JSpinner> spinners = new ArrayList<JSpinner>();

    private Map<Integer, JSpinner> spinners = new HashMap<Integer, JSpinner>();
    //private JButton enter = new JButton("Enter");
    //private JButton reLock = new JButton("Re-lock");

    public View1(ILock lockModel){
        super();

        // set the model
        this.model = lockModel;

        // create the view UI
        this.layoutView();

        this.registerControllers();
    }

    private void layoutView() {

        JPanel east = new LabelsLayout();
        JPanel west = new SpinnersLayout();
       // JPanel south = new ButtonsLayout();
        this.setLayout(new BorderLayout(5,5));

        this.add(east, BorderLayout.WEST);
        this.add(west, BorderLayout.EAST);
        //this.add(south, BorderLayout.SOUTH);
    }

    /*
    class ButtonsLayout extends JPanel {
        public ButtonsLayout(){
            super();

            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.add(enter);
            this.add(Box.createRigidArea(new Dimension(5, 0)));
            this.add(reLock);
        }
    }

    */

    class LabelsLayout extends JPanel {

        public LabelsLayout() {
            super();

            JLabel first = new JLabel("First Digit ");
            JLabel second = new JLabel("Second Digit ");
            JLabel third = new JLabel("Third Digit ");
            JLabel fourth = new JLabel("Fourth Digit ");
            JLabel fifth = new JLabel("Fifth Digit ");



            ArrayList<JLabel> labels = new ArrayList<JLabel>();

            labels.add(first);
            labels.add(second);
            labels.add(third);
            labels.add(fourth);
            labels.add(fifth);

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            for (int i=0; i < model.comboLength(); i++){
                this.add(labels.get(i));
                this.add(Box.createRigidArea(new Dimension(0, 28)));
            }

        }
    }


    class SpinnersLayout extends JPanel {

        public SpinnersLayout() {
            super();

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Need to create spinners based on comboLength
            for (int i=0; i < model.comboLength(); i++){

                JSpinner newSpinner = (new JSpinner(new SpinnerNumberModel(1, 1,
                        99, 1)));

                // Make spinner non-editable
                //newSpinner.setEditor(new JSpinner.DefaultEditor(newSpinner));

                // Prevent Spinners from resizing
                Dimension d = newSpinner.getPreferredSize();
                d.height = 20;
              //  d.width = 40;

                spinners.put(i, newSpinner);
                this.add(newSpinner);

                newSpinner.setMaximumSize(d);
                Dimension minSize = new Dimension(5, 23);
                Dimension prefSize = new Dimension(5, 23);
                Dimension maxSize = new Dimension(Short.MAX_VALUE, 23);
                this.add(new Box.Filler(minSize, prefSize, maxSize));
            }

        }
    }

    private void registerControllers() {

		ChangeListener sc = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner src = (JSpinner) e.getSource();
				Integer val = ((Integer)src.getValue()).intValue();

                for (int i=0; i < model.comboLength(); i++) {

                    JSpinner currentSpinner = View1.this.spinners.get(i);

                    if (src == currentSpinner) {
                        ((LockModel)View1.this.model).setEnteredComboDigit(i, val);
                    }
                }
			}
		};

        for (int i=0; i < model.comboLength(); i++){
            this.spinners.get(i).addChangeListener(sc);
        }


     /*
        //For checking the entered combination
        this.enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.unlock(((LockModel) model).getEnteredCombo());
            }
        });

        */


    /*
        //For re-locking the lock
        this.reLock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.lock();
                ((LockModel)model).resetEnteredCombo();
            }
        });
        */
    }

    // IView interface
    @Override
    public void updateView() {

        for (int i=0; i < model.comboLength(); i++){
            this.spinners.get(i).setValue(((LockModel)View1.this.model).getEnteredComboDigit(i));
        }

    }
}
