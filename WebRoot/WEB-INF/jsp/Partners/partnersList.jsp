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
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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
    <title>公司列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 合作伙伴 <span class="c-gray en">&gt;</span> 公司列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
        <form action="cloudprintUserListByKey" method="post">
        	<input type="hidden" value="1" name="page">
            <input type="text" class="input-text" style="width:250px" placeholder="输入公司名称" id="" name="key">
            <button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜名称</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  </span> 
     <span class="r">共有数据：<strong>${pageBean.totalCount}</strong> 条</span> </div>
    <table class="table table-border table-bordered table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="9">公司列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">序列号</th>
            <th width="150">公司名称</th>
            <th width="90">法定名称</th>
            <th width="150">公司级别</th>
            <th width="150">公司权限</th>
            <th width="130">公司状态</th>
            <th width="100">加入时间</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${pageBean.list}" varStatus="status">
        <tr class="text-c">
            <td><input type="checkbox" value="${vo.id}" name="batch"></td>
            <td>${((pageBean.page - 1) * pageBean.limit+status.index+1)}</td>
            <td>${vo.companyName}</td>
            <td>${vo.legalName}</td>
            <td>等级${vo.rank}</td>
            <td>${vo.permission}星级</td>
            <td>${vo.status}</td>
            <td>${vo.registerTime} </td>
            <td class="td-manage">      
                <a title="详情" href="javascript:;" onclick="admin_info('公司详情','/DascomBack/partnersInfo','${vo.id}','1300','600')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe665;</i></a>
                <a title="编辑" href="javascript:;" onclick="admin_edit('公司编辑','/DascomBack/partnersEdit','${vo.id}','1300','600')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                <a title="删除" href="javascript:;" onclick="admin_del(this,'${vo.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="page">
 
         <!-- 上一页 -->
           <c:choose>
			    <c:when test="${pageBean.page != 1 && (keyword =='' )}">
			       <span ><a href="<%=basePath %>/partnersList?page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    <c:when test="${pageBean.page != 1 && (keyword !='')}">
			       <span ><a href="<%=basePath %>/partnersByKey?key=${key}&page=${pageBean.page-1}">上一页</a></span>
			    </c:when>
			    <c:otherwise>
			        <span > 上一页</span>
			    </c:otherwise>
			</c:choose>
			
             <!-- 当前页/总页数 -->
              &nbsp;&nbsp;&nbsp;&nbsp;当前第 ${pageBean.page} / ${pageBean.totalPage}页 &nbsp;&nbsp;&nbsp;&nbsp;
       
		<!-- 下一页 -->
			<c:choose>
				<c:when test="${pageBean.page != pageBean.totalPage && (keyword =='' )}">
					<span ><a href="<%=basePath %>/partnersList?page=${pageBean.page+1}">下一页</a></span>
				</c:when>
				<c:when test="${pageBean.page != pageBean.totalPage && (keyword !='')}">
			       <span ><a href="<%=basePath %>/partnersByKey?key=${key}&page=${pageBean.page+1}">下一页 </a></span>
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
<script type="text/javascript" src="<%=basePath %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    /*
     参数解释：
     title	标题
     url		请求的url
     id		需要操作的数据id
     w		弹出层宽度（缺省调默认值）
     h		弹出层高度（缺省调默认值）
     */
     /*管理员-详情*/
    function admin_info(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }
    /*管理员-编辑*/
    function admin_edit(title,url,id,w,h){
        var url=url+'?id='+id;
        layer_show(title,url,w,h);
    }
    /*管理员-删除*/
    function admin_del(obj,id){
        $count=$('.r strong').html();
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '/DascomBack/partnersDel',
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
                	
                },
            });
        });
    }
    /*管理员-批量删除*/
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
        admin_del(arr,id);
    }
    
</script>
</body>
</html>
