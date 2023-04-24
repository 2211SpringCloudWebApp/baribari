function stringJoin(){
    var phone = $('#phone1').val() + '-' + $('#phone2').val() + '-' + $('#phone3').val();
    var email = $('#email-1').val() + '@' + $('#email-2').val();
    $('#userPhoneNumber').val(phone);
    $('#userEmail').val(email);
}

window.onload = function(){

    // 폰 번호 인풋창에 넣어줌
    var phoneNumber = $('#userPhoneNumber').val();
    var phone1 = phoneNumber.substring(0,3);
    var phone2 = phoneNumber.substring(4,8);
    var phone3 = phoneNumber.substring(9,13);
    $('#phone1').val(phone1).prop("selected",true);
    $('#phone2').val(phone2);
    $('#phone3').val(phone3);

    // 이메일 인풋창에 넣어줌
    let email_split = $('#userEmail').val().split('@');
    $('#email-1').val(email_split[0]);
    $('#email-2').val(email_split[1]);
}

$('.submit-btn').addEventListener('submit', function(event){
    stringJoin();
});


// 닉네임 정규표현식 및 유효성 검사
$('#nickName-input').focusout(function(){
    const regex = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
    if(regex.test($('#nickName-input').val())){
        $.ajax({
            url:'/ajaxNickNameCheck',
            type:'get',
            dataType: 'json',
            data:{
                'nickName' : $('#nickName-input').val()
            },
            success: function(data){
                console.log(data);
                if(data === '중복'){
                    $('.nickName-check-success').css('display','none');
                    $('.nickName-check-error').css('display','none');
                    $('.nickName-check-same').css('display','block');
                }else  {
                    $('.nickName-check-success').css('display','block');
                    $('.nickName-check-error').css('display','none');
                    $('.nickName-check-same').css('display','none');
                }
            },
            error:function(data){
                console.log('서버에러 : ',data );
            }
        });
    }else {
        $('.nickName-check-success').css('display','none');
        $('.nickName-check-error').css('display','block');
        $('.nickName-check-same').css('display','none');
    }
});
