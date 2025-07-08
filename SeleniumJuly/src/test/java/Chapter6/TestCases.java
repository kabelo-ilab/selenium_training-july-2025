package Chapter6;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {
    static List<String> nameList = new ArrayList<>();

    @BeforeAll
    static void addNames(){
       nameList.add("James");
       nameList.add("Martin");
       nameList.add("Tom");
       nameList.add("John");
       nameList.add("Mike");
        //Kabelo
        //Kabelo
        //Kabelo
    }

    @AfterAll
    static void displayAllNames(){
        for (String name : nameList){
            System.out.println(name);
        }
    }

    @BeforeEach
    void addNewName(){
        nameList.add("Kabelo");
    }

    @AfterEach
    void removeFirstName(){
        nameList.removeFirst();
    }

    @Test
    @Order(3)
    @Tag("Performance")
    @DisplayName("TC3 - Count all names")
    void countNames(){
        //Arrange
        int expected = 5;
        //Actual
        int actual = nameList.size();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @Order(1)
    @Tag("Sanity")
    @DisplayName("TC1 - First Test")
    void firstTest(){
        System.out.println("First Test");
    }

    @Test
    @Order(2)
    @Tag("Sanity")
    @Tag("System")
    @DisplayName("TC2 - Test Sum of Three Numbers")
    void testSumOfThree(){//5, 3, 1
        //Arrange - Initial and expected values / results
        int number1 = 5;
        int number2 = 3;
        int number3 = 1;
        int expected = 9;
        //Actual - actual processing done by the system
        int actual = number1 + number2 + number3;
        //Assert - compare expected results with actual results
        assertEquals(expected, actual);
    }

}
