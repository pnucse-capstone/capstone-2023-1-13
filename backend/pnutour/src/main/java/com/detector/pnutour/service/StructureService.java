package com.detector.pnutour.service;
import com.detector.pnutour.dto.StructureRequest;
import com.detector.pnutour.dto.StructureResponse;
import com.detector.pnutour.entity.Structure;
import com.detector.pnutour.exception.AppException;
import com.detector.pnutour.exception.ErrorCode;
import com.detector.pnutour.repository.StructureRepository;
import com.detector.pnutour.utils.DistanceComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class StructureService {

    private final StructureRepository structureRepository;

    public StructureService(StructureRepository structure) {
        this.structureRepository = structure;
    }



    public StructureResponse.StructureFindDTO structureFind(String code){
        com.detector.pnutour.entity.Structure structure = this.structureRepository.findByCode(code).orElseThrow(()-> new RuntimeException("해당 건물을 찾을 수 없습니다."));
        return new StructureResponse.StructureFindDTO(structure);

    }

//로컬용
//    public void structureImageInsert(String code, MultipartFile file)  {
//        if (!file.isEmpty()){
//            String absolutePath = new File("").getAbsolutePath();
//                System.out.println(absolutePath);
//                System.out.println(File.separator);
//            String path = absolutePath+"/static/images/structures/"+code+".jpg" ;
//            try {
//                file.transferTo(new File(path));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    //    도커 배포용
    public void structureImageInsert(String code, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 업로드 디렉토리와 파일 이름을 조합하여 저장 경로를 생성합니다.
                String path = "/app/static/images/structures/" + code + ".jpg";

                // 디렉토리가 없으면 생성합니다.
                File directory = new File("/app/static/images/structures/");
                if (!directory.exists()) {
                    directory.mkdirs(); // 디렉토리를 생성합니다.
                }

                // MultipartFile을 File로 변환하고 저장합니다.
                File destination = new File(path);
                file.transferTo(destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


//로컬용
//    public byte[] structureImageFind(String code) {
//        String absolutePath = new File("").getAbsolutePath();
//        File file =  new File(absolutePath+"/static/images/structures/"+code+".jpg");
//        try {
//            return Files.readAllBytes(file.toPath());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //도커배포용
    public byte[] structureImageFind(String code) {
        File file =  new File("/app/static/images/structures/"+code+".jpg");
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void structureInsert(List<StructureRequest.StructureInsertDTO> structureInsertDTO) {
        List<com.detector.pnutour.entity.Structure> structures = structureInsertDTO.stream()
                .map(s -> com.detector.pnutour.entity.Structure.builder().name(s.getName()).info(s.getInfo()).latitude(s.getLatitude()).longitude(s.getLongitude()).code(s.getCode()).type(s.getType()).build()).collect(Collectors.toList());
        structureRepository.saveAll(structures);
    }

    public  StructureResponse.StructureFindAllDTO structureFindAllOrderName(String structureType) {

        if (structureType.equals("buildings")){
            List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByCodeStartingWithOrderByName("b");
            return new StructureResponse.StructureFindAllDTO(structures);
        }else if (structureType.equals("conveniences")){
            List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByCodeStartingWithOrderByName("c");
            return new StructureResponse.StructureFindAllDTO(structures);
        } else if (structureType.equals("landmarks")) {
            List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByCodeStartingWithOrderByName("l");
            return new StructureResponse.StructureFindAllDTO(structures);
        }else if (structureType.equals("statues")) {
            List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByCodeStartingWithOrderByName("s");
            return new StructureResponse.StructureFindAllDTO(structures);
        }else {
            throw new AppException(ErrorCode.UNKNOWN_SERVER_ERROR,"잘못된 structureType입니다. URL을 다시 확인하세요");
        }
    }

    public StructureResponse.StructureFindAllDTO structureFindAllOrderCode(String structureType) {
        List<com.detector.pnutour.entity.Structure> structures = new ArrayList<>();
        if (structureType.equals("buildings")){
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("b");
        }else if (structureType.equals("conveniences")){
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("c");
        } else if (structureType.equals("landmarks")) {
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("l");
        }else if (structureType.equals("statues")) {
            structures = structureRepository.findAllByCodeStartingWithOrderByName("s");
        }else {
            throw new AppException(ErrorCode.UNKNOWN_SERVER_ERROR,"잘못된 structureType입니다. URL을 다시 확인하세요");
        }
        return new StructureResponse.StructureFindAllDTO(structures);
    }

    public StructureResponse.StructureFindAllDTO structureFindAllOrderDistance(String structureType, String code) {
        List<com.detector.pnutour.entity.Structure> structures = new ArrayList<>();
        if (structureType.equals("buildings")){
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("b");
        }else if (structureType.equals("conveniences")){
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("c");
        } else if (structureType.equals("landmarks")) {
            structures = structureRepository.findAllByCodeStartingWithOrderByCode("l");
        }else if (structureType.equals("statues")) {
            structures = structureRepository.findAllByCodeStartingWithOrderByName("s");
        }else {
            throw new AppException(ErrorCode.UNKNOWN_SERVER_ERROR,"잘못된 structureType입니다. URL을 다시 확인하세요");
        }

        Structure structure = structureRepository.findByCode(code).orElseThrow(()-> new AppException(ErrorCode.UNKNOWN_SERVER_ERROR,"해당 코드에 해당하는 건축물이 없습니다."));

        // 거리 비교자를 사용하여 건물 리스트를 정렬
        List<Structure> sortedStructures = structures.stream()
                .sorted(new DistanceComparator(Double.parseDouble(structure.getLatitude()), Double.parseDouble(structure.getLongitude())))
                .collect(Collectors.toList());

        return new StructureResponse.StructureFindAllDTO(sortedStructures);
    }

    public void structureDelete(String code) {
        structureRepository.deleteByCode(code);
    }

    public StructureResponse.StructureFindAllDTO structureFindAllByTypeOrderDistance(String type, String code) {
        List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByTypeAndCodeStartingWithOrderByCode(type,"c");
        Structure structure = structureRepository.findByCode(code).orElseThrow(()-> new AppException(ErrorCode.UNKNOWN_SERVER_ERROR,"해당 코드에 해당하는 건축물이 없습니다."));
        // 거리 비교자를 사용하여 건물 리스트를 정렬
        List<Structure> sortedStructures = structures.stream()
                .sorted(new DistanceComparator(Double.parseDouble(structure.getLatitude()), Double.parseDouble(structure.getLongitude())))
                .collect(Collectors.toList());
        return new StructureResponse.StructureFindAllDTO(sortedStructures);
    }

    public StructureResponse.StructureFindAllDTO structureFindAllByTypeOrderName(String type) {
        List<com.detector.pnutour.entity.Structure> structures = structureRepository.findAllByTypeAndCodeStartingWithOrderByName(type,"c");
        // 거리 비교자를 사용하여 건물 리스트를 정렬
        return new StructureResponse.StructureFindAllDTO(structures);
    }
}
