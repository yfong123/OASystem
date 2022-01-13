<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, Dept-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>部门列表</title>
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css?t=1554901098009" media="all">
  <style>
    body{margin: 10px;}
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
  </style>
  
  <style>
		#tbl{ margin-top:15px; margin-left:25px; }
		#tbl th{ height:45px; }
		#tbl td{
			font-weight:normal; height:45px;
			font-family:微软雅黑; font-size:17px;
		}
		#tbl [type='text']{
			height:28px; font-size:17px;
			text-indent:0.3em;
		}
		#tbl select { width:200px; height:32px; font-size:17px; }
		#tbl td:nth-child(1){ width:80px; }
		#tbl td:nth-child(2){ width:350px; }
		input[readonly]{
			background:#DDD;
			color:#333; border:1px solid #666;
		}
		div.layui-layer-title{
			background:#666; color:#EEE;
			font-size:15px;
		}
		
		label.layui-form-label{
			width:60px;
			padding-left:5px;
			padding-right:10px;
		}		
		.layui-form-item .layui-inline {
			margin-right:0px;
		}		
	</style>
</head>
<body>
 
 <form class="layui-form">
	 <div class="layui-form-item" style="margin-bottom:5px;">
	   
	    <div class="layui-inline">
	      <label class="layui-form-label">部门名</label>
	      <div class="layui-input-inline">
	        <input type="text" name="frmName" 
	        	id="frmName" autocomplete="off"
	        	class="layui-input">
	      </div>
	    </div>
	    
	    <label class="layui-form-label">上层部门</label>
   <div class="layui-input-inline">
     <select name="frmParentId" id="frmParentId">
     </select>
   </div>
  
	<button type="button" 
		class="layui-btn" onclick="testSearch();"
		style="margin-left:10px;">立即搜索</button>
	<button type="reset" 
		class="layui-btn layui-btn-normal"
		style="margin-left:10px;">重置</button>
	</div>

 
</form>

	
<script>
function updateSelect( id ){
	var $sel = $( id );
	var $dd = $sel.parent().find("dd");
	for(var idx=0; idx<$dd.size(); idx++){
		var obj = $dd[ idx ];
		if( $(obj).hasClass('layui-this') ){
			var op = $( id +" option")[idx];
			$(op).attr("selected",true);
		}		
	}
}

var schFlds = [
  {key:'deptName',id:'#frmName'},
  {key:'parentId',id:'#frmParentId'}
];

function testSearch(){
	//{2}创建一个条件对象 [传给后端的]
	var cond = {};   //花括号表示
	//{3}迭代  要搜索的字段	
	for( var i=0; i<schFlds.length; i++ ){
		var key = schFlds[i].key;
		var itemId = schFlds[i].id;    //表单项的id
		var val = $( itemId ).val();   //取表单项值 。
		if( val!="" ){
			//{4}使用 键值法来设置对象的 key, value
			//{4}设置到条件中 cond
			cond[ key ] = val;
		}		
	}
	console.log("搜索条件...");
	console.log( cond );	
	//{3}提交数据到后台, 重新渲染表格。
	//   底层使用 ajax 请求 url
	layui.table.reload(
		'DeptTbl',       /* 表格 id */
		{
			url:'${ctxPath}/Dept/jsonList',  /* 数据接口 */
			where:cond,  /* 搜索条件  */
			page:{
				curr:1   /* 默认第 1 页 */
			}
		}	
	);	
}
</script>

<table class="layui-hide" id="demo" lay-filter="test">
</table>
 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${ctxPath}/static/layui/layui.js?t=1554901098009" charset="utf-8">
</script>
<script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctxPath}/static/js/form.js"></script>

<script>

var dept_data = [];   //新建 dept

function initDeptData(){
	$.ajax(
		{
			url:'${ctxPath}/Dept/jsonDeptOptions',
			type:'get',
			dataType:'json',
			success:function(json){
				var ops = json['deptJson'];
				$("#frmParentId").append(
					"<option value=\"\">请选择部门</option>"		
				);
				for(var i=0; i<ops.length; i++){
					var op = document.createElement("option");
					op.value = ops[i]['val'];
					op.text = ops[i]['text'];
					$("#frmParentId").append(op);
				}
				layui.form.render('select');
			}
		}
	);
}
 
