package fr.mightycode.cpoo.router.controller;

import fr.mightycode.cpoo.router.model.Greeting;
import fr.mightycode.cpoo.router.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting route(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
