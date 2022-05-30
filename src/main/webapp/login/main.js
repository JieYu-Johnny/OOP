/*
 * Author: Minyoung
 * CreateAt: 2021年10月14日23:44:45
 * License: MIT
 */
const singUpBtn = document.querySelector('#sign-up-btn')
const singInBtn = document.querySelector('#sign-in-btn')
const container = document.querySelector('.container')

singUpBtn.addEventListener('click', () => {
  container.classList.add('sign-up-mode')
})
singInBtn.addEventListener('click', () => {
  container.classList.remove('sign-up-mode')
})

function isNoChinese(s){
  // 第一种正则
  var reg =/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
  //第二种正则
  //var reg = /^[u4E00-u9FA5]+$/;
  //alert(1);
  if (reg.test(s.value)){ //如果正则为 /^[u4E00-u9FA5]+$/;时要取反： ！reg.test(s.value)
    alert('不能为中文\n');
    s.focus();
    return false;
  }
  return true;
}

var table=document.getElementById("error_Msg");
c =''
switch(table.innerText){
  case "用户已存在，请登录": c="error";break;
  case "注册失败请联系管理员": c="error";break;
  case "用户名或密码错误" : c="error";break;
  case "信息核对失败，请检查" : c="error";break;
  case "注册成功！请登录" : c="success";break;
  case "用户存在" : c="error";break;
}
document.getElementById("error_Msg").classList=c;


var tables=document.getElementById("errorMsg");
a =''
switch(tables.innerText){
  case "用户已存在，请登录": a="error";break;
  case "注册失败请联系管理员": a="error";break;
  case "用户名或密码错误" : a="error";break;
  case "信息核对失败，请检查" : a="error";break;
  case "注册成功！请登录" : a="success";break;
  case "用户存在" : a="error";break;
}
document.getElementById("errorMsg").classList=a;









