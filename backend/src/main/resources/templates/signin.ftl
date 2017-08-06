<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${rc.contextPath}/bootstrap/favicon.ico">

    <title>酒庄集团后台管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="${rc.contextPath}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="${rc.contextPath}/bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/bootstrap/examples/signin/signin.css" rel="stylesheet">

    <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript" language="javascript" src="${rc.contextPath}/js/app/dataTables.init.js"></script>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="${rc.contextPath}/bootstrap/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${rc.contextPath}/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="${rc.contextPath}/signin" method="post">
        <h2 class="form-signin-heading">欢迎登录酒庄集团后台管理系统${errorMsg!""}</h2>
        <label for="account" class="sr-only">账户</label>
        <input type="text" id="account" name="account" class="form-control" placeholder="账户" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        <input type="hidden" name="domain" value="99wuxian">
        <input type="hidden" name="destination" value="${destination!""}">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="true" name="rememberMe"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${rc.contextPath}/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
    <script>
        $(document).ready(function() {
            DataTable.CONTEXT_PATH = '${rc.contextPath}';
            Ddic.init();
        });
    </script>
  </body>
</html>
