<#assign path="${request.getContextPath()}">
<html>
<head>
    <title>首页</title>

    <script src="${path}/statics/js/jquery-1.8.3.min.js"></script>
    <script>

        $(document).ready(function(){
                alert("首页加载完毕");
        });

    </script>

</head>
<body>

        ${hello}

<br>

    数据库信息：${db}


<br>

    小猫的颜色：${pet.color}

</body>
</html>