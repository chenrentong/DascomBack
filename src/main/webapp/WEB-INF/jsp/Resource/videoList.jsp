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
    <script type="text/javascript" src="../../../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../../../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/css/page.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../../../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>权限管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资源管理 <span class="c-gray en">&gt;</span> 视频管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form class="Huiform" method="GET" action="/DascomBack/videoListKey" target="_self">
        	<input type="hidden" name="page" value="1" >
            <input type="text" class="input-text" style="width:250px" placeholder="视频名称" name="key">
            <button type="submit" class="btn btn-success" ><i class="Hui-iconfont">&#xe665;</i> 搜视频</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="classify_add('添加视频','/DascomBack/videoAdd','','480')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加视频</a>
        </span>
        <span class="r">共有数据：<strong>${pageBean.totalCount} </strong> 条</span>
    </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="7">视频列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">序列号</th>
            <th width="200">视频名称</th>
            <th width="150">视频(点击播放)</th>
            <th width="150">视频大小</th>
            <th width="150">视频后缀</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach var="vo" items="${pageBean.list}" varStatus="status">
            <tr class="text-c">
                <td><input type="checkbox" value="${vo.pvid}" name="batch"></td>
                <td>${((pageBean.page - 1) * pageBean.limit+status.index+1)}</td>
                <td>${vo.pvname}</td>
               	<c:if test="${!empty (vo.pvpath)}">
               		 <td><a href="#" onclick="video_play('${vo.pvpath}');"><img height="60" src="image/123.jpg" alt=""></a></td>
               	</c:if>
               	<c:if test="${empty (vo.pvpath)}">
               		 <td><a href="#" onclick="video_play('400');"><img height="60" src="image/123.jpg" alt=""></a></td>
               	</c:if>
               	<td><fmt:formatNumber type="number" value="${vo.size/1024/1024 }" pattern="0.00" maxFractionDigits="2"/>  MB</td>
                <td>${vo.suffix }</td>
                <td>
                    <a title="编辑" href="javascript:;" onclick="classify_edit('视频编辑','/DascomBack/videoEdit','${vo.pvid}','','480')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="classify_del(this,'${vo.pvid}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
        <div class="page">
         	<!-- 上一页 -->
           <c:choose>
			    <c:when test="${pageBean.page != 1 && keyword ==''}">
			       <span ><a href="<%=basePath %>/videoList?page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    <c:when test="${pageBean.page != 1 && keyword !=''}">
			       <span ><a href="<%=basePath %>/videoListKey?key=${key}&page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    <c:otherwise>
			        <span > 上一页</span>
			    </c:otherwise>
			</c:choose>
			
             <!-- 当前页/总页数 -->
              &nbsp;&nbsp;&nbsp;&nbsp;当前第 ${pageBean.page} / ${pageBean.totalPage}页 &nbsp;&nbsp;&nbsp;&nbsp;    
			 <!-- 下一页 -->
			 <c:choose>
				<c:when test="${pageBean.page != pageBean.totalPage && keyword =='' }">
					<span ><a href="<%=basePath %>/videoList?page=${pageBean.page+1}">下一页</a></span>
				</c:when>
				<c:when test="${pageBean.page != pageBean.totalPage && keyword !=''}">
			       <span ><a href="<%=basePath %>/videoListKey?key=${key}&page=${pageBean.page+1}">下一页 </a></span>
			    </c:when>
				<c:otherwise>
					  <span >下一页 </span>
				</c:otherwise>
			 </c:choose>
        </div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basePath %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript">
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
    /*视频-添加*/
    function classify_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*视频-编辑*/
    function classify_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }

    /*视频-删除*/
    function classify_del(obj,id){
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '/DascomBack/videoDel',
                data:{id:id},
                dataType: 'json',
                success: function(data){
                
                layer.msg("删除成功",{icon:1,time:1500});
                var time=setInterval (showTime, 1000);

				function showTime(){ 
					 window.location.reload();
					clearInterval(time);
					} 
                },
                error:function(data) {
					layer.msg("删除失败,有产品绑定视频",{icon:2,time:1500});
             	   var time=setInterval (showTime, 1000);

					function showTime(){ 
						window.location.reload();
						clearInterval(time);
					} 
                },
            });
        });
    }
    /*视频批量删除*/
    function datadel() {
        var id='';
        var arr=[];
        $("input[name='batch']").each(function () {
            var obj=$(this);
            if (obj.get(0).checked) {
                id+=','+obj.val();
                arr.push(obj);
            }
        });
        if (id=='') {
            layer.msg('请选择删除项');
            return false;
        }
        id=id.substring(1);
        classify_del(arr,id)
    }
    //视频播放
    function video_play(url) {
        layer.open({
            type: 2,
            title: false,
            area: ['630px', '360px'],
            shade: 0.8,
            closeBtn: 0,
            shadeClose: true,
            content: url
        });
    }
</script>
</body>
</html>
