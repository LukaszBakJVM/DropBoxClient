package org.lukasz.dropboxclient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {
    private final ClientServices services;

    public ClientController(ClientServices services) {
        this.services = services;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Void> upload(@RequestParam("file") MultipartFile file) {
        return services.sendFile(file);
    }
}

