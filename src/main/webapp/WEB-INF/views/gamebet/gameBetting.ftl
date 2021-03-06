<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="/resources/main/css/style.css" />
		<link rel="stylesheet" type="text/css" href="/resources/main/css/iconfont.css" />
	</head>

	<body style="background: #eff1f3;">
		<!--游戏下注-->
		<div class="tableW GameBetting divbox TongjiTab">
			<div class="bettingL fl">
				<div class="tableDiv">
					<table class="layerTab">
						<thead>
							<tr>
								<th>1靴2局</th>
								<th>闲</th>
								<th>庄</th>
								<th>闲对</th>
								<th>庄对</th>
								<th>和</th>
								<th>1靴2局</th>
								<th>本局得分</th>
								<th>剩余分</th>
								<th>初始分</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>12</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td class="xiugai">修改</td>
							</tr>
							<tr>
								<td>12</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td>23</td>
								<td class="xiugai">修改</td>
							</tr>
							<tr>
								<td>总计:9999</td>
								<td>1000</td>
								<td>1000</td>
								<td></td>
								<td></td>
								<td></td>
								<td>总计:999</td>
								<td>1000</td>
								<td>1000</td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>

				</div>

				<!--分页-->
				<div class="pageWrap mT15">
					<div>
						<a><i class="iconfont icon-icon1"></i></a>
						<a class="on">1</a>
						<a>2</a>
						<a></a>
						<a>4</a>
						<a>10</a>
						<a><i class="iconfont icon-arrow-sl"></i> </a>
					</div>
				</div>

			</div>
			<div class="bettingR">
				<p class="operatingBtn btnP">开始/停止统计</p>
				<div class="paiInfo">
					<p class="fa3033"><span>庄：</span><span>9999999</span></p>
					<p class="fa3033"><span>庄对：</span><span>9999999</span></p>
					<p class="f016dff"><span>闲：</span><span>9999999</span></p>
					<p class="f016dff"><span>闲对：</span><span>99999</span></p>
					<p class="f00e288"><span>和：</span><span>9999</span></p>
				</div>
				<p class="TableShotsBtn btnP">台面截图</p>
				<p class="changexj ov"><label class="talignR"><input type="text" value="12" />靴</label> <label class="talignL"><input type="text" value="12" />局	</label></p>
				<p class="ModifyBoots btnP">修改靴局</p>
				<p class="toNextBtn btnP">进入下一靴</p>
				<p class="BettingShotsBtn btnP">投注表截图</p>
				<p class="xiuBettingShotsBtn btnP">投注表修改</p>
				<div class="changeCheck changexj ov">
					<label class="fa3033"><input type="text" />庄</label>
					<label class="f016dff"><input type="text" />闲</label>
					<label class="fa3033"><input type="text" />庄对</label>
					<label class="f016dff"><input type="text" />闲对</label>
					<label class="f00e288"><input type="text" />和</label>
				</div>
				<p class="LotterySettlementBtn btnP">开奖结算</p>
				<p class="ScoreBtn btnP">分数表截图</p>
				<p class="IntoNextBtn btnP">进入下一局</p>
			</div>
		</div>
        <!--开奖结算-->
		<div class="tip" style="display:none;">
			<div class="tipBox kaijiangBox">
				<p class="pTit tcenter f18 color33 mT10">开奖</p>
				<div class="tipCon">
					<p class="ov">
						<span class="zhuangRed">庄</span>
						<span class="zhuangRed">庄对</span>
						<span class="xianLan">闲对</span>
					</p>
				</div>
				<div class="BtnDiv mT20">
					<span class="tijiao mLR15">开奖</span>
				</div>
			</div>
		</div>
	</body>
	<script src="/resources/main/js/jquery2-1-4.min.js"></script>
	<script src="/resources/main/js/home.js"></script>
	<script type="text/javascript">
		$(".layerTab tbody tr,.pageWrap a").click(function() {
			$(this).addClass("on").siblings().removeClass("on");
		})
	</script>

</html>