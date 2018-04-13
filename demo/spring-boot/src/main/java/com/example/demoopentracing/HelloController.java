package com.example.demoopentracing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.HeaderParam;



/**
 * @author Pavol Loffay
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    // @RequestMapping("/hello")
    // public String hello() {
    //     return "Hello from Spring Boot!";
    // }

    @RequestMapping("/chaining")
    // public String chaining() {
    //     ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/hello", String.class);
    //     return "Chaining + " + response.getBody();
    // }

    public String chaining(@HeaderParam("x-request-id") String xreq,
                           @HeaderParam("x-b3-traceid") String xtraceid,
                           @HeaderParam("x-b3-spanid") String xspanid,
                           @HeaderParam("x-b3-parentspanid") String xparentspanid,
                           @HeaderParam("x-b3-sampled") String xsampled,
                           @HeaderParam("x-b3-flags") String xflags,
                           @HeaderParam("x-ot-span-context") String xotspan) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("x-request-id", xreq);
//        headers.add("x-b3-traceid", xtraceid);
//        headers.add("x-b3-spanid", xspanid);
//        headers.add("x-b3-parentspanid", xparentspanid);
//        headers.add("x-b3-sampled", xsampled);
//        headers.add("x-b3-flags", xflags);
//        headers.add("x-ot-span-context", xotspan);
//
//        RestTemplate template = new RestTemplate();
//        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
//        ResponseEntity<String> response = template.exchange("http://service2:9088/", HttpMethod.GET, requestEntity, String.class);
//        return "Chaining + " + response.getBody();
        System.out.print("x-request-id:");
        System.out.println(xreq);
        return  getHello(xreq, xtraceid,xspanid,xparentspanid,xsampled,xflags,xotspan);
    }

    public String getHello(String xreq, String xtraceid, String xspanid,
                               String xparentspanid, String xsampled, String xflags, String xotspan){
        ClientBuilder cb = ClientBuilder.newBuilder();
        Client client = cb.build();
        WebTarget ratingsTarget = client.target("http://service2:9088/");
        Invocation.Builder builder = ratingsTarget.request(MediaType.APPLICATION_JSON);
        if(xreq!=null) {
            builder.header("x-request-id",xreq);
        }
        if(xtraceid!=null) {
            builder.header("x-b3-traceid",xtraceid);
        }
        if(xspanid!=null) {
            builder.header("x-b3-spanid",xspanid);
        }
        if(xparentspanid!=null) {
            builder.header("x-b3-parentspanid",xparentspanid);
        }
        if(xsampled!=null) {
            builder.header("x-b3-sampled",xsampled);
        }
        if(xflags!=null) {
            builder.header("x-b3-flags",xflags);
        }
        if(xotspan!=null) {
            builder.header("x-ot-span-context",xotspan);
        }
        Response r = builder.get();

        return r.readEntity(String.class);

    }

}
