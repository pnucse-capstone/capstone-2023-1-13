import requests
import os
import requests
import os
import json


# 파일 업로드 함수
def upload_file(file_path, base_url):
    with open(file_path, 'rb') as file:
        file_name = os.path.basename(file_path)
        files = {'file': (file_name, file)}
        response = requests.put(base_url, files=files)
        if response.status_code == 200:
            print(f'{file_name} 업로드 성공')
        else:
            print(f'{file_name} 업로드 실패: {response.status_code}')

# JSON 데이터 전송 함수
def send_json_data(json_data, base_url):
    headers = {'Content-Type': 'application/json'}
    json_data=[json_data]
    response = requests.post(base_url, data=json.dumps(json_data), headers=headers)
    if response.status_code == 200:
        print('JSON 데이터 전송 성공')
    else:
        print(f'JSON 데이터 전송 실패: {response.status_code}')


def image_init():
    # 로컬 디렉토리에서 사진 파일 목록 가져오기
    photo_directory = "./images"
    photo_files = [f for f in os.listdir(photo_directory) if f.endswith(".jpg")]

    # 업로드할 서버의 기본 URL
    base_url = "localhost:8080/structures/"

    # 각 사진 파일을 서버에 업로드
    for photo_file in photo_files:
        file_path = os.path.join(photo_directory, photo_file)
        file_name = os.path.basename(file_path).split(".")[0]
        upload_file(file_path, base_url + file_name + "/images")

def json_init():
    # 로컬 디렉토리에서 .json 파일 목록 가져오기
    json_directory = "./jsons"  # 디렉토리 경로를 적절히 수정하세요.
    json_files = [f for f in os.listdir(json_directory) if f.endswith(".json")]
    # 업로드할 서버의 기본 URL
    base_url = "http://localhost:8080/structures/"

    # 각 .json 파일을 서버에 전송
    for json_file in json_files:
        file_path = os.path.join(json_directory, json_file)

        # .json 파일을 읽어 JSON 데이터로 파싱
        with open(file_path, 'r', encoding='utf-8') as json_file:
            json_data = json.load(json_file)
        send_json_data(json_data, base_url)

if __name__ == "__main__":
    json_init()
    print("-----------------json 업로드 완료-----------------")
    #이미지는 이미 올라가 있다.
    image_init()
    print("-----------------이미지 업로드 완료-----------------")


