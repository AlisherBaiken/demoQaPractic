package tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagsTest {
    @Test
    @Tag("needed")
    void simpleProperty5Test() {
        System.out.println("Hello,"+ System.getProperty("user_name","unknown student"));}
    @Test
        @Tag("prod")
        void simpleProperty6Test() {
            System.out.println("Hello,"+ System.getProperty("user_name","prod student"));
}}
