<%@ page language="java" import="java.util.*, com.wochu.enity.*,java.math.BigDecimal" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map<Goods, Integer> cart = new HashMap<Goods, Integer>();		
	if(request.getSession().getAttribute("user") != null) {
		//用户已登陆
		cart = (Map<Goods, Integer>)request.getAttribute("cart");
	} else {
		cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${pageContext.request.contextPath}/res/css/common.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/res/css/cartPage.css"
	rel="stylesheet" />
</head>

<body>
	<%@ include file="head.jsp"%>
	<div class="cartPage-view">
		<div class="nav-cart">
			<i><a href="${pageContext.request.contextPath}/User.jsp">首页</a>
			</i> &gt; <i><a href="/Cart">我的购物车</a>
			</i>
		</div>
		<div class="address-shippingDate">
			<div class="address-view">
				<div class="address-edit">
					<div class="your-address">您的收货地址</div>
					<div class="edit-address" style="display: none;">
						<a>切换地址</a>
					</div>
				</div>
				<div class="addr-detail">
					<div class="detail-ctl" style="display: none;"></div>
					<div class="add-newAddress">
						<a><img
							src="http://115.159.83.29:8888/add-newAddr.png">
						</a>
					</div>
				</div>
			</div>
			<div class="shippingTime-view">
            <div class="shippingTime-title">您已经选择7-8 ( 周六 )17:30以前配送</div>
            <nav>
                <ul class="date-time"><li class="disable-change handleCss" shippingdate="2017-07-06">今天配送</li><li class="handleCss" shippingdate="2017-07-07">7-7 ( 周五 )</li><li class="on handleCss" shippingdate="2017-07-08">7-8 ( 周六 )</li><li class="handleCss" shippingdate="2017-07-09">7-9 ( 周日 )</li></ul>
                <ul class="period-time"><li class="time-on handleCss" starttime="8" endtime="17">17:30以前</li><li class="handleCss" starttime="8" endtime="11">08:00-11:30</li><li class="handleCss" starttime="15" endtime="17">15:30-17:30</li><li class="handleCss" starttime="17" endtime="20">17:30-20:30</li></ul>
            </nav>
        </div>
		</div>
		<div class="cart-goods-list">
			<div class="goods-list-head">
				<div class="head-check-view"></div>
				<div class="head-img-view"></div>
				<div class="head-goodsName-view">商品</div>
				<div class="head-price-view">单价(元)</div>
				<div class="head-count-view">数量</div>
				<div class="head-marktprice-view">原价(元)</div>
				<div class="head-opration-view">操作</div>
			</div>
			<div class="goods-list-body">
				<ul id="goodsList">
					<% 	
						if(cart != null) {
							Double tempPrice = 0.0;
							int tempCount = 0;
							for(Goods key : cart.keySet()) {
								tempPrice += key.getGoodsPresentPrice().doubleValue()*cart.get(key);
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
						<a href="/Product/Deatail?id=034eca57-126e-47da-a111-f2748e8afce9"><div
								class="head-goodsName-view goods-Name"><%=key.getGoodsName()%></div>
					</a>
					<div class="head-price-view goods-price">￥<%=key.getGoodsPresentPrice() %></div>
						<div class="head-count-view cart-goods-count">
							<div class="show-goods-count">
								<a class="count-minus num-action"
									onclick="dismunis(<%=key.getGoodsId() %>, this, <%=key.getGoodsPresentPrice()%>)"><img
									src="http://115.159.83.29:8888/disminus.png">
								</a>
								<input name="cartGoodsnum" value="<%=cart.get(key) %>" type="text"
									class="cart-goods-num" readonly="readonly">
								<a
									class="count-plus num-action"
									onclick="plus(<%=key.getGoodsId() %>,this, <%=key.getGoodsPresentPrice()%>)"><img
									src="http://115.159.83.29:8888/plus.png">
								</a>
							</div>
						</div>
						<div class="head-marktprice-view goods-marketprice">￥<%=key.getGoodsOriginalPrice()==null ? key.getGoodsPresentPrice():key.getGoodsOriginalPrice() %></div>
						<div class="head-opration-view goods-delete handleCss"
							onclick="deleteGoods(<%=key.getGoodsId()%>, this, <%=key.getGoodsPresentPrice() %>)">删除</div>
					</li>
					<%	
						} 
						String totalPrice;
						if(tempPrice < 1) totalPrice = "0"; 
						else totalPrice = new java.text.DecimalFormat("#.00").format(tempPrice);
					%>
				</ul>
			</div>
		</div>	
		<div class="check-out-view">
			<div class="cudan">全场满99包邮</div>
			<div id="caculate" class="check-out-btn handleCss">去结算</div>
			<div class="goods-totalCount">
				已选择<span id="goodsCount"><%=tempCount %></span>件商品 | 商品金额<span id="totalPrice" class="total-price"><%=totalPrice %></span>
			</div>
		</div>
	
	<%}else{%>
		<div class="check-out-view">
			<div class="cudan">全场满99包邮</div>
			<div id="caculate" class="check-out-btn handleCss">去结算</div>
			<div class="goods-totalCount">
				购物车中暂无商品,快去首页逛逛吧!
			</div>
		</div>
	<%}%>
	</div>
	<%@ include file="bottom.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript">
		layui.define(['layer'], function(){
		  var layer = layui.layer;
		});
		$('#caculate').click(function() {
			$.ajax({  
				type:"POST",
				dataType:"json",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=caculate',
				success: function(data, status) {
					if(data.msg == "success") {
						layer.open({
							type:2,
							title:'结算购物车',
							content: ['${pageContext.request.contextPath}/goodsCart.jsp?method=caculatePage'],
					 		area: ['730px','500px'],
					 		shade: 0.1,
					 		shadeClose: false,//是否点击背景关闭, shade不为0
					 		resize: false,
					 		offset: ['20px', '350px'],
					 		btn:["确定", "取消"],
					 		btnAlign: 'c',//按钮居中
					 		yes: function(index, layero){
					 			$.ajax({
					 				type:"POST",
					 				dataType:"json",
					 				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=deleteCart',
					 				success: function(data, status) {
					 					layer.closeAll();
					 					if(data.msg = "success") {
					 						layer.msg("感谢您的购物,我们会尽快将商品送达",{time: 2500, offset: ['30%', '45%']});
					 						$('#goodsList').find('li').remove();
					 						$('#goodsCount').html(0);
					 						$('#totalPrice').html(0);
					 					}else if(data.msg = "fail") {
					 						layer.msg("结算失败",{time: 2500,offset: ['30%', '45%']});
					 					}
					 				},
					 				error: function() {
					 					layer.msg("结算出错",{time: 2500,offset: ['30%', '45%']});
					 				}
					 			});
					 		},
					 		btn2: function(index, layero){
				     			layer.closeAll();
				     		}
						});
					}else if(data.msg == "fail") {
						window.location.href = '${pageContext.request.contextPath}/userLogin?method=pageChange';
					}
				},
				error: function() {
					alert("错");
				}
			});
		}); 
		function dismunis(id, obj, presentPrice) {
			if(parseInt($(obj).next().val()) == 1) {
				return false;
			}
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=dismunis&goodsId='+id,
				success: function(data, status) {
					if(status == "success") {
						var count = parseInt($(obj).next().val());
						$(obj).next().val( count-1 );
						//总商品数减一
						var totalCount = parseInt($('#goodsCount').html())-1;
						$('#goodsCount').html(totalCount);
						//总价格减现价
						var totalPrice = (parseFloat($('#totalPrice').html()) - presentPrice).toFixed(2);
						$('#totalPrice').html(totalPrice);
					}else {
						layer.msg('操作失败');  
					}
				},
				error: function() {
					alert(XMLHttpRequest.status);
					layer.msg('操作错误！', {time: 1000,offset: ['50%', '50%']});  
				}
			});
		}
		function plus(id, obj, presentPrice) {
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=plus&goodsId='+id,
				success: function(data, status) {
					if(status == "success") {
						var count = parseInt($(obj).prev().val());
						$(obj).prev().val( count+1 );
						//总商品数加一
						var totalCount = parseInt($('#goodsCount').html())+1;
						$('#goodsCount').html(totalCount);
						//总价格加现价
						var totalPrice = (parseFloat($('#totalPrice').html()) + presentPrice).toFixed(2);
						$('#totalPrice').html(totalPrice);
					}else {
						layer.msg('操作失败');  
					}
				},
				error: function() {
					alert(XMLHttpRequest.status);
					layer.msg('操作错误！', {time: 1000,offset: ['50%', '50%']});  
				}
			});
		}
		function deleteGoods(id, obj, presentPrice) {
			var goodsCount = $(obj).prev().prev().children().children().next().val();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=deleteGoods&goodsId='+id,
				success: function(data, status) {
					changeGoodsList(data.cartjson, presentPrice, goodsCount);
				},
				error: function() {
					alert("错");
					layer.msg('操作错误！', {time: 1000,offset: ['50%', '50%']});  
				}
			});
		}
		function changeGoodsList(data, presentPrice, goodsCount) {
			var str = "";
			for(var i = 0; i < data.length; i++) {
				str += "<li><div class='head-check-view i-checked handleCss' onclick='CartPage.selectItem('')></div>"
						  +"<div class='head-img-view shop-img'>"
						  +"<a href=''><img src='http://115.159.83.29:8888/"+data[i].goodsImg+"1.jpg' alt='data[i].goodsName'></a></div>"
						  +"<a href=''><div class='head-goodsName-view goods-Name'>"+data[i].goodsName+"</div></a>"
						  +"<div class='head-price-view goods-price'>￥"+data[i].goodsPresentPrice+"</div>"
						  +"<div class='head-count-view cart-goods-count'><div class='show-goods-count'>"
						  +"<a class='count-minus num-action' onclick='dismunis("+data[i].goodsId+", this,"+data[i].goodsPresentPrice+")'><img src='http://115.159.83.29:8888//res/images/disminus.png'></a>"
						  +"<input name='cartGoodsnum' value="+data[i].goodsCount+" type='text' class='cart-goods-num' readonly='readonly'>"
						  +"<a class='count-plus num-action' onclick='plus("+data[i].goodsId+",this,"+data[i].goodsPresentPrice+")'><img src='http://115.159.83.29:8888//res/images/plus.png'></a></div></div>"
						  +"<div class='head-marktprice-view goods-marketprice'>￥"+data[i].goodsOriginalPrice+"</div>"	
						  +"<div class='head-opration-view goods-delete handleCss' onclick='deleteGoods("+data[i].goodsId+ ", this ,"+data[i].goodsPresentPrice+")'>删除</div></li>";	
				
				
			}
			//重新传入购物车ul内的数据
			document.getElementById("goodsList").innerHTML = str;
			//改变商品数和总价格
			var totalCount = parseInt($('#goodsCount').html()) - goodsCount;
			$('#goodsCount').html(totalCount);
			//计算结果并四舍五入的保留两位小数 
			var totalPrice = (parseFloat($('#totalPrice').html()) - (presentPrice*goodsCount)).toFixed(2);
			$('#totalPrice').html(totalPrice);
		}
	</script>
</body>
</html>
		