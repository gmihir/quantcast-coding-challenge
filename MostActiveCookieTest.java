import org.junit.*;
import java.util.*;
import jdk.jfr.Timestamp;

import static org.junit.Assert.*;
import java.time.LocalDate;

public class MostActiveCookieTest {

    MostActiveCookie mostActiveCookie;
    
    @Before
    public void setUp() {
        mostActiveCookie = new MostActiveCookie();
    }

    @Test
    public void testIsSameDate() {
        LocalDate d1 = LocalDate.of(2021,1,1);
        LocalDate d2 = LocalDate.of(2021,1,2);

        assertFalse(mostActiveCookie.isSameDate(d1,d2));
    }

    @Test 
    public void testSameDate() {
        LocalDate d1 = LocalDate.of(2021,1,1);
        LocalDate d2 = LocalDate.of(2021,1,1);

        assertTrue(mostActiveCookie.isSameDate(d1, d2));
    }

    @Test
    public void testParseTokenPair() {
        String[] values = {"A","2020-01-01"};
        TokenPair mock = new TokenPair("A", LocalDate.of(2020,1,1));

        assertEquals(mostActiveCookie.parseTokenPair(values), mock);
        assertNotEquals(mostActiveCookie.parseTokenPair(values),null);
    }

    @Test
    public void testParseTokenPairFalse() {
        String[] values = {"A","2020-01-02"};
        TokenPair mock = new TokenPair("A", LocalDate.of(2020,1,1));

        assertNotEquals(mostActiveCookie.parseTokenPair(values), mock);
    }

    @Test 
    public void testPrint() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("1",4);
        map.put("2",3);
        map.put("3",2);
        map.put("4",4);

        HashSet<String> ret = mostActiveCookie.printMostCommonCookie(map);

        HashSet<String> mock = new HashSet<String>();
        mock.add("4");
        mock.add("1");

        assertEquals(ret,mock);
    }

    @Test 
    public void testPrintFalse() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("1",4);
        map.put("2",3);
        map.put("3",2);
        map.put("4",4);

        HashSet<String> ret = mostActiveCookie.printMostCommonCookie(map);

        HashSet<String> mock = new HashSet<String>();
        mock.add("4");

        assertNotEquals(ret,mock);

        mock.add("1");
        assertEquals(ret,mock);
    }

}
