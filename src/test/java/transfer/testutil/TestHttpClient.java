package transfer.testutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import transfer.config.properties.ApplicationPropertiesModule;

public class TestHttpClient {

    private static final MediaType JSON = MediaType.get("application/json");
    private static final String ROOT_URL = "http://localhost:9090";

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public TestHttpClient() {
        this.client = new OkHttpClient();
        this.mapper = DaggerObjectMapperFactory.builder()
                .applicationPropertiesModule(new ApplicationPropertiesModule("application.properties"))
                .build()
                .mapper();
    }

    @SneakyThrows
    public <REQ, RES> TestHttpResponse<RES> post(String relativeUrl, REQ request, Class<RES> responseType,
            Object... pathParams) {

        var body = RequestBody.create(mapper.writeValueAsString(request), JSON);

        var httpRequest = new Request.Builder()
                .url(ROOT_URL + String.format(relativeUrl, pathParams))
                .post(body)
                .build();

        try (var response = client.newCall(httpRequest).execute()) {
            return new TestHttpResponse<>(response, mapper.readValue(response.body().string(), responseType));
        }
    }
}
