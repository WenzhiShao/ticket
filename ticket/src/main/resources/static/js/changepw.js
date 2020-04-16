function checkPassword() {
    var pattern = /^[0-9]{1,20}$/;
    console.log($("#password").val());
    console.log(pattern.exec($("#password").val()));
    if (!pattern.exec($("#password").val())){

        $('#passwordInfo').css("color","green");
        data="√";
    }else {
        $('#passwordInfo').css("color","red");
        data="请输入正确格式的密码";
    }
    $('#passwordInfo').html(data);
}
function checkRepwd() {

    console.log($("#password").val());
    console.log($("#repassword").val());
    if ( $("#password").val()===$("#repassword").val()){
        $('#repasswordInfo').css("color","green");
        data2="√";
    }else {
        $('#repasswordInfo').css("color","red");
        data2="请再次确认密码";
    }
    $('#repasswordInfo').html(data2);
}