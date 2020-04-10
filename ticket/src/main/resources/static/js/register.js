function checkUsername() {
    $.post({
        url:"/register",
        data:{"name":$("#username").val()},
        success:function (data) {
            if(data.toString()=='√' ){
                $('#nameInfo').css("color","green");
            }
            else {
                $('#nameInfo').css("color","red");
            }
            $('#nameInfo').html(data);

        }
    })
}
function isCardNo() {
    var pattern = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
    // console.log($("#identityNum").val());
    // console.log(pattern.test($("#identityNum")));
    if (pattern.test($("#identityNum").val())){
        $('#identityInfo').css("color","green");
        data="√";
    }else {
        $('#identityInfo').css("color","red");
        data="请输入正确身份证号";
    }
    $('#identityInfo').html(data);
}
function checkPassword() {
    var pattern = /^[0-9]{1,20}$/;
    // console.log($("#password").val());
    // console.log(pattern.test($("#password")));
    if (pattern.test($("#password").val())){

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
function checkemail() {
    var pattern = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    console.log($("#email").val());
    console.log(pattern.test($("#email")));
    if (pattern.test($("#email").val())){
        $('#emailInfo').css("color","green");
        data="√";
    }else {
        $('#emailInfo').css("color","red");
        data="请输入正确格式的邮箱";
    }
    $('#emailInfo').html(data);
}
function checkphone() {
    var pattern = /^[1]([3-9])[0-9]{9}$/;
    console.log($("#phone").val());
    console.log(pattern.test($("#phone")));
    if (pattern.test($("#phone").val())){
        $('#phoneInfo').css("color","green");
        data="√";
    }else {
        $('#phoneInfo').css("color","red");
        data="请输入正确格式的手机号";
    }
    $('#phoneInfo').html(data);
}



