function grabRequestsFromJson(){
    let xhr = new XMLHttpRequest() //readyState 0
	
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let request = JSON.parse(xhr.response)
			
			//alert(request.reimbReqId)
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
			//document.getElementById("userImage").src = "data:image/jpeg;base64," + data.userImage;
        }
    }
	var url = "/reimbursement/app/employee/ReimbRequestDetailShow"
    xhr.open('GET', url, true) //readyState 1
	xhr.send()
}

//Because we want these cards to appear as soon as the web page loads, we can take advantage of the window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    grabRequestsFromJson();
}