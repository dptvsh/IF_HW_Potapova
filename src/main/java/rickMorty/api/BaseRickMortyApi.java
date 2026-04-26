package rickMorty.api;

import spec.Specifications;
import config.ConfigReader;
import io.restassured.RestAssured;

public class BaseRickMortyApi {
    public BaseRickMortyApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.get("rickmorty.url"));
        RestAssured.responseSpecification = Specifications.baseResponseSpec();
    }
}
