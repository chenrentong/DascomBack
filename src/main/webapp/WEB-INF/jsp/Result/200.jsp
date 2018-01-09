<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			* {
				padding: 0;
				margin: 0;
			}
			.system-message {
    			padding: 24px 48px;
			}
			.system-message h1 {
				font-size: 100px;
				font-weight: normal;
				line-height: 120px;
				margin-bottom: 12px;
			}
			.system-message .error {
			    line-height: 1.8em;
			    font-size: 36px;
			}
			.system-message h1 {
				display: block;
				font-size: 6em;
			}
			.system-message .jump {
   				padding-top: 10px;
			}	
		</style>
	</head>

	<body>
		<div class="system-message">
			<h1>:）</h1>
			
			<p class="error">操作成功</p>


			<p class="detail"><span style="color: red"><strong>请按右上角按钮立刻关闭窗口。</strong></span>正在自动跳转......</p>
			<p class="jump" >
				等待时间：
				<b id="wait">3</b>
			</p>
		</div>

		<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script>
			<script type="text/javascript">
		//银行认证成功跳转
		if(time!=null){
			clearInterval(time);
		}
		var time=setInterval (showTime, 1000);
		var second=2;
		
		function showTime(){ 
			if(second==0){
				window.parent.location.reload();
				clearInterval(time);
			}
		$("#wait").html(second);
		second--;
		}
		</script>
	</body>
</html>