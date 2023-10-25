package com.detector.pnutour.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "structure")
@Builder
@Getter
public class Structure {

    @Id
    private String code;
    private String name;
    private String type;
    private String info;
    private String latitude;
    private String longitude;

}
