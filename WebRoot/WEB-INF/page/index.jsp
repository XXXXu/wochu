<%@ page import="java.util.List" %>
<%@ page import="com.wochu.dao.CategoryDao"%>
<%@ page import="com.wochu.dao.impl.GoodsDaoImpl"%>
<%@ page import="com.wochu.enity.Goods"%>
<%@ page import="com.wochu.enity.Category"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    
   List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
   List< List<Goods> > goodsList = (List< List<Goods> >)request.getAttribute("goodsList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link href="${pageContext.request.contextPath}/res/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/css/homePage.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/css/login.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/layui/css/layui.css" rel="stylesheet" />
  </head>
  <%@ include file="head.jsp"%>
  <body>
 	<div id="ad-slider" >
		<div class="bd">
			<ul>
				<li
					style="height:500px;background:url(http://115.159.83.29:8888/pcLunbotu.jpg);background-position:center top;background-size:cover;">
					<a href="http://www.wochu.cn/Member/Register" target="_blank">
				</a></li>
			</ul>
		</div>
		<div class="hd">
			<div class="hd-nav">
				<ul>
					<li class="on"></li>
				</ul>
			</div>
		</div>
	</div>
    
	<div class="ad-position">
		<ul class="ad-item">
			<li><a href="" target="_blank"><img
					src="http://115.159.83.29:8888/suancaiyu.jpg" />
			</a></li>
			<li><a href="" target="_blank"><img
					src="http://115.159.83.29:8888/suantaichaorousi.jpg" />
			</a></li>
			<li><a href="" target="_blank"><img
					src="http://115.159.83.29:8888/xiqinyaoguo.jpg" />
			</a></li>
			<li><a href="" target="_blank"><img
					src="http://115.159.83.29:8888/longgudunhaidaitang.jpg" />
			</a></li>
		</ul>
	</div>
	<div class="tj-goods">
		<% 
			if(categoryList != null) {
			for(int i = 0;i<categoryList.size();i++){
				
			%>
		<div class="tj-items">
			<p class="tj-tile">
				<a>				
				<img src="<%="http://115.159.83.29:8888/" + categoryList.get(i).getExplain() + "_title.jpg" %>" />
				</a>
			</p>
			<div class="goods-list">
				<div class="lft-goods">
					<%int categoryId = i+1;%>
					<a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=" +  categoryId %>">					
					<img src="<%="http://115.159.83.29:8888/" + categoryList.get(i).getExplain() + ".jpg" %>" />
					</a>
					<p>
						<a href="${pageContext.request.contextPath}/Goods?<%="method=goodsClassify&categoryId=" +  categoryId %>">更多>></a>
					</p>
				</div>
				<div class="rt-goods">
					<ul>
						<%
						if(goodsList != null) {
						for(int j = 0;j<8;j++){ %>
							<li><a href="${pageContext.request.contextPath}/Goods?<%="method=goodsDetail&goodId=" +  goodsList.get(i).get(j).getGoodsId() %>" target="right">					
							<img src="<%="http://115.159.83.29:8888/" + categoryList.get(i).getExplain() + "_" + goodsList.get(i).get(j).getGoodsImg() + ".jpg"%>">
							</a>
							<div class="goods-name">
								<a href="${pageContext.request.contextPath}/Goods?<%="method=goodsDetail&goodId=" +  goodsList.get(i).get(j).getGoodsId() %>" target="_blank"><%=goodsList.get(i).get(j).getGoodsName()%></a>
							</div>
							<div class="price-cart">
								<div class="price-ctl">
									<div class="market-price">
										￥<%=goodsList.get(i).get(j).getGoodsOriginalPrice()!=null?goodsList.get(i).get(j).getGoodsOriginalPrice().toString():""%>
									</div>
									<div class="price">
										￥<%=goodsList.get(i).get(j).getGoodsPresentPrice()%></div>
								</div>
								<div class="addCart handleCss"
									onclick="addToCart(<%=goodsList.get(i).get(j).getGoodsId()%>,this);">
									<img src="http://115.159.83.29:8888/addcart.png">
								</div>
							</div></li>	
						<%} }%>
					</ul>
				</div>
			</div>
		</div>	
			<%} 
				}%>		
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
					//layer.tips('成功添加至购物车！',$(obj));  
					layer.alert("成功添加至购物车！",{
						closeBtn:1,
						anim:5,
						offset:['45%','45%']
					});
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