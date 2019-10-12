package cpsc.dlsproject.ast;

import cpsc.dlsproject.AppTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for Base AST class.
 */
public class BaseASTTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BaseASTTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testBaseASTs() {
        assertTrue(true);
    }

}
