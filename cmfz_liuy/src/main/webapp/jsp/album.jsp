<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url:"${pageContext.request.contextPath}/album/query",
            datatype:"json",
            colNames:["id","标题","封面","章节数量","得分","作者","播音员","简介","发行时间"],
            colModel:[
                {name:"albumId",key:true},
                {name:"title",editable: true},
                {name:"cover",editable: true,edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<img style='height:70px;width:70' src='${pageContext.request.contextPath}/upload/"+cellvalue+"'/>"
                    }},
                {name:"count",editable: true},
                {name:"score",editable: true},
                {name:"author",editable: true},
                {name:"broadcast",editable: true},
                {name:"brief",editable: true},
                {name:"publishTime",editable:true,edittype:"date"},
            ],
            styleUI:"Bootstrap",
            pager:"#albumPager",
            rowNum:5,
            rowList:[2,5,10],
            autowidth:true,
            viewrecords:true,
            height:'60%',
            editurl:"${pageContext.request.contextPath}/album/edit",
            multiselect:true,
            rownumbers:true,
            subGrid:true,
            subGridRowExpanded:function (subGidId,albumId) {
                addSubGrid(subGidId,albumId);
            }
            /*子网格扩展   subGidId:建立子表需要的父表格id  albumId:字表依赖父表的字段id*/
        }).jqGrid("navGrid","#albumPager",
            {search:false,addtext: "添加",edittext: "修改",deltext: "删除"},
            {
                //修改
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/upload",
                        fileElementId:"cover",
                        type:"json",
                        data:{"albumID":response.responseText},
                        success:function () {
                            $("#albumList").trigger("reloadGrid");
                            $("#albumMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#albumMsgId").hide();
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
                        url:"${pageContext.request.contextPath}/album/upload",
                        fileElementId:"cover",
                        type:"post",
                        data:{"albumID":response.responseText},

                    });
                    return "[true]";
                },


            }
        );
    })
    function addSubGrid(subGidId,albumId) {

        alert(albumId)
        var subGridTableId = subGidId + "Table";
        var subGridPagerId = subGidId + "Pager";
        $("#"+subGidId).html(
            "<table id='"+subGridTableId+"'class='table table-striped'></table>" +
            "<div id='"+ subGridPagerId +"'style='height: 50px'></div>")
        $("#"+subGridTableId).jqGrid({
            url:"${pageContext.request.contextPath}/chapter/query?albumId="+albumId,
            datatype:"json",
            colNames:["id","专辑id","标题","文件大小","下载路径","上传时间","操作"],
            colModel:[
                {name:"chapterId",key: true},
                {name:"albumId"},
                {name:"title",editable: true},
                {name:"size",editable: true},
                {name:"downPath",editable: true,edittype:"file"},
                {name:"uploadTime",editable: true,edittype:"date"},
                {name : "downPath",formatter:function(cellvalue, options, rowObject){
                        return "<a onclick=\"\" class=\"btn btn-primary\"href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"
                    }},
            ],
            styleUI:"Bootstrap",
            pager:"#"+subGridPagerId,
            rowNum:2,
            rowList:[2,5,10],
            autowidth:true,
            viewrecords:true,
            height:'60%',
            editurl:"${pageContext.request.contextPath}/chapter/edit?albumId="+albumId,
            multiselect:true,
            rownumbers:true,
        }).jqGrid("navGrid","#"+subGridPagerId,
            {search: false,addtext: "添加",edittext: "修改",deltext: "删除"},
            {
                //修改
                closeAfterEdit: true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/chapter/upload?albumId="+albumId,
                        type:"post",
                        fileElementId:"downPath",
                        data:{"chapterId":response.responseText},
                        success:function () {
                            $("#"+subGridTableId).trigger("reloadGrid");
                            $("#albumMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#albumMsgId").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            {
                //添加
                closeAfterAdd: true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/chapter/upload?albumId="+albumId,
                        type:"post",
                        fileElementId:"downPath",
                        data:{"chapterId":response.responseText},
                        success:function () {
                            $("#"+subGridTableId).trigger("reloadGrid");
                            $("#albumMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#albumMsgId").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            {
                //删除
                afterComplete:function (response) {
                    var msg = response.responseJSON.msg;
                    $("#albumMsgId").show().html(msg);
                    setTimeout(function () {
                        $("#albumMsgId").hide();
                    },3000);
                }
            }
        )
    }
    function playAudio(cellvalue) {
        $("#playAudio").modal("show")
        $("#audioId").attr("src","${pageContext.request.contextPath}/AudioUpload/audio/"+cellvalue);
    }
    function downLoadAudio(cellvalue) {
        location.href="${pageContext.request.contextPath}/chapter/downLoadAudio?audioName="+cellvalue;
    }
</script>
<div id="playAudio" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<h3>专辑与章节管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">专辑与章节信息</a></li>

    </ul>
</div>
<table id="albumList"></table>
<div id="albumPager" style="height: 50px"></div>
<div id="albumMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>
