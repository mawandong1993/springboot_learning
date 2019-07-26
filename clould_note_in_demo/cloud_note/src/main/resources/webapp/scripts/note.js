var SUCCESS=0;
var ERROR=1;

$(function(){
//	var userId=getCookie("userId");
//	console.log(userId);
	
	//网页加载以后，立即读取笔记本列表
	loadNotebooks();
	/*
	 * on()绑定事件可以区分事件源
	 * click()绑定事件不可以区分事件源
	 */
	//绑定笔记本列表区域的点击事件,加载笔记列表
	$('#notebook-list').on('click','.notebook',loadNotes);
	//绑定点击，加载笔记内容
	$('#note-list').on('click','.note',loadNoteBody);
	
	//添加新笔记窗口
	$('#note-list').on('click','#add_note',showAddNoteDialog);
	//监听添加笔记确认按钮
	$('#can').on('click','.create_note',addNote)
	//监听关闭添加笔记窗口
	$('#can').on('click','.close,.cancel',closeDialog);
	
	//监听保存笔记的点击
	$('#save_note').click(update_note);
	
	//绑定笔记子菜单的触发事件
	$('#note-list').on('click','.btn-note-menu',showNoteMenu);
	$(document).click(hideNoteMenu);
	
	//监听移动，弹出对话框
	$('#note-list').on('click', '.btn_move', showMoveNoteDialog);
	//监听移动确认按钮
	$('#can').on('click','.move-note',moveNote)
	
	//监听删除，弹出对话框
	$('#note-list').on('click', '.btn_delete', showDeleteNoteDialog);
	//监听删除确认按钮
	$('#can').on('click','.delete-note',deleteNote)
	
	
	//监听回收站按钮被点击
	$('#trash_button').click(showTrashBin);
	//监听垃圾列表被点击，给定被选择效果
	$('#trash-bin').on('click','.trash',loadTrash);
	//监听，垃圾删除的方法,弹出删除对话框
	$('#trash-bin').on('click','.btn_delete',showDeleteTrashDialog);
	//监听删除垃圾确认按钮
	$('#can').on('click','.delete-trash',deleteTrash)
	
//	StartHartbat();
	
	//在document对象储存了初始的页号
	$(document).data("page",0);
	loadPageNotebooks();
	$('#notebook-list').on('click','.more',loadPageNotebooks);
});

function loadPageNotebooks(){
	var page=$(document).data('page');
	var userId=getCookie("userId");
	//从服务器拉数据
	var url='notebook/page.do';
	var data={userId:userId,page:page};
	$.getJSON(url,data,function(result){
		if(result.state==0){
			var notebooks=result.data;
			showPageNotebooks(notebooks,page);
			$(document).data('page',page+1);
		}else{
			alert(result.massage)
		}
	});
}

function showPageNotebooks(notebooks,page){
	var ul=$('#notebook-list ul');
	if(page==0){
		ul.empty();
	}else{
		//删除more
		ul.find('.more').remove();
	}
	for(var i=0;i<notebooks.length;i++){
		var notebook=notebooks[i];
		var li=notebookTemplate.replace('[name]',notebook.name);
		li=$(li);
		li.data('notebookId',notebook.id);
		ul.append(li);
	}
	var moreTemplate='<li class="online more">'
										+'<a>'
										+'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i> '
										+'more'
										+'</a>'
									+'</li>';
	ul.append(moreTemplate);
}

//心跳信号
function StartHartbat(){
	var url="user/hart.do";
	setInterval(function(){
		$.getJSON(url,function(result){
			console.log(result.message)
		});
	},5000);
}
function deleteTrash(){
	var li = $('#trash-bin .checked').parent();
	var noteId=li.data('noteId');
	console.log(noteId);
	closeDialog();//关闭对话框!
	url='note/deleteTrash.do';
	var data={"noteId":noteId};
	$.post(url,data,function(result){
		if(result.state==0){
			console.log(result.data);
			$('#trash_button').click();
		}else{
			alert(result.message);
		}
		
	});
}
function showDeleteTrashDialog(){
	$('#can').load('alert/alert_delete_rollback.html');
	$('.opacity_bg').show();
}
function showTrashBin(){
	$('#trash-bin').show();
	$('#note-list').hide();
	$('#trash-bin').find('.contacts-list').empty();
	loadTrashBin();
}
//展示垃圾内容，给定选定效果
function loadTrash(){
	var li=$(this);
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var noteId=li.data('noteId');
	var url='note/body.do';
	var data={"noteId":noteId};
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			$('#input_note_title').val(note.cn_note_title);
			um.setContent(note.cn_note_body);
		}else{
			alert(result.message)
		}
	});
	
}
//加载回收站垃圾
function loadTrashBin(){
	url='note/trash.do';
	data={"userId":getCookie('userId')};
	$.post(url,data,function(result){
		if(result.state==0){
			var notes=result.data;
			for(var i=0;i<notes.length;i++){
				var note=notes[i];
				var ul=$('#trash-bin').find('.contacts-list');
				var li=teashTemplate.replace("[回收站垃圾]",note.title);
				li=$(li);
				li.data("noteId",note.id);
				ul.append(li);
			}
		}else{
			alert(result.message);
		}
	})
}

