package Shell_scripting.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthCheckService {

    @Value("${monitor.url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean checkHealth() {

        try {

            ResponseEntity<String> response =
                    restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                System.out.println("✅ Application is Healthy");
                return true;

            } else {

                System.out.println("❌ Application returned : " + response.getStatusCode());
                return false;
            }

        } catch (Exception ex) {

            System.out.println("❌ Health Check Failed : " + ex.getMessage());
            return false;
        }
    }
}