<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="ensias.teams.buzinessLayer.*"%> 
<%@page import="ensias.teams.dao.*"%> 
<%
User user = (User)session.getAttribute("CurrentUser");
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Equipe</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>
<style>
    ::-webkit-scrollbar-track {
    width: 5px;
    background: #f5f5f5;
  }
  
  ::-webkit-scrollbar-thumb {
    width: 1em;
    background-color: #ddd;
    outline: 1px solid slategrey;
    border-radius: 1rem;
  }
  
  .text-small {
    font-size: 0.9rem;
  }
  
  .messages-box,
  .chat-box {
    height: 510px;
    overflow-y: scroll;
  }
  
  .rounded-lg {
    border-radius: 0.5rem;
  }
  
  input::placeholder {
    font-size: 0.9rem;
    color: #999;
  }

.card-box {
    padding: 20px;
    border-radius: 3px;
    margin-bottom: 30px;
    background-color: #fff;
}

.file-man-box {
    padding: 20px;
    border: 1px solid #e3eaef;
    border-radius: 5px;
    position: relative;
    margin-bottom: 20px
}

.file-man-box .file-close {
    color: #f1556c;
    position: absolute;
    line-height: 24px;
    font-size: 24px;
    right: 10px;
    top: 10px;
    visibility: hidden
}

.file-man-box .file-img-box {
    line-height: 120px;
    text-align: center
}

.file-man-box .file-img-box img {
    height: 64px
}

.file-man-box .file-download {
    font-size: 32px;
    color: #98a6ad;
    position: absolute;
    right: 10px
}

.file-man-box .file-download:hover {
    color: #313a46
}

.file-man-box .file-man-title {
    padding-right: 25px
}

.file-man-box:hover {
    -webkit-box-shadow: 0 0 24px 0 rgba(0, 0, 0, .06), 0 1px 0 0 rgba(0, 0, 0, .02);
    box-shadow: 0 0 24px 0 rgba(0, 0, 0, .06), 0 1px 0 0 rgba(0, 0, 0, .02)
}

.file-man-box:hover .file-close {
    visibility: visible
}
.text-overflow {
    text-overflow: ellipsis;
    white-space: nowrap;
    display: block;
    width: 100%;
    overflow: hidden;
}
h5 {
    font-size: 15px;
}


p {
  font-family: 'Poppins', sans-serif;
  font-size: 1.1em;
  font-weight: 300;
  line-height: 1.7em;
  color: #999;
}

a,
a:hover,
a:focus {
  color: inherit;
  text-decoration: none;
  transition: all 0.3s;
}

.navbar {
  padding: 15px 10px;
  background: #fff;
  border: none;
  border-radius: 0;
  margin-bottom: 40px;
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
}

.navbar-btn {
  box-shadow: none;
  outline: none !important;
  border: none;
}

.line {
  width: 100%;
  height: 1px;
  border-bottom: 1px dashed #ddd;
  margin: 40px 0;
}
/* ---------------------------------------------------
SIDEBAR STYLE
----------------------------------------------------- */

#sidebar {
  width: 250px;
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  z-index: 999;
  background: #7386D5;
  color: #fff !important;
  transition: all 0.3s;
}

#sidebar.active {
  margin-left: -250px;
}

#sidebar .sidebar-header {
  padding: 20px;
  background: #6d7fcc;
}

#sidebar ul.components {
  padding: 20px 0;
  border-bottom: 1px solid #47748b;
}

#sidebar ul p {
  color: #fff;
  padding: 10px;
}

#sidebar ul li a {
  padding: 10px;
  font-size: 1.1em;
  display: block;
  color:white;
}

#sidebar ul li a:hover {
  color: #7386D5;
  background: #fff;
}

ul ul a {
  font-size: 0.9em !important;
  padding-left: 30px !important;
  background: #6d7fcc;
}

ul.CTAs {
  padding: 20px;
}

ul.CTAs a {
  text-align: center;
  font-size: 0.9em !important;
  display: block;
  border-radius: 5px;
  margin-bottom: 5px;
}

a.download {
  background: #fff;
  color: #7386D5;
}

