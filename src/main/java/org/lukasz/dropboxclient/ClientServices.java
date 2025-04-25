package org.lukasz.dropboxclient;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientServices {
    private final WebClient webClient;

    public ClientServices(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    Mono<Void> sendFile(MultipartFile newFile) {
        MultipartBodyBuilder body = new MultipartBodyBuilder();
        body.part("file", newFile.getResource());

        return webClient.post().uri("/upload").contentType(MediaType.MULTIPART_FORM_DATA).body(BodyInserters.fromMultipartData(body.build())).retrieve().bodyToMono(Void.class);
    }

    Mono<AllFiles> showAllFiles() {
        return webClient.get().uri("/files").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AllFiles.class);
    }
}
