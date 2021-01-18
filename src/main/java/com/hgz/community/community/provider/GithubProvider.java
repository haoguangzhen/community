package com.hgz.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.hgz.community.community.dto.AccessTokenDTO;
import com.hgz.community.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(
            AccessTokenDTO accessTokenDTO
    ) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDTO gitUser = JSON.parseObject(string, GithubUserDTO.class);
            return gitUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