a.article,
a.article:hover {
  background: #6d7fcc !important;
  color: #fff !important;
}
/* ---------------------------------------------------
CONTENT STYLE
----------------------------------------------------- */

/* ---------------------------------------------------
MEDIAQUERIES
----------------------------------------------------- */

@media (max-width: 768px) {
  #sidebar {
    margin-left: -250px;
  }
  #sidebar.active {
    margin-left: 0;
  }
  #content {
    width: 100%;
  }
  #content.active {
    width: calc(100% - 250px);
  }
  #sidebarCollapse span {
    display: none;
  }
}
</style>


<script>
    function ShowMember(){
    document.getElementById("file").hidden=true;
    document.getElementById("chat").hidden=true;
    document.getElementById("ShowMember").hidden=false;
    console.log("New Membre");
  }
  function ShowEquipe(){
      console.log("Equipe");
     // document.getElementById("ShowMember").hidden=true;
     // document.getElementById("ShowGroupe").hidden=true;
      document.getElementById("ShowEquipe").hidden=!document.getElementById("ShowEquipe").hidden;
  }
  function ShowGroupe(){
    document.getElementById("ShowMember").hidden=true;
    document.getElementById("ShowEquipe").hidden=true;
    document.getElementById("ShowGroupe").hidden=!document.getElementById("ShowGroupe").hidden;
  }
  function ShowChat(){
    document.getElementById("ShowMember").hidden=true;
    document.getElementById("file").hidden=true;
    document.getElementById("chat").hidden=false;
  }
  function ShowFiles(){
    document.getElementById("ShowMember").hidden=true;
    document.getElementById("chat").hidden=true;
    document.getElementById("file").hidden=false;
  }
