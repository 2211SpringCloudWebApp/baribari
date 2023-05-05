function withdraw(userNo){
    if(confirm("탈퇴하시겠습니까?")){
        $.ajax({
            url:'/withdrawUser',
            type:'post',
            data:{
                'userNo':userNo
            },
            success:function(){
                alert("잘가요 ㅠㅠ");
                location.href = '/logout';
            },
            error: function(){
                alert("회원탈퇴 실패 \n 다시 시도 해주세요.");
            }
        });
    }
}