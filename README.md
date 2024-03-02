# 오늘의 술

아무데나 말고, 내가 가고 싶은 술집만 골라드릴게요.<br />
<br />

## 🗓️ 프로젝트 기간

2024.2.15 ~ 2024.2.29


## 📌 배포 주소

https://ohsul.site/
<br />

## 📌 FE 저장소
https://github.com/9walnut/Ohsul-FE

## 📌 주요 기능


지도 API를 활용해 <strong>사용자 취향에 딱 맞는 술집 정보를 제공</strong>하는 서비스입니다. <br />
술, 분위기 등의 태그를 선택해 원하는 조건에 맞는 술집을 확인할 수 있으며 즐겨찾기, 리뷰 작성이 가능합니다.

  <br />

## 👥 팀원 소개

<div align=center >

| 프로필                                                                                                                           | 이름                                      | 역할 | 역할 내용                                                                                                                                                             |
| -------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- | ---- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <img src="https://github.com/9walnut/TheWave/assets/144768130/cbffbe1f-68de-4974-9ba3-1cc76519ead8" width="150" height="150" />  | [권구호](https://github.com/9walnut)      | BE   | CI / CD 구축, https 보안 서버 구축, <br /> 로그인(session) / 회원가입 / 마이페이지 / 내 주변 술집 API 구현                                                            |
| <img src="https://github.com/9walnut/TheWave/assets/144768130/620a9128-de56-4b96-8775-9a37372ab9f1"  width="150" height="150" /> | [이예찬](https://github.com/yeeeeechan)   | BE   | DB 설계, 메인페이지 / 즐겨찾기 / 리뷰 API 구현 <br/> S3 이미지 업로드 기능 구현                                                                                       |
| <img src="https://github.com/9walnut/TheWave/assets/144768130/81420a4b-c1ca-48ec-a57a-45b8f5224bdd" width="150" height="150" />  | [양현정](https://github.com/hyeoonjeoong) | FE   | 기획/문서 작성 <br /> 카카오맵 API 적용, 기능, 커스텀 <br /> zustand를 통한 로그인 전역 상태관리, 회원가입, 로그인 기능 (react-hook-form 적용), <br /> 회원 기능 전반 |
| <img src="https://github.com/9walnut/TheWave/assets/144768130/bc0aa2ec-89f0-411a-a538-6de87c1fca4d" width="150" height="150" />  | [정우성](https://github.com/dntjd129)     | FE   | - 카카오맵 API적용, 커스텀, 현재 위치 가져오기, 검색 로직 작성, type 지정 <br />지도 정보 결과에 대한 api 요청, 데이터 적용           

</div>


<br />

## 📌 시작 가이드

```
$ git clone https://github.com/9walnut/Ohsul-BE.git
```

### Back-end

```
$ ./gradlew.bat build
$ cd build/libs
$ java -jar ohsul-be-3.1.5-SNAPSHOT.jav
```

<br />

<details>
<summary>
    
## 📌 화면 구성

</summary>


<div align=center >
 
<img src="https://github.com/9walnut/Ohsul-FE/assets/101249011/5810003a-551e-4efd-900a-ba90f6d63839" />

</details>

</div>

<br /><br />

## 📌 프로젝트 구성

<div align=center> 

</div>

<div align=center> 


|                                          **팀 노션**                                           |
| :--------------------------------------------------------------------------------------------: |
| [Ohsul_Notion](https://generated-teal-d69.notion.site/b28de37f75384e72916a0f82303f92d9?pvs=74) |

</div>
<br />
<div align=center> 
  
| **개체-관계 모델(ERD)** |
| :----------: |
| <img src='https://github.com/9walnut/Ohsul-BE/assets/144768130/bf5bb063-f306-47e8-8531-4e5ce3888dc0' width="800" /> |


| **시스템 구성** |
| :----------: |
| <img src='https://github.com/9walnut/Ohsul-BE/assets/100561986/d30cf1fc-b5db-4b8e-9747-742e183044b1' width="800" /> |


## 🛠️ 기술 스택


<img src="https://github.com/9walnut/Ohsul-FE/assets/101249011/70a4baba-da97-4dbb-b2e6-1b1acdd825fe" />

<details>
<summary>
  
## 📌 커밋 컨벤션

</summary>

| Emoji | Code                          | 기능     | Description              |
| ----- | ----------------------------- | -------- | ------------------------ |
| ✨    | `:sparkles:`                  | Feat     | 새 기능                  |
| ♻️    | `:recycle:`                   | Refactor | 코드 리팩토링            |
| 🔧    | `:wrench:`                    | Chore    | 리소스 수정/삭제         |
| 🐛    | `:bug:`                       | Fix      | 버그 수정                |
| 📝    | `:memo:`                      | Docs     | 문서 추가/수정           |
| 💄    | `:lipstick:`                  | Style    | UI/스타일 파일 추가/수정 |
| 🎉    | `:tada:`                      | Init     | 프로젝트 시작 / Init     |
| ✅    | `:white_check_mark:`          | Test     | 테스트 추가/수정         |
| ⏪    | `:rewind:`                    | Rewind   | 변경 사항 되돌리기       |
| 🔀    | `:twisted_rightwards_arrows:` | Merge    | 브랜치 합병              |
| 🗃     | `:card_file_box:`             | DB       | 데이터베이스 관련 수정   |
| 💡    | `:bulb:`                      | Comment  | 주석 추가/수정           |
| 🚀    | `:rocket:`                    | Deploy   | 배포                     |

</details>