var 	teashTemplate=
		'<li class="disable trash">'+
			'<a >'+
				'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [回收站垃圾]'+
				'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>'+
				'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button>'+
			'</a>'+
		'</li>'

function deleteNote(){
	var url = 'note/delete.do';
	var noteId = $(document).data('note').id;
	var data={"noteId":noteId};
	$.post(url,data,function(result){
		if(result.state==0){
			//删除成功，移除所选笔记，将第一个设置成默认，否则清空编辑区
			var li = $('#note-list .checked').parent();
			var lis = li.siblings();
			if(lis.size()>0){
				lis.eq(0).click();
			}else{
				$('#input_note_title').val("");
				um.setContent("");
			}
			li.remove();
			closeDialog();//关闭对话框!
		}else{
			alert(result.message)
		}
	});
	closeDialog();//关闭对话框!
}
function showDeleteNoteDialog(){
	var id=$(document).data('note').id;
	if(id){
		$('#can').load('alert/alert_delete_note.html');
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记');
}

//确认移动按钮
function moveNote(){
	var url = 'note/move.do';
	var noteId = $(document).data('note').id;
	var bookId=$('#moveSelect').val();
	if(bookId==$(document).data('notebookId')){
		return;
	}
	var data={'noteId':noteId,'bookId':bookId};
	$.post(url,data,function(result){
		if(result.state==0){
			//移动成功，移除所选笔记，将第一个设置成默认，否则清空编辑区
			var li = $('#note-list .checked').parent();
			var lis = li.siblings();
			if(lis.size()>0){
				lis.eq(0).click();
			}else{
				$('#input_note_title').val("");
				um.setContent("");
			}
			li.remove();
			closeDialog();//关闭对话框!
		}else{
			alert(result.message)
		}
	});
}

//弹出移动对话框
function showMoveNoteDialog(){
	var id=$(document).data('note').id;
	if(id){
		$('#can').load('alert/alert_move.html',showNotebookOptions);
		$('.opacity_bg').show();
		return;
	}
	alert('必须选择笔记');
}
//加载，移动对话框的笔记本列表
function showNotebookOptions(){
	var url = 'notebook/list.do';
	var data={userId:getCookie('userId')};
	$.getJSON(url,data,function(result){
		if(result.state==0){
			var id = $(document).data('notebookId');
			//清除下拉框
			$('#moveSelect').empty();
			var notebooks=result.data;
			for(var i=0;i<notebooks.length;i++){
				var notebook=notebooks[i];
				var opt=$('<option></option>').val(notebook.id).html(notebook.name);
				if(id==notebook.id){
					opt.attr('selected','selected');
				}
				$('#moveSelect').append(opt);
			}
		}else{
			alert(result.message);
		}
	});
}


function hideNoteMenu(){
	$('.note_menu').hide();
}
function showNoteMenu(){
	//找到菜单对象，调用show()方法
	var btn=$(this);
	//如果当前是被选定的笔记项目，就弹出子菜单
	//btn.parent('.checked')获取当前按钮的父元素，并且这个元素必须符合选择器'.checked',
	//如果不符合就返回空的jQuery对象 
	btn.parent('.checked').next().toggle();
	//阻止点击事件的继续传播
	return false;
}

//修改笔记的方法
function update_note(){
	var title=$('#input_note_title').val();
	var body=um.getContent();
	var noteId=$('#myEditor').data('noteId');
	console.log(noteId);
	console.log(title);
	console.log(body);
	var note=$(document).data('note');
	console.log(note);
	var data={
			"noteId":noteId,
			"title":title,
			"body":body
	}
	if(note==null || note.title==null ||  note.body==null ){
		return
	}
	if(title==null || body==null){
		return;
	}
	if(title==note.title&&body == note.body ){
        return
    }
	$.ajax({
		url:"note/updatenote.do",
		type:"post",
		data:data,
		dataType:'json',
		success:function(){
			console.log('修改ok');
			//实现选定效果
			$('#notebook-list ul').find('.checked').parent().click();
			//阻塞一下，然后模拟点击
			setTimeout(function(){
				$('#note-list ul').find('.note').first().click();
			},50);
		},
		error:function(e){}
	});
}

//添加笔记的方法
function addNote(){
	//获取用户ID，笔记本id，笔记本名字
	var note_title=$('#input_note').val();
	var userId=getCookie("userId");
	var notebookId=$('#notebook-list').find('.checked').parent().data('notebookId');
	var note={"note_title":note_title,"userId":userId,"notebookId":notebookId};
//	$.getJSON('note/addnote.do',note,addNoteSuccess);
	$.ajax({
		url:"note/addnote.do",
		type:"post",
		data:note,
		dataType:'json',
		success:addNoteSuccess,
		error:function(e){}
	});
}

//添加笔记成功后实现各种效果
function addNoteSuccess(result){
	if(result.state==0){
		
		var note=result.data;
	  	//实现选定效果
		$('#notebook-list ul').find('.checked').parent().click();
		//阻塞一下，然后模拟点击
		setTimeout(function(){
			$('#note-list ul').find('.note').first().click();
		},50);
		closeDialog();
		
	}else{
		alert(result.message)
	}
	

	
}

function closeDialog(){
	$('#can').empty();
	$('.opacity_bg').hide();
}

function showAddNoteDialog(){
	var id = $(document).data('notebookId');
    if(id){
        $('#can').load('alert/alert_note.html', function(){
            $('#input_note').focus();
        });
        $('.opacity_bg').show();
        return;
    }
    alert('必须选择笔记本!');
}

//加载笔记内容
function loadNoteBody(){
	var li=$(this);
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var noteId=li.data('noteId');
	var url='note/body.do';
	var data={"noteId":noteId};
	$.getJSON(url,data,function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			$('#input_note_title').val(note.cn_note_title);
			um.setContent(note.cn_note_body);
			//生成的时候绑定id
			$('#myEditor').data('noteId',note.cn_note_id);
			//生成的时候，把数据绑定到$(document)
			var note={
				'id':note.cn_note_id,
				'title':note.cn_note_title,
				'body':note.cn_note_body
			};
			$(document).data('note',note);
		}else{
			alert(result.message)
		}
	});
}
//加载笔记列表
function loadNotes(){
	//再点击笔记本列表时，为了显示笔记列表。关闭回收站，打开笔记列表
	$('#trash-bin').hide();
	$('#note-list').show();
	 //在被选定的笔记本li增加选定效果 
	var li=$(this);
	//实现选定效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var notebookId=$(this).data('notebookId');
	$(document).data('notebookId', notebookId);
	var url='note/list.do';
	var data={"notebookId":notebookId};
	$.getJSON(url,data,function(result){
//		console.log(result)
		if(result.state==SUCCESS){
			var notes=result.data;
			showNotes(notes);
		}else{
			alert(result.message)
		}
	});
}

