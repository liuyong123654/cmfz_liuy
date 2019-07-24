<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<%--    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jqgrid/css/css/cupertino/jquery-ui-1.8.16.custom.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jqgrid/js/jquery.jqGrid.src.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <title>持明法洲后台管理系统</title>
</head>
<body>
<%--标题导航栏--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持名法洲后台管理系统v1.0</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:${sessionScope.admin.username} <span class="glyphicon glyphicon-user"></span></a></li>
                <li class="dropdown"><a href="${pageContext.request.contextPath}/jsp/login.jsp">退出登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="row">
    <div class="col-md-2">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('user.jsp')">用户列表</a></li>
                        <li><a href="javascript:$('#carousel-example-generic').load('echarts.jsp')">用户列表</a></li>
                        <li><a href="javascript:$('#carousel-example-generic').load('user.jsp')">用户列表</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsethree" >
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapsethree" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('article.jsp')">查看文章</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsefour" >
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapsefour" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('album.jsp')">查看专辑</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="collapsed"  data-toggle="collapse" data-parent="#accordion" href="#collapsesix" >
                            轮播图
                        </a>
                    </h4>
                </div>
                <div id="collapsesix" class="panel-collapse collapse">
                    <div class="panel-body">
                        <li><a href="javascript:$('#carousel-example-generic').load('carousel.jsp')">轮播图管理</a></li>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="col-md-10">
        <div class="jumbotron">
            <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到持名法洲后台管理系统</h2>
        </div>
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="${pageContext.request.contextPath}/img/1.png" width="1366" height="450" alt="..." class="img-responsive center-block"
                    >
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/img/2.png" width="1366" height="450" alt="..." class="img-responsive center-block"
                    >
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                <img src="${pageContext.request.contextPath}/img/3.png" width="1366" height="450" alt="..." class="img-responsive center-block"
                >
                <div class="carousel-caption">
                    ...
                </div>
            </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>

        </div>

    </div>
</div>

<nav class="navbar navbar-default navbar-fixed-bottom">
    <div>
        <h6 class="text-center">@持明法洲</h6>
    </div>
</nav>
</body>
</html>