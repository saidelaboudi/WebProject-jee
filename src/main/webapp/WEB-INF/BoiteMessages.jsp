<%@ page import="ensias.teams.buzinessLayer.ChatPersoUser" %>
<%@ page import="ensias.teams.buzinessLayer.Message" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="css/css.css" rel="stylesheet">
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.5.1.min.js"></script>
</head>

<body>
  <div id='main' class="row rounded-lg overflow-hidden shadow">
    <!-- Users box-->
    <div class="col-2 px-0">
      <div class="bg-white">
      	
        <div class="bg-gray px-4 py-2 bg-light" >
          <p  style='align-items: left;text-align: center;' class="h5 mb-0 py-1"> Recent</p>
          <div class='d-flex align-items-center justify-content-between mb-1'  style='display:flex'>          		
    			<input id='input' style='width: 80%;' class='d-flex align-items-center justify-content-between mb-1' type="text" placeholder="Search.." >
               
                <button class=' list-group-item-light 'type="button" id='searchButton' style='width: 20%;' onclick='find()'> <i class="fa fa-search" aria-hidden="true"></i> </button>
                <button class=' list-group-item-light 'type="button" id='cancelButton' style='width: 20%;display:none;' onclick='cancel()'> <i class="fa fa-times" aria-hidden="true"></i> </button>
       			
          </div>
        </div>
	
        <div  id="messages-box" class="messages-box" >
          <div id='usersChat' class="list-group rounded-0" >
            <% 
		      	List<ChatPersoUser> users = (List)request.getAttribute("usersMessagerie");
			      for ( ChatPersoUser u : users){
				      out.println("<a class='list-group-item list-group-item-action list-group-item-light rounded-0' id='a"+u.getOtherUser().id+"' onclick='show(this)' >"
				            +"  <div class='media'>"
				            +"  <div class='media-body ml-4'>"
				       		+"<div class='d-flex align-items-center justify-content-between mb-1'>"
				       		+"<h6 class='mb-0'>"+u.getOtherUser().firstName + " " + u.getOtherUser().lastName +"</h6><small id=\"small" +u.getOtherUser().id +"\" class='small font-weight-bold'></small>"
				       	    +"</div></div></div></a>"		
				   		);
			      }
		      %>
          </div>
        </div>
        <!-- Box users in research  -->
        <div   id="searchUsers" class="messages-box" style ='display:none'	>
          <div id='usersDisplay' class="list-group rounded-0" >
          
          
          </div>
        </div>
      </div>
    </div>
    <!-- Chat Box-->
    <%
     for ( ChatPersoUser u : users){
    	out.println("<div style='display: none;' class='chatsMain col-7 px-0'  id='chatsMain"+u.getOtherUser().id +"'>" +
    			"<div  class='bg-light' style='text-align:center;'> <h6 class='mb-0' > "+u.getOtherUser().firstName + " " + u.getOtherUser().lastName +
    			"</h6>"+
    			"<small>"+u.getOtherUser().email+
    			
    			"</small></div>"+	
    		     " <div class='chats px-4 py-5 chat-box bg-white' id='chat"+u.getOtherUser().id +"'>");
    	for ( Message m : u.getMessages()){
    		if ( m.idSender == u.getOtherUser().id ){
    			out.println(" <div class='message media w-50 mb-3' id='"+m.id +"'>"+
        	"<div class='media-body ml-3'>"+
	            "<div class='bg-light rounded py-2 px-3 mb-2'>"+
	             "<p class='text-small mb-0 text-muted'>"+m.content+"</p></div>"+
	            "<p class='small text-muted'>"+m.date+"</p></div></div>");
    		}else{
    			out.println("  <div class='message media w-50 ml-auto mb-3' id='"+m.id +"'>"+
    		        	"<div class='media-body'>"+
    			            "<div class='bg-primary rounded py-2 px-3 mb-2'>"+
    			             "<p class='text-small mb-0 text-white'>"+m.content+"</p></div>"+
    			            "<p class='small text-muted'>"+m.date+"</p></div></div>");
    		}
    	}
    	out.println("  </div>"+
    		      "<!-- Typing area -->"+
    		     " <div class='bg-light'>"+
    		        "<div class='input-group'>"+
    		     	"<input name='reponse' id='input"+u.getOtherUser().id+"' type='text' placeholder='Type a message' aria-describedby='button-addon2' class='form-control rounded-0 border-0 py-4 bg-light'>"+
    		         " <div class='input-group-append' name='content' style=' :hover { background-color:powderblue; }' >"+
    		            "<button id='"+u.getOtherUser().id+"'  onclick ='send(this)' class='btn btn-link'> <img  src=\"img/send.png\" alt=\"Send\"></button>"+
    		          "</div> </div></div></div>");
     }
    
    %>
 
  </div>

