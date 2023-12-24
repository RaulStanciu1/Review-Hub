package com.reviewhub.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Optional;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    private ObjectId id;
    private String username;
    private String teamName;
    private Optional<String> message;
}
