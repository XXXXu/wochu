<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wochu.enity.Goods"%>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>主页</title>
    <link href="${pageContext.request.contextPath}/res/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/css/classify.css" rel="stylesheet" />
    <%	
    	List<Goods> goodsList = (List<Goods>)request.getAttribute("goodsList");
     %> 
     
</head>
<body>
	<%@ include file="head.jsp"%>
	<div class="classify-view">
		<div class="nav-classify">
        <i><a href="${pageContext.request.contextPath}/User.jsp">首页</a></i> ><i></i>
    	</div>
		<div class="cgoods-item">
    		<ul>
    		<% for(int i = 0;i<goodsList.size();i++){ %>
    		 <li>
                <a href="${pageContext.request.contextPath}/Goods?<%="method=goodsDetail&goodId=" +  goodsList.get(i).getGoodsId() %>" target="_blank">
                     <img alt="" src="<%="http://115.159.83.29:8888/" + goodsList.get(i).getGoodsImg() + "1.jpg" %>">
                </a>
    			<div class="goods-name"><%=goodsList.get(i).getGoodsName() %></div>
    			<div class="price-cart">
                    <div class="price-ctl">
                        <div class="market-price">￥<%=goodsList.get(i).getGoodsOriginalPrice() != null ?  goodsList.get(i).getGoodsOriginalPrice() : ""%></div>
                        <div class="price">￥<%=goodsList.get(i).getGoodsPresentPrice() %></div>
                    </div>
                    <div class="addCart handleCss" onclick="addToCart(<%=goodsList.get(i).getGoodsId()%>,this);">
                        <img src="http://115.159.83.29:8888/addcart.png">
                    </div>
                </div>
    		</li>
    		<%} %>               
		 	</ul>
		</div>
	</div>
	<%@ include file="bottom.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	
	<script>
		layui.define(['layer'], function(){
		  var layer = layui.layer;
		});  
		function addToCart(id, obj) {
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=addGoods&goodsId='+id,
				success: function(data, status) {
					/* layui.define(['layer'], function(){
						 var layer = layui.layer;
						 layer.msg('成功添加至购物车！', {time: 1000,offset: ['50%', '50%']});  
					}); */
					/* layer.msg('成功添加至购物车！', {time: 4000}); */
					layer.tips('成功添加至购物车！',$(obj));  
				},
				error: function() {
					alert(XMLHttpRequest.status);
					layer.msg('添加错误！', {time: 1000,offset: ['50%', '50%']});  
				}
			});
		}
	</script>
</body>
</html>