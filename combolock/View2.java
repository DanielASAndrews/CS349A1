package combolock;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.event.*;

/**
 * Created by cs349 on 21/05/14.
 */
public class View2 extends JPanel implements IView {

    // the model that this view is showing
    private ILock model;

    // the view's main user interface
    //private ArrayList<JSpinner> sliders = new ArrayList<JSpinner>();

    private Map<Integer, JSlider> sliders = new HashMap<Integer, JSlider>();
    //private JButton enter = new JButton("Enter");
    //private JButton reLock = new JButton("Re-lock");

    public View2(ILock lockModel){
        super();

        // set the model
        this.model = lockModel;

        // create the view UI
        this.layoutView();

        this.registerControllers();
    }

    private void layoutView() {

        //JPanel east = new LabelsLayout();
        JPanel west = new SlidersLayout();
        // JPanel south = new ButtonsLayout();
        this.setLayout(new BorderLayout(5,5));

        //this.add(east, BorderLayout.WEST);
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
                this.add(Box.createRigidArea(new Dimension(0, 5)));
            }

        }
    }


    class SlidersLayout extends JPanel {

        public SlidersLayout() {
            super();

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Need to create sliders based on comboLength
            for (int i=0; i < model.comboLength(); i++){

                JSlider newSlider = new JSlider(1,99);

                newSlider.setMajorTickSpacing(10);
                newSlider.setPaintTicks(true);

                /*
                //Create the label table
                Hashtable labelTable = new Hashtable();

                for (int j=0; j < 100; j+=10){
                    labelTable.put(new Integer(0), new JLabel(String.valueOf(j)) );
                }

                newSlider.setLabelTable( labelTable );
                newSlider.setPaintLabels(true);

                */

                Dimension d = newSlider.getPreferredSize();
                d.height = 40;
                //  d.width = 40;
                newSlider.setMaximumSize(d);

                Dimension minSize = new Dimension(5, 15);
                Dimension prefSize = new Dimension(5, 15);
                Dimension maxSize = new Dimension(Short.MAX_VALUE, 15);

                sliders.put(i, newSlider);
                this.add(newSlider);
            }

        }
    }

    private void registerControllers() {

        ChangeListener sc = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider src = (JSlider) e.getSource();
                Integer val = ((Integer)src.getValue()).intValue();

                for (int i=0; i < model.comboLength(); i++) {

                    JSlider currentSlider = View2.this.sliders.get(i);

                    if (src == currentSlider) {
                        ((LockModel)View2.this.model).setEnteredComboDigit(i, val);
                    }
                }

            }
        };

        for (int i=0; i < model.comboLength(); i++){
            this.sliders.get(i).addChangeListener(sc);
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
            this.sliders.get(i).setValue(((LockModel)View2.this.model).getEnteredComboDigit(i));
        }

    }
}