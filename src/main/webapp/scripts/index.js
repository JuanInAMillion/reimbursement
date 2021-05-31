function loadDoc() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    if(username && password){
	    var xhttp = new XMLHttpRequest();
	    
	    xhttp.onreadystatechange = function() {
	        if (this.readyState === 4 && this.status != 200) {
	          	let msgRet= "Invalid user credentials";
	            /*document.getElementById("message").innerHTML = msgRet;*/                    
	           
	            alert(msgRet);
	           
	            /*
	            alert("readyState: "+this.readyState);
	            alert("status : "+this.status);            
	            */
	        }
	    }
	    
    	xhttp.open('post', '/reimbursement/app/login');
    	xhttp.send();
    }
    
}

window.onload = () => {
    loadDoc();
}