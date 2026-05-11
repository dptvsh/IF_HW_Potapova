package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import utils.MaskingFilter;

public class BaseCucumberHooks {

    @Before
    public void setUp() {
        RestAssured.filters(new MaskingFilter());
    }

    @After
    public void tearDown() {
        RestAssured.reset();
    }
}
