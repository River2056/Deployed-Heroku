// comment onfocus clear all class
$('#comment').focus(function() {
	$(this).removeClass();
	$(this).addClass("form-control");
	$('#comment_feedback').html("");
	$('#comment_feedback').removeClass();
});

// comment onblur check if repeat, add classes
$('#comment').blur(function() {
	$.ajax({
		url: "check_comment.do",
		data: "comment=" + $(this).val(),
		type: "GET",
		dataType: "json",
		success: function(jsonObj) {
			if(jsonObj.state != 1) {
				// fail
				$('#comment').addClass('is-invalid');
				$('#comment_feedback').text('內容記事不可為空!');
				$('#comment_feedback').addClass('invalid-feedback');
				
			}
		}
	});
});