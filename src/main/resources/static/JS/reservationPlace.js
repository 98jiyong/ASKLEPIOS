let customOverlay = new kakao.maps.CustomOverlay({
  clickable: false,
  xAnchor: 0.5,
  yAnchor: 1,
  zIndex: 3
});
let mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.277232, 127.027966), // 지도의 중심좌표
      level: 1 // 지도의 확대 레벨
    };
let currentAddr;
let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
let zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
// 마커를 담을 배열입니다
let markers = [];
let infowindow = new kakao.maps.InfoWindow({zIndex:1});

let geocoder = new kakao.maps.services.Geocoder();
let startFlag = false;
const ps = new kakao.maps.services.Places();

window.onload = getLocation();

function getLocation(){
  let locPosition;
  // geolocation으로 사용
  if (navigator.geolocation) {

    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {

      let lat = position.coords.latitude, // 위도
          lon = position.coords.longitude; // 경도

      locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
          message =
          '<div class="customoverlay">'+
          '<a><span class="title" style="font-size:20px;">여기에 계신가요?!</span></a></div>'; // 인포윈도우에 표시될 내용입니다

      // 마커와 인포윈도우를 표시합니다

      displayMarker(locPosition, message);
      map.setCenter(locPosition);

      geocoder.coord2RegionCode(lon, lat, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          // 첫 번째 결과의 행정구역 이름 사용
          currentAddr = result[0].address_name;
          console.log("현재 주소:", currentAddr);

          // 키워드 검색 실행
          ps.keywordSearch(currentAddr + ' 병원', placesSearchCB, { size: 6 });
        } else {
          console.error("coord2RegionCode 실패:", status);
        }
      });
    },
        function (error) {
          console.error("Geolocation 에러:", error.message);
          alert("위치 정보를 가져올 수 없습니다.");
        });
  }
  else {
    // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    let locPosition = new kakao.maps.LatLng(37.277232, 127.027966),
        message = 'geolocation을 사용할수 없어요..'

    displayMarker(locPosition, message);
    map.setCenter(locPosition);
  }

  // 지도에 마커와 인포윈도우를 표시하는 함수입니다
  function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
      map: map,
      position: locPosition
    });

    let iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = false;

    // 커스텀 오버레이로 메세지를 표시
    customOverlay.setMap(map);
    customOverlay.setPosition(locPosition);
    customOverlay.setContent(message);


    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
    map.setLevel(1);
  }

}

