package org.lukasz.dropboxclient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/files")
    @ResponseStatus(HttpStatus.OK)
    Mono<AllFiles> allFiles() {
        return services.showAllFiles();
    }
}

