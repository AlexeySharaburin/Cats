package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static final String REQUEST_URL = "https://cat-fact.herokuapp.com/facts";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REQUEST_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);
        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object object = parser.parse(body);
            jsonObject = (JSONObject) object;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray notesArray = (JSONArray) jsonObject.get("all");

        List<Note> notes = mapper.readValue(notesArray.toString(), new TypeReference<List<Note>>() {
        });

        Stream<Note> stream = notes.stream();
        List<String> notNullUpvotes = stream
                .filter (value -> ((value.getUpvotes() != 0) && (value.getUpvotes() > 0)))
                .map(Note::toString)
                .collect(Collectors.toList());

        System.out.println("Факты (за которые проголосовали) о кошках и данные пользователей, которые эти факты запостили:");
        notNullUpvotes.forEach(System.out::println);

    }
}
