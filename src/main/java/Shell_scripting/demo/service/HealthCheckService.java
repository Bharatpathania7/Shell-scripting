package Shell_scripting.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthCheckService {

    @Value("${monitor.url}")
    private String url;

    @Autowired
    private EmailService emailService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRateString = "${monitor.interval}")
    public void checkHealth() {

        try {

            ResponseEntity<String> response =
                    restTemplate.getForEntity(url, String.class);

            if(response.getStatusCode() == HttpStatus.OK){

                System.out.println("Application Healthy");

            }else{

                emailService.sendAlert(
                        "Application Down",
                        "Status Code : " + response.getStatusCode()
                );

            }

        } catch (Exception ex){

            emailService.sendAlert(
                    "Application Down",
                    ex.getMessage()
            );

        }

    }

}