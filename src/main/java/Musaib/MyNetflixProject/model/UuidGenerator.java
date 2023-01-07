package Musaib.MyNetflixProject.model;

import lombok.Value;

import java.util.UUID;
public @interface UuidGenerator {

    UUID uuid = UUID.randomUUID();

}
