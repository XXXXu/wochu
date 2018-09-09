<%@ page language="java" import="java.util.*,com.wochu.enity.*,java.math.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Map<Goods, Integer> cart = (Map<Goods, Integer>)request.getAttribute("cart");
Double tempPrice = (Double)request.getAttribute("totalPrice");
java.text.DecimalFormat df = new java.text.DecimalFormat("#.00"); 
String totalPrice = df.format(tempPrice);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="min-width: 700px;">
  <head>
  	<link href="${pageContext.request.contextPath}/res/css/common.css"
	rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/res/css/cartPage.css"
	rel="stylesheet" />
	<style type="text/css">
		.goods-list-body ul li {
		    width: 700px;
		</style>
  </head>
  <body style="min-width: 700px;">
    <div class="cart-goods-list" style="width:700px;">
			<div class="goods-list-head" style="width:700px;">
				<div class="head-check-view" style="width:15%;"></div>
				<div class="head-img-view" style="width:15%;"></div>
				<div class="head-goodsName-view" style="width:30%;">商品</div>
				<div class="head-price-view" style="width:27%;">单价(元)</div>
			</div>
			<div class="goods-list-body">
				<ul id="goodsList">
					<% 	
						if(cart != null) {
							int tempCount = 0;
							for(Goods key : cart.keySet()) {
								tempCount += cart.get(key);
					%>
					<li>
						<div class="head-check-view i-checked handleCss" onclick="CartPage.selectItem('e623a4c6-00ae-47d9-8460-61788368b619','519638',1,1,0)"></div>
						<div class="head-img-view shop-img">
							<a
								href=""><img src="<%="http://115.159.83.29:8888/" + key.getGoodsImg() + "1.jpg"%>"
								alt="<%=key.getGoodsName()%>">
							</a>
						</div>
						<a href="/Product/Deatail?id=034eca57-126e-47da-a111-f2748e8afce9">
						<div style="width: 260px;"class="head-goodsName-view goods-Name"><%=key.getGoodsName()%></div>
					</a>
					<div class="head-price-view goods-price">￥<%=key.getGoodsPresentPrice() %></div>
						<div class="head-count-view cart-goods-count">
							<div class="show-goods-count">
								<input name="cartGoodsnum" value="<%=cart.get(key) %>" type="text"
									class="cart-goods-num" readonly="readonly">
							</div>
						</div>
					</li>
					<%	
						}
					%>
				</ul>
			</div>
		</div>	
		<div class="check-out-view" style="width: 650px; margin:0 auto;">
			<div class="goods-totalCount">
				已选择<span id="goodsCount"><%=tempCount %></span>件商品 | 商品金额<span id="totalPrice" class="total-price"><%=totalPrice%></span>
			</div>
		</div>
	
	<%}else{%>
		<div class="check-out-view">
			<div class="goods-totalCount">
				购物车中暂无商品,快去首页逛逛吧!
			</div>
		</div>
	<%}%>
	</div>
  </body>
</html>
