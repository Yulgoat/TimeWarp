package fr.mightycode.cpoo.server;

import fr.mightycode.cpoo.server.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyDomainMessageListener implements RouterService.MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MyDomainMessageListener.class);

    @Override
    public void onMessageReceived(RouterService.Message message) {
        logger.info("onMessageReceived {}", message);
    }
}
