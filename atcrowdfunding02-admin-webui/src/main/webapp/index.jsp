<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">

        $(function () {

            $("#btn1").click(function () {
                $.ajax({
                    url : "send/array/one.html",
                    type : "post",
                    data : {
                        "array" : [5,8,12]
                    },
                    dataType : "text",
                    success : function (response) {
                        alert(response);
                    },
                    error : function (response) {
                        alert("发现错误");
                    }
                });
            });

            $("#btn2").click(function () {
                $.ajax({
                    url : "send/array/two.html",
                    type : "post",
                    data : {
                        "array[0]" : 5,
                        "array[1]" : 8,
                        "array[2]" : 12
                    },
                    dataType : "text",
                    success : function (response) {
                        alert(response);
                    },
                    error : function (response) {
                        alert("发现错误");
                    }
                });
            });

            $("#btn3").click(function () {

                var array = [5,8,12];
                console.log(array);

                var requestBody = JSON.stringify(array);    //将JSON数组转换为JSON字符串
                console.log(requestBody);

                $.ajax({
                    url : "send/array/three.html",
                    type : "post",
                    contentType : "application/json;charset=UTF-8", //设置请求体的应用类型，告诉服务器端本次请求的请求体是JSON数据
                    data : requestBody,
                    dataType : "text",
                    success : function (response) {
                        alert(response);
                    },
                    error : function (response) {
                        alert("发现错误");
                    }
                });
            });

        });

    </script>
</head>
<body>
<a href="test/ssm.html">测试SSM整合环境</a>
<br>
<br>
<button id="btn1">Send [5,8,12] One</button>
<br>
<br>
<button id="btn2">Send [5,8,12] Two</button>
<br>
<br>
<button id="btn3">Send [5,8,12] Three</button>
<br>
<br>
<button id="btn">Send Student Object</button>
</body>
</html>
