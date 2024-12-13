$('.timepicker').timepicker({
  timeFormat: 'HH:mm',
  interval: 30,
  minTime: '09:00',
  maxTime: '18:00',
  // defaultTime: '9',
  startTime: '09:00',
  dynamic: false,
  dropdown: true,
  scrollbar: false
});

let hospitalNameFlag = false;
let addressFlag = false;
let duplicateFlag = false;

function searchAddress() {
  new daum.Postcode({
    oncomplete: function(data) {
// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

// 각 주소의 노출 규칙에 따라 주소를 조합한다.
// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else { // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if(data.userSelectedType === 'R'){
// 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        // document.getElementById("sample6_extraAddress").value = extraAddr;
        // document.getElementById("sample6_extraAddress").readOnly = true;
      } else {
        // document.getElementById("sample6_extraAddress").value = '';
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('sample6_postcode').value = data.zonecode;
      document.getElementById("address").value = addr;
      document.getElementById("sample6_postcode").readOnly = true;
      document.getElementById("address").readOnly = true;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("detailAddress").focus();
    }
  }).open();
}

function duplicateHospital(){
  let hospitalName = document.querySelector('#hospital_name').value;
  let hospitalAddress = document.querySelector('#address').value;
  let hospitalDetailAddress = document.querySelector('#detailAddress');

  if (hospitalName === '') {
    Swal.fire({
      icon: 'warning',
      title: '입력오류',
      text: '병원명을 입력해주세요.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
  }else{
    hospitalNameFlag = true;
  }

  $.ajax({
    url: '/asklepios/duplicateHospital',
    type: 'post',
    data: {
      hospital_name: hospitalName,
      hospital_address: hospitalAddress
    },
    success:function(data){
      // console.log(data)
      if (data){
        Swal.fire({
          icon: 'warning',
          title: '확인결과',
          text: hospitalName + '은(는) 이미 등록된 병원입니다.',
          confirmButtonColor: '#3B82F6',
          confirmButtonText: '확인'
        });
      }else{
        Swal.fire({
          icon: 'success',
          title: '확인결과',
          text: hospitalName + '은(는) 등록 가능한 병원입니다.',
          confirmButtonColor: '#3B82F6',
          confirmButtonText: '확인'
        });
        addressFlag = true;
        duplicateFlag = true;
        hospitalDetailAddress.removeAttribute('readonly');
      }
    },
    error: function (){
      alert("왜 안될까?");
    }
  });
}

function validateForm(){
  let addr1 = document.querySelector('#address').value;
  let addr2 = document.querySelector('#detailAddress').value;
  let tel1 = document.querySelector('#tel1').value;
  let tel2 = document.querySelector('#tel2').value;
  let tel3 = document.querySelector('#tel3').value;
  let time1 = document.querySelector('#openTime').value;
  let time2 = document.querySelector('#closeTime').value;
  let resultRadio = $('input[name=default-radio]:checked').val();
  let resultAddr = addr1 + ' ' + addr2;
  let resultTel = tel1 + '-' + tel2 + '-' + tel3;
  let resultTime = time1 + '-' + time2;
  document.querySelector('#hospital_addr').value = resultAddr;
  document.querySelector('#hospital_tel').value = resultTel;
  document.querySelector('#hospital_date').value = resultRadio;
  document.querySelector('#hospital_time').value = resultTime;
  // console.log(document.querySelector('#hospital_addr').value);
  console.log(resultRadio)
  if (addr1 === '') {
    Swal.fire({
      icon: 'warning',
      title: '입력 오류',
      text: '주소를 입력해주세요.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return false;
  }

  if (tel1 === '' || tel2 === '' || tel3 === '') {
    Swal.fire({
      icon: 'warning',
      title: '입력 오류',
      text: '병원 연락처를 입력해주세요.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return false;
  }

  if (time1 === '' || time2 === '') {
    Swal.fire({
      icon: 'warning',
      title: '입력 오류',
      text: '근무시간을 입력해주세요.',
      confirmButtonColor: '#3B82F6',
      confirmButtonText: '확인'
    });
    return false;
  }

  let flag = confirm("등록하시겠습니까?");
  if(flag){
    alert("등록되었습니다.");
    return flag;
  }else{
    alert("다시 한번 확인해주세요.");
    return flag;
  }
}
