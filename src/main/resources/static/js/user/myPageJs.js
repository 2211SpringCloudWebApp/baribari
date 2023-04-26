window.onload = function(){
    var point = ($('.userLevelPoint').text() /  $('.levelMaxPoint').text()) * 100;
    point = Math.floor(point);
    $('.userMyPageData-bar').width(point+'%');

}
function MonthSearch(month){
    const nowDate = new Date();
    const agoDate = new Date();
    agoDate.setMonth(nowDate.getMonth()-month);
    let endDate = nowDate.getUTCFullYear() + '-' + leftPad(nowDate.getMonth()) + '-' + leftPad(nowDate.getDate()-1);
    let startDate = agoDate.getUTCFullYear() + '-' + leftPad(agoDate.getMonth()) + '-' + leftPad(agoDate.getDate()-1);
    location.href='/myPageUser/orderLogistic?endDate='+endDate+'&startDate='+startDate;
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