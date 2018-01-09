<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 产品管理 <span class="c-gray en">&gt;</span> 产品列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form action="/DascomBack/productListByKeyAndType" method="get">
            <select name="type" class="input-text" style="width:150px;">
                <option value="0">全部分类</option>
                 <c:forEach var="type" items="${typeList}" varStatus="status">
                 	<c:if test="${type.ptid == type1}">
                 		<option  selected="selected" value="${type.ptid} " >${type.ptname}</option>
                 	</c:if>
                 	<c:if test="${type.ptid != type1}">
                 		<option value="${type.ptid} ">${type.ptname}</option>
                 	</c:if>
                </c:forEach>
            </select>
            <input type="hidden" value="1" name="page">
            <input type="text" name="key" placeholder=" 产品名称" style="width:250px" class="input-text">
            <button  class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜产品</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="picture_add('添加产品','/DascomBack/productAdd',950,800)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a></span> <span class="r">共有数据：<strong>${pageBean.totalCount}</strong> 条</span> </div>
    
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr>
            	<th scope="col" colspan="7">产品列表</th>
       		 </tr>
            <tr class="text-c">
                <th width="20"><input name="" type="checkbox" value=""></th>
                <th width="45">序列号</th>
                <th width="80">产品分类</th>
                <th width="100">图片</th>
                <th width="100">产品名称</th>
                <th width="350">产品描述</th>
                <th width="60">操作</th>
            </tr>
            </thead>
            <tbody>
           <c:forEach var="vo" items="${pageBean.list}" varStatus="status">
            <tr class="text-c">
                <td><input name="batch" type="checkbox" value="${vo.pid}"></td>
                <td>${((pageBean.page - 1) * pageBean.limit+status.index+1)}</td>
                <td>${vo.product_Type.ptname}</td>
                <td><a href="javascript:;" class="maincolor" ><img height="60" class="picture-thumb" src="${vo.pimage}"></a></td>
                <td>${vo.pname}</td>
                <td>${vo.pintroduce}</td>
                <td class="td-manage">
                    <a style="text-decoration:none" class="ml-5" onClick="picture_edit('产品编辑','/DascomBack/productEdit','${vo.pid}',950,800)" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,'${vo.pid}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
		<div class="page">
         <!-- 上一页 -->
           <c:choose>
			    <c:when test="${pageBean.page != 1 && (keyword =='' && type1 ==0 )}">
			       <span ><a href="<%=basePath %>/productList?page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    <c:when test="${pageBean.page != 1 && (keyword !='' || type != 0)}">
			       <span ><a href="<%=basePath %>/productListByKeyAndType?key=${key}&page=${pageBean.page-1}&type=${type1}">上一页</a></span>
			    </c:when>
			    <c:otherwise>
			        <span > 上一页</span>
			    </c:otherwise>
			</c:choose>
             <!-- 当前页/总页数 -->
              &nbsp;&nbsp;&nbsp;&nbsp;当前第 ${pageBean.page} / ${pageBean.totalPage}页 &nbsp;&nbsp;&nbsp;&nbsp;
			<!-- 下一页 -->
			<c:choose>
				<c:when test="${pageBean.page != pageBean.totalPage && (keyword =='' && type1 ==0 )}">
					<span ><a href="<%=basePath %>/productList?page=${pageBean.page+1}">下一页</a></span>
				</c:when>
				<c:when test="${pageBean.page != pageBean.totalPage && (keyword !='' || type1 !=0 )}">
			       <span ><a href="<%=basePath %>/productListByKeyAndType?key=${key}&page=${pageBean.page+1}&type=${type1}">下一页 </a></span>
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

<script type="text/javascript">

    /*产品-添加*/
    function picture_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*产品-编辑*/
    function picture_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }

    /*产品-查看*/
    function picture_show(title,url,id){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-删除*/
    function picture_del(obj,id){
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '/DascomBack/productDel',
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
					layer.msg("删除失败",{icon:2,time:1500});
             	   var time=setInterval (showTime, 1000);

					function showTime(){ 
						 window.location.reload();
						clearInterval(time);
					} 
                	/* if(data.responseText==200){
                		alert("删除成功");
                		layer.msg("删除成功",{icon:1,time:1500});
                		window.location.reload();
                		
                	}else{
                	
                		alert("删除失败");
                	} */
                },
            });
        });
    }
    /*分类批量删除*/
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
        picture_del(arr,id)
    }
    
</script>
</body>
</html>
