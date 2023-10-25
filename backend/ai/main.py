from fastapi import FastAPI ,UploadFile
from ultralytics import YOLO
app = FastAPI()
building_model= YOLO('building.pt')
statue_model= YOLO('statue.pt')
# 이미지 전처리 함수 정의

@app.post("/detect/statues")
async def predict(file: UploadFile):
    try:
        #이미지를 임시파일로 저장
        image_path = "temp_image.jpg"
        with open(image_path, "wb") as temp_image:
            temp_image.write(file.file.read())

        result = statue_model.predict(source=image_path)[0]
        buildingName= statue_model.names[int(result.boxes.cls[0])]

        return {"label": buildingName}
    except Exception as e:
        return {"error": str(e)}


@app.post("/detect/buildings")
async def predict(file: UploadFile):
    try:
        #이미지를 임시파일로 저장
        image_path = "temp_image_building.jpg"
        with open(image_path, "wb") as temp_image:
            temp_image.write(file.file.read())

        result = building_model.predict(source=image_path)[0]
        buildingName= building_model.names[int(result.boxes.cls[0])]
        return {"label": buildingName}
    except Exception as e:
        return {"error": str(e)}
