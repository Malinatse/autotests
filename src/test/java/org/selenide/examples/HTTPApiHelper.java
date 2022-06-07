package org.selenide.examples;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import javax.annotation.concurrent.NotThreadSafe;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@NotThreadSafe
class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDeleteWithBody() {
        super();
    }
}


public class HTTPApiHelper {

    private String BASE_URL = ConfProperties.getProperty("baseurl");


    public void doTest() throws Exception {
        deleteProspectByMail("leotards2015@gmail.com");
    }

    /**
     * Получение id стади из переданного url
     * @param url текущий url
     * @return id стади
     */
    public String getStudyIdFromUrl(@NotNull String url)
    {
        String id = url.split("=")[1];
        return id;
    }

    /**
     * Удаление стади по заданному id
     * @param id номер стади
     * @throws Exception
     */
    public void deleteStudyById(String id) throws Exception {
        String token = getApiToken(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));

        HttpDelete del = new HttpDelete(BASE_URL + "/studies/"+ id);
        del.setHeader(HttpHeaders.AUTHORIZATION, token);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(del)) {
            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
        }
    }


    /**
     * Получение токена
     * @param userMail имя пользователя
     * @param userPath пароль
     * @return String
     * @throws Exception http exception
     */
    public String getApiToken(String userMail, String userPath) throws Exception
    {
        String token = "";
        HttpPost post = new HttpPost(BASE_URL + "/auth/login");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", userMail));
        urlParameters.add(new BasicNameValuePair("password", userPath));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            token = obj.getString("token");
        }
        return token;
    }

//    public void deleteProspectByMail(String mail) throws Exception
//    {
//        String token = getApiToken(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
//
//        HttpDelete del = new HttpDelete(BASE_URL + "/prospects/"+ mail);
//        del.setHeader(HttpHeaders.AUTHORIZATION, token);
//
//        try (CloseableHttpClient httpClient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpClient.execute(del)) {
//            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
//        }
//    }

    public void deleteProspectByMail(String mail) throws Exception {
        String token = getApiToken(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));

        HttpDeleteWithBody del = new HttpDeleteWithBody(BASE_URL + "/prospects/");
        del.setHeader(HttpHeaders.AUTHORIZATION, token);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("emailList", mail));

        del.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(del)) {
            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
        }
    }

    public String getProspectIdByMail(String inpMail) throws Exception
    {
        String token = getApiToken(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        HttpGet get = new HttpGet(BASE_URL + "/prospects?perPage=5000");
        get.setHeader(HttpHeaders.AUTHORIZATION, token);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray prospects = obj.getJSONArray("prospects");
            for(int i = 0; i< prospects.length(); i++)
            {
                JSONObject prospect = prospects.getJSONObject(i);
                String email = prospect.getString("email");
                String id = prospect.getString("id");
                if(inpMail.equals(email))
                    return id;
            }
        }
        return "-1";
    }

    public String getStudyInviteById(String id) throws Exception
    {

        String token = getApiToken(ConfProperties.getProperty("login"), ConfProperties.getProperty("password"));
        HttpGet get = new HttpGet(BASE_URL + "/studies?perPage=5000");
        get.setHeader(HttpHeaders.AUTHORIZATION, token);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {

            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            JSONArray studies = obj.getJSONArray("studies");
            for(int i = 0; i< studies.length(); i++)
            {
                JSONObject study = studies.getJSONObject(i);
                int s_id = study.getInt("id");
                String invitation = study.getString("invitationLink");
                if(Integer.parseInt(id) == s_id)
                    return invitation;
            }
        }
        return "-1";
    }

}
