package api.Auth;

import api.Specifications;
import config.ConfigReader;
import io.restassured.RestAssured;

public class BaseAuthApi {
    public BaseAuthApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.get("schAT.url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpec();
    }
}
