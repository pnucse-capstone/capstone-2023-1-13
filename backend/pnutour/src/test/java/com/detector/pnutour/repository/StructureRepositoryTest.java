package com.detector.pnutour.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


@DataMongoTest
class StructureRepositoryTest {

    @Autowired
    StructureRepository structure;

    @Test
    void findByCode() {
    }

    @Test
    void findAllByCode() {
    }

    @Test
    void findAll() {
    }

//    @Test
//    void save() {
//        com.detector.pnutour.entity.Structure structure = com.detector.pnutour.entity.Structure.builder()
//                .code("b123")
//                .name("test")
//                .info("test")
//                .longitude("test")
//                .latitude("test")
//                .build();
//        this.structure.save(structure);
//
//        com.detector.pnutour.entity.Structure savedStructure = this.structure.findByCode(structure.getCode()).get();
//
//        System.out.println(savedStructure.toString());
//
//
//    }
}

