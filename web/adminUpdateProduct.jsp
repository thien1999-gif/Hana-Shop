<%-- 
    Document   : adminUpdateProduct
    Created on : Mar 13, 2020, 10:10:51 AM
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

        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
        <!-- http://api.jqueryui.com/datepicker/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">

    </head>

    <body>

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
                    <div class="container tm-mt-big tm-mb-big">
                        <div class="row">
                            <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                                <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                                    <div class="row">
                                        <div class="col-12">
                                            <h2 class="tm-block-title d-inline-block">Update Product</h2>
                                        </div>
                                    </div>
                                    <div class="row tm-edit-product-row">
                                        <div class="col-xl-6 col-lg-6 col-md-12">
                                            <form action="AdminController" method="POST" class="tm-edit-product-form">
                                                <div class="form-group mb-3">
                                                    <label
                                                        for="name"
                                                        >Food Name
                                                    </label>
                                                    <input
                                                        id="name"
                                                        name="txtFoodName" value="${sessionScope.FOOD_UPDATE.foodName}"
                                                        type="text"
                                                        class="form-control validate"
                                                        readonly
                                                        />
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label
                                                        for="description"
                                                        >Description</label
                                                    >
                                                    <textarea
                                                        name="txtDescription" 
                                                        class="form-control validate"
                                                        rows="3"

                                                        ></textarea>
                                                    <font color="red">
                                                    ${requestScope.INVALID.descriptionError}
                                                    </font>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label
                                                        for="category"
                                                        >Category</label
                                                    >
                                                    <select
                                                        name="cbCategory" 
                                                        class="custom-select tm-select-accounts"
                                                        id="category"

                                                        >
                                                        <option value="-1">Select category</option>
                                                       
                                                        <c:forEach var="dto" items="${sessionScope.LIST_CATEGORY_FOOD}">
                                                            <option value="${dto.categoryFoodID}">${dto.categoryFoodName}</option>
                                                        </c:forEach>
                                                         <%--
                                                        <option value="1">fast food</option>
                                                        <option value="2">traditional food</option>
                                                        <option value="3">drink</option>
                                                        <option value="4">soup</option>
                                                         --%>
                                                    </select>
                                                    <font color="red">
                                                    ${requestScope.INVALID.categoryError}
                                                    </font>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group mb-3 col-xs-12 col-sm-6">
                                                        <label
                                                            for="stock"
                                                            >Units Price
                                                        </label>
                                                        <input
                                                            id="stock"
                                                            name="txtPrice"
                                                            type="text"
                                                            class="form-control validate"

                                                            />
                                                        <font color="red">
                                                        ${requestScope.INVALID.priceError}
                                                        </font>
                                                    </div>
                                                </div>
                                                <div class="form-group mb-3">
                                                    <label
                                                        for="Status"
                                                        >Status Food</label
                                                    >
                                                    <select
                                                        name="cbStatus" 
                                                        class="custom-select tm-select-accounts"
                                                        id="category"

                                                        >
                                                        <option value="-1">Select status</option>
                                                        <%--
                                                        <c:forEach var="dto" items="${requestScope.LIST_CATEGORY_FOOD}">
                                                            <option value="${dto.categoryFoodID}">${dto.categoryFoodName}</option>
                                                        </c:forEach>
                                                        --%>
                                                        <option value="true">Active</option>
                                                        <option value="false">Inavtive</option>
                                                        
                                                    </select>
                                                    <font color="red">
                                                    ${requestScope.INVALID.statusError}
                                                    </font>
                                                </div>
                                        </div>
                                        <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                            <div class="tm-product-img-dummy mx-auto">
                                                <i
                                                    class="fas fa-cloud-upload-alt tm-upload-icon"
                                                    onclick="document.getElementById('fileInput').click();"
                                                    ></i>
                                            </div>
                                            <div class="custom-file mt-3 mb-3">
                                                <input id="fileInput" type="file" style="display:none;" name="txtImageLink"/>
                                                <label
                                                    class="btn btn-primary btn-block mx-auto"
                                                    value=""
                                                    >UPLOAD PRODUCT IMAGE</label>

                                                <font color="red">
                                                ${requestScope.INVALID.imageLinkError}
                                                </font>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <input type="submit" class="btn btn-primary btn-block text-uppercase" name="action" value="Update product"/>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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