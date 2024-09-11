# 정감 클라우드 기반의 조달 구매 웹 시스템 구축 ver N

## 프로젝트 소개
이 프로젝트는 팀 프로젝트인 **[정감 클라우드 기반의 조달 구매 웹 시스템 구축](https://github.com/Team-Junggam/procurement-system)** 에서 파생된 개인 프로젝트입니다.  
팀 프로젝트 종료 후, 추가적으로 기능을 구현하여 시스템을 개선하고자 진행 중입니다.

### 기간
2024년 9월 11일 ~ 현재 진행중

### 추가 구현 예정 기능
- [ ] **메인 대시보드** - 데이터베이스와 연동하여 실시간 데이터 표시
- [ ] **엑셀 업로드 및 다운로드** - 엑셀 파일로 데이터를 손쉽게 관리
- [ ] **스프링 시큐리티 기반 로그인 처리** - 보안이 강화된 사용자 인증 및 권한 관리
- [ ] **도움말 기능** - 처음 이용하는 사용자도 쉽게 사용할 수 있도록 도움말 기능 추가
- [ ] 미정(추가 계획중)

---

# 기존 팀 프로젝트 : [정감 클라우드 기반의 조달 구매 웹 시스템 구축](https://junggam.site)

## 팀 소개
### 팀 명
- **정감(Junggnam)** :정(情)이 감. 정시마감, 정확한마감 

### 팀 구성원
| 이름(ID)  | 역할   | 담당 업무 |
| :---: | :--: | :----------------------------------------: |
| **김나현**</br>([@LongTypeNo6](https://github.com/LongTypeNo6)) | 팀장 | 프로젝트 구현 전반, 시스템 설계 및 개발, 코드 작성 및 관리 |
| **최영호**</br>([@mit-cyh](https://github.com/mit-cyh)) | 팀원 | 사용자 인터페이스(UI) 구성, 시스템 설계 및 개발, 코드 작성 및 관리 |
| **최미선**</br>([@mmisunn](https://github.com/mmisunn))</br>([@Team-Junggam](https://github.com/Team-Junggam)) | 팀원 | 사용자 인터페이스(UI) 구성, 프레젠테이션 자료 디자인 및 작성, 깃관리 |
| **박승희**</br>([@Usern4](https://github.com/Usern4)) | 팀원 | 데이터베이스 설계 및 개발담당, 서류 정리 및 테스트 진행, 트렐로관리 |

</br>

## 프로젝트 소개
### 프로젝트 명 
- 정감 클라우드 기반의 조달 구매 웹 시스템 구축

### 프로젝트 내용 
-  BPM(비즈니스 프로젝트 매니지먼트) 시스템 중 일부인 조달/구매 프로젝트  
- 수작업을 통해 정확도와 편의성이 떨어지므로, 전산을 통해 발주 작업 및 자재 수량 확인, 입출고 확인, 편의성과, 효율성을 증대  
- 인텔리제이, 스프링부트, 마리아디비를 이용하여 클라우드 기반의 웹 시스템을 구축  

### 프로젝트 목표 :  
- 시스템 전산화를 통해 생산 효율성 증대와 업무시간 단축  
- 타 부서와 연계 중 발생하는 사고 방지 및 신속한 조치  
- 전산 시스템 구축을 통한 프론트엔드, 백엔드, db기술 내재화  

### 기술 스택
- 언어: Java 17
- 프레임워크: Spring Boot 3.3.2
- 빌드 도구: Gradle
- 템플릿 엔진: Thymeleaf
- 데이터베이스: MariaDB
- ORM: JPA
- ORM 툴: QueryDSL
- 매핑 도구: MapStruct
- 개발 편의성: Lombok
- 테스트 프레임워크: JUnit 5

### 주요 구현 기능 
- 발주 계획 및 조달 계획 등록
- 제품, 유닛, 자재 등록
- 제품 재고 관리
- 구매 발주 관리
- 시스템 통계 및 대시보드 제공

### 배포
- [정감사이트(https://junggam.site)](https://junggam.site)

### 데이터베이스
- [정감팀 ERD 다이어그램](https://trello.com/1/cards/66cc12de5f6ddcd56b927283/attachments/66cc133595ed92ecf8900390/previews/66cc133695ed92ecf89007f8/download/%EC%A0%95%EA%B0%90%ED%8C%80_ERD_%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png)

### 일정 관리 및 아카이브
- [트렐로(Trello)](https://trello.com/b/vm17OY76/%EC%A0%95%EA%B0%90-%EC%A0%95%EC%8B%9C%EB%A7%88%EA%B0%90-%EC%A0%95%EC%9D%B4-%EB%A7%8E%EB%8B%A4-junggam) 

### 보고서 공유
- [구글스프레드시트(Google Sheets)](https://docs.google.com/spreadsheets/d/1CKDVvGwNOMHf7OHyEvPoogQiNbyiSshwT2kCXxPc0Ow/edit?gid=185188384#gid=185188384
)  
