
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AppTest {

    @Test
    public void whenCheck() {
        App app = new App();
        assertThat(app.check(2), is(4));
    }
}