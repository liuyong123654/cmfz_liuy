<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<script>
    function updateStatus(userId,status) {
        alert("已修改")
        $.post("${pageContext.request.contextPath}/user/updatestatus?userId="+userId+"&status="+status,function (date) {
            $("#userList").trigger("reloadGrid");
        },"json")
        $("#userList").trigger("reloadGrid");
    }
    $(function () {

        $("#userList").jqGrid({
            url:"${pageContext.request.contextPath}/user/query",
            editurl: "${pageContext.request.contextPath}/user/edit",
            datatype:"json",
            colNames:["用户ID","头像","手机号码","密码","盐值","法号","省","市","性别","签名","状态","注册时间"],
            colModel:[
                {name:"userId",key:true},
                {name:"profile",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:70px;height:70px' src='${pageContext.request.contextPath}/upload/"+cellvalue+"'/>";
                    }},
                {name:"phone",editable: true},
                {name:"password",editable: true},
                {name:"salt",editable: true},
                {name:"dharmaName",editable: true},
                {name:"province",editable: true},
                {name:"city",editable: true},
                {name:"gender",editable: true},
                {name:"personalSign",editable: true},
                {name:"status",
                    formatter:function (cellvalue, options, rowObject) {
                        return "<button class='btn btn-default' onclick=\"updateStatus('"+rowObject.userId+"','"+cellvalue+"')\">"+cellvalue+"</button>"

                    }
                },

                {name:"registTime",editable: true,edittype:"date"},
            ],
            styleUI:"Bootstrap",
            pager:"#UserPager",
            rowNum:1,
            rowList:[2,5,10],
            autowidth:true,
            viewrecords:true,
            height:'60%',
            multiselect:true,
            rownumbers:true,
        }).jqGrid("navGrid","#UserPager",
            {search: false,addtext: "添加",edittext: "修改",deltext: "删除"},
            {
                //修改
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        fileElementId:"profile",
                        type:"post",
                        data:{"userId":response.responseText},
                        success:function () {
                            /*刷新表格*/
                            $("#userList").trigger("reloadGrid");
                        }
                    });
                    return response;
                }
            },
            {
                //添加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        fileElementId:"profile",
                        type:"post",
                        data:{"userId":response.responseText},
                        success:function () {
                            /*刷新表格*/
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return "[true]";
                }
            },
            {
                //删除

            }
        )

    })

    function xiazai() {
        location.href="${pageContext.request.contextPath}/user/derives";
        $("#userList").trigger("reloadGrid");//刷新表格
    }

</script>
<h3>用户管理</h3>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="xiazai()">
    下载用户
</button>
<div id="UserPager" style="height: 50px"></div>
 <table id="userList"></table>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:300px;"></div>
<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>
<form method="post" action="${pageContext.request.contextPath}/user/abcd" enctype="multipart/form-data">
        <input type="file" name="file1">

        <input  type="submit" value="提交"/>
</form>
