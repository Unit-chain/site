package com.unitserver.unit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@RestController
@RequestMapping("/api/")
public class RequestHandler {
   @Value("${app.bip44.executable.path}")
   private String path;

    private String getWallet() {
        synchronized (RequestHandler.class) {
            try {
                Runtime rt = Runtime.getRuntime();
                Process ps = rt.exec(path);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(ps.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                reader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @GetMapping("/getWallet")
    public Mono<String> walletInfo() {
        String wallet = this.getWallet();
        if (wallet == null)
            return Mono.just("something went wrong");
        else
            return Mono.just(this.getWallet());
    }
}
