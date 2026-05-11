package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.StringUtils;

public class MaskingFilter implements Filter {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {

        String maskedRequest = formatRequestWithMaskedPassword(requestSpec);
        Allure.addAttachment("Запрос (пароль скрыт)", "text/plain", maskedRequest, ".txt");
        Response response = filterContext.next(requestSpec, responseSpec);
        Allure.addAttachment("Ответ", "text/plain", response.asString(), ".txt");
        return response;
    }

    private String formatRequestWithMaskedPassword(FilterableRequestSpecification spec) {
        StringBuilder sb = new StringBuilder();
        sb.append(spec.getMethod()).append(" ").append(spec.getURI()).append("\n\n");

        sb.append("Headers:\n");
        spec.getHeaders().forEach(h -> sb.append(h.getName()).append(": ").append(h.getValue()).append("\n"));
        sb.append("\n");

        String body = getBodyAsString(spec);
        String maskedBody = maskPasswordInJson(body);
        sb.append("Body:\n").append(maskedBody).append("\n\n");

        sb.append("Curl:\n");
        sb.append(generateCurl(spec, maskedBody));

        return sb.toString();
    }

    private String getBodyAsString(FilterableRequestSpecification spec) {
        Object body = spec.getBody();
        if (body == null) return "";
        if (body instanceof String) return (String) body;
        try {
            return mapper.writeValueAsString(body);
        } catch (Exception e) {
            return body.toString();
        }
    }

    private String maskPasswordInJson(String json) {
        if (json == null || json.isEmpty()) return json;
        return json.replaceAll("\"password\"\\s*:\\s*\"[^\"]*\"", "\"password\": \"******\"");
    }

    private String generateCurl(FilterableRequestSpecification spec, String maskedBody) {
        StringBuilder curl = new StringBuilder();
        curl.append("curl -v -X ").append(spec.getMethod()).append(" '").append(spec.getURI()).append("'");

        spec.getHeaders().forEach(h -> curl.append(" -H '").append(h.getName()).append(": ").append(h.getValue()).append("'"));

        if (StringUtils.isNotBlank(maskedBody)) {
            String escapedBody = maskedBody.replace("'", "'\\''");
            curl.append(" -d '").append(escapedBody).append("'");
        }
        return curl.toString();
    }
}