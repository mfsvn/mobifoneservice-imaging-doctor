package com.tunv.ham10000.api;

import com.google.gson.JsonObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class PingApi {

    @RequestMapping(value="ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String chao() {
        JsonObject jo = new JsonObject();
        jo.addProperty("word", "school");
        jo.addProperty("meaning", "trường học");
        jo.addProperty("audio", "https://www.oxfordlearnersdictionaries.com/media/english/us_pron/s/sch/schoo/school__us_1.mp3");

        return jo.toString();
    }

    @RequestMapping(value="ping2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String chao2() {
        JsonObject jo = new JsonObject();
        jo.addProperty("word", "school");
        jo.addProperty("meaning", "trường học");
        jo.addProperty("audio", "");

        return jo.toString();
    }
}
