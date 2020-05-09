<%-- 
    Document   : adminManagerProduct
    Created on : Mar 12, 2020, 12:36:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Simple Sidebar - Start Bootstrap Template</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/simple-sidebar.css" rel="stylesheet">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body>
        <c:if test="${sessionScope.ROLE ne 'admin'}">
            <c:redirect url="index.jsp"/>
        </c:if>
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Welcome ${sessionScope.USERNAME} </div>
                <div class="list-group list-group-flush">
                    <c:url var="addProduct" value="LoadCategoryFood" />
                    <a href="${addProduct}" class="list-group-item list-group-item-action bg-light">Add New product</a>
                    <a href="adminManagerProduct.jsp" class="list-group-item list-group-item-action bg-light">Manager Product</a>
                    <a href="adminManagerUser.jsp" class="list-group-item list-group-item-action bg-light">Manager User</a>
                    <a href="adminViewHistory.jsp" class="list-group-item list-group-item-action bg-light">View History</a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">

                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <form action="LogoutController" method="POST">
                                    <input type="submit" class="nav-link" name="action" value="Logout"/>
                                </form>
                            </li>

                        </ul>
                    </div>
                </nav>

                <div class="container-fluid">
                    <h1 class="mt-4">Food</h1>
                    <!-- Main Content -->
                    <div id="content">
                        <!-- Topbar -->
                        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                            <!-- Sidebar Toggle (Topbar) -->
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>

                            <!-- Topbar Search -->
                            <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="AdminController" method="POST">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for Food name or Category food" aria-label="Search" aria-describedby="basic-addon2" name="txtAdminSearchFood" value="" required>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="submit" name="action" value="Admin Search Food">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>

                        </nav>
                        <!-- End of Topbar -->
                        <c:if test="${requestScope.LIST_ADMIN_FOOD != null}">
                            <c:if test="${not empty requestScope.LIST_ADMIN_FOOD}" var="checkData">
                                <!-- Begin Page Content -->
                                <div class="container-fluid">
                                    <!-- DataTales Example -->
                                    <div class="card shadow mb-4">
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Manager Product</h6>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                    <thead>
                                                        <tr>
                                                            <th>Food Name</th>
                                                            <th>Food Image</th>
                                                            <th>Description</th>
                                                            <th>Category Food</th>
                                                            <th>Create date</th>
                                                            <th>Price</th>
                                                            <th>Delete</th>
                                                            <th>Edit</th>
                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th>Food Name</th>
                                                            <th>Food Image</th>
                                                            <th>Description</th>
                                                            <th>Category Food</th>
                                                            <th>Create date</th>
                                                            <th>Price</th>
                                                            <th>Delete</th>
                                                            <th>Edit</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                        <c:forEach var="dto" items="${requestScope.LIST_ADMIN_FOOD}" >
                                                            <tr>
                                                                <td>${dto.foodName}</td>
                                                                <td>
                                                                    <img src="img/${dto.imageLink}" height="auto" width="100"/> 
                                                                </td>
                                                                <td>${dto.description}</td>
                                                                <td>${dto.categoryFoodName}</td>
                                                                <td>${dto.createDate}</td>
                                                                <td>${dto.price}</td>
                                                                <td>
                                                                    <form action="AdminController" method="POST">
                                                                        <input type="hidden" name="idDelete" value="${dto.foodName}"/>
                                                                        <input type="hidden" name="txtAdminSearchFood" value="${param.txtAdminSearchFood}"/>
                                                                        <input type="submit" name="action" value="Delete" onclick="return confirm('Are you sure you want to delete ${dto.foodName}?')" />
                                                                    </form>
                                                                </td>
                                                                <td>
                                                                    <form action="AdminController" method="POST">
                                                                        <input type="hidden" name="idUpdate" value="${dto.foodName}"/>
                                                                        <input type="hidden" name="txtAdminSearchFood" value="${param.txtAdminSearchFood}"/>
                                                                        <input type="submit" name="action" value="Edit"/>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.container-fluid -->
                            </c:if>
                            <c:if test="${!checkData}">
                                No record found!
                            </c:if>
                        </c:if>  
                    </div>
                    <!-- End of Main Content -->


                </div>
                <!-- End of Content Wrapper -->

            </div>

        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
</div>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
  $("#menu-toggle").click(function (e) {
   e.preventDefault();
   $("#wrapper").toggleClass("toggled");
 });
</script>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
</body>

</html>

