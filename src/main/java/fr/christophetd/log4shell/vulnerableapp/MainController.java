package fr.christophetd.log4shell.vulnerableapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @GetMapping("/")
    public String index(@RequestHeader("X-Api-Version") String apiVersion) {
        logger.info("===== Direct string logged====");
        logger.info("Received a request for API version " + apiVersion);
        logger.info("===== String formatted logged====");
        logger.info(String.format("Received a request for API version %s", apiVersion));
        try {
            throw new Exception(apiVersion);
        } catch (Exception ex) {
            logger.info("===== Handled exception formatted logged====");
            logger.info(ex);
        }
        return "Hello, world!";
    }

}
