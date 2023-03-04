package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void simplePropertyTest() {
        String browserName = System.getProperty("browser");
        System.out.println(browserName); //null
    }

    @Test
    void simpleProperty1Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName); //opera
    }

    @Test
    void simpleProperty2Test() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName); //firefox
    }

    @Test
    void simpleProperty3Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName); //opera
    }

    @Test
    @Tag("propety")
    void simpleProperty4Test() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName); //firefox
        // gradle clean property_test
        //firefox
        // gradle clean property_test -Dbrowser=safari
        //safari
        // gradle clean property_test -Dbrowser=safari
    }

    @Test
    @Tag("hello")
    void simpleProperty5Test() {
        System.out.println("Hello,"+ System.getProperty("user_name","unknown student"));
//        gradle clean hello_test
//          Hello,unknown student

//        gradle clean hello_test -Duser_name=Alex
//          Hello,Alex

//        gradle clean hello_test -Duser_name="Alex Egorov"
//          Hello,Alex Egorov
    }
}