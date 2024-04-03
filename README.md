# 🏥 병원 관리 시스템
## 소개
병원 관리 시스템은 병원 내 다양한 업무를 관리할 수 있는 웹 페이지입니다. <br>
`인하대학교 정보통신공학과 데이터베이스설계` 수업을 수강하면서 배운 이론을 실습을 통해 개발 지식 및 역량을 강화하기 위해 스스로 개발했습니다.

<br>

## 🔑 주요 기능 및 URL별 설명
- **관리자**: 의사와 간호사의 정보를 관리하고, 전체 시스템을 모니터링합니다.
- **의사**: 환자의 진료 기록을 관리하고, 진료 예약을 확인할 수 있습니다.
- **간호사**: 환자의 치료 기록을 관리하고, 필요한 치료 정보를 기록합니다.
- **환자**: 자신의 예약 상태를 확인하고, 새로운 예약을 할 수 있습니다.
  
### ▶️ 공통
<img width="1111" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/78008507-14e7-47e3-820e-78bf7c6e92a9">

- `/login`: 로그인 페이지, 모든 사용자는 자신의 역할에 맞는 계정으로 로그인할 수 있습니다.

### ▶️ 관리자
<img width="1125" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/a8c7d7b7-d649-499d-9e0c-20b9c9c7d01a">

- `/admin`: 관리자 메인 페이지, 의사와 간호사의 정보를 추가, 수정, 삭제할 수 있습니다.
- `/api/addDoctor`: 새로운 의사를 추가하는 기능입니다.
- `/api/addNurse`: 새로운 간호사를 추가하는 기능입니다.
- `/api/doctor`: 의사 정보를 수정하거나 삭제하는 기능입니다.
- `/api/nurse`: 간호사 정보를 수정하거나 삭제하는 기능입니다.

### ▶️ 의사
<img width="535" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/aaf98ac8-8880-46f5-9604-3e75b834e51a">

- `/doctor`: 의사 메인 페이지, 환자의 진료 기록을 조회하고 관리할 수 있습니다.
- `/api/addExamination`: 새로운 진료 기록을 추가하는 기능입니다.
- `/api/examination`: 진료 기록을 수정하거나 삭제하는 기능입니다.
- `/api/searchExamination`: 환자 이름으로 진료 기록을 검색하는 기능입니다.

### ▶️ 간호사
<img width="542" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/63b14d4f-a176-4f4c-a67e-31e5e848be9e">

- `/nurse`: 간호사 메인 페이지, 환자의 치료 기록을 조회하고 관리할 수 있습니다.
- `/api/addTreatment`: 새로운 치료 기록을 추가하는 기능입니다.
- `/api/treatment`: 치료 기록을 수정하거나 삭제하는 기능입니다.
- `/api/searchTreatment`: 환자 이름으로 치료 기록을 검색하는 기능입니다.

### ▶️ 환자
<img width="1094" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/800e54e3-2040-40f3-a2af-84007dc5f088">

- `/patient`: 환자 메인 페이지, 자신의 예약 현황을 조회하고 새로운 예약을 할 수 있습니다.
- `/api/addReservation`: 새로운 예약을 추가하는 기능입니다.
- `/api/reservation`: 예약을 취소하는 기능입니다.
  
<br>

## ERD
<img src="https://github.com/DAY0522/hospital-system/assets/79097171/895a8b2c-6df1-4fec-9bb3-17dca1c10d9e" width="800">

### ▶️ 정규화 진행
<img width="773" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/4b0e1b59-9b12-4565-a927-8a0409af6f9e">

<img width="888" alt="image" src="https://github.com/DAY0522/hospital-system/assets/79097171/81b51e20-44fe-4371-8196-975b9e06be8b">

- 모든 릴레이션에 대해 `1NF`, `2NF`, `3NF`, `BCNF`를 만족하도록 ERD를 설계했습니다.
- 정규화 우수성 판단을 위해 각 릴레이션 별 함수 종속을 나타냈습니다.
