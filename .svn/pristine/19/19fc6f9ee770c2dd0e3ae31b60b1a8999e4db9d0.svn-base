

// $(function () {
//
// });


 function resetPwd() {
     var pass1 = $("#passwd").val();
     var pass2 = $("#password").val();
     if(!pass1||!pass2){
         parent.layer.msg("密码不能为空");
         return;
     }
     if(pass1!=pass2){
         parent.layer.msg("密码不一致！！！");
         return;
     }
     var data = $("#resetPwdForm").serialize();
     $.ajax({
         url:'/biz/userInfo/resetPwd',
         data:data,
         type:'post',
         success:function (data) {
             if (data.code == 0) {
                 debugger
                 parent.layer.msg(data.msg);
             } else {
                 parent.layer.msg(data.msg);
             }
         },
         error:function (request) {
             alert("Connection error");
         }

     });
 }
 function validate() {

 }