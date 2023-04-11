    function stringJoin(){
    var phone = $('#phone1').val() + '-' + $('#phone2').val() + '-' + $('#phone3').val();
    var email = $('#email-id').val() + '@' + $('#domain').val();
    $('#phone0').val(phone);
    $('#email').val(email);
}

    window.addEventListener('load', () => {
    const forms = document.getElementsByClassName('validation-form');
    Array.prototype.filter.call(forms, (form) => {
    form.addEventListener('submit',function (event) {
    stringJoin();
    if (form.checkValidity() === false) {
    event.preventDefault();
    event.stopPropagation();
}
    form.classList.add('was-validated');
    $('.id-check-success').css('display','none');
    $('.id-check-same').css('display','none');
    $('.nickName-check-success').css('display','none');
    $('.nickName-check-same').css('display','none');
    $('.pass-check-success').css('display','none');
    $('.re-pass-check-success').css('display','none');
    form.style.margin = '0px';
}, false);
});
}, false);

    // 아이디 중복 검증 및 정규표현식
    $('#id').focusout(function(){
    // 아이디 정규표현식
    const regex = /^[a-z0-9]{4,16}$/;
    if(regex.test($('#id').val())){
    $.ajax({
    url:"ajaxCheckId",
    data:{
    'id':$('#id').val()
},
    type:'get',
    dataType:'json',
    success:function(data){
    if(data === '사용가능'){
    $('.id-check-error').css('display','none');
    $('.id-check-success').css('display','block');
    $('.id-check-same').css('display','none');
}else {
    $('.id-check-error').css('display','none');
    $('.id-check-success').css('display','none');
    $('.id-check-same').css('display','block');
}
},
    error:function(){
    console.log("서버에러");
}
});
}else {
    $('.id-check-same').css('display','none');
    $('.id-check-success').css('display','none');
    $('.id-check-error').css('display','block');
}
});

    // 패스워드 정규표현식
    $('#password').focusout(function(){
    const regex = /^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*()._-]{6,16}$/;
    if(regex.test($('#password').val())){
    $('.pass-check-success').css('display','block');
    $('.pass-check-error').css('display','none');
}else {
    $('.pass-check-error').css('display','block');
    $('.pass-check-success').css('display','none');
}
});

    // 비밀번호 확인 검증
    $('#re-password').focusout(function(){
    const rePass = $('#re-password').val();
    const pass = $('#password').val();
    if(rePass === pass){
    $('.re-pass-check-success').css('display','block');
    $('.re-pass-check-error').css('display','none');
}else {
    $('.re-pass-check-success').css('display','none');
    $('.re-pass-check-error').css('display','block');
}
});

    // 이메일 셀렉트 박스 도메인에 넣기
    $('.domain-selectBox').on("change",function(){
    const inputBox = $('#domain');
    const selectBox = $('.domain-selectBox').val();
    if(selectBox === 'etc'){
    inputBox.val('');
    inputBox.prop('readonly',false);
}else {
    inputBox.val(selectBox);
    inputBox.prop('readonly',true);
}
});

    // 닉네임 정규표현식
    $('#nickname').focusout(function(){

    const regex = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;

    if(regex.test($('#nickname').val())){
    $.ajax({
    url:'ajaxNickNameCheck',
    type:'get',
    dataType: 'json',
    data:{
    'nickName' : $('#nickname').val()
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

