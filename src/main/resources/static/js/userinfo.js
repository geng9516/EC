window.onload = function() {

			//	ユーザー名判断
			var usernameErrorSpan = document.getElementById("usernameError");
			// 给用户名文本框绑定blur事件
			var usernameElt = document.getElementById("username");
			usernameElt.onblur = function() {
				// 获取用户名
				var username = usernameElt.value;
				// 去除前后空白
				username = username.trim();
				if (username === "") {
					// 用户名为空
					usernameErrorSpan.innerText = "ユーザー名を入力してください";
				}
			}
			// 给username这个文本框绑定获得焦点事件
			usernameElt.onfocus = function() {
				// 清空非法的value
				if (usernameErrorSpan.innerText != "") {
					usernameElt.value = "";
				}
				// 清空span
				usernameErrorSpan.innerText = "";
			}

			//ニックネーム判断
			var nicknameErrorSpan = document.getElementById("nicknameError");
			   // 给用户名文本框绑定blur事件
			   var nicknameElt = document.getElementById("nickname");
			   nicknameElt.onblur = function(){
				   // 获取用户名
				   var nickname = nicknameElt.value;
				   // 去除前后空白
				   nickname = nickname.trim();
				   if(nickname === ""){
					   // 用户名为空
					   nicknameErrorSpan.innerText = "ニックネームを入力してください";
				   }else{
					   // 用户名不为空
					   // 继续判断长度[6-14]
					   if(nickname.length < 4 || nickname.length > 14){
						   // 用户名长度非法
						   nicknameErrorSpan.innerText = "ニックネームの長さを4~14までにしてください";
					   }else{
						   // 用户名长度合法
						   // 继续判断是否含有特殊符号
						   var regExp = /^[A-Za-z0-9]+$/;
						   var ok = regExp.test(nickname);
						   if(ok){
							   // 用户名最终合法
						   }else{
							   // 用户名中含有特殊符号
							   nicknameErrorSpan.innerText = "数字とアルファベットで作成してください";
						   }
					   }
				   }
			   }

			   // 给username这个文本框绑定获得焦点事件
			   nicknameElt.onfocus = function(){
				   // 清空非法的value
				   if(nicknameErrorSpan.innerText != ""){
					   nicknameElt.value = "";
				   }
				   // 清空span
				   nicknameErrorSpan.innerText = "";
			   }

			   //メール判断
			// 获取email的span
			   var emailSpan = document.getElementById("emailError");
			   // 给email绑定blur事件
			   var emailElt = document.getElementById("email");
			   emailElt.onblur = function(){
				   // 获取email
				   var email = emailElt.value;
				   // 编写email的正则
				   var emailRegExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				   var ok = emailRegExp.test(email);
				   if(ok){
					   // 合法
				   }else{
					   // 不合法
					   emailSpan.innerText = "不正なメールアドレス";
				   }
			   }

			   // 给emailElt绑定focus
			   emailElt.onfocus = function(){
				   if(emailSpan.innerText != ""){
					   emailElt.value = "";
				   }
				   emailSpan.innerText = "";
			   }
			   //パスワード
			   var pwdErrorSpan = document.getElementById("pwdError");
			   var password2Elt = document.getElementById("password2");
			   password2Elt.onblur= function(){
				   var password1Elt = document.getElementById("password1");
				   var password1 = password1Elt.value;
				   var password2 = password2Elt.value;
				   if(password1 != password2){
					   pwdErrorSpan.innerText="パスワード不一致";
				   }
			   }
			   password2Elt.onfocus=function(){
				   if(pwdErrorSpan.innerText != ""){
					   password2Elt.value = "";
				   }
				   pwdErrorSpan.innerText = "";
			   }

			   var submitBtnElt = document.getElementById("submitBtn");
			   submitBtn.onclick = function(){
				   // 触发username的blur userpwd2的blur email的blur
				   // 不需要人工操作,使用纯JS代码触发事件.
				   usernameElt.focus();
				   usernameElt.blur();

				   nicknameElt.focus();
				   nicknameElt.blur();

				   password2Elt.focus();
				   password2Elt.blur();

				   emailElt.focus();
				   emailElt.blur();

				   // 当所有表单项都是合法的时候,提交表单
				   if(usernameErrorSpan.innerText == "" && pwdErrorSpan.innerText == "" && emailSpan.innerText == "" && nicknameErrorSpan.innerText == ""){
					   // 获取表单对象
					   var userFormElt = document.getElementById("userForm");
					   // 可以在这里设置action,也可以不在这里.
					   userFormElt.action = "/saveUser";
					   // 提交表单
					   userFormElt.submit();
				   }
			   }
		}