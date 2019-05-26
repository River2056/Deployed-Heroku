var htmlTemplate =
"<li class=\"list-group-item list-group-item-primary\">"
+   "%DAY%" + " " + "%Todo%" 
+   "<input onclick=\"deleteTodo(%ID%)\" type=\"button\" value=\"Done?\" class=\"btn btn-secondary btn-sm\"/>"
+ "</li>";

function showListItems() {
  var url = "get_list.do";
  $.ajax({
    url: url,
    type: "GET",
    dataType: "json",
    success: function (jsonObj) {
      $('#list_group').empty();
      var htmlString = "";
      for (var i = 0; i < jsonObj.data.length; i++) {
        var todos = jsonObj.data[i];
        htmlString += htmlTemplate;
        htmlString = htmlString.replace("%DAY%", todos.day);
        htmlString = htmlString.replace("%Todo%", todos.todo);
        htmlString = htmlString.replace(/%ID%/g, todos.id);
      }
      $('#list_group').html(htmlString);
    }
  });
};

function deleteTodo(id) {
  var url = "handle_delete.do";
  var data = "id=" + id;
  $.ajax({
    url: url,
    data: data,
    type: "GET",
    dataType: "json",
    success: function (jsonObj) {
      if (jsonObj.state != 1) {
        alert(jsonObj.message);
      }
      showListItems();
    },
    error: function () {
      alert("登入訊息已過期, 請重新登入!");
      location.href = "../user/login.do";
    }
  });
};

function clearList() {
	var checkIfDone = confirm("Done all todos?");
	var url = "handle_clear_list.do";
	if(checkIfDone) {
		$.ajax({
			url: url,
			type: "GET",
			dataType: "json",
			success: function(jsonObj) {
				showListItems();
			}, 
			error: function() {
				alert("登錄信息已經過期!請重新登錄!");
			    location.href = "../user/login.do";
			}
		});
	}
}

function showPopup() {
  // 清空表單中各控件已有的值
  $("#form-todo")[0].reset();

  // day auto input value
  var date = new Date();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  var line = month + "-" + day;
  $('#day').val(line);

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
}

// 隱藏彈出窗口
function dismissPopup() {
  $("#mask").hide();
  $("#popup_content").hide();
}

function postForm() {
  var url = "handle_addTodo.do";
  var data = $("#form-todo").serialize();
  // 發出請求並處理響應
  $.ajax({
    "url": url,
    "data": data,
    "type": "POST",
    "dataType": "json",
    "success": function (jsonObj) {
      dismissPopup();
      showListItems();
    },
    "error": function () {
      alert("登錄信息已經過期!請重新登錄!");
      location.href = "../user/login.do";
    }
  });
}

