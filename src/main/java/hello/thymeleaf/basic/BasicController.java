package hello.thymeleaf.basic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic")
public class BasicController {

    // 텍스트 - text, utext
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        log.info("textBasic - model = {}", model);
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    // 텍스트 - text, utext
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        log.info("text-unescaped - model = {}", model);
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    // 변수 - SpringEL
    @GetMapping("/variable")
    public String variable(Model model) {
        log.info("variable - model = {}", model);
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @Data
    static class User{

        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    // 기본 객체들
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        log.info("basicObjects - session = {}", session);
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            log.info("hello - data = {}", data);
            return "Hello " + data;
        }
    }

    // 유틸리티 객체와 날짜
    @GetMapping("/date")
    public String date(Model model) {
        log.info("date - model = {}", model);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    // URL 링크
    @GetMapping("/link")
    public String link(Model model) {
        log.info("link - model = {}", model);
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    // 리터럴
    @GetMapping("/literal")
    public String literal(Model model) {
        log.info("literal - model = {}", model);
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    // 연산
    @GetMapping("/operation")
    public String operation(Model model) {
        log.info("operation - model = {}", model);
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    // 속성 값 설정
    @GetMapping("/attribute")
    public String attribute() {
        log.info("attribute");
        return "basic/attribute";
    }

    // 반복
    @GetMapping("/each")
    public String each(Model model) {
        log.info("each - model = {}", model);
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model) {
        log.info("addUsers - model = {}", model);
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        model.addAttribute("users", list);
    }

}
