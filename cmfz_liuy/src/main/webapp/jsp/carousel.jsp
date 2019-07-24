<%@page isELIgnored="false" contentType="text/html;utf-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            /*获取数据的地址*/
            url:"${pageContext.request.contextPath}/carousel/query",
            datatype: "json",
            /*表头*/
            colNames: ["id","标题","图片","图片状态","上传时间",],
            /*行属性*/
            colModel:[
                {name:"carouselId",key:true},
                {name:"title",editable: true},
                {name:"imgPath",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                        return "<img style='width:70px;height:70px' src='${pageContext.request.contextPath}/upload/"+cellvalue+"'/>";
                    }},
                {name:"status",editable:true,edittype: "select",
                    editoptions: {value:"展示:展示;不展示:不展示"}},
                {name:"createTime",editable: true,edittype:"date"},
            ],
            styleUI:"Bootstrap",
            pager:"#bannerPager",
            rowNum:5,
            rowList:[2,5,10],
            /*是否显示总记录数*/
            viewrecords:true,
            autowidth: true,
            editurl: "${pageContext.request.contextPath}/carousel/edit",
            height:'60%',
            multiselect:true,
            /*每一行前面加标号*/
            rownumbers: true
        }).jqGrid("navGrid","#bannerPager",
            {search:false,addtext: "添加",edittext: "修改",deltext: "删除"},
            {
                //修改
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/carousel/upload",
                        fileElementId:"imgPath",
                        type:"post",
                        data:{"carouselId":response.responseText},
                        success:function () {
                            /*刷新表格*/
                            $("#bannerList").trigger("reloadGrid");
                            /*刷新提示*/
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            },3000);
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
                        url:"${pageContext.request.contextPath}/carousel/upload",
                        fileElementId:"imgPath",
                        type:"post",
                        data:{"carouselId":response.responseText},
                        success:function () {
                            /*刷新表格*/
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return "[true]";
                }
            }

        );
    })
</script>
<h3>轮播图管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图信息</a></li>

    </ul>
</div>
<table id="bannerList"></table>
<div id="bannerPager" style="height:50px"></div>

<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>