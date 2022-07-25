package nts.sixblack.itemservice.controller;

import nts.sixblack.itemservice.model.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {


    @RequestMapping("/")
    public String index(){
        return "This is home controller item, port: 8200";
    }

    @RequestMapping("/items")
    public List<Item> itemList() {
        List<Item> list = new ArrayList<Item>();
        list.add(new Item(1, "Item 1"));
        list.add(new Item(2, "Item 2"));
        list.add(new Item(3, "Item 3"));
        list.add(new Item(4, "Item 4"));
        return list;
    }
}
