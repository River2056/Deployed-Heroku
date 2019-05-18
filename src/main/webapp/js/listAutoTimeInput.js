// auto time input buttons
function autoTimeInput() {
	var crtTime = new Date();
	var workHr = crtTime.getHours();
	var workMin = crtTime.getMinutes();
	var workSec = crtTime.getSeconds();
	if (workHr < 10) { 
		workHr = "0" + workHr; 
    } 
    if (workMin < 10) { 
    	workMin = "0" + workMin; 
    } 
    if (workSec < 10) {
    	workSec = "0" + workSec; 
    }
	var workTime = workHr + ":" + workMin + ":" + workSec;
	return workTime;
}


$('#popup_content #work-time').click(function() {
	var line = autoTimeInput();
	$('#popup_content #work').val(line);
});

$('#popup_content #rest-time').click(function() {
	var line = autoTimeInput();
	$('#popup_content #rest').val(line);
});