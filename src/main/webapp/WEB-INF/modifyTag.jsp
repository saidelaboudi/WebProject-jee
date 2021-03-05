<%@page import="java.util.ArrayList"%>
<%@page import="ensias.teams.buzinessLayer.Tag"%>
<%@page import="ensias.teams.buzinessLayer.User"%>
<%@page import="ensias.teams.dao.TagDAOImp"%>
<%@page import="ensias.teams.dao.DAOFactory"%>

<%
	User user = (User) request.getSession().getAttribute("_SESSION");
%>
	
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ENSIAS TEAMS</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">


    <div id="wrapper">

        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Index">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">ENSIAS TEAMS<sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="Groups">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Mes Groupes</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="CreateTeam"  data-target="#collapseTwo" aria-expanded="true">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Creer une equipe</span>
                </a>
            </li>
            <hr class="sidebar-divider">

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="CreateTags" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Creer une etiquette</span>
                </a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Messagerie -->
            <li class="nav-item">
                <a class="nav-link" href="BoiteMessages" role="button" >
                    <i class="fas fa-fw fa-table"></i>
                    <span>Messagerie</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>
                        
                        
                        <!-- Nav Item - Messages -->
                         <li class="nav-item dropdown no-arrow mx-1">
                            
                            <a class="nav-link dropdown-toggle" href="BoiteMessages" id="messagesDropdown" role="button">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                            </a>
                            
                        </li>
                        
                        
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                
                                <%
                            		
										out.println(user.firstName + " " + user.lastName);
                                %>
                                </span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">

                                    <a class="dropdown-item" href="Profile">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>

                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Parametres
                                    </a>

                                <div class="dropdown-divider"></div>

                                <a class="dropdown-item" href="Deconnexion" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Deconnexion
                                </a>

                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->


		<%
			if (request.getAttribute("ownerDeleteError") != null){
		%>
			<script>
				alert("vous ne pouvez pas supprimer votre compte des utilisateur de ce Tag");
			</script>
		<%
			}
			else{
				User member = (User)request.getAttribute("member");
				if (member == null && request.getAttribute("marker") == null){
		%>
				<script>
					alert("le membre que vous avez saisit n'existe pas!");
				</script>
		<%
				}
			}
		
	%>
			<div class="container-fluid">

                    <!-- Page Heading -->
	<h2> tag name : 
	<%
	Tag tg = (Tag) request.getAttribute("_TAG");
	out.println(tg.tagName +" " + tg.tagId); %> </h2>
	<section>
		<header>
			<div>
				Add Members from Excel
			</div>
			<div>
				Add Members from another Tag
			</div>
			<div>
				<form method='post' action="ModifyTags">
					<input type="text" name="tagName" value='<% out.println(tg.tagName); %>' style='display:none;'>
					<input type="text" name="Nmember" placeholder="Email du membre a ajouter">
					<input type="submit" value="Ajouter">
				</form>
			</div>
		</header>
		
		<main>
		<br> <br>

			<!-- Begin Page Content -->
			<div class="row">
	<%
			DAOFactory db = DAOFactory.getInstance();
			try{
				ArrayList<User> members = tg.tagged;
				for (User t : members){
		%>
								
                        <!-- Earnings (Annual) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
	                                        <div class="h5 mb-0 font-weight-bold text-gray-800">
	                                        <% out.println(t.email);%>
	                                        </div>
                                        </div>
                                	<form method="post" action="ModifyTags" >
                                        <div class="col-auto">
											<input type="text" name="tagName" value='<% out.println(tg.tagName); %>' style='display:none;'>			
											<input type="text" name="Demail" value="<% out.println(t.email);%>" style='display:none;'/>
											<input type="submit" value="Delete" >
                                        </div>
                                    </form>
                                    </div>
                                </div>
                            </div>
                        </div>                         	
							<%
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							%>
                                   
		</div>
	</main>
		</section>
		</div>
			
</body>
</html>