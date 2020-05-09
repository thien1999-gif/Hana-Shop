<%-- 
    Document   : userCart
    Created on : Mar 18, 2020, 10:54:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/simple-sidebar.css" rel="stylesheet">
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet"> 
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,600,600i,700,700i,800" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min_1.css">
        <link rel="stylesheet" href="css/plugins.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/vendor/modernizr-3.5.0.min.js"></script> 
    </head>

    <body>

        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Welcome ${sessionScope.USERNAME}</div>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">View history transaction</a>
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
                                <!-- Topbar Search -->
                                <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="UserController" method="POST">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="What do you need ?" aria-label="Search" aria-describedby="basic-addon2" name="txtUserSearchFood" value="" required>
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit" name="action" value="User Search Food">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Advance Search <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="userViewCart.jsp">View cart <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Login / Sign in</a>
                            </li>

                        </ul>
                    </div>
                </nav>
                <c:if test="${sessionScope.LIST_RESULT_USER_FOOD != null}">
                    <c:if test="${not empty sessionScope.LIST_RESULT_USER_FOOD}" var="checkData"> 

                        <div class="container-fluid">
                            <div class="cart-main-area section-padding--lg bg--white">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12 col-sm-12 ol-lg-12">
                                            <form action="#">               
                                                <div class="table-content wnro__table table-responsive">
                                                    <table>
                                                        <thead>
                                                            <tr class="title-top">
                                                                <th class="product-thumbnail">Image</th>
                                                                <th class="product-name">Product</th>
                                                                <th class="product-price">Price</th>
                                                                <th class="product-name">Category food</th>
                                                                <th class="product-name">Description</th>
                                                                <th class="product-remove">Add to cart</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="dto" items="${sessionScope.LIST_RESULT_USER_FOOD}" >
                                                                <tr>
                                                                    <td class="product-thumbnail"><img src="img/${dto.imageLink}"</td>
                                                                    <td class="product-name">${dto.foodName}</td>
                                                                    <td class="product-price"><span class="amount">${dto.price}</span></td>
                                                                    <td class="product-name">${dto.categoryFoodName}</td>
                                                                    <td class="product-name">${dto.description}</td>
                                                                    <td>
                                                                        <form action="UserController" method="POST">
                                                                            <input type="hidden" name="txtImageLink" value="${dto.imageLink}"/>
                                                                            <input type="hidden" name="txtFoodName" value="${dto.foodName}"/>
                                                                            <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                                                            <input type="hidden" name="txtCategoryFoodName" value="${dto.categoryFoodName}"/>
                                                                            <input type="hidden" name="txtDescription" value="${dto.description}"/>
                                                                            <input class="product-add-to-cart" type="submit" name="action" value="Add"/>
                                                                        </form>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </form> 
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${!checkData}">
                        No record found
                    </c:if>
                </c:if>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

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

    </body>

</html>