initDeptData();
</script>



<script>
layui.config({
  version: '1554901098009' //为了更新 js 缓存，可忽略
});
 
layui.use(
	['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], 
	function(){
	  var laydate = layui.laydate //日期
	  ,laypage = layui.laypage //分页
	  ,layer = layui.layer //弹层
	  ,table = layui.table //表格
	  ,carousel = layui.carousel //轮播
	  ,upload = layui.upload //上传
	  ,element = layui.element //元素操作
	  ,slider = layui.slider //滑块
  
  //{ps} ?op=update  ?op=add
  var op = "${param['op']}";
  if( op=='add' ){
	  layer.msg('添加部门成功..');
  }else if(op=='update'){
	  layer.msg('更新部门成功..');
  }else {
	  layer.msg('正在加载数据..');
  }
  
  //{ps} 监听Tab切换
  element.on('tab(demo)', function(data){
    layer.tips('切换了 '+ data.index +': '+ this.innerHTML, this, {
      tips: 1
    });
  });
  
  //{ps} 执行一个 table 实例
  var ran = Math.random();
  table.render({
    elem: '#demo'
    ,height: 450
    ,url: '${ctxPath}/Dept/jsonList?ran='+ ran   //数据接口
    ,title: '部门表'
    ,page: true     //{ps} 开启分页
    ,id: 'DeptTbl'
     //{ps} 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
     //toolbar: 'default'
    ,totalRow: false   //{ps} 开启合计行
    ,cols: [[          //{ps} 表头
      {type: 'checkbox', fixed: 'left'},
      
      /*------------------ [改动] -------------------*/
      {field:'id', title: 'ID', width:80, sort: true, fixed: 'left'},
      {field:'deptname', title: '部门名称', width:120},
      {field:'deptdesc', title: '部门描述', width:120},
      {field:'parentName', title: '上层部门', width:120},
      {field:'level', title: '等级', width:120},
      /*------------------ [改动] -------------------*/      
      
      {fixed: 'right', width: 185, align:'center', toolbar: '#barDemo'}
    ]]
  });
  
  //{ps} 监听头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id)
    ,data = checkStatus.data; //获取选中的数据
    switch(obj.event){
      case 'add':
        layer.msg('添加');
      break;
      case 'update':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else if(data.length > 1){
          layer.msg('只能同时编辑一个');
        } else {
          layer.alert('编辑 [id]: '+ checkStatus.data[0].id);
        }
      break;
      case 'delete':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else {
          layer.msg('删除[id]: '+ checkStatus.data[0].id);
        }
      break;
    };
  });
  
  //{ps} 监听行工具事件
  //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
  table.on('tool(test)', function(obj){ 
    var data = obj.data     //获得当前行数据
    , layEvent = obj.event; //获得 lay-event 对应的值
    
    if(layEvent === 'detail'){
      layer.msg('查看操作');
    } else if(layEvent === 'del'){
      layer.confirm('真的删除部门么', function(index){
		delDept(data['id']);
      });
    } else if(layEvent === 'edit'){
    	layer.msg('你点击了编辑部门..');
    	//先获得部门列表
    	getDeptData(data['id']);
    }
  });
  
  //执行一个轮播实例
  carousel.render({
    elem: '#test1'
    ,width: '100%' //设置容器宽度
    ,height: 200
    ,arrow: 'none' //不显示箭头
    ,anim: 'fade' //切换动画方式
  });
  
  //将日期直接嵌套在指定容器中
  var dateIns = laydate.render({
    elem: '#laydateDemo'
    ,position: 'static'
    ,calendar: true //是否开启公历重要节日
    ,mark: { //标记重要日子
      '0-10-14': '生日'
      ,'2018-08-28': '新版'
      ,'2018-10-08': '神秘'
    } 
    ,done: function(value, date, endDate){
      if(date.year == 2017 && date.month == 11 && date.date == 30){
        dateIns.hint('一不小心就月底了呢');
      }
    }
    ,change: function(value, date, endDate){
      layer.msg(value)
    }
  });
  
  //分页
  laypage.render({
    elem: 'pageDemo' //分页容器的id
    ,count: 8        //总页数
    ,skin: '#1E9FFF' //自定义选中色值
    ,limit: 3
    //,skip: true    //开启跳页
    ,jump: function(obj, first){
      if(!first){
        layer.msg('第'+ obj.curr +'页', {offset: 'b'});
        alert( "TTTTTTTTT" );
      }
    }
  });
  
  //上传
  upload.render({
    elem: '#uploadDemo'
    ,url: '' //上传接口
    ,done: function(res){
      console.log(res)
    }
  });
  
  slider.render({
    elem: '#sliderDemo'
    ,input: true //输入框
  });

});
</script>