<script>

function show(ele){
	
	var id = ele.id.substring(1);
	var small = document.getElementById("small"+id);
	small.innerHTML = '';
	var idStr = id.toString();
	var str1 = "chatsMain";
	var x = document.getElementById(str1.concat(idStr));
	var d = document.getElementsByClassName('chatsMain');
	for (i = 0; i < d.length; i++) {
		if ( x.id != d[i].id )
			d[i].style.display = "none";
	}
	if (x.style.display == "block"){
		x.style.display = "none";
	}else{
		x.style.display = "block";
	}
		
}
function send(ele){
	var content= ele.parentNode.parentNode.firstElementChild.value;
	var id = ele.getAttribute('id');
	if ( content.length > 0 ){
		$.post("NewMessage" , {id :id ,content:content },false );
	}
	ele.parentNode.parentNode.firstElementChild.value = '';
	
}
function addUserSearch(usersDisplay , idUser , firstName , lastName , email ) {
	console.log('addUsers called');
	let a = document.createElement('a');
	let div = document.createElement('div');
	let div1 = document.createElement('div');
	let div2 = document.createElement('div');
	let h = document.createElement('h6');
	let small = document.createElement('small');
	
	a.className = 'list-group-item list-group-item-action list-group-item-light rounded-0';
	a.id = "s"+ idUser;
	a.setAttribute('onclick','newMessage(this)');
	a.setAttribute('data-firstName',firstName);
	a.setAttribute('data-lastName',lastName);
	a.setAttribute('data-email',email);
	div.className = 'media';
	div1.className = 'media-body ml-4';
	div2.className = 'd-flex align-items-center justify-content-between mb-1';
	h.className = 'mb-0';
	h.innerHTML = firstName + " " + lastName;
	small.className ='small font-weight-bold';
	small.id = "small" + idUser;
	a.appendChild(div);
	div.appendChild(div1);
	div1.appendChild(div2);
	div2.appendChild(h);
	div2.appendChild(small);
	usersDisplay.appendChild(a);
}
function cancel(){
	var search = document.getElementById('searchButton');
	var cancel = document.getElementById('cancelButton');
	cancel.style.display = 'none';
	search.style.display = 'block';
	
	
	var boxUsers = document.getElementById('messages-box');
	var searchBox = document.getElementById('searchUsers');
	searchBox.style.display = "none";
	boxUsers.style.display = "block";
	usersDisplay.innerHTML = '';
	var input = document.getElementById('input');
	input.value = '';
}
function newMessage(ele){
	var id = ele.id.substring(1);
	if ( !document.getElementById("a"+id) ){
		var firstName = ele.getAttribute('data-firstName');
		var lastName = ele.getAttribute('data-lastName');
		var email = ele.getAttribute('data-email');
		addUser(id , firstName , lastName ,email);
		
	}
	var boxUsers = document.getElementById('messages-box');
	var searchBox = document.getElementById('searchUsers');
	searchBox.style.display = "none";
	boxUsers.style.display = "block";
	usersDisplay.innerHTML = '';
	var input = document.getElementById('input');
	input.value = '';
	
	
	var search = document.getElementById('searchButton');
	var cancel = document.getElementById('cancelButton');
	cancel.style.display = 'none';
	search.style.display = 'block';
	
}


