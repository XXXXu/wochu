<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/1
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" 
	import="java.util.*, com.wochu.enity.*, com.wochu.dao.CategoryDao,com.wochu.dao.impl.CategoryDaoImpl" language="java" %>
<%
    CategoryDao categoryDao = new CategoryDaoImpl();
	List<Category> categoryParent = (List<Category>)request.getAttribute("category_parent");
	List<Category> categorySon = null;
%>
<html>
<head>
    <title>我厨网</title>
    <link href="${pageContext.request.contextPath}/res/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/css/login.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/layui/css/layui.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/res/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/jquery.SuperSlide.2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/jquery-ui.min.js"></script>    
    
    <script>
        
//        jQuery(".ad-slider").slide({
//            mainCell: ".bd  ul",
//            effect: "left",
//            autoPlay: true,
//            trigger: "mouseover",
//            easing: "swing",
//            delayTime: "700",
//            mouseOverStop: true,
//            pnLoop: true
//        });
//        $(window).resize(function () {
//            window.location.reload();
//        });

//        $('.catalogs-list ').hover(function () {
//            $(this).addClass('current');
//            $(this).find('.sub-item').show();
//        }, function () {
//            $(this).removeClass('current');
//            $(this).find('.sub-item').hide();
//        });
		$(document).ready(function() {
		$(".item li").hover(function() { 
			$(this).find("ul").show(); 
		} , function() { 
			$(this).find("ul").hide(); 
		});
		});
        $(window).scroll(function () {
            var totop = $(this).scrollTop();
            if (totop >= 100) {
                $('.nav-top').addClass('navtop_fixed');
                $(".scroll-search").show();
                $('.catalogs .catalogs-list').hide();
                $('.catalogs').hover(function () {
                    $('.catalogs .catalogs-list').show();
                }, function () {
                    $('.catalogs .catalogs-list').hide();
                });
            } else {
                $(".nav-top").removeClass('navtop_fixed');
                $('.catalogs .catalogs-list').show();
                $(".scroll-search").hide();
                $('.catalogs').hover(function () {
                    $('.catalogs .catalogs-list').show();
                });
            };
        });
    </script>
</head>
<body>
<div class="login-info">
    <div class="login-ctl">
        <ul class="login-rt">
            <li>
                <div class="kf-phone"><span class="glyphicon glyphicon-user"></span>4008-527-957</div>
            </li>
            <li class="wx">
                <div class="tit">
                    <a href="javascript:void(0)">
                        <span class="glyphicon glyphicon-qrcode"></span>我厨微信
                        <div class="con">
                            <img src="http://115.159.83.29:8888/wx_QR.png" alt="扫码关注我厨" />
                        </div>
                    </a>
                </div>
            </li>
            <li class="mobile">
                <div class="tit">
                    <a href="javascript:void(0)">
                        <span class="glyphicon glyphicon-phone"></span>我厨APP
                        <div class="con">
                            <img src="http://115.159.83.29:8888/downapp_QR.png" alt="扫码下App" />
                        </div>
                    </a>
                </div>
            </li>
            <li class="psfw">
                仅送上海
            </li>
            <li class="logout">
                <a>退出账户</a>
            </li>
            <li class="personal-center">
                <a href="">个人中心</a>
            </li>
            <li class="register">
            	<%
	            	User user = (User)request.getSession().getAttribute("user");
	            	if(user != null) {
            	%>
            			<a href="${pageContext.request.contextPath}/User.jsp?method=outLogin">退出登录</a>
            			<span>用户: <%=user.getUserName()%></span>
            	<%
            		} else{
            	%>
            			<a href="${pageContext.request.contextPath}/userLogin?method=pageChange" >[登录]</a>
		                <a href="${pageContext.request.contextPath}/User.jsp?method=registerPage" >[注册有好礼]</a>
            	<%
            		} 
            	%>
            </li>
        </ul>
    </div>
</div>
<div class="header-ctl">
    <ul class="search-ctl">
        <li class="wlogo"><a href="/"><img src="http://115.159.83.29:8888/wlogo.png" /></a></li>
        <li>
            <div class="search-view">
                <input class="searchId" placeholder="搜索" id="keyWord" onkeydown="" />
                <div class="glyphicon glyphicon-search" onclick="">
                </div>
            </div>
        </li>
    </ul>
</div>
<div class="nav-top">
    <div class="nav-ctl">
        <div class="catalogs" >
            <div class="catalogs-title"><a href="${pageContext.request.contextPath}/Goods?<%="method=allGoods" %>">全部商品<s></s></a></div>
            <div class="catalogs-list">
                <ul class="item">
                <%	if(categoryParent != null) {
                	for(int i = 0;i<categoryParent.size();i++){ %>
                	<li class = "current">
                        <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsByParentId&parentCategoryId=" +  categoryParent.get(i).getCategoryId() %>" class="uncheckicon"><i><img src="<%="http://115.159.83.29:8888/" + categoryParent.get(i).getExplain() + "1.png" %>" /></i><%=categoryParent.get(i).getCategoryName() %></a>
                        <a style="display:none;" href="${pageContext.request.contextPath}/Goods?<%="method=goodsByParentId&parentCategoryId=" +  categoryParent.get(i).getCategoryId() %>s" class="w_checkicon"><i><img src="<%="http://115.159.83.29:8888/" + categoryParent.get(i).getExplain() + "2.png" %>" /></i><%=categoryParent.get(i).getCategoryName() %></a>
                		<ul class="item-sub" style="display:none;">
                            <li>
                                <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsByParentId&parentCategoryId=" +  categoryParent.get(i).getCategoryId() %>">所有商品</a>
                            </li>
                            <% categorySon = categoryDao.selectAllSonByParentId(categoryParent.get(i).getCategoryId());%>
                            <% 	
                            if(categorySon != null) {
                            	for(int j = 0;j<categorySon.size();j++){ %>
                             	<li>
                                	<a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=" +  categorySon.get(j).getCategoryId() %>"><%=categorySon.get(j).getCategoryName() %></a>
                            	</li>
                            <%	} 
                             }%> 
                        </ul> 
                     </li>              
                <%	} 
                }%>                  
               </ul>
            </div>
        </div>

        <ul class="nav-page">
            <li>
                <a href="${pageContext.request.contextPath}/User.jsp">首页</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=1" %>">每日必需</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=2" %>">入店必抢</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=3" %>">新品专区</a>
            </li>
        </ul>
        <ul class="cart-view">
            <li class="cart-num">
                <!--<p>60</p>-->
            </li>
            <li class="go-cart">
            <%if(user != null) { %>
                <a href="${pageContext.request.contextPath}/goodsCart.jsp?userId=<%=user.getUserId()%>" ><img src="http://115.159.83.29:8888/pccart.png" /></a>
            <%}else{ %>
            	 <a href="${pageContext.request.contextPath}/goodsCart.jsp" ><img src="http://115.159.83.29:8888/pccart.png" /></a>
            <%} %>
            </li>

        </ul>
    </div>
</div>

