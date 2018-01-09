<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">

    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="__H-UI__/lib/html5shiv.js"></script>
    <script type="text/javascript" src="__H-UI__/lib/respond.min.js"></script>

    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />

    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->
  	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/page.css" />
    <link href="<%=basePath %>/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th >序号</th>
                <th >资源名称</th>
                <th >资源大小</th>
                <th>资源后缀</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:choose>
			    <c:when test="${type == 2 }">			
			        <c:forEach var="vo" items="${product.sfirmwareList}" varStatus="status">
            		<tr class="text-c">
	                	<td>${status.index+1}</td>
	                	<td><a href="${vo.spath}"  class="maincolor">${vo.sname}</a></td>
	                	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
	               		 <td>${vo.suffix} </td>
	               		 <td> <a title="删除" href="/DascomBack/delchoiceRes?id=${vo.sid }&type=${type}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
           			 </tr>
       			 </c:forEach>
			    </c:when>
			    <c:when test="${type == 3 }">
			    ${type == 3 }
			        <c:forEach var="vo" items="${product.stoolList}" varStatus="status">
            		<tr class="text-c">
	                	<td>${status.index+1}</td>
	                	<td><a href="${vo.spath}"  class="maincolor">${vo.sname}</a></td>
	                	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
	               		 <td>${vo.suffix} </td>
	               		 <td> <a title="删除" href="/DascomBack/delchoiceRes?id=${vo.sid }&type=${type}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
           			 </tr>
       			 </c:forEach>
			    </c:when>
			    <c:when test="${type == 4 }">
			        <c:forEach var="vo" items="${product.sguideList}" varStatus="status">
            		<tr class="text-c">
	                	<td>${status.index+1}</td>
	                	<td><a href="${vo.spath}"  class="maincolor">${vo.sname}</a></td>
	                	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
	               		 <td>${vo.suffix} </td>
	               		 <td> <a title="删除" href="/DascomBack/delchoiceRes?id=${vo.sid }&type=${type}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
           			 </tr>
       			 </c:forEach>
			    </c:when>
			    <c:when test="${type == 5 }">
			        <c:forEach var="vo" items="${product.skitList}" varStatus="status">
            		<tr class="text-c">
	                	<td>${status.index+1}</td>
	                	<td><a href="${vo.spath}"  class="maincolor">${vo.sname}</a></td>
	                	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
	               		 <td>${vo.suffix} </td>
	               		 <td> <a title="删除" href="/DascomBack/delchoiceRes?id=${vo.sid }&type=${type}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
           			 </tr>
       			 </c:forEach>
			    </c:when>
			    <c:otherwise>
			    	<c:forEach var="vo" items="${product.sdriveList}" varStatus="status">
            		<tr class="text-c">
	                	<td>${status.index+1}</td>
	                	<td><a href="${vo.spath}"  class="maincolor">${vo.sname}</a></td>
	                	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
	               		 <td>${vo.suffix} </td>
	               		 <td> <a title="删除" href="/DascomBack/delchoiceRes?id=${vo.sid }&type=${type}"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
           			 </tr>
       			 </c:forEach>
			    </c:otherwise>
			</c:choose>
            </tbody>
        </table>
	</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
 	
</script>
</body>
</html>