function find(){
	var boxUsers = document.getElementById('messages-box');
	var searchBox = document.getElementById('searchUsers');
	var usersDisplay = document.getElementById('usersDisplay');
	var input = document.getElementById('input');
	if ( input.value.length > 0 ){ 
		var search = document.getElementById('searchButton');
		var cancel = document.getElementById('cancelButton');
		cancel.style.display = 'block';
		search.style.display = 'none'; 
		
		boxUsers.style.display = "none";
		usersDisplay.innerHTML = '';
		searchBox.style.display = "block";
		
		$.post('GeneratorUsers' , {'value' :input.value } , function(data){
			parser = new DOMParser();
			xmlDoc = parser.parseFromString(data,"text/xml");
			var users = xmlDoc.getElementsByTagName('user');
			for ( j = 0 ; j< users.length ; j++){
				var str1 = "chat";
				var idUser = users[j].getAttribute('id');
				var firstName = users[j].getAttribute('firstname');
				var lastName = users[j].getAttribute('lastname');
				var email = users[j].getAttribute('email');
				addUserSearch(usersDisplay , idUser , firstName , lastName , email );
			}
		});
	}
	
}
function addUser(idUser , firstName , lastName ,email){
	var chatBox = document.getElementById("usersChat");
	var main = document.getElementById("main");
	
	
	let a = document.createElement('a');
	let div = document.createElement('div');
	let div1 = document.createElement('div');
	let div2 = document.createElement('div');
	let h = document.createElement('h6');
	let small = document.createElement('small');
	
	a.className = 'list-group-item list-group-item-action list-group-item-light rounded-0';
	a.id =  "a"+idUser;
	a.setAttribute('onclick','show(this)');
	div.className = 'media';
	div1.className = 'media-body ml-4';
	div2.className = 'd-flex align-items-center justify-content-between mb-1';
	h.className = 'mb-0';
	h.innerHTML = firstName + " " + lastName;
	small.className ='small font-weight-bold';
	small.id = "small" + idUser;
	small.innerHTML = 'new';
	a.appendChild(div);
	div.appendChild(div1);
	div1.appendChild(div2);
	div2.appendChild(h);
	div2.appendChild(small);
	
	
	//new Chat content zone for this new user 
	

	let div8 = document.createElement('div');
	let h1   = document.createElement('h6');
	let small1 = document.createElement('small');
	let div3 = document.createElement('div');
	let div4 = document.createElement('div');
	let div5 = document.createElement('div');
	let div6 = document.createElement('div');
	let input = document.createElement('input');
	let div7 = document.createElement('div');
	let button = document.createElement('button');
	let img = document.createElement('img');
	
	div3.appendChild(div8);
	div3.appendChild(div4);
	div3.appendChild(div5);
	div5.appendChild(div6);
	div6.appendChild(input);
	div6.appendChild(div7)
	div7.appendChild(button);
	button.appendChild(img);
	div8.appendChild(h1);
	div8.appendChild(small1);
	
	div8.className = 'bg-light';
	div8.style = 'text-align:center;';
	h1.className = 'mb-0';
	h1.innerHTML = firstName + " " + lastName;
	small1.innerHTML = email;
	
	div3.style='display: none;';
	div3.className='chatsMain col-7 px-0';
	div3.id = "chatsMain" + idUser;
	
	div4.className = 'chats px-4 py-5 chat-box bg-white';
	div4.id = "chat"+idUser;
	
	div5.className = 'bg-light';
	
	div6.className = 'input-group';
	
	input.name = 'reponse';
	input.id = 'input'+idUser;
	input.type = 'text';
	input.placeholder = 'Type a message';
  	input.className = 'form-control rounded-0 border-0 py-4 bg-light';
      
   	div7.className = 'input-group-append';
   	div7.style = ' :hover { background-color:powderblue; }';
      
   	button.id = idUser;
   	button.setAttribute('onclick' ,'send(this)');
   	button.className = 'btn btn-link';
   	
   	img.src = "img/send.png";
   	img.alt = "Send";
   	
   	
   	
	chatBox.appendChild(a);
	main.appendChild(div3);
	
}


