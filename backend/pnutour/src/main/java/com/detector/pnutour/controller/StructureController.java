package com.detector.pnutour.controller;

import com.detector.pnutour.dto.StructureRequest;
import com.detector.pnutour.service.StructureService;
import com.detector.pnutour.utils.ApiUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class StructureController {
    private final StructureService structureService;

    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }


    //건물,편의시설,명소,조형물 개별 조회
    @GetMapping("structures/{code}")
    public ResponseEntity<ApiUtils.ApiResult> buildingFind(@PathVariable String code){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFind(code)));
    }

    // 건물,편의시설,명소,조형물 종류별 전체 조회 - 이름순
    @GetMapping("/structures/{structureType}/order-name")
    public ResponseEntity<ApiUtils.ApiResult> buildingFindAllOrderName(@PathVariable String structureType){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFindAllOrderName(structureType)));
    }
    // 건물,편의시설,명소,조형물 종류별 전체 조회 - 코드순
    @GetMapping("/structures/{structureType}/order-code")
    public ResponseEntity<ApiUtils.ApiResult> buildingFindAllOrderCode(@PathVariable String structureType){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFindAllOrderCode(structureType)));
    }


    // 건물,편의시설,명소,조형물 전체 조회 - 거리순
    @GetMapping("/structures/{structureType}/order-distance/{code}")
    public ResponseEntity<ApiUtils.ApiResult> buildingFindAllOrderDistance(@PathVariable String structureType,@PathVariable String code){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFindAllOrderDistance(structureType,code)));
    }

    // 편의시설 타입별 조회 - 거리순
    @GetMapping("/structures/conveniences/order-distance/{type}/{code}")
    public ResponseEntity<ApiUtils.ApiResult> buildingFindAllByTypeOrderDistance(@PathVariable String type,@PathVariable String code){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFindAllByTypeOrderDistance(type,code)));
    }

    // 편의시설 타입별 전체 조회 - 이름순
    @GetMapping("/structures/conveniences/order-name/{type}")
    public ResponseEntity<ApiUtils.ApiResult> buildingFindAllByTypeOrderName(@PathVariable String type){
        return ResponseEntity.ok().body(ApiUtils.success(structureService.structureFindAllByTypeOrderName(type)));
    }

    //  정보 추가 및 변경
    @PostMapping("/structures")
    public ResponseEntity<ApiUtils.ApiResult> buildingInsert(@RequestBody List<StructureRequest.StructureInsertDTO> buildingInsertDTOS){
        structureService.structureInsert(buildingInsertDTOS);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    //  정보 삭제
    @DeleteMapping("/structures/{code}")
    public ResponseEntity<ApiUtils.ApiResult> buildingDelete(@PathVariable String code){
        structureService.structureDelete(code);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    //이미지 변경
    @PutMapping("structures/{code}/images")
    public ResponseEntity<ApiUtils.ApiResult> buildingIamgeInsert(@PathVariable String code, @RequestBody MultipartFile file){
        structureService.structureImageInsert(code,file);
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    //이미지 조회
    @GetMapping("structures/{code}/images")
    public ResponseEntity<?> buildingImageFind(@PathVariable String code){
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(structureService.structureImageFind(code));
    }






}
