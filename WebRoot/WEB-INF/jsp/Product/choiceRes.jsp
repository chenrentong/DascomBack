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
 	<form action="/DascomBack/choiceResByIds" method="post" class="form form-horizontal" id="form-article-add">
   
    <div class="mt-20">
   	    <input type="hidden" value="${type} " name="type"/>
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="30"></th>
                <th width="60">序号</th>
                <th width="180">资源名称</th>
                <th width="100">资源大小</th>
                <th>资源后缀</th>
            </tr>
            </thead>
            <tbody>
                
		<c:forEach var="vo" items="${pageBean.list}" varStatus="status">
            <tr class="text-c">
            	
                <td><input type="checkbox" value="${vo.sid} " name="batch"></td>               
                <td>${((pageBean.page - 1) * pageBean.limit+status.index+1)}</td>
                <td><a href="${vo.spath}" download="${vo.sname}" class="maincolor">${vo.sname}</a></td>
                <td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
                <td>${vo.suffix} </td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
        
        <div  style="margin-top: 15px;margin-left: aotu;"  class="page">
         <!-- 上一页 -->
           <c:choose>
			    <c:when test="${pageBean.page != 1}">
			       <span ><a href="<%=basePath %>/choiceRes?type=${type }&page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    
			    <c:otherwise>
			        <span > 上一页</span>
			    </c:otherwise>
			</c:choose>
			
             <!-- 当前页/总页数 -->
              &nbsp;&nbsp;&nbsp;&nbsp;当前第 ${pageBean.page} / ${pageBean.totalPage}页 &nbsp;&nbsp;&nbsp;&nbsp;
       
		<!-- 下一页 -->
			<c:choose>
				<c:when test="${pageBean.page != pageBean.totalPage}">
					<span ><a href="<%=basePath %>/choiceRes?type=${type}&page=${pageBean.page+1}">下一页</a></span>
				</c:when>
				
				<c:otherwise>
					  <span >下一页 </span>
				</c:otherwise>
			</c:choose>
        </div>
        
        <div style="margin-top: 15px">
	       <div class="form-label col-xs-4 col-sm-2">
	       	 <input  class="btn btn-secondary radius" type="submit" value="单页批量修改" />
	       </div>
        </div>
    </div>
        </form>
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
