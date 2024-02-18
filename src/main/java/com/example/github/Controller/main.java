package com.example.github.Controller;

import com.example.github.Services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



@Controller
public class main {
    @Autowired
    private GitHubService gitHubService;
    
    //public String owner;
    
    @GetMapping("/")
    public String index() {
    	
    	return "index";
    }
    
    
    //@PostMapping("/urlregister")
    public String urlRegistration() {
    	
    	return "branches";
    }
    
    
    
    
    
    //@GetMapping("/branches")
    public List<String> getbranches(String owner,String repo){
    	
        List<String> branches=gitHubService.getBranches(owner,repo);
//        model.addAttribute("branches",branches);
        //System.out.printf(branches.toString());
        return  branches;
    }
    //@GetMapping("/repo")
    @PostMapping("/urlregister")
    public String getRepo(@RequestBody String url,Model model){
		
		  
		 int i;
		 String concatenatedStr = null;
		 
    	System.out.println(url);
    	String owner= extractOwner(url);
    	System.out.println("owner="+owner);

        List<String>repo=gitHubService.getRepo(owner);
       // model.addAttribute("repo",repo);
       // getbranches(owner);
        System.out.println(repo);
        List<String> branches=new ArrayList();
		 for (i=0;i<repo.size();i++) { 
			// int j=i;
			 String str= repo.get(i);
			 
			 branches.addAll(getbranches(owner,str));
			 
			 //System.out.println(str);
		  }
		 
		 String bran= String.join("\n",branches);
		 model.addAttribute("brnchs",bran);
		 
		// for(i=1;i<=repo.size();i++) {
			 //System.out.println(i);
			 
		 //}
		 concatenatedStr = String.join("\n", repo);
		 
         
        model.addAttribute("urls","https://github.com/" + owner);
        model.addAttribute("repos",concatenatedStr);
       // model.addAttribute(url);
        
        // Branches
      //  List<String>branches=gitHubService.getBranches(owner,repos);
       // String concatenatedBr = String.join("\n", branches);
       // model.addAttribute("branches",concatenatedBr);
        return  "index";
    }
    
	/*
	 * // @GetMapping("/comm") public String getComm(Model model){
	 * List<String>comm=gitHubService.getCommit("vijaykumarp2","helloworld",
	 * "hellopart2"); model.addAttribute("comm",comm);
	 * System.out.printf(comm.toString()); return comm.toString(); }
	 */
    
    
    private String extractOwner(String repoUrl) {
		String[] parts=repoUrl.replace("url=https%3A%2F%2Fgithub.com%2F", "").split("%2F");
		//System.out.println(parts[0] + "/" + parts[1]);
		return parts[0];
		}
    
    

}
