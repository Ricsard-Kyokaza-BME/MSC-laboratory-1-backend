package hu.bme.msc.agiletool.tests.logic;

import hu.bme.msc.agiletool.logic.SomeLogic;
import org.junit.Assert;
import org.junit.Test;

public class SomeLogicTest {

    @Test
    public void testMultiply() {
        SomeLogic someLogic = new SomeLogic(2, 5);

        Assert.assertEquals(someLogic.multiply(), 10);
    }

    @Test
    public void testAddition() {
        SomeLogic someLogic = new SomeLogic(2, 5);

        Assert.assertEquals(someLogic.addition(), 7);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivision() {
        SomeLogic someLogic = new SomeLogic(2, 0);

        someLogic.division();
    }
}