var noteTemplate=
	'<li class="online note">'
		+'<a>'
			+'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [NoteName]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down btn-note-menu"><i class="fa fa-chevron-down"></i></button>'
		+'</a>'
		+'<div class="note_menu" tabindex="-1">'
		 +'<dl>'
				+'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
				+'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
				+'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
			+'</dl>'
		+'</div>'
	+'</li>'

function showNotes(notes){
//	console.log(notes);
	var ul=$('#note-list ul');
	ul.empty();
	for(var i=0;i<notes.length;i++){
		var note=notes[i];
		var li=noteTemplate
		li=li.replace("[NoteName]",note.title);
		li=$(li);
		li.data("noteId",note.id);
		ul.append(li);
	}
	
}


function loadNotebooks(){
	//利用ajax从服务器获取（get）数据
	var url='notebook/list.do';
	var data={userId:getCookie("userId")};
	$.getJSON(url,data,function(result){
//		console.log(result);
		if(result.state==SUCCESS){
			var notebooks=result.data;
			//在showNotebooks方法中将全部的笔记本数据
			//notebooks显示到notebook-list区域
			showNotebooks(notebooks);
		}else{
			alert(result.massage);
		}
	});
}
/*
 * 在notebook-list区域中显示笔记本列表
 */
function showNotebooks(notebooks){
	//找到显示笔记本列表的区域
	//遍历notebooks数组，将为每个对象创建一个li元素，添加到ul区域中
	var ul=$('#notebook-list ul');
	ul.empty();
	for(var i=0;i<notebooks.length;i++){
		var notebook=notebooks[i];
		var li=notebookTemplate.replace('[name]',notebook.name);
		li=$(li);
		//将notebook.id 绑定到li
		li.data('notebookId',notebook.id)
		ul.append(li);
	}
	
}

var notebookTemplate='<li class="online notebook">'
										+'<a>'
										+'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i> '
										+'[name]'
										+'</a>'
									+'</li>';

//重写JS原生alert函数
window.alert=function(e){
    $('#can').load('./alert/alert_error.html',function(){
        $('#error_info').text(' '+e);
        $('.opacity_bg').show();
    });
}