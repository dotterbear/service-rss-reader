package com.dotterbear.service.rss.reader.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dotterbear.service.rss.reader.entity.RSSFeed;
import com.dotterbear.service.rss.reader.manager.RSSManager;
import com.dotterbear.service.rss.reader.repository.RSSRepository;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebController {
  @Autowired RSSRepository rssRepository;

  @Autowired RSSManager rssManager;

  @GetMapping("findById")
  @ResponseBody
  public ResponseEntity<RSSFeed> findById(@RequestParam("id") String id) {
    return new ResponseEntity<RSSFeed>(
        rssRepository.findById(id).orElse(new RSSFeed()), HttpStatus.OK);
  }

  @GetMapping("fetchRSSFeed")
  @ResponseBody
  public ResponseEntity<RSSFeed> fetchRSSFeed() throws IOException {
    return new ResponseEntity<RSSFeed>(
        rssManager.fetchRSSFeed().orElse(new RSSFeed()), HttpStatus.OK);
  }

  @GetMapping("getLatest")
  @ResponseBody
  public ResponseEntity<RSSFeed> getLatest() {
    return new ResponseEntity<RSSFeed>(
        rssRepository.getLatest().orElse(new RSSFeed()), HttpStatus.OK);
  }
}
