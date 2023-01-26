package com.unitserver.unit;

import com.unitserver.unit.requestModels.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.*;
import java.util.Objects;

@Component
@RestController
@RequestMapping("/api/")
@CrossOrigin(value = "*", origins = "*", maxAge = 3600)
public class RequestHandler {
   @Value("${app.bip44.executable.path}")
   private String path;

    private String getWallet(String password) {
        synchronized (RequestHandler.class) {
            try {
                Process ps = new ProcessBuilder(this.path, password).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @PostMapping(value="/createWallet", produces="application/json")
    public Mono<String> walletInfo(@Valid @RequestBody Request request) {
        String wallet = this.getWallet(request.password);
        return Mono.just(Objects.requireNonNullElse(wallet, "false"));
    }
}