$(document).ready(function(){
	
	
	 window.setInterval(function(){
		 function show(ele){
				var id = ele.id;
				var idStr = id.toString();
				var str1 = "chatsMain";
				var x = document.getElementById(str1.concat(idStr));
				var d = document.getElementsByClassName('chatsMain');
				for (i = 0; i < d.length; i++) {
				  d[i].style.display = "none";
				}
				if (x.style.display != "block")
					x.style.display = "block";
			}
		 function addMessage(element ,idMessage ,date, content,side){
			 var str = "me";
			 let div = document.createElement('div');
			 let div2 = document.createElement('div');
			 let div3 = document.createElement('div');
			 let p = document.createElement('p');
			 let p2 =  document.createElement('p');
			 if ( side.localeCompare(str) == 0){
				 div.className = 'message media w-50 ml-auto mb-3';
				 div2.className ='media-body'; 
				 div3.className = 'bg-primary rounded py-2 px-3 mb-2';
				 p.className = 'text-small mb-0 text-white';
			 }else{
				 div.className  = 'message media w-50 mb-3';
				 div2.className = 'media-body ml-3'
				 div3.className = 'bg-light rounded py-2 px-3 mb-2';
				 p.className = 'text-small mb-0 text-muted';
			 }
			 p.innerHTML = content;
			 div.id = idMessage;
			 p2.className = 'small text-muted';
			 p2.innerHTML = date;
			 div.appendChild(div2);
			 div2.appendChild(div3);
			 div3.appendChild(p);
			 div2.appendChild(p2);
		        
			 element.appendChild(div);
		 }
		 	var messages = $(".message");
			var id =0;
			for ( i =0 ; i< messages.length ; i++){
				if ( parseInt(messages[i].id,10) > id ){
					id = messages[i].id;
				}
					
			}
			
			$.post("BoiteMessages" , { maxid : id } , function(data){
				parser = new DOMParser();
				xmlDoc = parser.parseFromString(data,"text/xml");
				
				var users = xmlDoc.getElementsByTagName('user');
				for ( j = 0 ; j< users.length ; j++){
					var str1 = "chat";
					var idUser = users[j].getAttribute('id');
					var firstName = users[j].getAttribute('firstname');
					var lastName = users[j].getAttribute('lastname');
					var email = users[j].getAttribute('email');
					str1 = str1.concat(idUser);
					if ( document.getElementById(str1)){
						var messages = users[j].getElementsByTagName('message');
						for ( i = 0 ; i< messages.length ; i++ ){
							var idMessage = messages[i].getAttribute('id');
							var date = messages[i].getAttribute('date');
							var content = messages[i].innerHTML;
							var side = messages[i].getAttribute('side');
							if ( side == "him"){
								var small = document.getElementById("small"+idUser);
								small.innerHTML = 'new';
							}
							
							addMessage(document.getElementById(str1),idMessage,date,content,side);
							
						}
						
					}else{
						addUser(idUser , firstName , lastName,email);
					}
				}
				
			});
			
			 
			 function addUser(idUser , firstName , lastName ,email){
					var chatBox = document.getElementById("usersChat");
					var main = document.getElementById("main");
					
					
					let a = document.createElement('a');
					let div = document.createElement('div');
					let div1 = document.createElement('div');
					let div2 = document.createElement('div');
					let h = document.createElement('h6');
					let small = document.createElement('small');
					
					a.className = 'list-group-item list-group-item-action list-group-item-light rounded-0';
					a.id =  "a"+idUser;
					a.setAttribute('onclick','show(this)');
					div.className = 'media';
					div1.className = 'media-body ml-4';
					div2.className = 'd-flex align-items-center justify-content-between mb-1';
					h.className = 'mb-0';
					h.innerHTML = firstName + " " + lastName;
					small.className ='small font-weight-bold';
					small.id = "small" + idUser;
					small.innerHTML = 'new';
					a.appendChild(div);
					div.appendChild(div1);
					div1.appendChild(div2);
					div2.appendChild(h);
					div2.appendChild(small);
					
					
					//new Chat content zone for this new user 
					
    			
    				let div8 = document.createElement('div');
    				let h1   = document.createElement('h6');
    				let small1 = document.createElement('small');
					let div3 = document.createElement('div');
					let div4 = document.createElement('div');
					let div5 = document.createElement('div');
					let div6 = document.createElement('div');
					let input = document.createElement('input');
					let div7 = document.createElement('div');
					let button = document.createElement('button');
					let img = document.createElement('img');
					
					div3.appendChild(div8);
					div3.appendChild(div4);
					div3.appendChild(div5);
					div5.appendChild(div6);
					div6.appendChild(input);
					div6.appendChild(div7)
					div7.appendChild(button);
					button.appendChild(img);
					div8.appendChild(h1);
					div8.appendChild(small1);
					
					div8.className = 'bg-light';
					div8.style = 'text-align:center;';
					h1.className = 'mb-0';
					h1.innerHTML = firstName + " " + lastName;
					small1.innerHTML = email;
					
					div3.style='display: none;';
					div3.className='chatsMain col-7 px-0';
					div3.id = "chatsMain" + idUser;
					
					div4.className = 'chats px-4 py-5 chat-box bg-white';
					div4.id = "chat"+idUser;
					
					div5.className = 'bg-light';
					
					div6.className = 'input-group';
					
					input.name = 'reponse';
					input.id = 'input'+idUser;
					input.type = 'text';
					input.placeholder = 'Type a message';
				  	input.className = 'form-control rounded-0 border-0 py-4 bg-light';
				      
				   	div7.className = 'input-group-append';
				   	div7.style = ' :hover { background-color:powderblue; }';
				      
				   	button.id = idUser;
				   	button.setAttribute('onclick' ,'send(this)');
				   	button.className = 'btn btn-link';
				   	
				   	img.src = "img/send.png";
				   	img.alt = "Send";
				   	
				   	
				   	
					chatBox.appendChild(a);
					main.appendChild(div3);
					
				}
			 
			
		}, 1000);
	 
	
});
  
</script>


</body>



</html>