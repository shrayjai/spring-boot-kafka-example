package sample.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Validated
public class MainController {

    @Autowired
    private Producer producer;

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class.getName());

    @GetMapping(value = "/push")
    public ResponseEntity<?> push(
        @RequestHeader Map<String, String> headers,
        @RequestParam String message
    ) {
        headers.forEach((key, value) -> LOG.debug(String.format("Header '%s' = %s", key, value)));

        {
            LOG.debug("Requested /push");
            LOG.debug("Message: {}", message);

            try {
                producer.send(message);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                UUID uuid = UUID.randomUUID();
                LOG.error(uuid.toString().concat(" | ").concat(String.valueOf(e)));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(uuid);
            }
        }
    }
}
