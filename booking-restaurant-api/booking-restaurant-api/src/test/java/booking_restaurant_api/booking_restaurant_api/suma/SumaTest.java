package booking_restaurant_api.booking_restaurant_api.suma;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import suma.Suma;

public class SumaTest {
    Suma sum = new Suma();

    @Test
    public void testSuma() {
        int sumTest=sum.suma(1,1);
        Assertions.assertEquals(2,sumTest);
    }
}
