$(document).ready(function () {
    $('#myTable').DataTable({
            "searching": false,
            "lengthChange": false,
            "info": false,
            "ordering": false,
            "autoWidth": false,
            "language": {
                "emptyTable": "주문내역이 없어요ㅜㅜ",
                url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/ko.json'
            }

        }
    );
})
function MonthSearch(month){
    const nowDate = new Date();
    const agoDate = new Date();
    agoDate.setMonth(nowDate.getMonth()-month);
    let endDate = nowDate.getUTCFullYear() + '-' + leftPad(nowDate.getMonth()) + '-' + leftPad(nowDate.getDate()-1);
    let startDate = agoDate.getUTCFullYear() + '-' + leftPad(agoDate.getMonth()) + '-' + leftPad(agoDate.getDate()-1);
    location.href='/myPageUser/orderLogistic?endDate='+endDate+'&startDate='+startDate;
}
function inputBySearch(){
    location.href='/myPageUser/orderLogistic?endDate=' +$('#edate').val() + '&startDate=' +  $('#sdate').val();
}

window.onload = function () {
    // 조회 input창에 날짜 입력
    let now2Date = new Date();
    let date1 = now2Date.getUTCFullYear() + '-' + leftPad(now2Date.getMonth()-1) + '-' + leftPad(now2Date.getDate() - 1);
    let date2 = now2Date.getUTCFullYear() + '-' + leftPad(now2Date.getMonth()) + '-' + leftPad(now2Date.getDate());
    $('#sdate').val(date1);
    $('#edate').val(date2);
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
    value += 1;
    if (value < 10) {
        value = "0" + value;
        return value;
    }
    return value;
}

