<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--_meta 作为公共模版分离出去-->
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
    <link href="<%=basePath %>/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="__H-UI__/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->
     
</head>
<body>
<div class="page-container">
	<form action="/DascomBack/productSumbbitAdd" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data">
         <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">产品视频：</label>
            <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.videoList.size() }个视频</div>
            </div>
        </div>
        <div class="row cl" style="color: gray;margin-left: 105px">
       		    <button onClick="open_res('查看产品视频','choiceResVidioDetail',0,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看视频</button>
            	<button onClick="open_res('选择产品视频','choiceRes',0,800,600,this);" class="btn btn-primary radius" type="button" >选择视频</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">产品驱动：</label>
            <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.sdriveList.size() }个驱动</div>
            </div>
        </div>
         <div class="row cl" style="color: gray;margin-left: 105px">
       		    <button onClick="open_res('查看产品驱动','choiceResDetail',1,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看驱动</button>
            	<button onClick="open_res('选择产品驱动','choiceRes',1,800,600,this);" class="btn btn-primary radius" type="button" >选择驱动</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">产品固件：</label>
             <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.sfirmwareList.size() }个固件
            	
            	</div>
            </div>
        </div>
       <div class="row cl" style="color: gray;margin-left: 105px">
       		  <button onClick="open_res('查看产品固件','choiceResDetail',2,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看固件</button>
            	<button onClick="open_res('选择产品固件','choiceRes',2,800,600,this);" class="btn btn-primary radius" type="button" >选择固件</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">管理工具：</label>
            <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.stoolList.size() }个管理工具
            	
            	</div>
            </div>
        </div>
        <div class="row cl" style="color: gray;margin-left: 105px">
       		   <button onClick="open_res('查看管理工具','choiceResDetail',3,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看工具</button>
            	<button onClick="open_res('选择管理工具','choiceRes',3,800,600,this);" class="btn btn-primary radius" type="button" >选择工具</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">用户指南：</label>
             <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.sguideList.size() }个用户指南
            	
            	</div>
            </div>
        </div>
        <div class="row cl" style="color: gray;margin-left: 105px">
       		    <button onClick="open_res('查看用户指南','choiceResDetail',4,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看指南</button>
            	<button onClick="open_res('选择用户指南','choiceRes',4,800,600,this);" class="btn btn-primary radius" type="button" >选择指南</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">二次开发包：</label>
            <div class="formControls col-xs-8 col-sm-5">
            	<div style="color: gray">绑定了${product.skitList.size() }个二次开发包
            	
            	</div>
            </div>
        </div>
       <div class="row cl" style="color: gray;margin-left: 105px">
       		<button onClick="open_res('查看二次开发包','choiceResDetail',5,800,600,this);" class="btn btn-primary radius" type="button" style="margin-left: 50px">查看开发包</button>
            <button onClick="open_res('选择二次开发包','choiceRes',5,800,600,this);" class="btn btn-primary radius" type="button" >选择开发包</button>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"  placeholder="请输入产品名称" id="name" name="name">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品分类：</label>
             <div class="formControls col-xs-8 col-sm-5">
            <select name="type" class="input-text" style="width:150px;">
                 <c:forEach var="type" items="${typeList}" varStatus="status">
                 	<c:if test="${type.ptid == type1}">
                 		<option  selected="selected" value="${type.ptid} " >${type.ptname}</option>
                 	</c:if>
                 	<c:if test="${type.ptid != type1}">
                 		<option value="${type.ptid} ">${type.ptname}</option>
                 	</c:if>
                </c:forEach>
            </select>
			</div>
        </div>
        
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品图片：</label>
            <!--<input type="file" name="file">-->
            <div class="formControls col-xs-8 col-sm-9">
                <span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly placeholder="请添加图片！" style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览图片</a>
				<input type="file" multiple name="file-2" class="input-file">
				</span>
            </div>
        </div>
       
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea cols="100" rows="10"  id="description" name="description" class="ckeditor" placeholder="请输入产品描述"></textarea>
                
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="article_save();" class="btn btn-secondary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 提交</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
<script type="text/javascript" src="<%=basePath %>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    //选择资源
     function open_res(title,url,id,w,h){
        var url=url+'?type='+id+'&page=1';
        layer_show(title,url,w,h);
    }
    //数据检验
     $(function(){
        $("#form-member-add").validate({
            rules:{
                name:{
                    required:true,
                    minlength:2
                },
                description:{
                	required:true,
                },
                uploadfile:{
                	required:true,
                },
                type:{
                	required:true,
                }
            }
        });
    });
</script>
</body>
</html>
