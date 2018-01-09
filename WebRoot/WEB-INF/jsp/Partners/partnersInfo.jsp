<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head lang="en">
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="description" content="得实客户支持">
		<meta name="shop" content="查看门店">
		<title>公司信息</title>
		<link rel="stylesheet" href="<%=basePath %>/ds/css/base.css" />
		<link rel="stylesheet" href="<%=basePath %>/ds/css/public-style.css" />
		<link rel="stylesheet" href="<%=basePath %>/ds/css/corporate_search.css" />
	</head>

	<body>
		
	<!-------------公司搜索--------------->
		<div class="company_information">
			<ul class="information_content">
				<li class="user_information ">
					<label class="lead_name">公司信息</label>
				</li>
				<li  class="user_information  second"></li>
			
				<li class="user_information ">
						<label>公司名称</label>
						<input type="text" value="${partners.companyName }" />
				</li>
				<li  class="user_information  second">
						<label>电话号码</label>
						<input type="text" value="${partners.phone }"/>
				</li>
				<li class="user_information ">
						<label>公司法定名称</label>
						<input type="text" value="${partners.legalName }"/>
				</li>
				<li  class="user_information  second">
						<label>传真号码</label>
						<input type="text" value="${partners.fax }"/>
				</li>
				<li class="user_information">
						<label>公司URL</label>
						<input type="text" value="${partners.url }"/>
				</li>
				<li  class="user_information  second">
						<label>公司邮箱</label>
						<input type="text" value="${partners.email }"/>
				</li>
				<li  class="user_information">
						<label>税号</label>
						<input type="text" value="${partners.dutyCode }"/>
				</li>
				<li  class="user_information  second"></li>
	
				<li class="user_information">
						<label>国家/地区</label>
						<input type="text" value="${partners.companyCountry }"/>
				</li>
				<li  class="user_information  second">
					<label>邮寄国家/地区</label>
					<input type="text" value="${partners.postCountry }"/>
				</li>

				<li  class="user_information">
						<label>省份/州</label>
						<input type="text" value="${partners.companyProvince }"/>
				</li>
				<li  class="user_information second">
						<label>邮寄省份/州</label>
						<input type="text" value="${partners.postProvince }"/>
				</li>
				
				<li class="user_information">
						<label>城市<span></span></label>
						<input type="text" value="${partners.companyCity }"/>
				</li>
				<li  class="user_information second">
						<label>邮寄城市</label>
						<input type="text" value="${partners.postCity }"/>
				</li>
				<li class="user_information">
						<label>县区<span></span></label>
						<input type="text" value="${partners.companyArea }"/>
				</li>
				<li  class="user_information second">
						<label>邮寄县区</label>
						<input type="text" value="${partners.postArea }"/>
				</li>
				
				<li  class="user_information">
						<label>街道地址</label>
						<input type="text" value="${partners.companyStreet }"/>
				</li>
				<li  class="user_information second">
						<label>邮寄街道地址</label>
						<input type="text" value="${partners.postStreet }"/>
				</li>
				<li  class="user_information">
						<label>邮政编码</label>
						<input type="text" value="${partners.companyPostNumber }"/>
				</li>
				<li  class="user_information second">
						<label>邮政编码</label>
						<input type="text" value="${partners.postNumber }"/>
				</li>
			</ul>
		</div>

		<!-----企业负责人------>
		<div class="leading_official">
			<div class="representative">
				<ul class="information_content">
					<li class="user_information "></li>
					<li  class="user_information  second"></li>
					<li class="user_information ">
						<label class="lead_name">负责人信息</label>
							
					</li>
					<li class="user_information second"></li>
					<li class="user_information ">
							<label>名字</label>
							<input type="text" value="${partners.personName }"/>
					</li>
					<li  class="user_information  second">
							<label>姓氏</label>
							<input type="text" value="${partners.personLastName }"/>
					</li>
					
					<li class="user_information ">
							<label>称呼</label>
							<input type="text" value="${partners.personCall }"/>
					</li>
					<li  class="user_information  second">
							<label>工作职能</label>
							<input type="text" value="${partners.personFunction }"/>
					</li>
					<li class="user_information ">
							<label>国家</label>
							<input type="text" value="${partners.personCountry }"/>
					</li>
					<li  class="user_information  second">
							<label>邮政编号</label>
							<input type="text" value="${partners.personPostNumber }"/>
					</li>
					<li class="user_information ">
							<label>州/省</label>
							<input type="text" value="${partners.personProvince }"/>
					</li>
					<li  class="user_information  second">
							<label>工作电话</label>
							<input type="text" value="${partners.personPhone }"/>
					</li>
					<li class="user_information ">
							<label>城市</label>
							<input type="text" value="${partners.personCity }"/>
					</li>
					<li  class="user_information  second">
							<label>手机号码</label>
							<input type="text" value="${partners.personMobilePhone }"/>
					</li>
					<li class="user_information ">
							<label>接收消息类型</label>
							<input type="text" value="${partners.personAllowType }"/>
					</li>
					<li  class="user_information  second"></li>
				</ul>
			</div>
		</div>
		
		<!-------------权限--------------->
		<div class="leading_official">
			
			<div class="representative">
				<ul class="information_content">
					<li class="user_information "></li>
					<li  class="user_information  second"></li>
					<li class="user_information ">
						<label class="lead_name">权限信息</label>					
					</li>
					<li class="user_information second"></li>
					
					<li  class="user_information  ">
						<label>公司代号</label>
						<input type="text" value="${partners.id }" />
					</li>
					<li  class="user_information second "></li>
					<li class="user_information ">
							<label>公司级别</label>
							<input type="text" value="等级${partners.rank }"/>
					</li>
					<li  class="user_information  second">
							<label>公司权限</label>
							<input type="text" value="${partners.permission }星级"/>
					</li>
					
					<li class="user_information ">
							<label>公司状态</label>
							<input type="text" value="${partners.status }"/>
					</li>
					<li  class="user_information  second">
							<label>加入时间</label>
							<input type="text" value="${partners.registerTime} "/>
					</li>
				</ul>
			</div>
		</div>
	</body>

</html>
