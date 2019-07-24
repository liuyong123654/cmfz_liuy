<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>持明法洲登录页面</title>
    <script>
        function formSubmit(){
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/login",
                type:"post",
                datatype:"json",
                data:$("#loginForm").serialize(),
                success:function(data){
                    if (data.code == '200'){
                        location.href="${pageContext.request.contextPath}/jsp/main.jsp"
                    }else{
                        $("#message").html(data.message)
                    }
                }
            })
        }
    </script>
</head>
<body>
<form id="loginForm" action="javascript:void(0)">
    <div class="form-group">
        <label for="exampleInputUsername">username</label>
        <input type="text" name="username" class="form-control" id="exampleInputUsername">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <input size="4" maxlength="4" name="code1"/>
    <img src="${pageContext.request.contextPath}/admin/createImg"/><br/>
    <button class="btn btn-default" onclick="formSubmit()" >Submit</button>
    <span id="message" style="color:red"></span>
</form>
</body>
</html>
