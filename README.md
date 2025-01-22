<h1 align="center">
  <br>
  <img src="https://github.com/user-attachments/assets/7438f3f3-b3b1-4c8a-97b5-d38712131a62" alt="ASKLEPIOS" width="400">
  <br>
  온라인 병원 예약 플랫폼
  <br>
</h1>

## 📌 목차
1. [🖥️ 프로젝트 개요](#%EF%B8%8F-프로젝트-개요)
2. [📕 주요기능](#-주요기능)
3. [🔧 아키텍처](#-아키텍처)
4. [🏗️ ERD](#%EF%B8%8F-erd)
5. [📖 요구사항 정의서 및 개발일정](#-요구사항-정의서-및-개발일정)
6. [📽️ 시연영상](#%EF%B8%8F-시연영상)
7. [🗃️ 발표자료](#%EF%B8%8F-발표자료)

      <br><br>

## 🖥️ 프로젝트 개요
### :calendar: 개발기간
  - 2024.11.18 ~ 2024.12.16 (총 28일 , 4주)

### 🔖프로젝트 주제
  - 기획 배경: <br>
    - 통계청 자료를 토대로 다양한 의료서비스 불만족 이유 중에서 <strong><mark>긴 대기시간이</mark> 1위를 차지함</strong>
    - 환자의 <strong><mark>불편함을 최소화하고 의료 서비스의 질을 향상시키고자</mark></strong> 프로젝트를 기획
  - 기획 목적: <br>
    - 병원 진료 시 <strong>긴 대기시간 해소</strong>
    - 병원리뷰를 통한 <strong>치료 결과 개선</strong>
    - 의사와 환자의 <strong>원활한 소통</strong>
  - 기대 효과: <br>
    - 온라인 예약을 통해 <strong>긴 대기시간 문제를 해결</strong>
    - 리뷰로 피드백을 통해 <strong>향상된 의료서비스 제공</strong> => 환자의 <strong>의료서비스 만족도 향상</strong>
    
### ⚙️ 개발환경 및 도구
  - **Frontend**: HTML, CSS, Javascript, JQuery, TailwindCSS, Sweetalert, Fontawesome, flatpickr, Thymeleaf
  - **Backend**: Java17, SpringBoot(3.3.5), SpringSecurity, ApacheTomcat(10.1.31), Gradle(8.10.2)
  - **DataBase**: MariaDB, DBeaver
  - **ORM**: Mybatis
  - **IDE**: IntelliJ, VisualStudio
  - **VersionControl**: Git
  - **Collaboration**: GitHub, Code With Me
### 🧑‍🤝‍🧑 멤버 구성
|팀원명|프로필|담당업무|
|---|---|---|
|[조준곤<br>(팀장)](https://github.com/Jungon-Jo)|<p align="center"><img src="https://avatars.githubusercontent.com/u/174285554?v=4" width="100"></p>|회의 진행 및 일정·팀원 관리, 전체 설계 담당<br>DB설계 및 ERD작성, 기능 별 페이지 작성<br>회원가입, 마이페이지 기능 구현|
|[신태균<br>(부팀장)](https://github.com/Taekyun131)|<p align="center"><img src="https://avatars.githubusercontent.com/u/174299045?v=4" width="100"></p>|팀장 부재 시 일정·팀원 관리<br>DB설계 및 ERD작성, 기능 별 페이지 작성<br>게시판 및 병원 검색 기능 구현, Spring Security 인증, 인가 구현<br> 병원 리스트 엑셀 다운로드 구현<br>Ajax 비동기 통신을 활용한 게시글 좋아요 기능 구현 |
|[정지용<br>(팀원)](https://github.com/98jiyong)|<p align="center"><img src="https://avatars.githubusercontent.com/u/119985920?v=4" width="100"></p>|DB설계 및 ERD작성, 기능 별 페이지 작성<br>로그인, 아이디 찾기, 메일인증을 통한 임시 비밀번호 발급 기능 구현 <br>kakao map API를 활용한 병원 & 진료 예약 기능 구현<br>Daum API를 활용한 병원 등록 기능 구현<br>Ajax 비동기 통신을 활용한 실시간 예약 현황 조회 기능 구현 |
|[임동준<br>(팀원)](https://github.com/NAGABARA)|<p align="center"><img src="https://avatars.githubusercontent.com/u/174768701?v=4" width="100"></p>|DB설계 및 ERD작성|

[📌 목차로 이동](#-목차)
<br><br>

## 📕 주요기능
회원제를 통해 차별화된 서비스를 제공하고, 회원은 병원 예약 및 진료 예약, 의사는 자신의 병원을 등록할 수 있음.

<details>
  <summary><b>1. 로그인 / 아이디 찾기 / 임시 비밀번호 발급[정지용]</b> (👈 Click)</summary>
  <br>
  <div markdown="1">
    <h3>로그인</h3>
    <ol>
      <li>회원제를 통한 ¹퀵메뉴 및 ²실시간 예약 순위 정보를 제공하여 차별화된 서비스를 구현.</li>
      <img src="https://github.com/user-attachments/assets/1c67cf2b-a618-450d-a810-eb21dbb53cc4" width="600" alt="로그인1"><br><br>
      <li>Security를 활용하여 로그인.
      <ul>
      <li>모든 정보를 올바르게 입력해야만 로그인이 가능하며,</li>
      <li>실패 시 => Config의 오류 메시지를 받아서 View로 반환.</li>
      </ul>
      </li>
      <img src="https://github.com/user-attachments/assets/463491af-6e48-453c-9a22-54d1251ebae9" width="400" alt="로그인2"><br><br>
      <li>성공 시 => 퀵메뉴 및 실시간 예약순위를 확인할 수 있고, 회원별 기능 사용 가능.</li>
      <img src="https://github.com/user-attachments/assets/059796e1-9c9e-4d18-b0d4-99c80df947cc" width="600" alt="로그인3">
    </ol>
    <br>
    <h3>아이디 찾기</h3>
    <ul>
      <li>회원은 이름과 주민등록번호를 입력해 아이디를 찾을 수 있음.</li>
      <li>필수정보를 입력하지 않으면 경고문이 나타나며, 아이디를 찾을 수 없음.</li>
      <img src="https://github.com/user-attachments/assets/0889b60c-462a-4440-90f3-4d8e14a5d150" alt="아이디 찾기1"><br><br>
      <li>실패 시 => 입력한 정보가 일치하지 않다는 경고문이 나타남.</li>
      <img src="https://github.com/user-attachments/assets/06cbbb4a-86dc-4b0c-b1ee-ef8d77714210" width="400" alt="아이디 찾기2"><br><br>
      <li>성공 시 => 마스킹 된 자신의 아이디를 확인할 수 있음.</li>
      <img src="https://github.com/user-attachments/assets/beb7d563-a136-417e-bf86-5fbe07f91a68" width="400" alt="아이디 찾기3">
    </ul>
    <br>
    <h3>임시 비밀번호 발급</h3>
    <ul>
      <li>메일을 통해 일회용 인증코드를 전송하여 본인확인 후 임시 비밀번호를 발급 받을 수 있음.</li>
      <img src="https://github.com/user-attachments/assets/38c7def0-34c7-4095-a05c-550ad51ddc11" alt="임시 비밀번호 발급1"><br><br>
      <li>회원가입시 입력한 아이디와 이메일이 일치해야 이메일 인증이 가능.</li>
      <img src="https://github.com/user-attachments/assets/bde7edff-1a37-4656-9a61-8a405a23ddfc" width="400" alt="임시 비밀번호 발급2"><br><br>
      <li>인증코드를 통한 인증번호 확인 후 임시 비밀번호 발급 가능.</li>
      <img src="https://github.com/user-attachments/assets/3cbf7e9c-e6eb-4176-92b3-c4cc50f88ed8" width="400" alt="임시 비밀번호 발급3">
    </ul>
  </div>
</details>

<details>
  <summary><b>2. 병원 및 진료 예약하기 [정지용]</b> (👈 Click)</summary>
  <br>
  <div markdown="1">
    <h3>병원 예약</h3>
    <ul>
      <li>초기화면</li>
      <ul>
        <li>초기화면은 리스트와 지도에 아무것도 표시되지 않음</li>
        <li>위치 정보 제공 동의 시 사용자 위치 중심으로 맵과 리스트에 병원을 제공</li>
      </ul>
      <img src="https://github.com/user-attachments/assets/1dd32c0f-0f16-4dd9-8cbf-d32564b75ea9" width="600" alt="예약1"><br><br>
      <li>병원 클릭 시</li>
      <ul>
        <li>해당 병원이 플랫폼에 등록돼있으면 예약하기 버튼을 표시</li>
        <li>해당 병원에 마커 클릭시 도착지를 자동 입력하여 길찾기 서비스 제공</li>
        <li>사용자는 손쉽게 출발지를 입력해 길을 찾을 수 있음.</li>
      </ul>
      <img src="https://github.com/user-attachments/assets/adc218c4-9c2a-4026-ac0d-61344dbd3ad8" width="600" alt="예약2"><br><br>
      <li>병원 검색 시</li>
      <ul>
        <li>지역 검색 시 해당 지역 중심으로 병원 정보를 제공</li>
        <li>키워드 검색을 통해 원하는 지역의 진료과목 또한 검색 가능</li>
      </ul>
      <img src="https://github.com/user-attachments/assets/8a64423c-281a-48d2-b041-6c4d8b539f3b" width="600" alt="예약3"><br><br>
    </ul>
    <h3>병원 예약 및 진료 예약</h3>
    <ul>
      <li>예약하기 버튼 클릭 시</li>
      <ul>
        <li>예약하기 버튼 클릭 시 해당 병원에 대한 상세정보를 확인할 수 있음.</li>
        <li>해당 병원 정보를 확인한 후 원하는 의사를 선택해 예약 가능</li>
        <li>예약 시 선택한 병원, 담당 의사, 사용자 정보가 자동으로 입력됨.</li>
      </ul>
      <img src="https://github.com/user-attachments/assets/354e9ea3-5823-4d0d-9c4c-d86d7c389480" width="600" alt="예약4"><br><br>
    </ul>
    <br>
      <h3>진료 예약</h3>
      <ul>
      <li>진료 예약 시</li>
      <ul>
        <li>날짜 선택 시 해당 날짜에 예약 가능한 시간대 확인 가능</li>
        <li>이미 예약된 시간은 예약이 불가능하도록 선택 불가능</li>
        <li>회원은 원하는 시간대 및 간단한 메모를 작성한 후 예약을 진행</li>
      </ul>
      <img src="https://github.com/user-attachments/assets/e0ecb3f5-5f4f-4efb-87c1-0b10e897d483" width="600" alt="예약5">
    </ul>
    <br>
  </div>
</details>

<details>
  <summary><b>3. 예약 현황 확인하기 [정지용]</b> (👈 Click)</summary>
  <br>
  <h3>예약현황 확인</h3>
  <ul>
    <li>회원은 자신이 예약한 내용을 예약 현황을 통해 확인할 수 있음.</li>
    <img src="https://github.com/user-attachments/assets/99385c2f-ed6e-4043-af39-04ec53be4ddf" width="600" alt="예약현황 확인1">
  </ul>
  <br>
  <ul>
    <li>예약 현황을 클릭 시 현재 내가 예약한 진료의 상태를 확인할 수 있음(대기 / 거절 / 승인).</li>
    <img src="https://github.com/user-attachments/assets/7b0e7d69-b2d4-4604-b4c0-3d481ea4b621" width="600" alt="예약현황 확인2">
  </ul>
  <br>
  <ul>
    <li>담당 의사가 진료 상태를 변경하면 실시간으로 회원의 예약 현황에서도 상태가 변경됨.<br>
    => <b>담당 의사는 진료 시간에 맞춰 다음 환자의 예약 상태를 알맞게 변경 가능.</b></li>
    <img src="https://github.com/user-attachments/assets/59805f57-9b68-43fe-91b8-3122348d21bd" width="600" alt="예약현황 확인3">
  </ul>
</details>

<details>
  <summary><b>4. 병원 등록하기 [정지용]</b> (👈 Click)</summary>
  <br>
  <h3>병원 등록</h3>
  <ul>
    <li>초기 화면</li>
    <ul>
      <li>의사는 병원 등록을 통해 병원을 등록할 수 있음.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/ef42a354-559c-4c9b-b2e8-645f870e47fb" width="600" alt="병원 등록하기1">
  </ul>
  <br>
  <ul>
    <li>중복 확인</li>
    <ul>
      <li>기존에 이미 등록된 병원이라면, 경고 문구를 표시하고 등록이 불가.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/544b8eb7-03b4-421d-83e4-f97c35422224" width="600" alt="병원 등록하기2">
  </ul>
  <br>
  <ul>
    <li>중복 확인</li>
    <ul>
      <li>신규 병원이라면, 확인 문구를 표시하고 상세 주소칸이 활성화됨.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/2da9e553-2178-4b8c-a689-91ba2a50a9e5" width="600" alt="병원 등록하기3">
  </ul>
  <br>
  <ul>
    <li>병원 등록</li>
    <ul>
      <li>병원에 대한 상세정보를 입력한 후 병원 등록.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/d9a06743-226c-49d2-a127-cbf3791d1e9a" width="600" alt="병원 등록하기4">
  </ul>
  <br>
  <ul>
    <li>등록된 병원 확인</li>
    <ul>
      <li>병원 검색을 통해 병원이 잘 등록됐는지 조회.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/44b94cff-5de6-4061-b831-099d816ba83e" width="600" alt="병원 등록하기5">
  </ul>
</details>

<details>
  <summary><b>5. 회원가입 [조준곤]</b> (👈 Click)</summary>
  <br>
  <h3>회원가입</h3>
  <ol>
    <li>약관 동의를 진행한 후 회원가입을 할 수 있음</li>
    <ul>
      <li>약관을 모두 동의하지 않고 회원가입을 진행하면 경고창이 뜸</li>
      <li>모든 약관을 동의할 시, 상단에 전체 체크 버튼이 활성화됨</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/e21ef9d4-1a1a-4337-b217-ffa9205f47b8" width="600" alt="회원가입1"><br><br>
    <li>원하는 아이디 및 비밀번호를 입력하여 계정을 생성</li>
    <ul>
      <li>아이디 중복 확인을 통해 사용할 수 있는지 확인</li>
      <li>비밀번호 재입력을 통한 비밀번호 확인.</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/4ef28dc5-694f-45b4-a6c6-958352b1d083" width="600" alt="회원가입2"><br><br>
    <li>이메일 인증을 통한 무결성과 보안을 보장.</li>
    <img src="https://github.com/user-attachments/assets/4b74c3e9-bc2a-4962-9456-87e0860c9b8e" width="600" alt="회원가입3"><br><br>
    <li>주소 및 프로필 사진을 첨부하여 회원가입 완료</li>
    <ul>
      <li>다음 주소검색 API를 사용해 주소를 입력</li>
      <li>사진 첨부를 통해 프로필 사진 설정 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/20a15c01-e6f8-4131-b6ff-0adf5f8e433d" width="600" alt="회원가입4"><br><br>
    <li>가입대상이 의사라면 추가작성을 통해 소속된 병원 및 경력 설정</li>
    <ul>
      <li>병원 검색을 통해 자신이 소속된 병원을 클릭해 병원코드 설정</li>
      <li>상세정보에 표시될 진료과목, 경력 및 학력을 작성해 회원가입 완료</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/cc51ace0-8740-4f37-b1a6-e6fad736a43e" width=
    "600" alt="회원가입5">
  </ol>
</details>

<details>
  <summary><b>6. 마이페이지 [조준곤]</b> (👈 Click)</summary>
  <br>
  <h3>마이페이지</h3>
  <ul>
    <li>비밀번호 변경</li>
    <ul>
      <li>만약 임시 비밀번호를 발급받았다면, 마이페이지를 통해 비밀번호를 변경할 수 있음</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/45bae6a9-d601-4e96-acd7-919ee350eb60" width="600" alt="마이페이지1">
  </ul>
  <ul>
    <li>사진 수정</li>
    <ul>
      <li>변경하고 싶은 사진을 첨부하면 미리보기를 통해 사진을 확인할 수 있음</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/b5bbc0ad-5baa-4f48-a9f0-a61f061535c4" width="600" alt="마이페이지2">
  </ul>
  <ul>
    <li>의사정보 수정</li>
    <ul>
      <li>의사가 이직할 시, 의사정보 수정을 통해 이직한 병원의 정보를 입력할 수 있음</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/e367ce17-9043-4067-a387-fc5bfd8c9ef5" width="600" alt="마이페이지3">
  </ul>
  <ul>
    <li>수정된 정보확인</li>
    <ul>
      <li>저장한 후 마이페이지를 확인해 수정된 정보가 잘 입력된 것을 확인할 수 있음</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/10499b2c-36d4-4e38-afef-3b35d4d4ebdd" width="600" alt="마이페이지4">
  </ul>
</details>

<details>
  <summary><b>7. 게시판 [신태균]</b> (👈 Click)</summary>
  <br>
  <h3>게시판 구현 - 동영상 편집 미완료</h3>
  <ul>
    <li>추후에 영상 편집이 완료되면 업데이트 예정</li>
    <img src="https://github.com/user-attachments/assets/39d75804-9928-4a84-8a48-0e0a1687e37d" width="600" alt="준비중">
  </ul>
  <br>
</details>

<details>
  <summary><b>8. 병원검색 [신태균]</b> (👈 Click)</summary>
  <br>
  <h3>병원 검색</h3>
  <ul>
    <li>키워드로 검색</li>
    <ul>
      <li>네비게이션에 있는 진료과목을 선택해 등록돼있는 병원 검색 가능</li>
      <li>특정 키워드를 검색해 키워드가 포함된 병원 검색 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/2ee20b53-7def-46ab-baec-e4636d30b490" width="600" alt="병원검색1">
  </ul>
  <br>
  <ul>
    <li>지역으로 검색</li>
    <ul>
      <li>검색어에 지역을 입력해 해당 지역에 등록돼있는 병원 검색 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/c1d45feb-d1ee-4a1b-b6a1-542827b9f1b8" width="600" alt="병원검색2">
  </ul>
  <br>
  <ul>
    <li>중복 필터 검색</li>
    <ul>
      <li>해당 지역에 대한 검색 후 진료과목을 선택해 중복 필터링 가능</li>
      <li>해당 지역에 원하는 진료과목을 필터링해 검색 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/27984cd6-f2fc-4ae4-a918-a4711fb454aa" width="600" alt="병원검색3">
  </ul>
  <br>
  <ul>
    <li>진료중인 병원 필터링</li>
    <ul>
      <li>현재시간을 기준으로 등록돼있는 병원 중 진료중인 병원 검색 가능</li>
      <li>현재시간 18시 10분을 기준으로 진료중인 병원을 필터링하여 검색 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/010518dc-24b6-489a-9332-2f87cbdedfe6" width="600" alt="병원검색4">
  </ul>
  <br>
  <ul>
    <li>주말 진료 여부</li>
    <ul>
      <li>등록돼있는 병원 중 주말 진료를 하는 병원을 필터링해 검색 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/0f090105-453a-4066-a1de-1f131b867f20" width="600" alt="병원검색5">
  </ul>
  <br>
  <ul>
    <li>병원 상세정보 확인</li>
    <ul>
      <li>검색한 병원을 클릭하면 해당 병원에 대한 상세정보를 확인할 수 있음</li>
      <li>또한, 상세정보에서 담당의사를 선택해 해당병원의 진료예약 가능</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/e6d92172-9095-4460-a242-d349ab1f723e" width="600" alt="병원검색6">
  </ul>
</details>

<details>
  <summary><b>9. 병원 리스트 다운로드(엑셀) [신태균]</b> (👈 Click)</summary>
  <br>
  <h3>병원 리스트 다운로드 - 엑셀</h3>
  <ul>
    <li>병원 리스트 다운로드 - 비회원</li>
    <ul>
      <li>해당 기능은 회원만 제공하기 때문에 비회원 클릭 시, 로그인 세션으로 이동</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/a458015d-b12e-496b-a722-8dbf1bfd7260" width="600" alt="엑셀다운로드">
  </ul>
  <br>
  <ul>
    <li>병원 리스트 다운로드 - 회원</li>
    <ul>
      <li>엑셀 다운로드 클릭 시, 병원들에 대한 정보를 엑셀로 다운로드하여 확인할 수 있음</li>
    </ul>
    <img src="https://github.com/user-attachments/assets/a96d74a1-3560-4fa5-b9d2-599438db2a5e" width="600" alt="엑셀다운로드">
  </ul>
</details>

[📌 목차로 이동](#-목차)
<br><br>

## 🔧 아키텍처
####  * 서버 배포를 진행하지 않았음
![ASKLEPIOS-아키텍처](https://github.com/user-attachments/assets/0b231397-9dd7-4294-8ad1-f8ab88823976)<br><br>
[📌 목차로 이동](#-목차)
<br><br>

## 🏗️ ERD
![ERD](https://github.com/user-attachments/assets/e66666d7-eba0-4379-90ce-0ee20c9d1959)<br><br>
[📌 목차로 이동](#-목차)
<br><br>

## 📖 요구사항 정의서 및 개발일정
- [[📑 요구사항 정의서]](https://docs.google.com/spreadsheets/d/1319HK6tojzy0Jqov5cYQ1aQAfhmJnO5vXLnDfRDZolg/edit?gid=1651580600#gid=1651580600)
- [[📑 개발일정]](https://docs.google.com/spreadsheets/d/1319HK6tojzy0Jqov5cYQ1aQAfhmJnO5vXLnDfRDZolg/edit?gid=0#gid=0)<br>

[📌 목차로 이동](#-목차)
<br><br>

## 📽️ 시연영상
[[📹 시연영상]](https://www.youtube.com/watch?v=W2Vq8N1xsIk&list=PLNdYfRO3wDimceWjFMnr3KoMn73idCSHj)<br>

[📌 목차로 이동](#-목차)
<br><br>

## 🗃️ 발표자료
[[📂 발표자료]](https://www.canva.com/design/DAGZZpxk6Wk/LY2Gb7eVNRx5tHq7PCic7Q/edit)<br>

[📌 목차로 이동](#-목차)
