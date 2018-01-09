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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="__H-UI__/lib/html5shiv.js"></script>
    <script type="text/javascript" src="__H-UI__/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/page.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>产品列表</title>
</head>
<body>
<div   style="margin-top: 15px" class="page-container">
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="60">序号</th>
                <th width="180">视频名称</th>
                <th width="100">视频大小</th>
                <th>视频后缀</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
              <c:forEach var="vo" items="${product.videoList}" varStatus="status">
		            <tr class="text-c">
	                	 <td>${status.index+1}</td>
		             	 <td> ${vo.pvname} </td>
		             	 <td> <fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB </td>
               			<td> ${vo.suffix}  </td>
               			<td><a title="删除" href="/DascomBack/delchoiceResVidio?id=${vo.pvid }"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a> </td>
		            </tr>
       		  </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript">
 	
</script>
</body>
</html>
