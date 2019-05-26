var htmlTemplate = 
    "<div class=\"card bg-light\">"
	+ "<div class=\"card-header\">"
	+    "%DAY%"
	+ "</div>"
	+ "<div class=\"card-body\">"
	+     "<blockquote class=\"blockquote mb-0\">"
	+       "<p>%COMMENT%</p>"
	+       "<p>上班: <span id=\"work\">%START%</span> 下班: <span id=\"rest\">%END%</span></p>"
	+       "<a href=\"###\" onclick=\"showPopup(%ID%)\" class=\"btn btn-primary\">EDIT</a>"
	+       "<input type=\"button\" value=\"DELETE\" onclick=\"deleteNote(%ID%)\" class=\"btn btn-danger\">"
	+     "</blockquote>"
	+ "</div>"
+  "</div>";

function showListItems() {
	var url = "get_list.do";
	$.ajax({
		url: url,
		type: "GET",
		dataType: "json",
		success: function(jsonObj) {
			$('#listItems').empty();
			var htmlString = "";
			for(var i = 0 ; i < jsonObj.data.length ; i++) {
				var note = jsonObj.data[i];
				htmlString += htmlTemplate;
				htmlString = htmlString.replace("%DAY%", note.day);
				htmlString = htmlString.replace("%COMMENT%", note.comment);
				htmlString = htmlString.replace("%START%", note.start);
				htmlString = htmlString.replace("%END%", note.end);
				htmlString = htmlString.replace(/%ID%/g, note.id);
			}
			$('#listItems').html(htmlString);
		}
	});
};

function deleteNote(id) {
	var checkIfDelete = confirm('確定要刪除嗎?');
	var url = "handle_delete.do";
	var data = "id=" + id;
	if(checkIfDelete) {
    	$.ajax({
    		url: url,
    		data: data,
    		type: "GET",
    		dataType: "json",
    		success: function(jsonObj) {
    			if(jsonObj.state != 1) {
    				alert(jsonObj.message);
    			}
    			showListItems();
    		},
    		error: function() {
    			alert("登入訊息已過期, 請重新登入!");
    			location.href = "../user/login.do";
    		}
    	});
	}
};

function showPopup(id) {
	// 清空表單中各控件已有的值
	$("#form-edit")[0].reset();
	
	// 將id設置到隱藏域中, 以便於後須一併提交
	$("#id").val(id);

    // popup mask (use json format for css)
    $("#mask").css({
        "width": $(document).width(),
        "height": $(document).height()
    });
    
    $("#popup_content").css({
    	"left": ($(document).width() - $('#popup_content').width()) / 2 - 15,
        "top": 90
    });
    
    
    $("#mask").show();
    $("#popup_content").show();
    
    // 發出AJAX請求獲取需要編輯的數據, 並顯示到各控件中
    // 按照編輯模式處理頁面
    if(id != 0) {
    	var url = "get_note.do";
    	var data = "id=" + id;
    	$.ajax({
    		"url": url,
    		"data": data,
    		"type": "GET",
    		"dataType": "json",
    		"success": function(jsonObj) {
    			var note = jsonObj.data;
    			$("#day").val(note.day);
    			$("#comment").val(note.comment);
    			$("#popup_content #work").val(note.start);
    			$("#popup_content #rest").val(note.end);
    		}
    	});
    } 
    
}

// 隱藏彈出窗口
function dismissPopup() {
    $("#mask").hide();
    $("#popup_content").hide();
}

function postForm() {
	var url = "handle_edit.do";
	var data = $("#form-edit").serialize();
	// 發出請求並處理響應
	$.ajax({
		"url": url,
		"data": data,
		"type": "POST",
		"dataType": "json",
		"success": function(jsonObj) {
			alert(jsonObj.message);
			dismissPopup();
			showListItems();
			
		},
		"error": function() {
			alert("登錄信息已經過期!請重新登錄!");
			location.href = "../user/login.do";
		}
	});
}
