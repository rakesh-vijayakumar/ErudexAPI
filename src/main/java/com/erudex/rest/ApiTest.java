package com.erudex.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
/**
 * Created by rakes on 2/1/2017.
 */
public class ApiTest {

    public static int userId;

    public static String sessionId;

    public static void main(String[] args) throws ClientProtocolException, IOException {

        List nameValuePairs = new ArrayList();

        for (int i = 0; i < 4; i++) {

            HttpClient client = new DefaultHttpClient();

            HttpPost post = new HttpPost("http://dev.erudex.com/ErudexWebService/rest/user/validateUser");


            nameValuePairs.add(new BasicNameValuePair("username", "manasa.9a03"));
            nameValuePairs.add(new BasicNameValuePair("password", "SOME.Paper.Back"));//you can as many name value pair as you want in the list.

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = client.execute(post);

            String json = IOUtils.toString(response.getEntity().getContent());

            JSONObject jsonObj = new JSONObject(json);

            JSONObject jsonObj2 = jsonObj.getJSONObject("userInfo");

            userId = jsonObj2.getInt("id");

            sessionId = jsonObj.getString("sessionId");

            System.out.println(userId);
            System.out.println(sessionId);

            // =========================================================================

           /* HttpClient client2 = new DefaultHttpClient();

            HttpPost post2 = new HttpPost("http://dev.erudex.com/ErudexWebService/rest/user/getStudentCurriculum");

            JSONObject jsonObjPost = new JSONObject();

            jsonObjPost.put("userId", userId);
            jsonObjPost.put("sessionId", sessionId);

            StringEntity input = new StringEntity(jsonObjPost.toString());
            input.setContentType("application/json");
            post2.setEntity(input);

            HttpResponse response2 = client.execute(post2);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));

            String line = " ";

            while ((line = rd.readLine()) != null) {

                System.out.println(line);

            }*/

            //==============================================================================

            HttpClient client3 = new DefaultHttpClient();

            HttpPost post3 = new HttpPost("http://dev.erudex.com/ErudexWebService/rest/user/userLogout");

            JSONObject jsonObjLogoutPost = new JSONObject();

            jsonObjLogoutPost.put("userId", userId);
            jsonObjLogoutPost.put("sessionId", sessionId);

            StringEntity input2 = new StringEntity(jsonObjLogoutPost.toString());
            input2.setContentType("application/json");
            post3.setEntity(input2);

            HttpResponse response3 = client.execute(post3);

            System.out.println("user is been logout");

            BufferedReader rd2 = new BufferedReader(new InputStreamReader(response3.getEntity().getContent()));

            String line2 = " ";

            while ((line2 = rd2.readLine()) != null) {

                System.out.println(line2);

            }

            nameValuePairs.clear();
        }

    }

}
