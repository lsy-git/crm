<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<script>

		$(function () {

			//页面加载完毕将用户文本框中的内容清空
			$("#loginAct").val("");
			$("#loginPwd").val();

			//页面加载完毕之后，让用户的文本框自动获取焦点
			$("#loginAct").focus();

			//为登录按钮绑定事件，执行登录操作
			$("#submitBtn").click(function () {

			})

			//为当前登录页窗口绑定敲键盘事件
			$(window).keydown(function (event) {
				if (event.keyCode == 13){
					login();
				}
			})

		})

		//普通的自定义的function方法, 一定要定义在$(function(){})外边
		function login() {
			//验证登录账户密码不能空
			//取得账号密码
			//将文本中的左右空格去掉，使用$.trim(文本)
			var loginAct = $.trim($("#loginAct").val());
			var loginPwd = $.trim($("#loginPwd").val());

			if (loginAct == "" || loginPwd == ""){
				$("#msg").html("账号密码不能为空");

				//如果账号密码为空，则需要及时强制终止该方法
				return false;
			}

			//去后台验证登录的操作
			//url前面不加  /
			$.ajax({
				url : "settings/user/login.do",
				data : {
					"loginAct": loginAct,
					"loginPwd": loginPwd
				},
				type : "post",
				dataType : "json",
				success : function (data) {
					/*
					分析 data
						{
						“success” : true/false
						"msg": ”哪错了“
						}
					 */

					//如果登录成功
					if(data.success){
						//跳转到工作台的初试页
						window.location.href = "workbench/index.html";
					}else {
						$("#msg").html("msg");
					}
				}
			})
		}

	</script>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
							<span id="msg"></span>
					</div>
					<!--
					 注意：按钮写在form表单中，默认的行为就是提交表单
					 一定要将按钮的类型设置为button
					 按钮所接触的行为应该是由js代码决定
					 -->
					<button id="submitBtn" type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>