<!-- {ps} 编写我们的 js 代码! -->
<script>
//{选项数据}
var dept_data = [];

//{ps}获取其它部门下拉列表。。
function getDeptData( deptId ){
	$.ajax(
			{
				url:'${ctxPath}/Dept/jsonDeptOptions',
				type:'get',
				data:{id:deptId},
				dataType:'json',
				success:function(json){
					var arr=json['deptJson'];
					var op = {text:"请选择部门",val:""};
					arr.splice(0,0,op);
					console.log(arr);
					gInputs[3]['options'] = arr;
					editDept( deptId );
				}
			}	
		);
}
function editDept( deptId ){
	$.ajax(
			{
				url:'${ctxPath}/Dept/jsonInfo',
				type:'post',
				dataType:'json',
				data:{id:deptId},
				success:function(respTxt){
					if(respTxt['result']=='success'){
						var dept = respTxt['dept'];
						var tbl = makeTable(gInputs,dept);
						showBox(tbl);
					}else{
						layer.msg(respTxt['cause']);
					}
				}
			}	
		);
}

//{ps} 把 makeTable 删除


//{ps}用来生成表单
//title:标题, name:表单项目名, readonly:只读, type:文本,下拉菜单,隐藏域
var gInputs = [
   	{title:"部门名称", name:"deptname", readonly:"readonly", type:"text"},
   	{title:"部门描述", name:"deptdesc", type:"text"},
   	{title:"部门等级", name:"level", type:"text"},
   	{title:"上层部门", name:"parentid", type:"select",options:dept_data},
   	{name:"id", type:"hide"}
];


function showBox( tbl ){
	layer.open({
		type: 1
		,title: '编辑部门'
		,closeBtn: false
		,area: ['500px','395px']
		,shade: 0
		,id: 'LAY_layuipro'   //设定一个 id, 防止重复弹出
		,btn: ['保存部门', '关闭对话框']
		,btnAlign: 'c'
		,moveType: 1          //拖拽模式, 0 或者 1
		,content: tbl
		,yes: function(){
			console.log("{DEBUG} 点击确认保存 ...");
			updateDept();
		}
		,success: function( layero ){
			console.log("{DEBUG} success ...");
		}
	});
}

var items = [
	"id","parentid", "deptname",  "deptdesc" , "level"
];

//{ps} 抓取表单数据
function pickData(){
	//定义一个对象, 存放所有数据。
	var obj = {};
	for( var i=0; i<items.length; i++ ){
		var id = items[i];  //获取表单项 id
		var val = $("#"+id).val();
		//{3}填入 obj 对象
		obj[ id ] = val;		
	}
	return obj;
}

/* 
 *  函数: updateDept
 *  提交数据, 通过 ajax 对象。
 *	提交地址: /Dept/save
 */
function updateDept(){
	var obj = pickData();
	$.ajax(
			{
				url:'${ctxPath}/Dept/save',
				type:'post',
				dataType:'json',
				data:obj,
				success:function(respTxt){
					if(respTxt['result']=='success'){
						layer.msg("修改部门成功:"+obj.deptname);
						setTimeout(reloadPage( 'update' ),2000);
					}else{
						layer.msg(respTxt['cause']);
					}
				}
			}	
		);
}

function reloadPage( op ){
	window.location = '${ctxPath}/Dept/viewList?op='+ op;
}

function delDept( _id ){
	$.ajax(
			{
				url:'${ctxPath}/Dept/delete',
				type:'post',
				dataType:'json',
				data:{id:_id},
				success:function(respTxt){
					if(respTxt['result']=='success'){
						layer.msg("删除部门成功");
						setTimeout(reloadPage( 'update' ),2000);
					}else{
						layer.msg(respTxt['cause']);
					}
				}
			}	
		);
}
</script>



</body>
</html>        
