
<html>
<head>
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/interface/dWRTestController.js"></script>
    <script type="text/javascript">
        dwr.engine.setActiveReverseAjax( true);
        dwr.engine.setNotifyServerOnPageUnload( true);
//        $(document).ready(function(){
            function test(){
                $.ajax({
                    type: "POST",
                    url: '/dWR/sendMsg',
                    data : JSON.stringify("test"),
//                    dataType:"json",
//                    contentType : 'application/json',
                    success: function(data){
                        console.log("@@@@@@@@@@@@@@@"),
                        console.log("ajax"),
                        console.log(data);
                    },
                    error: function(res){
                        console.log(res);
                        console.log("fail");
                    }
                });
//                dWRTestController.sendMsg("TEST");
            };
//        });
        function show(data) {
            console.log("***********");
            console.log("show");
            console.log(data);
        }
    </script>
</head>
<body>
<h2>Hello World!</h2>
<input type="button" name="button1" value="测试1" onclick="test()"/>
<textarea id="result" rows="5" cols="60"></textarea>

</body>
</html>
