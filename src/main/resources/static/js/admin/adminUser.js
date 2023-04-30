$(document).ready(function () {
    $('#myTable').DataTable({
            "info": false,
            "ordering": false,
            "autoWidth": false,
            "language": {
                "emptyTable": "회원이 없습니다.",
                url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/ko.json'
            }

        }
    );
})

function userLock(userNo){
    $.ajax({
        url:'/userBlock',
        type:'post',
        data:{
            'userNo' : userNo
        },
        success:function(data){
            if(data == '성공'){
                alert("작업 완료");
                location.href = location.href;
            }else {
                alert("작업 실패");
                location.href = location.href;
            }
        },
        error:function (data){
            console.log(data);
        }
    })
}

function userUnLock(userNo){
    $.ajax({
        url:'/userUnBlock',
        type:'post',
        data:{
            'userNo' : userNo
        },
        success:function(data){
            if(data == '성공'){
                alert("작업 완료");
                location.href = location.href;
            }else {
                alert("작업 실패");
                location.href = location.href;
            }
        },
        error:function (data){
            console.log(data);
        }
    })
}