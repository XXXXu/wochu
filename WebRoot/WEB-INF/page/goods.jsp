<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wochu.dao.GoodsDao"%>
<%@ page import="com.wochu.dao.impl.GoodsDaoImpl"%>
<%@ page import="com.wochu.enity.Goods"%>
<html>
<head>
    <title>主页</title>
    <link href="${pageContext.request.contextPath}/res/css/common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/res/css/detail.css" rel="stylesheet" /> 
	<script src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath}/res/js/zzsc.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
</head>
<body>
<%
	Goods good = (Goods)request.getAttribute("good");
	
 %>
 <%@ include file="head.jsp"%>
<div class="detail-view">
    <div class="nav-top-detail">
        <i><a href="${pageContext.request.contextPath}/User.jsp">首页</a></i> >
        <i><%=good.getGoodsName() %></i>
    </div>
    <div class="details">
        <div class="goods-img" id = "fangdajing">
            <div class="main-img">           
                <img id="largeImg" src="<%="http://115.159.83.29:8888/" + good.getGoodsImg() + "1.jpg" %>"/>
            </div>     
            <ul class="pic-thumb">
            	<%for(int i = 1; i<=good.getGoodsImgnum();i++){ %>
                <li class="on">                
                	<img src="<%="http://115.159.83.29:8888/" + good.getGoodsImg() + i + ".jpg" %>"
                	data-bigimg="<%="http://115.159.83.29:8888/" + good.getGoodsImg() + i + ".jpg" %>"
                	>
                </li>
                <%} %>
            </ul>
        </div>
        <div class="goods-info">
            <div class="good-name"><%=good.getGoodsName() %></div>
            <div class="good-desc">
                <%=good.getGoodsIntroduce() != null ?  good.getGoodsIntroduce() : ""%>
            </div>
            <div class="price-view">
                <p>价格</p>
                <div class="price">
                    	￥<span><%=good.getGoodsPresentPrice() %></span>
                 <span class="market-price">
					￥<%=good.getGoodsOriginalPrice() != null ?  good.getGoodsOriginalPrice() : "" %></span>
                </div>
                <div class="QR-img">
                    <img alt="扫码下载" src="http://115.159.83.29:8888/downapp_QR.png">
                </div>
                <div class="scan-remind">扫码购买</div>
            </div>
            <div class="count-view">
                <p>数量</p>
                <div class="show-lft-cart">
                    <a class="minus num-action" onclick="dismunis(<%=good.getGoodsId() %>, this)">
                        <img src="http://115.159.83.29:8888/minus.png">
                    </a>
                    <input id="addCartNumber" name="addCartNumber" value="1" type="text" class="addCartNumber" readonly="readonly">
                    <a class="plus num-action" onclick="plus(<%=good.getGoodsId() %>, this)">
                        <img src="http://115.159.83.29:8888/plus.png">
                    </a>
                </div>
            </div>
            <div class="addToCart handleCss" onclick="addToCart(<%=good.getGoodsId()%>,this);">加入购物车</div>
            <div class="storage-condition">
                <ul>
                    <li>
                        <label>产地</label><%=good.getGoodsOrigin() != null ?  good.getGoodsOrigin() : ""%>
                    </li>
                    <li>
                        <label>保质期</label><%=good.getGoodsShelflife() != null ?  good.getGoodsShelflife() : ""%>
                    </li>
                    <li>
                        <label>规格</label><%=good.getGoodsSpecifications() != null ?  good.getGoodsSpecifications() : ""%>
                    </li>
                    <li>
                        <label>储存条件</label><%=good.getGoodsStorageCondition() != null ?  good.getGoodsStorageCondition() : ""%>
                    </li>
                </ul>
            </div>
        </div>
    </div>
	<div class="bt-content">
		<div class="detail-desc">
            <div class="detail-comment">
                <div class="nav-detail current-nav">商品介绍</div>
            </div>
            <div class="detail-content">
                <p style="font-family: Arial, Verdana, sans-serif; font-size: 12px; white-space: normal;">
                    <span style="font-family: 'lucida Grande', Verdana; font-size: 14px;"></span>
                </p>
                <p style="font-family: Arial, Verdana, sans-serif; font-size: 12px; white-space: normal;">
                   <p>                   
                   <img src="<%="http://115.159.83.29:8888/" + good.getGoodsImg() + "_detail.jpg" %>" /></p>
                </p>
            </div>       
        </div>
	</div>
</div>
<%@ include file="bottom.jsp"%>
<script type="text/javascript">
	layui.define(['layer'], function(){
		  var layer = layui.layer;
		});
	function addToCart(id, obj) {
			var count = $('#addCartNumber').val();
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/goodsCart.jsp?method=addGoods&goodsId='+id+'&num='+count,
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
		function plus(id, obj) {
			var count = parseInt($(obj).prev().val());
			$(obj).prev().val( count+1 );
		}
		function dismunis(id, obj) {
			if(parseInt($(obj).next().val()) == 1) {
				return false;
			}
			var count = parseInt($(obj).next().val());
			$(obj).next().val( count-1 );
		}
</script>
</body>
</html>