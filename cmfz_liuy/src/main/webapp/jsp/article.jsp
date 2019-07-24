<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    $(function () {
        $("#articleList").jqGrid({
            url:"${pageContext.request.contextPath}/acticle/query",
            editurl: "${pageContext.request.contextPath}/acticle/edit",
            datatype:"json",
            colNames:["文章ID","喇嘛id","经文标题","念经","发布时间","操作"],
            colModel:[
                {name:"articleId",key:true},
                {name:"guruId",editable: true},
                {name:"title",editable: true},
                {name:"content",editable: true,hidden:true},
                {name:"publishTime",editable: true,edittype:"date"},
                {name:"content",
                    formatter:function (cellValue,option,value) {
                        return "<a href='#' onclick=\"lookModal('" + value.articleId + "')\">查看详情</a>";
                    }
                }
            ],
            styleUI:"Bootstrap",
            pager:"#ArticlePager",
            rowNum:5,
            rowList:[2,5,10],
            autowidth:true,
            viewrecords:true,
            height:'60%',
            multiselect:true,
            rownumbers:true,
        }).jqGrid("navGrid","#ArticlePager",
            {search: false,add: false,edittext: "修改",deltext: "删除"},{
                closeAfterEdit:true,
            },

            {
                //删除
                closeAfterAdd:true,

            }
        )
    })
    //添加文本类容
    $(function(){
        KindEditor.create('#editor_id',{
            width : '700px',
            uploadJson:'${pageContext.request.contextPath}/acticle/upload',
            fileManagerJson:'${pageContext.request.contextPath}/acticle/showAll',
            allowFileManager:true,
            filePostName:'file',
            afterBlur:function(){
                this.sync();
            }
        });

    })
    //自定义模态框添加数据

    function addArticle(){
        $("#articleList").empty();

        $.ajax({
            url:"${pageContext.request.contextPath}/acticle/addArticle",
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),

            success:function(){
                $('#myModal').modal('hide');//隐藏模态框

            }
        })
        $("#articleList").trigger("reloadGrid");//刷新表格
    }
    function lookModal(i) {

        $.post("${pageContext.request.contextPath}/acticle/selectArticle?articleId="+i,function (data) {
            $("#showContent").append(data.content);//showContent(需要展示内容的id名称)
            $("#showContent").append(data.title);
            $("#showContent").append(data.publishTime);
            $("#myModals").modal('show');//手动展示模态框
            $("#articleList").trigger("reloadGrid");//刷新表格
        })
    }
</script>

<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    文章上传
</button>

<div id="ArticlePager" style="height: 50px"></div>//分页相关
<table id="articleList"></table>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 800px;height:300px;"></div>
<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <form action="javascript:void(0);" id="articleForm">
                <div class="modal-body">
                    大师Id:<input type="text" name="guruId"><br/>
                    文章标题:<input type="text" name="title"><br/>

                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea><br/>
                    上传时间:<input type="date" name="publishTime">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addArticle()">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="myModals" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabe2">内容展示</h4>
            </div>
            <div class="modal-body">
                <p id="showContent"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
