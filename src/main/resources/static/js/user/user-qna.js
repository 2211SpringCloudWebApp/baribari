function btnSelect(YN){
    $('.qna-btn').removeClass('active');
    var tr = $('.'+YN).parents('tr');
    if(YN == 'all'){
        $('.table-qna-tr').css('display','table-row');
        $('#all').addClass('active');
    }else {
        $('#'+YN).addClass('active');
        $('.table-qna-tr').css('display','none');
        tr.css('display','table-row');
    }
}