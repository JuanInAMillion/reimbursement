/**
 * 
 */
function grabRequestsFromJson(){
    let xhr = new XMLHttpRequest() //readyState 0
	
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let employee = JSON.parse(xhr.responseText)
			//alert("In pfofile username:" +employee["username"]+ " "+employee.username)
			if(employee !== null){
				document.getElementById("username").value = employee.username
				document.getElementById("password").value = employee.password
				document.getElementById("firstname").value = employee.firstname
				document.getElementById("lastname").value = employee.lastname
				document.getElementById("email").value = employee.email
	           	document.getElementById("workyears").value = employee.workyears									
			}
        }
    }
	var url = "/reimbursement/app/manager/profileShow"
    xhr.open('GET', url, true) //readyState 1
	xhr.send()
}

//Because we want these cards to appear as soon as the web page loads, we can take advantage of the window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    grabRequestsFromJson();
}