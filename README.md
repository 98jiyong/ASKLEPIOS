<h1 align="center">
  <br>
  <img src="https://github.com/user-attachments/assets/7438f3f3-b3b1-4c8a-97b5-d38712131a62" alt="ASKLEPIOS" width="400">
  <br>
  온라인 병원 예약 플랫폼
  <br>
</h1>

## 📌 목차
1. [🖥️ 프로젝트 개요](#-프로젝트-개요)
2. [📕 주요기능](#-주요기능)
3. [🔧 아키텍처](#-아키텍처)
4. [🏗️ ERD](#-ERD)
5. [📖 요구사항 정의서 및 개발일정](#-요구사항-정의서-및-개발일정)
6. [📽️ 작동 영상](#-작동-영상)
7. [🗃️ 발표자료](#-발표자료)

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


<br><br>

## 📕 주요기능
회원제를 통해 차별화된 서비스를 제공하고, 회원은 병원 예약 및 진료 예약이 가능하며, 의사는 자신의 병원을 등록할 수 있다.

<details>
  <summary><b>1. 로그인/임시 비밀번호 발급[정지용]</b> (👈 Click)</summary>
  <br>
  <div markdown="1">
    <h3>로그인</h3>
    <ul>
      <li>Security를 활용하여 로그인한다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/f6fd7296-ad69-4b96-9804-8483eddedfde" alt="로그인1">
      <li>로그인 실패 시, Config의 오류 메시지를 받아서 View로 반환한다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/3f8d1bc2-bba2-482c-bb1c-787e56066516" alt="로그인2">
    </ul>
    <br>
    <h3>비밀번호 재설정</h3>
    <ul>
      <li>메일을 통해 일회용 인증코드를 전송하여 본인확인 후 비밀번호를 변경(재설정)할 수 있다.</li>
      <li>비밀번호 재설정 화면은 다음과 같다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/e5b90d48-379d-429a-ad38-3faa2ea6507d" alt="비밀번호 재설정1">
      <li>비밀번호 재설정 인증 이메일</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/746fb881-1959-4698-b0f7-04f35f20fe85" alt="비밀번호 재설정2">
      <li>비밀번호 재설정 인증번호를 입력 후, 비밀번호를 변경(재설정)할 수 있다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/60d917a5-9d5b-457d-a9ff-caff9fd6c735" alt="비밀번호 재설정3">
    </ul>
  </div>
</details>

<details>
  <summary><b>2. 사원정보조회/거래처관리/계약관리 [이시연]</b> (👈 Click)</summary>
  <br>
  <div markdown="1">
    <h3>사원정보조회</h3>
    <ul>
      <li>사원정보조회 화면</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/68f39768-82ee-45fd-8c1e-5c6559faa5fd" alt="사원정보조회">
    </ul>
    <br>
    <h3>거래처관리</h3>
    <ul>
      <li>거래처관리 화면</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/3749cb6b-2a36-4c6f-9234-953622588273" alt="거래처관리">
    </ul>
    <br>
    <h3>계약관리</h3>
    <ul>
      <li>계약관리 화면</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/12e38992-5e2c-40ba-8153-563c15e94ee1" alt="계약관리">
    </ul>
  </div>
</details>

<details>
  <summary><b>3. 권한관리 [이시연]</b> (👈 Click)</summary>
  <br>
  <h3>권한관리 - 권한 변경</h3>
  <ul>
    <li>직원 목록의 체크박스 선택 후, [권한변경] 버튼을 누르면 바로 위의 권한으로 변경할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/3be67aeb-a96a-4ba7-859b-b18775f6ff91" alt="권한관리1">
  </ul>
  <br>
  <h3>권한관리 - 사원등록</h3>
  <ul>
    <li>[사원등록] 버튼을 누르면 사원등록 모달창이 뜬다.</li>
    <li>사원 정보 입력 후, [등록] 버튼을 누르면 DB에 저장이 된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/ff698974-afb7-4b90-8491-7dee9e4e8412" alt="사원등록">
  </ul>
</details>

<details>
  <summary><b>4. 자재/제품정보관리 [이현주]</b> (👈 Click)</summary>
  <br>
  <h3>자재/제품정보관리 - 검색 인터페이스 & 조회</h3>
  <ul>
    <li>select box의 option에서 검색 주제를 선택한 후, 키워드를 입력하여 검색하면 해당하는 내용의 목록을 볼 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/508dfc17-c9b7-49bb-924e-9fd8603a7ba4" alt="자재/제품정보 검색">
  </ul>
  <br>
  <h3>자재/제품정보관리 - 등록</h3>
  <ul>
    <li>[제품등록] 버튼을 누르면 제품정보 등록 모달창이 뜬다.</li>
    <li>제품의 모든 정보를 입력 해야지만(예외: 제품번호), [등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/4fad14e3-8670-4da3-b953-a88bde335404" alt="자재/제품정보 등록">
  </ul>
  <br>
  <h3>자재/제품정보관리 - 수정</h3>
  <ul>
    <li>[제품 목록]에서 원하는 행의 [수정] 버튼을 누르면 수정 모달창이 뜬다.</li>
    <li>수정할 내용을 입력 후(예외: 제품번호), [수정] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/70ace592-ce92-4af7-89df-1b8d055bb2b8" alt="자재/제품정보 등록">
  </ul>
  <br>
  <h3>자재/제품정보관리 - 등록 및 sweetAlert2</h3>
  <ul>
    <li>외부 라이브러리를 사용하여 등록 시, 사용자에게 정확한 정보를 줄 수 있는 alert창이 뜬다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/af1b1db0-e303-469e-9a5f-7841adb92c9d" alt="등록 및 sweetAlert2">
  </ul>
</details>

<details>
  <summary><b>5. BOM 관리 [이현주]</b> (👈 Click)</summary>
  <br>
  <h3>BOM 관리 - 세부목록 조회</h3>
  <ul>
    <li>제품번호 클릭 시, 해당하는 자재리스트를 화면에서 바로 조회할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/e5d0454b-2828-4f13-8cf2-923272e72988" alt="BOM 세부목록 조회">
  </ul>
  <br>
  <h3>BOM 관리 - 세부목록 수정</h3>
  <ul>
    <li>원하는 행의 [수정] 버튼을 누른 후 값을 입력하고 [수정] 버튼을 누르면 DB에 저장된다.</li>
    <li>취소시 저장된 수량값이 DB에 다시 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/d97aab8b-b26f-41bb-a83a-096e10483150" alt="BOM 수정">
  </ul>
  <br>
  <h3>BOM 관리 - BOM 등록</h3>
  <ul>
    <li>'제품번호', '자재번호'를 선택하고 '단위', '수량'을 입력한 후, [추가] 버튼을 누르면 DB에 저장된다.</li>
    <li>등록된 '제품번호'와 '자재번호'가 존재할 경우, [추가] 버튼 클릭시 오류 메세지가 출력된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/2fd179a2-0b65-4c76-a657-331d633e9faa" alt="BOM 등록">
  </ul>
</details>

<details>
  <summary><b>6. 작업자 스케쥴 조회: 월별 스케줄러 [이현주]</b> (👈 Click)</summary>
  <br>
  <h3>작업자 스케쥴 조회</h3>
  <ul>
    <li>구글 캘린더 API를 적용하여 작업자의 스케쥴을 조회할 수 있도록 했다.</li>
    <li>select box의 option에서 검색 주제를 선택한 후, 키워드를 입력하여 검색하면 해당하는 내용의 목록을 볼 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/f89201c2-f113-4e08-91e1-25d5cc13d0ed" alt="작업자 스케쥴 조회">
  </ul>
</details>

<details>
  <summary><b>7. 제조지시/제조수행지시/공정정보관리 [조다혜]</b> (👈 Click)</summary>
  <br>
  <h3>제조지시/제조수행지시/공정정보관리 - 검색</h3>
  <ul>
    <li>datalist를 사용하여 검색 키워드를 입력하거나, 선택할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/57b4268a-f21e-403b-adcf-7a5f3942cd58" alt="제조지시 등 검색list">
  </ul>
  <br>
  <h3>제조지시 - 등록</h3>
  <ul>
    <li>[등록] 버튼을 누르면 모달창이 뜬다.</li>
    <li>계약 목록에서 원하는 계약을 선택한 후 [+] 버튼을 누르면, 계약 정보가 자동으로 등록된다.</li>
    <li>세부 정보를 입력하고 [추가] 버튼을 누르면 제조지시 목록에 나타난다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/2c8ec92d-d1c9-4c32-aebf-e2bcf1f43407" alt="제조지시 등록1">
    <li>원하는 만큼 정보를 추가한 후, [등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/1b795249-6b2e-45dd-a4fe-a4b974a03de8" alt="제조지시 등록2">
  </ul>
  <br>
  <h3>제조지시 - PDF 저장</h3>
  <ul>
    <li>'제조지시 목록'에서 저장을 원하는 행의 체크박스를 선택 후, [계약 내역서]를 누르면 '제품 공급 계약 내역서'를 조회할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/b56036b9-b660-41fe-ad2f-9cc3e7b32329" alt="pdf 저장1">
    <li>jsPDF 라이브러리를 연결하여, [PDF로 보기] 버튼을 누르면 PDF 파일로 저장할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/87d47a84-7aa2-44ab-aad7-e09e99d5a252" alt="pdf 저장2">
  </ul>
  <br>
  <h3>제조수행지시 - 등록</h3>
  <ul>
    <li>[등록] 버튼을 누르면 제조수행지시등록 모달창이 뜬다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/1eb05ea4-44f2-474d-8759-b57e4a705279" alt="제조수행지시 등록1">
    <li>내용을 입력한 후, [추가] 버튼을 누르면 하단에 목록이 생긴다.</li>
    <li>추가한 목록은 [삭제] 버튼을 통해 삭제할 수 있다.</li>
    <li>원하는 만큼 추가한 후, [등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/0da4d35e-d7ed-4026-a3fd-acf18b0eee34" alt="제조수행지시 등록2">
  </ul>
  <br>
  <h3>공정정보관리 - 등록</h3>
  <ul>
    <li>[등록] 버튼을 누르면 공정정보등록 모달창이 뜬다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/42f99311-f22a-4090-9f8e-45226459ef09" alt="공정정보 등록1">
    <li>'설비목록'에서 검색을 통해 조회가 가능하고, 원하는 설비를 선택한 후, [+] 버튼을 누르면 '추가된 설비목록'으로 이동한다.</li>
    <li>'추가된 설비목록'에서 원하는 설비를 선택하고 [+] 버튼을 누르면 상단의 input에 값이 채워진다.</li>
    <li>나머지 세부정보를 입력하고 [공정등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/4eb97f31-353a-46ba-b9a2-bf8cef55ad7d" alt="공정정보 등록2">
  </ul>
</details>

<details>
  <summary><b>8. 공정관리 [배지현]</b> (👈 Click)</summary>
  <br>
  <h3>공정관리 - 작업관리 - 검색 및 조회</h3>
  <ul>
    <li>대부분의 검색항목을 datalist를 이용하여 해당하는 항목의 정보를 확인하고 가져올 수 있도록 했다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/37933cfc-ed88-4cc4-a2f0-769c4952e9db" alt="작업관리 검색">
  </ul>
  <br>
  <h3>공정관리 - 작업관리 - 조회 및 작업등록</h3>
  <ul>
    <li>work의 작업목록 위에 있는 [작업등록] 버튼을 누르면 '작업등록 모달창'이 뜬다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/846c1325-e839-474e-971f-2229d2c02a2f" alt="작업등록1">
    <li>'제조LOT번호'를 입력하면,<br>'제품번호', '제품명', '생산단위'의 내용이 자동으로 채워진다.</li>
    <li>'공정번호'를 입력하면,<br>'설비번호', '설비명', '작업위치', '남은생산수량'의 내용이 자동으로 채워진다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/a8dd3322-7fa9-42e8-99d7-3b2baf81c18a" alt="작업등록2">
    <li>나머지 세부 정보를 입력한 후, [추가] 버튼을 누르면 입력한 데이터가 배열에 저장되고 '작업등록목록'을 조회할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/59a5476d-1ecc-4470-ae50-680bd890efdc" alt="작업등록3">
    <li>원하는 만큼 추가한 후, [등록] 버튼을 누르면 DB에 저장된다.</li>
    <li>이 때, 소요시간 계산 수식은 다음과 같다.</li>
    <ul>
      <li>소요시간 = 작업종료시간 - 작업시작시간</li>
      <li>조건1. 작업시간 차이가 810분(13시간 30분) 이상인 경우 -90분</li>
      <li>조건2. 작업시간 차이가 4시간 이상 8시간 미만인 경우 -30분</li>
      <li>조건3. 작업시간 차이가 4시간 미만인 경우 -0분</li>
      <li>그 외. -60분</li>
    </ul>
  </ul>
  <br>
  <h3>공정관리 - 작업관리 - 작업 상세 및 수정/삭제</h3>
  <ul>
    <li>work의 '작업목록' 중, [작업상세] 버튼을 누르면, 작업상세 조회와 수정/삭제를 할 수 있는 모달창이 나타난다.</li>
    <li>앞서 작업등록에 이용된 것처럼, '작업종료시간 - 작업시작시간'으로 소요시간을 계산한다.</li>
    <li>작업시작시간이 현재시간보다 과거라면, 이미 작업이 시작된 것으로 readonly 속성을 가진다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/e7d83890-26a1-492e-a4de-31b682f5e586" alt="작업상세 수정1">
    <ul>
      <li><b>수정할 경우, 한 번에 수정되어야 할 사항은 다음과 같다.</b></li>
      1. 생산수량이 1 이상일 경우, process 테이블의 누적생산수량에 합해진다.<br>
      2. 진행상황이 '작업중'일 경우, 설비 작동 테이블의 MW_STATUS = '가동'<br>
      3. 진행상황이 '작업정지' 혹은 '작업종료'일 경우, 설비 작동 테이블의 MW_STATUS = '비가동'<br>
      4. 진행상황이 '작업중단'일 경우, 설비 작동 테이블의 MW_STATUS = '수리중', MW_CONDITION = '수리요청'
    </ul>
    <li>한 번에 수정하기 위하여, MyBatis를 이용해 작성한 쿼리를 트랜잭션 처리하도록 했다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/9928b7a8-e445-4023-b076-c020e801b047" alt="작업상세 수정2">
  </ul>
  <br>
  <h3>공정관리 - 작업관리 - 자재투입 - 등록</h3>
  <ul>
    <li>work의 '작업목록' 위에 있는 [자재투입] 버튼을 누르면, 자재투입등록 모달창이 나타난다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/3e3ffdc3-e638-4df8-ac96-2a20eb42f6b5" alt="자재투입 등록1">
    <li>'제조LOT번호'를 입력하면, 해당하는 '제조LOT번호'에 따른 '공정번호'를 조회하고, 선택할 수 있다.</li>
    <li>'공정번호'를 선택하면, '제품번호', '제품명', '계획생산수량'의 내용이 자동으로 채워진다.</li>
    <li>불러온 '제품번호'에 따른 BOM의 '자재번호' 목록을 조회하고 선택할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/5ce67b24-989e-4225-962b-1379a477b6f6" alt="자재투입 등록2">
    <li>'자재번호'를 선택하면, 해당하는 '자재번호'에 따른 '자재명', '제품1EA별 투입수량', '최소투입수량', '단위'의 내용이 자동으로 채워진다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/7dcfb793-bfea-4858-8b4e-ba5082463107" alt="자재투입 등록3">
    <li>'투입수량'을 입력하고 [추가] 버튼을 누르면, 추가할 자재투입내역을 미리 보여주는 테이블이 생성된다.</li>
    <li>만약, '자재투입목록'의 [삭제] 버튼을 누르면, 배열에 저장된 자재투입내역이 삭제된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/b7fc49cc-76d0-4f54-9b6d-0f0ee72578bb" alt="자재투입 등록4">
    <li>추가된 자재투입목록을 확인 후, [등록] 버튼을 누르면, DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/e5e5dac8-c013-496a-a83b-439d43de2fa8" alt="자재투입 등록5">
  </ul>
  <br>
  <h3>공정관리 - 작업관리 - 자재투입 - 수정</h3>
  <ul>
    <li>work의 '자재투입내역' 중, [수정] 버튼을 누르면 '자재투입수정' 모달창이 나타난다.</li>
    <li>'투입수량'을 수정하고 [수정] 버튼을 누르면, DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/3fb784fb-1651-49d8-afff-275d04a860d9" alt="자재투입 수정1">
  </ul>
  <br>
  <h3>공정관리 - 작업자관리 - 등록/상세 및 수정/삭제</h3>
  <ul>
    <li>workerInsert의 '작업자배치' 중, [관리] 버튼을 누르면, '작업자관리' 모달창이 나타난다.</li>
    <li>작업자 관리 모달창</li>
    <ul>
      <li>조회된 데이터를 통해 작업자 등록을 할 수 있다.</li>
      <li>부서명 선택 시, 부서명에 따라 사원번호 datalist를 조회하고 선택할 수 있다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/adfb4477-d961-45b6-be56-a2fe7c61c29b" alt="작업자관리1">
    </ul>
  </ul>
  <br>
  <h3>공정관리 - 작업자관리 - 등록/상세 및 수정/삭제 - 상세 및 수정</h3>
  <ul>
    <li>작업번호에 따른 작업자 정보 조회</li>
    <ul>
      <li>등록된 작업자 목록에 조회한 데이터가 나타난다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/be74b065-bba7-4632-aee9-8cfbf1bb1111" alt="작업자관리2">
    </ul>
    <li>'사원번호'를 입력 후 [추가] 버튼을 누르면, '추가된 작업자 목록'에 입력한 사원번호가 등록된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/25701df8-d700-4a41-97a7-770f8e3d88a0" alt="작업자관리3">
    <li>'추가된 작업자 목록'은 [등록] 버튼을 누르면, DB에 저장된다.</li>
    <li>단, 이미 등록된 작업자를 추가하려고 할 경우, 아래와 같이 alert창을 통해 추가를 방지한다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/87e15cf8-8e7d-42fc-aa50-f7ab8d5cc3ce alt="작업자관리4">
  </ul>
  <br>
  <h3>공정관리 - 작업자관리 - 등록/상세 및 수정/삭제 - 삭제</h3>
  <ul>
    <li>'등록된 작업자 목록' 중 [삭제] 버튼을 누르면, 작업자 삭제 confirm 창이 뜨고, 작업자가 삭제된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/b06e0d5d-e379-4543-a7d1-171e41ab91dc" alt="작업자관리5">
  </ul>
</details>

<details>
  <summary><b>9. 설비관리 [김다은]</b> (👈 Click)</summary>
  <br>
  <h3>설비관리 - 설비정보관리 - 등록</h3>
  <ul>
    <li>[추가] 버튼을 누르면, '설비별 체크리스트 정보 추가' 모달창이 나타난다.</li>
    <li>내용을 입력한 후, [등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/d9748d8b-c3be-4056-b636-e546c6e70c3f" alt="설비정보 등록1">
  </ul>
  <br>
  <h3>설비관리 - 설비정보관리 - 수정</h3>
  <ul>
    <li>원하는 행의 [수정] 버튼을 누르면, '설비수정' 모달창이 나타난다.</li>
    <li>내용을 수정한 후, [등록] 버튼을 누르면 수정된 내용이 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/98fedd0f-f28b-4794-a1ea-a379096d8d33" alt="설비정보 수정1">
  </ul>
  <br>
  <h3>설비관리 - 설비체크기준관리 - 등록</h3>
  <ul>
    <li>[추가] 버튼을 누르면, '설비별 체크리스트 정보 추가' 모달창이 나타난다.</li>
    <li>내용을 입력한 후, [등록] 버튼을 누르면 DB에 저장된다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/efc7d045-ab5e-427d-b116-9ad92f5fdb36" alt="설비체크기준 등록1">
  </ul>
  <br>
  <h3>설비관리 - 설비 체크리스트 - 등록</h3>
  <ul>
    <li>설비를 검색한 후, '체크리스트' 답변을 등록할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/a2363b35-e837-4ed2-a642-cd05ffa3afd4" alt="설비체크리스트 등록1">
  </ul>
  <br>
  <h3>설비관리 - 설비관리</h3>
  <ul>
    <li>각 버튼을 누르면, ajax를 사용해 서버로 데이터를 전송하여 상태를 업데이트 한다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/250087e4-54fd-4e0f-add7-af4c98fb14e0" alt="설비관리1">
  </ul>
  <br>
  <h3>설비관리 - 설비수리이력 조회 & 설비가동 현황</h3>
  <ul>
    <li><b>설비수리이력 조회</b></li>
    <ul>
      <li>설비관리에서 '수리완료'를 누르면, '수리날짜', '수리 이력 내용'이 뜬다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/73dac92e-2413-4d9d-8c01-a54b95eb80f2" alt="설비수리이력 조회1">
    </ul>
    <li><b>설비가동 현황</b></li>
    <ul>
      <li>설비관리에서 클릭하는 버튼에 따라 상태와 현황이 바뀐다.</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/e6c24e99-28be-4b57-beaa-5a9de29785d2" alt="설비가동 현황1">
    </ul>
  </ul>
</details>

<details>
  <summary><b>10. 품질관리 [정수하]</b> (👈 Click)</summary>
  <br>
  <h3>품질관리 - 수입검사관리 화면</h3>
  <ul>
    <li>수입검사관리 화면에서 할 수 있는 것은 다음과 같다.</li>
    <ul>
      <li>검색 및 조회</li>
      <li>등록</li>
      <li>수정</li>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/bf6cd88e-f150-4a48-9ed9-192ab83205e2" alt="수입검사관리 화면">
    </ul>
  </ul>
  <br>
  <h3>품질관리 - 수입검사관리 - 검색 및 조회</h3>
  <ul>
    <li>화면 상단의 '검색창'에서 datalist를 통해 검색용 데이터를 조회하면서 입력할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/9e72451e-2276-40c6-bc4a-1ea3ebe0e8be" alt="수입검사관리 - 검색 및 조회1">
    <li>'검색창'의 '불량유형1'을 선택하면, '불량유형1'에 따른 '불량유형2'의 option을 조회하고 선택할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/0f4c16b8-2010-45bc-86c5-3a5595bdbabe" alt="수입검사관리 - 검색 및 조회2">
    <li>검색 키워드 입력 후, [검색] 버튼을 누르면, 해당하는 내용을 조회할 수 있다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/d8e7dba3-f61f-461e-8926-a636bc38eccb" alt="수입검사관리 - 검색 및 조회3">
  </ul>
  <br>
  <h3>품질관리 - 수입검사관리 - 등록</h3>
  <ul>
    <li>[자재불량등록] 버튼을 누르면, '수입검사 - 자재불량 및 입고등록' 모달창이 나타난다.</li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/deb4497c-6d38-4f2f-8def-ce763b655f43" alt="수입검사관리 - 등록1">
    <li>'수입검사관리 등록 모달창' 내의 로직은 다음과 같다.</li>
    <ul>
      <b>1. '계약번호' 입력 시, 관련 내용이 자동으로 채워진다. → Ajax 사용</b>
      <ul>
        <li>거래처명</li>
        <li>단위</li>
        <li>자재번호</li>
        <li>자재명</li>
        <li>자재용도</li>
        <li>계약입고수량</li>
        <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/fb7e366a-d75a-43ce-8c94-bf944c9bb1d6" alt="수입검사관리 - 등록2">
      </ul>
      <b>2. 나머지 정보를 입력하고 [추가] 버튼을 누르면, '자재불량목록'에서 추가된 내용을 확인할 수 있다.</b>
      <ul>
        <li>이 때, 동일한 검사 대상이더라도, 다양한 불량유형이 있을 수 있다.</li>
        <li>위의 조건이라면, 자동으로 '양품수량'을 계산하여 표기하도록 했다.</li>
        <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/9dda289a-45d9-4f31-ac68-1464b55a2269" alt="수입검사관리 - 등록3">
        <li>'불량유형2', '비고'는 null 가능하지만, 다른 항목은 null이 불가능하다.</li>
        <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/bc123911-2939-4558-8a8f-b52c3d41a1d9" alt="수입검사관리 - 등록4">
      </ul>
      <b>3. 원하는 만큼 추가한 후, [등록] 버튼을 누르면, 테이블에서 데이터를 수집한 후, ajax 통신으로 DB에 저장된다.</b>
      <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/8a22d8f6-260c-432a-963b-7aab3d5e4880" alt="수입검사관리 - 등록5">
    </ul>
    <li><b>[최종 등록] 시, DB에 저장되는 로직과 코드는 다음과 같다.</b></li>
    <img src="https://github.com/heyJSH/CNR_FURNITURE/assets/150403977/8b47c956-cfe5-4357-880d-6ae71230c074" alt="수입검사관리 - 등록6">
  </ul>
</details>

<br><br>

## 🔧 아키텍처
####  * 서버 배포를 진행하지 않았음
![ASKLEPIOS-아키텍처](https://github.com/user-attachments/assets/0b231397-9dd7-4294-8ad1-f8ab88823976)
<br><br>

## 🏗️ ERD
![ERD](https://github.com/user-attachments/assets/e66666d7-eba0-4379-90ce-0ee20c9d1959)
<br><br>

## 📖 요구사항 정의서 및 개발일정
- [[📑 요구사항 정의서]](https://docs.google.com/spreadsheets/d/1319HK6tojzy0Jqov5cYQ1aQAfhmJnO5vXLnDfRDZolg/edit?gid=1651580600#gid=1651580600)
- [[📑 개발일정]](https://docs.google.com/spreadsheets/d/1319HK6tojzy0Jqov5cYQ1aQAfhmJnO5vXLnDfRDZolg/edit?gid=0#gid=0)
<br><br>

## 📽️ 작동 영상
[[📹 작동 영상]](https://www.youtube.com/watch?v=W2Vq8N1xsIk&list=PLNdYfRO3wDimceWjFMnr3KoMn73idCSHj)

<br><br>

## 🗃️ 발표자료
[[📂 발표자료]](https://www.canva.com/design/DAGZZpxk6Wk/LY2Gb7eVNRx5tHq7PCic7Q/edit)
