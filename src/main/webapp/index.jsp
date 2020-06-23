<%--
  Created by IntelliJ IDEA.
  User: wally
  Date: 2020/6/22
  Time: 3:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url: "user/testAjax",
                    contentType: "application/json;charset=UTF-8",
                    data: '{"uname": "hehe", "age": 18}',
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        //data是服务器端返回的数据
                        alert(data)
                        alert(data.uname)
                    }
                })
            });
        });
    </script>
</head>
<body>
<h3>入门案例</h3>
<a href="user/hello">入门案例</a>
</br>
<form action="user/saveAccount" method="post"><br>
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    金额：<input type="text" name="money"><br>
    用户姓名：<input type="text" name="user.uname"><br>
    用户年龄：<input type="text" name="user.age"><br>
    <input type="submit" value="提交">
</form>

<br>
<h3>保存用户</h3>
<form action="user/saveUser" method="post">
    用户姓名：<input type="text" name="uname"><br>
    用户年龄：<input type="text" name="age"><br>
    用户生日：<input type="text" name="date"><br>
    <input type="submit" value="保存">
</form>
<br>
<a href="user/testServlet">测试原生Servlet的API</a>
<br>
<a href="user/testParam?name=哈哈">测试RequestParam注解</a>
<br>
<h3>测试RequestBody注解</h3>
<form action="/user/testRequestBody" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="提交">
</form>
<a href="user/testPathVariable/123">测试PathVariable注解</a><br>
<a href="user/testRequestHeader">测试RequestHeader注解</a><br>
<a href="user/testCookieValue">测试CookieValue注解</a><br>
<button id="btn">测试ajax请求</button>
<br>
<h3>传统方式文件</h3>
<form action="upload/testUpload1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="提交"><br>
</form>
<br>
<h3>SpringMVC方式文件</h3>
<form action="upload/testUpload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="提交"><br>
</form>
<br>
<h3>跨服务器上传文件</h3>
<form action="upload/testUpload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="提交"><br>
</form>
<br>
<a href="user/testException">测试异常处理器</a>
</body>
</html>