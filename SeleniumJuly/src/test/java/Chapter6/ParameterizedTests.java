package Chapter6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create test cases with parameters from constant values,
 * methods and .csv file
 */
public class ParameterizedTests {

    @ParameterizedTest(name = "[{index}] - TC1 - High Price Increase: {arguments}")
    @ValueSource(doubles = {20.55, 78.90, 23.95, 50, 65.99})
    void testHighPriceIncrease(double price){
        //Arrange
        double percentage = 0.15;
        boolean expected = true;
        //Actual
        double priceIncrease = price * percentage;
        boolean actual = (priceIncrease > 5);

        assertEquals(expected, actual, "Price increase of " + priceIncrease
                + " on " + price + " is less than 5");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Tom", "Smith", "John", "Jessica", "Carol", "Mike"})
    void testNamesWith_O(String name){
        //Assert
        assertTrue(name.contains("o"), name + " doesn't contain the letter 'O'");
    }
    @ParameterizedTest
    @CsvSource(value = {"John,21,1.69,false", "Jessica,38,1.63,true", "Tom,45,1.71,true",
    "Mike,18,1.77,false", "Carol,28,1.62,false"})
    void testIsEmployed(String name, int age, double height, boolean employed){
        System.out.println("name = " + name + ", age = " + age + ", height = " + height + ", employed = " + employed);
        //Assert
        assertTrue(employed);
    }

    @ParameterizedTest(name = "[{index}] Test for loan approval")
    @CsvFileSource(files = "src/test/resources/person data.csv",numLinesToSkip = 1)
    void testLoanApproval(String name, int age, double height, boolean employed){
        //to be approved, you must be at least 25 years and be employed
        System.out.println("name = " + name + ", age = " + age + ", height = " + height + ", employed = " + employed);

        assertTrue(age >= 25 && employed, "Declined... You must be 25yrs (" + age + ")" +
                " or older, and you must be employed (" + employed + ")");

    }

    @ParameterizedTest
    @MethodSource("getNames")
    void testNamesStartsWith_M(String name){
        System.out.println("name = " + name);
        assertTrue(name.startsWith("M"), name + " doesn't start with 'M'");
    }

    static List<String> getNames(){
        return new ArrayList<>(
                List.of("MIke", "Michael", "Lewis", "John", "Carol", "Dave", "Simon")
        );
    }





}
