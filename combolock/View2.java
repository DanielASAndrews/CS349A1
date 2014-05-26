package combolock;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.*;

/**
 * Created by cs349 on 21/05/14.
 */
public class View2 extends JPanel implements IView {

    // the model that this view is showing
    private ILock model;

    // the view's main user interface

    private Map<Integer, JSlider> sliders = new HashMap<Integer, JSlider>();

    public View2(ILock lockModel){
        super();

        // set the model
        this.model = lockModel;

        // create the view UI
        this.layoutView();

        // add the controllers to the view
        this.registerControllers();
    }

    private void layoutView() {
        JPanel west = new SlidersLayout();
        this.setLayout(new BorderLayout(5,5));
        this.add(west, BorderLayout.EAST);
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


                Dimension d = newSlider.getPreferredSize();
                d.height = 40;
                newSlider.setMaximumSize(d);

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
    }

    // IView interface
    @Override
    public void updateView() {

        for (int i=0; i < model.comboLength(); i++){
            this.sliders.get(i).setValue(((LockModel)View2.this.model).getEnteredComboDigit(i));
        }

    }
}