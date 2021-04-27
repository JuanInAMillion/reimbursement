function grabRequestsFromJson(){
	let tbody = document.getElementById('reimbTbody')
    let xhr = new XMLHttpRequest() //readyState 0
	
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let requests = JSON.parse(xhr.response)

            //We will add all our new cards as divs, so let's create a new div for each
            for(let req of requests){
				let newTR = document.createElement('tr')	
				let reimbReqId = document.createElement('td')
				
				//applicant's button
				let applicantId = document.createElement('td')			
				let applicantForm = document.createElement('form')
				applicantForm.setAttribute("action", "/reimbursement/app/manager/employeeInfo");			
				applicantForm.setAttribute("method", "get");		
				let applicantInput = document.createElement('input')
				applicantInput.setAttribute("type", "hidden")
				applicantInput.setAttribute("name", "employeeId")
				applicantInput.setAttribute("value", req.employeeId)			
				let applicantButton = document.createElement('button')
				applicantButton.setAttribute("class","btn btn-primary")
				applicantButton.innerText=req.employeeId
				applicantForm.append(applicantInput)
				applicantForm.append(applicantButton)
				applicantId.append(applicantForm)
			
				let location = document.createElement('td')
				let cost = document.createElement('td')
				let requestDate = document.createElement('td')
				let approvalDate = document.createElement('td')
				let description = document.createElement('td')
				let receipt_image = document.createElement('td')
				let statusId = document.createElement('td')
				
				reimbReqId.innerText = req.reimbReqId;
				//location.innerText = req.location;
				//location.innerText = "see detail";
				cost.innerText = '$'+req.cost;
				requestDate.innerText = new Date(req.requestDate).toDateString();
				if(req.approvalDate !== null)
					approvalDate.innerText = new Date(req.approvalDate).toDateString();
				//description.innerText = "see detail";
				//receipt_image.innerText = "see detail";
				if(req.statusId == 1){
                	statusId.innerText = 'Pending'; 					
				}else if(req.statusId == 2){
                	statusId.innerText = 'Approved'; 										
				}else if(req.statusId == 3){
                	statusId.innerText = 'Denied'; 															
				}

				let detail = document.createElement('td')
				let form = document.createElement('form')
				form.setAttribute("action", "/reimbursement/app/manager/ReimbRequestDetail");			
				form.setAttribute("method", "get");		
				let input = document.createElement('input')
				input.setAttribute("type", "hidden")
				input.setAttribute("name", "reimbReqId")
				input.setAttribute("value", req.reimbReqId)			
				let button = document.createElement('button')
				button.setAttribute("class","btn btn-primary")
				if(req.statusId == 1){
                	button.innerText="Option"; 					
				}else{
                	button.innerText="Details"; 									
				}
				form.append(input)
				form.append(button)
				detail.append(form)
				
				newTR.append(reimbReqId)
				newTR.append(applicantId)
				newTR.append(location)
				newTR.append(cost)
				newTR.append(requestDate)
				newTR.append(approvalDate)
				newTR.append(description)
				newTR.append(receipt_image)
				newTR.append(statusId)
				newTR.append(detail)
				
				tbody.append(newTR)
            }
            
        }
    }
	var url = "/reimbursement/app/manager/deniedReq"
    xhr.open('GET', url, true) //readyState 1
	xhr.send()
}

//Because we want these cards to appear as soon as the web page loads, we can take advantage of the window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    grabRequestsFromJson();
}