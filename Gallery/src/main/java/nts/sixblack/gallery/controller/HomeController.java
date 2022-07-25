package nts.sixblack.gallery.controller;

import nts.sixblack.gallery.model.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String index(){
        return "This is home controller gallery, port: 8100";
    }

    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable String id){
        Gallery gallery = new Gallery();
        gallery.setId(id);

        List<Object> list = restTemplate.getForObject("http://item-service/items", List.class);
        gallery.setItems(list);

        return gallery;
    }
}
