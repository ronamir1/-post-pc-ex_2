package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-"; // TODO: decide the expected output when having a single minus
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(2);
    calc.insertDigit(3);
    calc.deleteLast();
    assertEquals("2", calc.output());

  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(2);
    calc.insertDigit(3);
    calc.clear();
    assertEquals("0", calc.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertDigit(5);
    firstCalculator.insertDigit(6);
    firstCalculator.insertPlus();
    secondCalculator.insertDigit(7);
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);
    secondCalculator.loadState(savedState);
    assertEquals("56+", secondCalculator.output());


    // TODO: implement the test based on this method's name.
    //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
  }

  @Test
  public void complex_insertion_delete_last(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(5);
    calc.insertPlus();
    calc.insertDigit(1);
    calc.insertDigit(7);
    calc.insertMinus();
    calc.insertDigit(1);
    calc.insertDigit(3);
    calc.deleteLast();
    calc.insertDigit(2);
    calc.insertDigit(5);
    assertEquals("5+17-125", calc.output());
  }

  @Test
  public void complex_insertion_clear(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(9);
    calc.clear();
    calc.insertDigit(1);
    calc.insertDigit(2);
    calc.clear();
    calc.insertDigit(8);
    calc.insertMinus();
    calc.insertDigit(7);
    calc.insertEquals();
    assertEquals("1", calc.output());
  }

  @Test
  public void multiple_equals(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(8);
    calc.insertMinus();
    calc.insertDigit(7);
    calc.insertEquals();
    calc.insertPlus();
    calc.insertDigit(4);
    calc.insertEquals();
    calc.insertMinus();
    calc.insertDigit(1);
    calc.insertEquals();
    assertEquals("4", calc.output());
  }

  @Test
  public void check_negative_result(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(9);
    calc.insertDigit(9);
    calc.insertDigit(9);
    calc.insertMinus();
    calc.insertDigit(8);
    calc.insertDigit(8);
    calc.insertDigit(8);
    calc.insertMinus();
    calc.insertDigit(2);
    calc.insertDigit(2);
    calc.insertDigit(2);
    calc.insertEquals();
    calc.insertMinus();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.insertDigit(3);
    assertEquals("-111-333", calc.output());
  }

  @Test
  public void big_numbers(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.insertPlus();
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertEquals();
    assertEquals("9999", calc.output());
  }

  @Test
  public void two_zeros(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(0);
    assertEquals("0", calc.output());
  }

  @Test
  public void long_complex_calc(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.clear();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.deleteLast();
    calc.insertPlus();
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertEquals();
    assertEquals("6669", calc.output());
  }

  @Test
  public void negative_output(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(5);
    calc.insertMinus();
    calc.insertDigit(1);
    calc.insertDigit(0);
    calc.insertEquals();
    assertEquals("-5", calc.output());
  }

  @Test
  public void combine_all_buttons(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.clear();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.deleteLast();
    calc.insertPlus();
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertMinus();
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertEquals();
    assertEquals("3", calc.output());
  }

  @Test public void combine_all_buttons_and_save(){
    SimpleCalculatorImpl calc = new SimpleCalculatorImpl();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.clear();
    calc.insertDigit(3);
    calc.insertDigit(3);
    calc.deleteLast();
    calc.insertPlus();
    // save current state
    Serializable savedState = calc.saveState();
    assertNotNull(savedState);
    calc.clear();
    assertEquals("0", calc.output());
    calc.loadState(savedState);
    assertEquals("3+", calc.output());
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertMinus();
    calc.insertDigit(6);
    calc.insertDigit(6);
    calc.insertEquals();
    assertEquals("3", calc.output());
  }



  // TODO:
  //  the existing tests are not enough since they only test simple use-cases with small inputs.
  //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
  //  examples:
  //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
  //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
  //  - given input "8-7=+4=-1=", expected output is "4"
  //  - given input "999-888-222=-333", expected output is "-111-333"
  //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
  //  etc etc.
  //  feel free to be creative in your tests!
}