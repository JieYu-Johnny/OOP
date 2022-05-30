<%--
  Created by IntelliJ IDEA.
  User: Adiministrator
  Date: 2022/5/29
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <script>
    function onblurFunction(){
      document.getElementById("error_Msg").style.display = 'none';
      document.getElementById("errorMsg").style.display = 'none';
    }
  </script>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Landing...</title>
  <link rel="stylesheet" href="login/style.css">


</head>
<body>

<div class="container">
  <div class="form-warp">
    <form class="sign-in-form" action="/OOP_Final_v2_0/login" id="login"  method="POST" >
      <h2 class="form-title">登录</h2>
      <div id="error_Msg" class="error">${msg}</div>
      <input type ="text" placeholder="用户名" name="login_username" id ="vaild1" onblur="onblurFunction()"/>
      <input type="password" placeholder="密码" name="login_password" id ="vaild2" onblur="onblurFunction()"/>

    <input type="submit" value="立即登录" class="submit-btn" />
<%--      <button form="login" class="submit-btn">立即登录</button>--%>
      <!--        <div class="submit-btn">立即登录</div>-->
    </form>
    <form class="sign-up-form" id="sign" action="/OOP_Final_v2_0/login" method="POST">
      <h2 class="form-title">注册</h2>
      <div id="errorMsg" class="error" >${msg}</div>
      <input type ="text" placeholder="用户名" name="sign_username"  id ="vaild3" onblur="onblurFunction()"/>
      <input type="password" placeholder="密码" name="sign_password" id ="vaild4" onblur="onblurFunction()"/>
      <input type="submit" value="立即注册" class="submit-btn"/>
<%--      <button form="sign" class="submit-btn">立即注册</button>--%>
      <!--        <div class="submit-btn">立即注册</div>-->
    </form>
  </div>
  <div class="desc-warp">
    <div class="desc-warp-item sign-up-desc">
      <div class="content">
        <button id="sign-up-btn">注册</button>
      </div>
      <img src="login/img/log.svg" alt="">
    </div>
    <div class="desc-warp-item sign-in-desc">
      <div class="content">
        <button id="sign-in-btn">登录</button>
      </div>
      <img src="login/img/register.svg" alt="">
    </div>
  </div>
</div>
<script src="login/main.js">
</script>
</body>
</html>
