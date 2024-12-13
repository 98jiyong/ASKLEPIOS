flatpickr("#calender",{
  inline:true
});
let hospitalCode = document.querySelector("input[name='reservation_hospital_code']").value;
let doctorCode = document.querySelector("input[name='reservation_user_doctor_code']").value;

document.querySelector('#calender').addEventListener('change', ()=>{
  let reservationDate = document.querySelector('#calender').value;
  let buttonContainer = document.querySelector('.timeContainer');
  // 예약 시간 배열 생성 (30분 단위)
  let reservationTime = [
      '09:00','09:30','10:00','10:30','11:00','11:30','13:00','13:30',
    '14:00','14:30','15:00','15:30','16:00','16:30','17:00','17:30'];
  // 기존 날짜에 있던 버튼들을 삭제 (새로 버튼을 추가하기 위해)
  removeAllChildNods(buttonContainer);
  console.log(reservationDate);
  console.log(doctorCode);
    $.ajax({
      url: "/asklepios/isReserve",
      type: "post",
      data:{
        hospital_code : hospitalCode,
        doctor_code : doctorCode,
        reservation_date : reservationDate
      },
      success:function(data){
        console.log(data);
        // alert("success!");
        for(let i=0; i<reservationTime.length; i++){
          let flag = true;
          let timeButton = document.createElement('button');
          timeButton.type = 'button';
          for(j=0; j<data.length; j++){
            if(reservationTime[i] === data[j]){
              flag = false;
            }
            // console.log(data[j]);
          }
          if(flag){
            timeButton.className = 'timebox';
            timeButton.disabled = false;
          }else{
            timeButton.className = 'timebox unselectable';
            timeButton.disabled = true;
          }
          timeButton.textContent = reservationTime[i];
          buttonContainer.appendChild(timeButton);
        }
        document.querySelectorAll('.timebox').forEach(button => {
          button.addEventListener('click', () => {
            // 모든 버튼에서 active 클래스 제거
            document.querySelectorAll('.timebox').forEach(btn =>
                btn.classList.remove('selected'));
            // 클릭된 버튼에 active 클래스 추가
            button.classList.add('selected');
            // let buttonTime = button.innerHTML;
            // document.querySelector('#label').textContent = button.value;
            // 선택한 버튼의 값을 가져온다 (시간)
            // console.log(typeof (buttonTime));
            // console.log(buttonTime);
            document.querySelector('#time').value = button.innerHTML;
            let reservationTime = document.querySelector('#time').value;
            console.log(reservationTime);
          });
        });
      },
      error:function (){
        alert("fail!");
      }
    })
})

// 버튼의 자식을 삭제 !
function removeAllChildNods(buttonContainer) {
  while (buttonContainer.hasChildNodes()) {
    buttonContainer.removeChild (buttonContainer.lastChild);
  }
}

function validateForm(){
  let flag = confirm("예약하시겠습니까?");
  if(flag){
    alert("예약되었습니다.")
    return flag;
  }else{
    return flag;
  }
}

