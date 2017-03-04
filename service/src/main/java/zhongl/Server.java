package zhongl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Controller
@SpringBootApplication
public class Server {

    final RestTemplate template = new RestTemplate();

    @GetMapping("/")
    public
    @ResponseBody
    String ok() {
        return "OK";
    }

    @GetMapping("/{chain}")
    public
    @ResponseBody
    String call(@PathVariable("chain") String chain) {
        final String[] split = chain.split(",", 2);
        final String url;
        if (split.length > 1) {
            url = MessageFormat.format("http://service{0}:808{0}/{1}", split[0], split[1]);
        } else {
            url = MessageFormat.format("http://service{0}:808{0}/", split[0]);
        }
        return template.getForEntity(url, String.class).getBody();
    }


    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
