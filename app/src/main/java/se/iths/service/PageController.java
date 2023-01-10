package se.iths.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import se.iths.persistency.model.Artist;


@Controller
public class PageController {

    @Value("${se.iths.backend.url}")
    private String backendUrl;

    Logger logger = LoggerFactory.getLogger(PageController.class);
    @GetMapping("/")
    public ModelAndView index() {
        logger.info("Backend URL: " + backendUrl);
        ResponseEntity<Artist[]> artists = (new RestTemplate()).getForEntity(backendUrl + "/api/artist", Artist[].class);

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Artists");
        mav.addObject("subtitle", "Albums");
        mav.addObject("artists", artists.getBody());
        mav.setStatus(HttpStatus.OK);
        mav.setViewName("index");
        return mav;
    }
}
