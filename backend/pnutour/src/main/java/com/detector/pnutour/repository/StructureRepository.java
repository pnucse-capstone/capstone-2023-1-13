package com.detector.pnutour.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StructureRepository extends MongoRepository<com.detector.pnutour.entity.Structure,String> {
    Optional<com.detector.pnutour.entity.Structure> findByCode(String code);
    List<com.detector.pnutour.entity.Structure> findAllByCodeStartingWithOrderByName(String structureType);
    List<com.detector.pnutour.entity.Structure> findAllByCodeStartingWithOrderByCode(String structureType);
    List<com.detector.pnutour.entity.Structure> findAllByTypeAndCodeStartingWithOrderByCode(String convenenceType,String structureType);
    List<com.detector.pnutour.entity.Structure> findAllByTypeAndCodeStartingWithOrderByName(String convenenceType,String structureType);
    com.detector.pnutour.entity.Structure save(com.detector.pnutour.entity.Structure structure);
    Optional<com.detector.pnutour.entity.Structure> deleteByCode(String code);
}
