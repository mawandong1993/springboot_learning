$(function(){
	//console.log('Hello World!')
	$('#login').click(loginAction);
	$('#count').blur(checkName);
	$('#password').blur(checkPassword);
	//注册功能
	$('#regist_button').click(registActive);
	$('#regist_username').blur(checkRegistName);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkRegistConfirm);
});

function checkRegistConfirm(){
	var pwd=$('#regist_password').val().trim();
	var confirm=$('#final_password').val().trim();
	//pwd 若果是空值是false,非空true
	if(pwd && pwd==confirm){
		$('#final_password').next().hide();
		return true;
	}
	$('#final_password').next().show().find('span').html('确认密码不一致');
	return false;
}

function checkRegistPassword(){
	var pwd=$('#regist_password').val().trim();
	var rule=/^\w{4,10}$/;
	if(rule.test(pwd)){
		$('#regist_password').next().hide();
		return true;
	}
	$('#regist_password').next().show().find('span').html('4~10个字符');
	return false;
}

function checkRegistName(){
	var name=$('#regist_username').val().trim();
	var rule=/^\w{4,10}$/;
	if(rule.test(name)){
		$('#regist_username').next().hide();
		return true;
	}
	$('#regist_username').next().show().find('span').html('4~10个字符');
	return false;
}

function registActive(){
	console.log('registActive');
	//检验界面参数
	var n=checkRegistName()+checkRegistPassword()+checkRegistConfirm();
	if(n!=3){
		return;
	}
	//获取界面中表单数据
	var name =$('#regist_username').val().trim();
	var nick = $('#nickname').val().trim();
	var pwd=$('#regist_password').val().trim();
	var confirm=$('#final_password').val().trim();
	//发起ajax请求
	var url='user/regist.do';
	var data={'name':name,
					'nick':nick,
					'password':pwd,
					'confirm':confirm};
	$.post(url,data,function(result){
		console.log(result);
		if(result.state==0){
			$('#back').click();
			var name=result.data.name;
			$('#count').val(name);
			$('#password').focus();
			
			//成功后清空数据
			$('#regist_username').val('');
			$('#nickname').val('');
			$('#regist_password').val('');
			$('#final_password').val('');
			
			
		}else if(result.state==4){
			$('#regist_username').next().show().find('span').html(result.message);
		}else if(result.state==3){
			$('#regist_password').next().show().find('span').html(result.message);
		}else{
			alert(result.message);
		}
		
		
		
	});
	//得到响应以后,更新界面
}

//检查用户名是否符合规范
function checkName(){
	var name=$('#count').val();
	var rule=/^\w{4,10}$/;
	if(!rule.test(name)){
		$('#count').next().html('4~10个字符');
		return false;
	}
	$('#count').next().empty();
	return true;
}
//检查密码是否符合规范
function checkPassword(){
	var password=$('#password').val();
	var rule=/^\w{4,10}$/;
	if(!rule.test(password)){
		$('#password').next().html('4~10个字符');
		return false;
	}
	$('#password').next().empty();
	return true;
}

function loginAction(){
	//console.log('liginAction');
	var name=$('#count').val();
	var password=$('#password').val();
	var data={"name":name,"password":password};
	
	var n=checkName()+checkPassword();
	if(n!=2){
		return;
	}
	
	//data对象中的属性名要与服务器控制器的参数名一致! login(name,password)
	$.ajax({
		url:'user/login.do',
		type:'post',
		data:data,
		dataType:'json',
		success:function(result){
			console.log(result);
			if(result.state==0){
				//登陆成功
				var user = result.data;
				console.log(user);
				
				//登陆成功以后将userId保存到cookie中
				addCookie("userId",user.id);
				
				//跳转到edit.html;
				location.href='edit.html';
			}else{
				var msg=result.message;
				if(result.state==2){
					$('#count').next().html(msg);
				}else if(result.state==3){
					$('#password').next().html(msg);
				}else{
					alert(msg);
				}
				
			}
		},
		error:function(e){
			alert('通讯失败');
		}
	});
	
}