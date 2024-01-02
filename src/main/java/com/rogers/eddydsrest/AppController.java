package com.rogers.eddydsrest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AppController {

  @GetMapping("/v1/hello/{id}")
  String one(@PathVariable("id") String id) {

    if (id.contains ("dd")) {
      log.info("response is : no pizza for you " + id + "!");
      return "no pizza for you " + id + "!";
    }

    log.info("id: " + id);
    
    return "Hello mr: " + id;
  }

  static class Name {
    String myName;

    String getMyName() {
      return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
  }

  @GetMapping("/find-name")
  String findName() {

    String uri = "https://run.mocky.io/v3/7d685949-33b6-42a7-b608-1a38e30036b3";

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Name> response = restTemplate.getForEntity(uri, Name.class);
    Name result = response.getBody();

    String myName = result.getMyName();

    log.info("name: " + myName);

    return myName;
  }
}
