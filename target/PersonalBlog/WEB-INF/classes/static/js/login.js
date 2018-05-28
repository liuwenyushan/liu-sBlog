	//控制登陆功能的js代码
$(document).ready(function(){
	  $("#login").click(function(){
		  $("#loginModal").show();
	  });
	});
	
	$(function(){
		$("#loginClose").click(function(){
			  $("#loginModal").hide();
		})
	});
	$(function(){
		$("#loginButton").click(function(){
			$.ajax({
				url:"/user/login",
				type:"POST",
				dataType:"json",
				data :  new FormData($("#loginForm")[0]),
				contentType : false,// 告诉jQuery不要去设置Content-Type请求头
		        processData: false,// 告诉jQuery不要去处理发送的数据
				success:function(data){
					if(data.success){
						alert(data.msg);
						window.location.reload();//登陆成功则刷新当前页面
					}else{
						alert(data.msg);
					}
				},
				error:function(jqXHR){
					alert("ajax请求失败:"+jqXHR.status);
				}
			});
		})
	});
	