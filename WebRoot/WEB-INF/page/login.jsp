<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/30
  Time: 14:35
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
    <div class="login-view">
        <div class="login-label">
            登录
        </div>
        <form id = "loginForm" action="${pageContext.request.contextPath}/userLogin" method="post">
            <div class="login-form">
                <div class="form-item">
                    <label>用户名:</label><input style="margin-right:70px;" type="text" name="username" id="username" value="" placeholder="请输入你的用户名" maxlength="11" autocomplete="off" />
                </div>
                <div class="form-item">
                    <label>密码:</label><input style="margin-right:70px;" type="password" name="password" id="password" value="" placeholder="请输入密码" autocomplete="off"/>
                </div>
                <div class="form-item2">
                    <div class="check-saveA">
                        <img class="check-img handleCss" src="http://115.159.83.29:8888/unchecked.png" />记住账号
                    </div>
                </div>
                <div class="register-error">

                </div>
                <div class="login-btn handleCss" onclick="doLogin()">
                    登  录
                </div>
                <div class="goResetPwd handleCss">忘记密码？</div>
            </div>
        </form>
    </div>
</div>
<%@ include file="bottom.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.js"></script>
<script>
	layui.define(['layer'], function(exports){
	  var layer = layui.layer
	  exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
	});   
	function doLogin() {
		var userName = $('#username').val();
		var passWord = $('#password').val();
		if(userName == "") {
			layer.tips('用户名不能为空', '#username');
		}else if(passWord == ""){
			layer.tips('密码不能为空', '#password');
		}else {
			$.ajax({
				type:"post",
				dataType:"json",
				url:'${pageContext.request.contextPath}/userLogin',
				data:{"userName":userName, "passWord":passWord},
				success:function(data) {
					if(data.status == "success") {
						window.parent.location.href = data.url;
					}else if(data.status == "fail"){
						layer.msg(data.message,{time:2500, offset:['47%','75%']});
						
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status);
					layer.msg('登陆错误！', {time: 1000,offset: ['50%', '50%']});  
				}
			});
		}
	}
</script>
