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
		<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
			/* window.parent.location.reload(); */
			var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
		</script>
	</body>
</html>