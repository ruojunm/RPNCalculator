import com.ruojunm.rpn.RPNCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RPNCalculatorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPNCalculatorTest.class);
    RPNCalculator c;

    @BeforeMethod
    public void runBeforeEachTest() {
        c = new RPNCalculator();
    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample1() {
        Assert.assertEquals(c.processRPNInput("5 2"), "stack: 5 2");
    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample2() {
        Assert.assertEquals(c.processRPNInput("2 sqrt"), "stack: 1.4142135623");
        Assert.assertEquals(c.processRPNInput("clear 9 sqrt"), "stack: 3");

    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample3() {
        Assert.assertEquals(c.processRPNInput("5 2 -"), "stack: 3");
        Assert.assertEquals(c.processRPNInput("3 -"), "stack: 0");
        Assert.assertEquals(c.processRPNInput("clear"), "stack: ");


    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample4() {
        Assert.assertEquals(c.processRPNInput("5 4 3 2"), "stack: 5 4 3 2");
        Assert.assertEquals(c.processRPNInput("undo undo *"), "stack: 20");
        Assert.assertEquals(c.processRPNInput("5 *"), "stack: 100");
        Assert.assertEquals(c.processRPNInput("undo"), "stack: 20 5");

    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample5() {
        Assert.assertEquals(c.processRPNInput("7 12 2 /"), "stack: 7 6");
        Assert.assertEquals(c.processRPNInput("*"), "stack: 42");
        Assert.assertEquals(c.processRPNInput("4 /"), "stack: 10.5");


    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample6() {
        Assert.assertEquals(c.processRPNInput("1 2 3 4 5"), "stack: 1 2 3 4 5");
        Assert.assertEquals(c.processRPNInput("*"), "stack: 1 2 3 20");
        Assert.assertEquals(c.processRPNInput("clear 3 4 -"), "stack: -1");


    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample7() {
        Assert.assertEquals(c.processRPNInput("1 2 3 4 5"), "stack: 1 2 3 4 5");
        Assert.assertEquals(c.processRPNInput("* * * *"), "stack: 120");

    }

    @Test(groups = {"funcf-test", "basic"})
    public void testExample8() {
        Assert.assertEquals(c.processRPNInput("1 2 3 * 5 + * * 6 5"),
                "operator * (position: 15): insufficient parameters" + System.lineSeparator() + "stack: 11");

    }

    @Test(groups = {"funcf-test", "Addition"})
    public void testExceptionCases() {
        Assert.assertEquals(c.processRPNInput("1 0 /"),
                "operator / (position: 5): The input causes a number format error or divided by 0" + System.lineSeparator() + "stack: ");

    }

    @Test(groups = {"funcf-test", "Addition"})
    public void testExceptionCases2() {
        Assert.assertEquals(c.processRPNInput("12 8 3 * 6 / - 2 + -20.5  undo1"),
                "operator undo1 (position: 27): The input causes a number format error or divided by 0" + System.lineSeparator() + "stack: 10 -20.5");
    }

    @Test(groups = {"funcf-test", "Addition"})
    public void testDivide() {
        Assert.assertEquals(c.processRPNInput("1 2 360 / "),
                "stack: 1 0.0055555555");
    }

    @Test(groups = {"func-test", "Addition"})
    public void testAddition() {
        String[] cases = new String[]{
                "-4 5 +", "1",
                "5 2 /", "2.5",
                "5 2.5 /", "2",
                "5 1 2 + 4 * 3 - +", "14",
                "4 2 5 * + 1 3 2 * + /", "2",
                "12 8 3 * 6 / - 2 + -20.5", "10 -20.5",
                "12 8 3 * 6 / - 2 + -20.5 undo", "10",
                "12 8 3 * 6 / - 2 + -20.5 undo undo", "8 2",
        };
        for (int i = 0; i < cases.length; i += 2) {
            runBeforeEachTest();
            Assert.assertEquals(c.processRPNInput(cases[i]), "stack: " + cases[i + 1]);
        }

    }

    @Test(groups = {"performance-test"})
    public void testPerformance() {
        //todo
    }


}
