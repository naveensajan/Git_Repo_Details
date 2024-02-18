package com.example.github.Services;

import com.example.github.GitHubBranch;
import com.example.github.GitHubCommit;
import com.example.github.GitHubRepo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {
    @Value("${github.api.urlb}")
    private String apiUrlb;
    
    @Value("${github.api.urlr}")
    private String apiUrlr;

    @Value("${github.api.urlc}")
    private String apiUrlc;
    
   // @Value("${github.api.token}")
    //private  String apiToken;

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public List<String>getBranches(String owner,String repo){
        String url=apiUrlb.replace("{owner}",owner).replace("{repo}",repo);
       // HttpHeaders headers=new HttpHeaders();
        //headers.set("authorization","Bearer "+apiToken);
      //  HttpEntity<String>entity=new HttpEntity<>(headers);

        ResponseEntity<GitHubBranch[]>response=restTemplate.exchange(url, HttpMethod.GET,null,GitHubBranch[].class);
        List<String>branches= Arrays.stream(response.getBody()).map(GitHubBranch::getName).collect(Collectors.toList());
        
        return  branches;


    }

    public List<String>getRepo(String owner){
        String url=apiUrlr.replace("{owner}",owner);
       // System.out.println("hi");
       // HttpHeaders headers=new HttpHeaders();
        //headers.set("authorization","Bearer "+apiToken);
      //  HttpEntity<String>entity=new HttpEntity<>(headers);
       // System.out.println(url);

        ResponseEntity<GitHubRepo[]>response=restTemplate.exchange(url, HttpMethod.GET,null,GitHubRepo[].class);
//        System.out.println(response);

        List<String>repos= Arrays.stream(response.getBody()).map(GitHubRepo::getName).collect(Collectors.toList());
        return  repos;


    }
    
    public List<String>getCommit(String owner,String repo ,String branch){
        String url=apiUrlc.replace("{owner}",owner).replace("{repo}",repo).replace("{branch}",branch);
        
       // HttpHeaders headers=new HttpHeaders();
        //headers.set("authorization","Bearer "+apiToken);
      //  HttpEntity<String>entity=new HttpEntity<>(headers);

        ResponseEntity<GitHubCommit[]>response=restTemplate.exchange(url, HttpMethod.GET,null,GitHubCommit[].class);
        List<String>commits= Arrays.stream(response.getBody()).map(GitHubCommit::getEmail).collect(Collectors.toList());
        return  commits;


    }
    
    
}
