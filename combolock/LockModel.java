package combolock;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cs349 on 21/05/14.
 */
public class LockModel implements ILock {
    // the data for this model

    private int[] lockCombination;
    private int[] enteredCombo;
    private Boolean lockState;
    // all views of this model
    private ArrayList<IView> views = new ArrayList<IView>();

    public LockModel(int[] combination){
        lockCombination = combination;
        lockState = false;
        enteredCombo = new int[comboLength()];
        Arrays.fill(enteredCombo, 1);
    }

    @Override
    public void updateAllViews(){

        System.out.println("Calling Update Views");

        for (IView view : views){
            view.updateView();
        }
    }

    @Override
    public boolean isLocked(){
        return lockState;
    }

    @Override
    public void lock(){
        lockState = true;
        resetEnteredCombo();
    }

    @Override
    public void unlock(int[] combo){

        System.out.println("Now running unlock...");

        Boolean isDigitCorrect = true;

        for (int i=0; i < lockCombination.length; i++){

            if (lockCombination[i] != combo[i]){
                isDigitCorrect = false;
                break;
            }
        }

        if (isDigitCorrect){
            lockState = false;
        }

        updateAllViews();

        System.out.println("Lock state is: " + lockState);
    }

    @Override
    public int comboLength(){
        return lockCombination.length;
    }
    @Override
    public void addView(IView view){
        this.views.add(view);
        view.updateView();
    }

    @Override
    public void removeView(IView view){
        this.views.remove(view);
        view.updateView();
    }

    public void setEnteredComboDigit(Integer position, Integer value)
    {
        enteredCombo[position] = value;
        updateAllViews();

        System.out.println("New combo position of enteredCombo is: " + enteredCombo[position]);
    }

    public int getEnteredComboDigit(Integer position)
    {
        return enteredCombo[position];
    }

    public int[] getEnteredCombo(){
        return enteredCombo;
    }

    public void resetEnteredCombo(){
        Arrays.fill(enteredCombo, 1);
        updateAllViews();
    }

}
