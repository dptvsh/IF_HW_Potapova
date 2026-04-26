package auth.api;

import spec.Specifications;
import config.ConfigReader;
import io.restassured.RestAssured;

public class BaseAuthApi {
    public BaseAuthApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.get("schAT.url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpec();
    }
}
