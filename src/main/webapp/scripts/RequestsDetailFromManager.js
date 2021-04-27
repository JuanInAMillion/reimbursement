function grabRequestsFromJson(){
	let table = document.getElementById('reimbTableApproved')
    let xhr = new XMLHttpRequest() //readyState 0
	
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let request = JSON.parse(xhr.response)
			
			if(request !== null){
				document.getElementById("reimbReqId").innerText = request.reimbReqId
				document.getElementById("employeeId").innerText = request.employeeId
				document.getElementById("location").innerText = request.location
				document.getElementById("cost").innerText = "$"+request.cost
				document.getElementById("description").innerText = request.description
				if(request.statusId == 1){
	            	document.getElementById("statusId").innerText = 'Pending'; 					
				}else if(request.statusId == 2){
	            	document.getElementById("statusId").innerText = 'Approved'; 										
				}else if(request.statusId == 3){
	            	document.getElementById("statusId").innerText = 'Denied'; 															
				}
				let requestDate = new Date(request.requestDate).toDateString()
				document.getElementById("requestDate").innerText = requestDate
				if(request.approvalDate !== null){
					let approvalDate = new Date(request.approvalDate).toDateString()
					document.getElementById("approvalDate").innerText = approvalDate
				}
						
				let receipt = document.getElementById("receipt")
				receipt.setAttribute("src", "data:image/jpeg;base64," +request.receipt)
	        	if(request.statusId == 1){
	    			document.getElementById('optionid').style.display = "block";
	    			document.getElementById('submit').style.display = "block";
	    			document.getElementById("reqid").value = request.reimbReqId;
					receipt.setAttribute("height", "180")
					receipt.setAttribute("width", "320")				
				}else{
	    			document.getElementById('optionid').style.display = "none";
	    			document.getElementById('submit').style.display = "none";
					receipt.setAttribute("height", "210")
					receipt.setAttribute("width", "350")				
	        	}						
			}
        }
    }
	var url = "/reimbursement/app/manager/ReimbRequestDetailShow"
    xhr.open('GET', url, true) //readyState 1
	xhr.send()
}

function validateOption(event){
}

// Let's create an event listener for the form's button
let button = document.getElementById('submit')
button.addEventListener('click', validateOption)

document.getElementById("form_req_option").addEventListener("submit", function(event){
  	let select = document.getElementById('optionid')
    //window.alert("select value."+select.value)
    if(select.value == 1){
    	window.alert("Choose option to do.")
	    if(event.cancelable){
	        event.preventDefault();    //stop form from submitting
	    }
    }
});


//Because we want these cards to appear as soon as the web page loads, we can take advantage of the window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    grabRequestsFromJson();
}