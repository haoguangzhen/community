package com.hgz.community.community.controller;

import com.hgz.community.community.dto.AccessTokenDTO;
import com.hgz.community.community.mapper.UserMapper;
import com.hgz.community.community.model.User;
import com.hgz.community.community.provider.GithubProvider;
import com.hgz.community.community.dto.GithubUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;


    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.uri}")
    private String uri;


    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            HttpServletRequest request
    ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null){
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("githubUser",githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}