</script>

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


                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <div class="container-fluid" id="choice">
                        <!-- Content Row -->
                        <div class="row">
                            <div class="col-xl-3 col-md-9 mb-7">
                                <a href="#" onclick="ShowFiles()">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Fichiers</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                
                            <div class="col-xl-3 col-md-9 mb-7">
                                <a href="#" onclick="ShowChat()">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Chat</div>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>

                            <div class="col-xl-3 col-md-9 mb-7">
                                <a href="#" onclick="ShowMember()">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Les Membres</div>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>


                        </div>
                    </div>

                    <div class="ShowMember container" id="ShowMember" hidden="true" >
                        <div class="table-responsive">
                            <div class="table-wrapper">
                                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <h2>Les <b>Membres</b></h2>
                                        </div>
                                        <div class="col-sm-6">
                                            <a href="#addMemberModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Ajouter un membre</span></a>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>
                                                <span class="custom-checkbox">
                                                    <input type="checkbox" id="selectAll">
                                                    <label for="selectAll"></label>
                                                </span>
                                            </th>
                                            <th>Nom Complet</th>
                                            <th>Email</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                     // print users List !! 
                                        
                
                        ArrayList<User> users= new ArrayList<User>();
                        users=(ArrayList<User>) session.getAttribute("TeamMembers");
                         if(user!=null)
                                        for(int i=0;i<users.size();i++){
                                        %>
                                                <tr>  
                                                <td>
                                                    <span class="custom-checkbox">
                                                        <input type="checkbox" id="checkbox2" name="options[]" value="1">
                                                        <label for="checkbox2"></label>
                                                    </span>
                                                </td>    
                                                <td><% out.print(users.get(i).firstName+" "+users.get(i).lastName); %></td>
                                                <td><%= users.get(i).email %></td> 
                                                 <td>
                                                    <a  id="<%= users.get(i).email %>" href="#deleteMemberModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                                </td>
                                            </tr>
                                            <%} %>
                                        <tr>
                                    </tbody>
                                </table>
                                
                            </div>
                        </div>
                        
                      
               
                    <!-- Edit Modal HTML -->
                    <div id="addMemberModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                
                                <form action="ShowTeam" method="post">
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Ajouter un membre</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <div class="form-group">
                                            <label>email</label>
                                            <input type="text" class="form-control" required name="email">
                                        </div>					
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Annuler">
                                        <input type="submit" class="btn btn-success" value="Ajouter">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Delete Modal HTML -->
                    <div id="deleteMemberModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form>
                                    <div class="modal-header">						
                                        <h4 class="modal-title">Supprimer un membre</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <p>Voulez-vous vraiment supprimer ce membre ?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Annuler">
                                        <input type="submit" class="btn btn-danger" value="Supprimer">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    </div>
                    
                    
                    <div class="chat" id="chat" !hidden >
                        
                        <div class="container">
                            <div class="px-0">
                              <div class="px-4 py-5 chat-box bg-white">
                                <!-- Sender Message-->
                                <div class="media w-50 mb-3"><img src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg" alt="user" width="50" class="rounded-circle">
                                  <div class="media-body ml-3">
                                    <div class="bg-light rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-muted">Test which is a new approach all solutions</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                                <!-- Reciever Message-->
                                <div class="media w-50 ml-auto mb-3">
                                  <div class="media-body">
                                    <div class="bg-primary rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-white">Test which is a new approach to have all solutions</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                                <!-- Sender Message-->
                                <div class="media w-50 mb-3"><img src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg" alt="user" width="50" class="rounded-circle">
                                  <div class="media-body ml-3">
                                    <div class="bg-light rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-muted">Test, which is a new approach to have</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                                <!-- Reciever Message-->
                                <div class="media w-50 ml-auto mb-3">
                                  <div class="media-body">
                                    <div class="bg-primary rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-white">Apollo University, Delhi, India Test</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                                <!-- Sender Message-->
                                <div class="media w-50 mb-3"><img src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg" alt="user" width="50" class="rounded-circle">
                                  <div class="media-body ml-3">
                                    <div class="bg-light rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-muted">Test, which is a new approach</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                                <!-- Reciever Message-->
                                <div class="media w-50 ml-auto mb-3">
                                  <div class="media-body">
                                    <div class="bg-primary rounded py-2 px-3 mb-2">
                                      <p class="text-small mb-0 text-white">Apollo University, Delhi, India Test</p>
                                    </div>
                                    <p class="small text-muted">12:00 PM | Aug 13</p>
                                  </div>
                                </div>
                        
                              </div>
                        
                              <!-- Typing area -->
                              <form action="#" class="bg-light">
                                <div class="input-group">
                                  <input type="text" placeholder="Type a message" aria-describedby="button-addon2" class="form-control rounded-0 border-0 py-4 bg-light">
                                  <div class="input-group-append">
                                    <button id="button-addon2" type="submit" class="btn btn-link"> <i class="fa fa-paper-plane"></i></button>
                                  </div>
                                </div>
                              </form>
                        
                            </div>
                          </div>
                    </div>
                    <div class="file" id="file" hidden="true" >
                        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
                        <div class="content">
                          <div class="container">
                              <div class="row">
                                  <div class="col-12">
                                      <div class="card-box">
                                          <div class="row">
                                              <div class="col-lg-6 col-xl-6">
                                                  <h4 class="header-title m-b-30">My Files</h4>
                                              </div>
                                          </div>
                        
                                          <div class="row">
                                          <% for(int i = 0 ; i < 10 ; i ++ ){ %>
                                              <div class="col-lg-3 col-xl-2">
                                                  <div class="file-man-box"><a href="" class="file-close"><i class="fa fa-times-circle"></i></a>
                                                      <div class="file-img-box"><img src="https://coderthemes.com/highdmin/layouts/assets/images/file_icons/pdf.svg" alt="icon"></div><a href="#" class="file-download"><i class="fa fa-download"></i></a>
                                                      <div class="file-man-title">
                                                          <h5 class="mb-0 text-overflow">invoice_project.pdf</h5>
                                                          <p class="mb-0"><small>568.8 kb</small></p>
                                                      </div>
                                                  </div>
                                              </div>
                                              <% } %>
                                          </div>
                                          
                                          <form action="uploadToDB">
	                                          <div class="text-center mt-3">
	                                              <button type="button" class="btn btn-outline-danger w-md waves-effect waves-light"><i class="mdi mdi-refresh"></i> Load More Files</button>
	                                          </div>                                          
                                          </form>
                                          
                                      </div>
                                  </div>
                                  <!-- end col -->
                              </div>
                              <!-- end row -->
                          </div>
                          <!-- container -->
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="Deconnexion">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>