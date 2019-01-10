package pqab.service.rss.reader.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pqab.service.rss.reader.entity.RSSFeed;
import pqab.service.rss.reader.manager.RSSManager;
import pqab.service.rss.reader.repository.RSSRepository;

@RestController
public class WebController {
  @Autowired RSSRepository rssRepository;

  @Autowired RSSManager rssManager;

  @RequestMapping("/findById")
  @ResponseBody
  public RSSFeed findById(@RequestParam("id") String id) {
    return rssRepository.findById(id).orElse(new RSSFeed());
  }

  @RequestMapping("/fetchRSSFeed")
  @ResponseBody
  public RSSFeed fetchRSSFeed() throws MalformedURLException, IOException {
    return rssManager.fetchRSSFeed().orElse(new RSSFeed());
  }
}
