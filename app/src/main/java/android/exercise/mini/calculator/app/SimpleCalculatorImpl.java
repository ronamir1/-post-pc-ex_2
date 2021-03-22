package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed
  String history = "";
  HashSet<Character> set = new HashSet<>(Arrays.asList('+', '-'));

  @Override
  public String output() {
    // todo: return output based on the current state
    if (history.length() == 0){
      return "0";
    }
    return history;
  }

  @Override
  public void insertDigit(int digit) {
    if (digit > 9 || digit < 0){
      throw new IllegalArgumentException();
    }
    // todo: insert a digit
    history = history.concat(String.valueOf(digit));
  }

  @Override
  public void insertPlus() {
    if (history.length() == 0){
      history = "0";
    }
    if (!set.contains(history.charAt(history.length() - 1))){
      history = history.concat("+");
    }
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    if (history.length() == 0){
      history = "0";
    }
    if (!set.contains(history.charAt(history.length() - 1))){
      history = history.concat("-");
    }
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
    if (history.length() == 0){
      return;
    }
    int cur = 0;
    char lastOp = 'z';
    history = history.concat("+");
    StringBuilder cur_string = new StringBuilder();
    for (int i = 0; i < history.length(); i++){
      while (!set.contains(history.charAt(i))){
        cur_string.append(history.charAt(i));
        i++;
      }
      if (lastOp == 'z'){
        lastOp = history.charAt(i);
        cur = Integer.parseInt(cur_string.toString());
        cur_string.setLength(0);
      }
      else if (lastOp == ('+')){
        cur += Integer.parseInt(cur_string.toString());
        cur_string.setLength(0);
        lastOp = history.charAt(i);
      }
      else {
        cur -= Integer.parseInt(cur_string.toString());
        cur_string.setLength(0);
        lastOp = history.charAt(i);
      }
    }
    history = String.valueOf(cur);
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (history.length() > 0){
      history = history.substring(0, history.length() - 1);
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    history = "";
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.status = history;
    // todo: insert all data to the state, so in the future we can load from this state
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    history = casted.status;
    // todo: use the CalculatorState to load
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    String status = "";
  }
}
