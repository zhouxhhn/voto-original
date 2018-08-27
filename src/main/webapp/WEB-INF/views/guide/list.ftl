<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>新手指南管理</title>
		<link rel="stylesheet" type="text/css" href="/resources/main/css/style.css" />
		<link rel="stylesheet" type="text/css" href="/resources/main/css/iconfont.css" />
	</head>

	<body>
		<div class="tableW PhoneBetting divbox pad20 ">
			<div class="phoneTop">
                <div class="batchEdit Btn1 yanQing" la="1" inDiv>新增新手指南</div>
			</div>

			<div class="divW w100">
				<table class="layerTab">
					<thead>
						<tr>
                            <th>序号</th>
                            <th>创建时间</th>
							<th>标题</th>
							<th>内容</th>
                            <th>操作</th>
						</tr>
					</thead>
					<tbody>
					[#if pagination??]
					[#list pagination.data as guide]
						<tr>
                            <td>${guide_index+1}</td>
                            <td>${guide.createDate}</td>
                            <td>${guide.title}</td>
                            <td>${guide.content}</td>
							<td class="xiugai" onclick="window.location.href='/guide/delete?id=${guide.id}'">删除</td>
						</tr>
					[/#list]
					[/#if]
					</tbody>
				</table>
			</div>
			<!--分页-->
		[#if pagination??]
			[@mc.customPagination '/guide/pagination' /]
		[/#if]

		</div>

        <div class="tip" lay="1" style="display: none;">
            <div class="tipBox addBox" style="height: 410px;width: 600px;margin-top: -220px;margin-left: -260px">
                <p class="pTit tcenter f18 color33 mT10">新增新手指南</p>
                <form action="/guide/create" method="post" enctype="multipart/form-data" id="create">
                    <div class="tipCon">
                        标题：<br/>
						<input style="height: 28px;width: 515px" type="text" name="title" id="title" />
                    </div>
                    <div class="tipCon">
                        内容：<br/>
                        <textarea style="width: 515px;height: 120px" name="content" id="content"></textarea>
                    </div>
                    <div class="BtnDiv mT20">
                        <span class="quxiao mLR15" type="button" onclick="$('[lay]').css('display','none')">取消</span>
                        <span class="tijiao mLR15" type="submit" onclick="submit()">确定</span>
                    </div>


            </div>
            </form>
        </div>

	</body>
	<script src="/resources/main/js/jquery2-1-4.min.js"></script>
	<script src="/resources/main/js/home.js"></script>
	<script type="text/javascript">
		$(".layerTab tbody tr,.pageWrap a").click(function() {
			$(this).addClass("on").siblings().removeClass("on");
		})

        $("[la]").click(function(){
            $("[lay]").css("display","none");
            $("[lay="+$(this).attr("la")+"]").css("display","block");
        });

		function submit() {
			var content = $('#content').val();
			if(content.length>500){
			    alert('内容不能超过500字');
			    return;
			}
			$('#create').submit();
        }

	</script>

</html>