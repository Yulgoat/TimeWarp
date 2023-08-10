package fr.mightycode.cpoo.server;

import fr.mightycode.cpoo.server.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DomainMessageListener implements RouterService.MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DomainMessageListener.class);

    @Value("${server.domain}")
    private String serverDomain;

    @Override
    public String getDomain() {
        return serverDomain;
    }

    @Override
    public void onMessageReceived(RouterService.Message message) {
        logger.info("onMessageReceived {}", message);
    }
}
