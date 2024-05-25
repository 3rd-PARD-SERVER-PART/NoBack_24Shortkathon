package com.example.demo.Image.DTO;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ImageDTO {
    @Setter
    @Getter
    public class Create{
        private String filePath;
        private Long imageId;

    }
}