// 키워드 검색을 요청하는 함수입니다
function addressSearch() {
  let keyword = document.getElementById('searchPlace').value;

  if (!keyword.replace(/^\s+|\s+$/g, '')) {
    Swal.fire({
      icon: 'warning',
      title: '검색 실패 !',
      text: '검색할 주소를 입력해주세요.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return false;
  }

  if(!(keyword.includes('병원'))){
    keyword += '병원';
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB, {size : 6});
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {

    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data);

    // 페이지 번호를 표출합니다
    displayPagination(pagination);

  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    Swal.fire({
      icon: 'warning',
      title: '검색 실패 !',
      text: '검색 결과가 존재하지 않습니다.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return;

  } else if (status === kakao.maps.services.Status.ERROR) {
    Swal.fire({
      icon: 'erro',
      title: '검색 실패 !',
      text: '검색 결과 중 오류가 발생했습니다.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return;
  }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

  let listEl = document.getElementById('hospitalList'),
      menuEl = document.getElementById('menu_wrap'),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds(),
      listStr = '';

  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();

  for (let i=0; i<places.length; i++ ) {

    // 마커를 생성하고 지도에 표시합니다
    let latitude = places[i].y; // 위도
    let longitude = places[i].x; // 경도
    let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function(marker, title) {
      kakao.maps.event.addListener(marker, 'click', function() {
        displayInfowindow(marker, title, latitude, longitude);
      });


      // onclick vs onmouseover => onclick으로 설정
      itemEl.onclick =  function () {
        displayInfowindow(marker, title, latitude, longitude);
        let moveLatLon = new kakao.maps.LatLng(latitude, longitude);

      };


    })(marker, places[i].place_name);

    fragment.appendChild(itemEl);
  }

  // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
// Ajax를 사용해 DB에 있는 장소만 예약하기 활성화
function getListItem(index, places) {
  let flag;
  let el = document.createElement('div');
  el.tabIndex = 0;
  el.className = 'item';
  let itemStr = /* '<i class="fa-solid fa-house-chimney-medical fa-2xl markerbg"></i>' */
      '<li class="item">' + '<img src="Img/hospital_li.png" alt="아이콘" class="markerbg" />' +
      // '<span class="markerbg marker_' + (index+1) + '"></span>' +
      '<div class="info">' + '<h2>' + places.place_name + '</h2>';

  if (places.road_address_name) {
    itemStr += '<span>' + places.road_address_name.substring(0,15) + '</span>' +
        '<span class="jibun gray">' +  places.address_name.substring(0,15)  + '</span>';
  } else {
    itemStr += '<span>' +  places.address_name.substring(0,15)  + '</span>';
  }
  itemStr += '<span class="tel">' + places.phone  + '</span>' + '</li>';
  itemStr += '<div class="button">';
  itemStr += '</div>';
  el.innerHTML = itemStr;

  el.addEventListener('click', () => {
    document.querySelectorAll('.item').forEach(item=>{
      item.classList.remove('selected');
    });
    el.classList.add('selected');
  });

  let buttonContainer = el.querySelector('.button');
  $.ajax({
    url: '/asklepios/findHospitalName',
    type: 'post',
    data: {
      hospital_name: places.place_name,
      hospital_address: places.road_address_name.substring(0,15)
    },
    success:function(data){
      // console.log(data)
      if (data){
        let reserveButton = document.createElement('button');
        reserveButton.type = 'button';/*<= 모달창을 띄울때는 button으로 변경  */
        reserveButton.name = 'hospital_name';
        reserveButton.value = places.place_name;
        // reserveButton.setAttribute('onclick', 'addEvent()');
        reserveButton.className =
            'my-auto d-flex w-36 h-16 rounded-lg font-semibold text-white text-3xl bg-blue-500 hover:bg-blue-600';
        reserveButton.textContent = '예약하기';
        reserveButton.addEventListener('click', () => {
          showInfo(places.place_name, places.road_address_name.substring(0, 15));
        });
        buttonContainer.appendChild(reserveButton);

      }
    },
    error: function (){
      alert("왜 안될까?");
    }
  });
  return el;
  // 버튼 클릭시 모달 이벤트로 변경
  function addEvent(name, address){
    console.log(places.place_name);
    console.log(places.road_address_name.substring(0,15));
    showInfo(name, address);
  }
}

let modalInfo=document.getElementById("modalInfo");
let hospitalname=document.getElementById("hospitalName");
let closeButton2=document.getElementById("closebutton2");
//병원정보모달 닫기
closeButton2.addEventListener("click",()=>{
    closeModal();
})
function closeModal() {
  modalInfo.style.display = "none";
  document.body.style.backgroundColor = "white";
}
//병원이름 클릭시 병원정보모달
let clickHospitalInfo;
let doctorData;
function showInfo(name, addr){
  console.log(name);
  console.log(addr);
  $.ajax({
    url: "/asklepios/findHospital",
    type: 'post',
    data: {
      hospital_name1: name,
      hospital_address1: addr
    },
    success:function(result){
      console.log(result);
      clickHospitalInfo = result;
      console.log(clickHospitalInfo.hospital_code);
      if(clickHospitalInfo.hospital_code === null){
        alert(name+"을/를 찾지못했습니다")
      }else{
        // //병원코드로 리뷰 받아오기

        //모달창 병원정보 입력
        document.getElementById("clickhospitalname").innerText=clickHospitalInfo.hospital_name;
        document.getElementById("clickhospitaladdr").innerText=clickHospitalInfo.hospital_addr;
        document.getElementById("clickhospitaltel").innerText=clickHospitalInfo.hospital_tel;
        document.getElementById("clickhospitaltime").innerText=clickHospitalInfo.hospital_time;
        document.getElementById("infoIntro").innerText=clickHospitalInfo.hospital_intro;
        document.getElementById("infoNotice").innerText=clickHospitalInfo.hospital_notice;
        document.getElementById("hospitalCode").value=clickHospitalInfo.hospital_code;
        document.getElementById("hospitalName2").value=clickHospitalInfo.hospital_name;
        $.ajax({
          url: "/asklepios/findDoctors",
          type: "post",
          data:{
            hospital_code : clickHospitalInfo.hospital_code
          },
          success:function (doctors){
            console.log(doctors);
            doctorData = doctors;
            let doctorTable=document.getElementById("doctorTable");
            doctorTable.innerHTML="";
            doctorData.forEach((doctor, index) => {
              let doctorRow = `
              <tr>
                  <td>이름</td>
                  <td>${doctor.user_name}</td>
                  <td rowspan="3">`;
                  if(doctor.user_image == null){
                    doctorRow += `<img src="../profile_image/doctor_image.jpg" alt="의사 사진" width="150px" height="200px" style="border-radius: 10px;" />`;
                  }else{
                    doctorRow += `<img src="profile_image/${doctor.user_image}" alt="의사 사진" width="150px" height="200px" style="border-radius: 10px;" />`;
                  }
                  doctorRow += `</td>
                  <td rowspan="3" style="text-align: center">
                      <input type="radio" name="selectDoctor" class="w-6 h-6 mr-10" value="${doctor.user_doctor_code}" ${index === 0 ? "checked" : ""}/>
                  </td>
              </tr>
              <tr>
                  <td>진료과목</td>
                  <td>${doctor.user_doctor_medical}</td>
              </tr>
              <tr>
                  <td>약력</td>
                  <td>${doctor.user_doctor_career}<br>${doctor.user_doctor_graduate}</td>
              </tr>
              <br>`;
              doctorTable.innerHTML += doctorRow;
            })
            // 첫 번째 의사의 코드를 기본값으로 설정
            const doctorCodeInput = document.getElementById("doctorCode");
            const defaultRadio = document.querySelector("input[name='selectDoctor']:checked");
            if (defaultRadio) {
              doctorCodeInput.value = defaultRadio.value;
            }

            // 라디오 버튼 변경 이벤트 처리
            document.querySelectorAll("input[name='selectDoctor']").forEach(radio => {
              radio.addEventListener("change", function () {
                doctorCodeInput.value = this.value; // 선택된 라디오 버튼의 값을 hidden input에 설정
                console.log("선택된 의사 코드:", this.value); // 디버그 로그
              });
            });
          },
          error:function (){
            alert("error");
          }
        })
      }
    },
    error: function (){
      alert("왜 안될까?");
    }
  });
  document.body.style.backgroundColor="gray";
  modalInfo.style.backgroundColor="white";
  modalInfo.style.display="flex";
  hospitalname.innerText="상세정보";
}

function sendPage(){
  let flag = confirm("예약하시겠습니까?");
  if(flag){
    return flag;
  }else{
    return flag;
  }
}
// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
  var imageSrc = 'Img/hospital_marker.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(65, 69),
      imgOptions =  {
        // spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        // spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(27, 69) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage
      });

  marker.setMap(map); // 지도 위에 마커를 표출합니다
  markers.push(marker);  // 배열에 생성된 마커를 추가합니다

  return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for ( var i = 0; i < markers.length; i++ ) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  var paginationEl = document.getElementById('pagination'),
      fragment = document.createDocumentFragment();

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild (paginationEl.lastChild);
  }

  for (i=1; i<=pagination.last; i++) {
    let el = document.createElement('a');
    // el.href = "#";
    el.innerHTML = i;

    if (i===pagination.current) {
      el.className = 'on';
    } else {
      el.onclick = (function(i) {
        return function() {
          pagination.gotoPage(i);
        }
      })(i);
    }

    fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title, latitude, longitude) {
  let content = '<div class="customoverlay">' +
      '<a href="https://map.kakao.com/link/to/'+ title + ',' + latitude + ',' + longitude +
      '"target="_blank"><span class="title">' + title + '</span></a></div>';
  let position = new kakao.maps.LatLng(latitude, longitude);

  customOverlay.setMap(null);
  customOverlay.setMap(map);
  customOverlay.setContent(content);
  customOverlay.setPosition(position);
  map.setCenter(position);
  map.setLevel(2);
  map.setMaxLevel(5);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
  while (el.hasChildNodes()) {
    el.removeChild (el.lastChild);
  }
}


