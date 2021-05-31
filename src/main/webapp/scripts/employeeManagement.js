function grabRequestsFromJson(){
	let tbody = document.getElementById('reimbTbody')
    let xhr = new XMLHttpRequest() //readyState 0
	
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let requests = JSON.parse(xhr.responseText)
            //We will add all our new cards as divs, so let's create a new div for each
            for(let req of requests){
            	//alert(req[0]+" "+ req[1]+ " "+req[2])
				let newTR = document.createElement('tr')	
				let employeeId = document.createElement('td')				
				let username = document.createElement('td')
				let firstname = document.createElement('td')
				let lastname = document.createElement('td')
				let email = document.createElement('td')
				let workyears = document.createElement('td')
				
				employeeId.innerText = req[0];
				username.innerText = req[1];
				firstname.innerText = req[2];
				lastname.innerText = req[3];
				email.innerText = req[4];
				workyears.innerText = req[5];
				
				//manager's button
				let managerId = document.createElement('td')			
				let managerForm = document.createElement('form')
				managerForm.setAttribute("action", "/reimbursement/app/manager/employeeInfo");			
				managerForm.setAttribute("method", "get");		
				let managerInput = document.createElement('input')
				managerInput.setAttribute("type", "hidden")
				managerInput.setAttribute("name", "employeeId")
				managerInput.setAttribute("value", req[6])			
				let managerButton = document.createElement('button')
				managerButton.setAttribute("class","btn btn-primary")
				if(req[6] !==null){
					managerButton.innerText=req[6]				
				}else{
					managerButton.innerText="//"
					
				}
				managerForm.append(managerInput)
				managerForm.append(managerButton)
				managerId.append(managerForm)
				
				newTR.append(employeeId)
				newTR.append(username)
				newTR.append(firstname)
				newTR.append(lastname)
				newTR.append(email)
				newTR.append(workyears)
				if(req[6] !==null){
					newTR.append(managerId)				
				}
				tbody.append(newTR)
            }
            
        }
    }
	var url = "/reimbursement/app/manager/employeeManagementShow"
    xhr.open('GET', url, true) //readyState 1
	xhr.send()
}

//Because we want these cards to appear as soon as the web page loads, we can take advantage of the window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    grabRequestsFromJson();
}