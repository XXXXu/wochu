<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/1
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html> 
	<head>
	   <link href="${pageContext.request.contextPath}/res/css/common.css" rel="stylesheet" />
	   <link href="${pageContext.request.contextPath}/res/css/homePage.css" rel="stylesheet" />
	   <link href="${pageContext.request.contextPath}/res/css/login.css" rel="stylesheet" />
		
	</head>
<body>
<%@ include file="head.jsp"%>
<div class="register-view">
    <div class="lft-imgview">
        <a>
            <img src="http://115.159.83.29:8888/ad_login.jpg" />
        </a>
    </div>
    <div class="register-info">
        <div class="register-label">
            注册
        </div>
        <form id = "registerForm" action="${pageContext.request.contextPath}/User.jsp?method=register" method="post">
            <div class="register-form">
                <div class="form-item">
                    <label>用户名</label><input type="text" id="register_name" name="username" value="" placeholder="请输入你的用户名" autocomplete="off" />
                </div>
                <div class="form-item">
                    <label>密码</label><input id="register_password" type="password" name="password" value="" placeholder="请输入密码" autocomplete="off" />
                </div>
                <div class="form-item">
                    <label>确认密码</label><input id="next_register_password" type="password" name="nextpassword" value="" placeholder="请再输入密码" autocomplete="off" />
                </div>
                <div class="form-item1">

                </div>
                <div class="register-btn handleCss" onclick="document.getElementById('registerForm').submit()">
                    注  册
                </div>
                <div class="goLogin"><a href="login.jsp">已有我厨账号，去登录>></a></div>
            </div>
        </form>
    </div>
    
</div>
<%@ include file="bottom.jsp"%>
</body>
</html>