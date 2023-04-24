function removeAddress(addressNo){
    console.log(addressNo);
    $.ajax({
        url:'/ajaxRemoveAddress',
        type:'post',
        data:{
            'addressNo' : addressNo
        },
        success:function(){
            alert("삭제했어요!");
            location.replace(location.href);
        },
        error:function(data){
            console.log(data);
        }
    })
}
function stringJoin(){
    var phone = $('#phone1-address').val() + '-' + $('#phone2-address').val() + '-' + $('#phone3-address').val();
    $('#address-phone').val(phone);
}
function findAddress(){
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            $('.address-zip').val(data.zonecode);
            if(data.jibunAddress == ""){
                $('.address-jibun').val(data.autoJibunAddress + " " + data.buildingName);
            }else {
                $('.address-jibun').val(data.jibunAddress + " " + data.buildingName);
            }
        }
    }).open();
}

function modalActive(){
    $('.address-add-modal').addClass('modal-active');
    $('.modal-back').addClass('modal-back-active');
    $('.modal-close-btn').addClass('modal-close-btn-active');
}

function modalRemove(){
    $('.address-add-modal').removeClass('modal-active');
    $('.modal-back').removeClass('modal-back-active')
    $('.modal-close-btn').removeClass('modal-close-btn-active');
}