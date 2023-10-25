package com.detector.pnutour.dto;

import com.detector.pnutour.entity.Structure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


public class StructureRequest {


    @NoArgsConstructor
    @Getter
    public static class StructureInsertDTO {
        private String code;
        private String name;
        private String latitude;
        private String longitude;
        private String info;
        private String type;
    }




}