package com.app.javademotest;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerDevice {

    @GetMapping("/")
    public Map<String, Object> getServerInfo() {
        Map<String, Object> response = new HashMap<>();

        try {

            // Lấy địa chỉ IP local
            InetAddress localHost = InetAddress.getLocalHost();
            String localIp = localHost.getHostAddress();
            response.put("localIp", localIp);

            String externalIp = getExternalIp();
            response.put("externalIp", externalIp);

            String serverName = localHost.getHostName();
            response.put("serverName", serverName);

            String os = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");
            response.put("os", os);
            response.put("osVersion", osVersion);

            LocalDateTime currentDateTime = LocalDateTime.now();
            response.put("dateTimeNow", currentDateTime);

        } catch (Exception e) {
            response.put("error", "Không thể lấy thông tin server: " + e.getMessage());
        }

        return response;
    }

    private String getExternalIp() {
        try {
            URL url = new URL("https://api.ipify.org");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String externalIp = in.readLine();
            in.close();

            return externalIp;
        } catch (Exception e) {
            return "Không thể lấy IP external: " + e.getMessage();
        }
    }
}

