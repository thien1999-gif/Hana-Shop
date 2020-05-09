<%-- 
    Document   : adminMain
    Created on : Mar 9, 2020, 1:26:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >

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
                    <form action="AdminController" method="POST">
                        Date from: <input type="date" name="txtDateFrom" value=""/>
                        <font color="red">
                        ${requestScope.INVALID.dateFromErrorObj}
                        </font>
                        Date to:<input type="date" name="txtDateTo" value=""/>
                        <font color="red">
                        ${requestScope.INVALID.dateToErrorObj}
                        </font>

                        <input type="submit" name="action" value="View"/>
                        <%-- <input type="submit" name="action" value="View all"/> --%>
                    </form>

                    
                    <c:if test="${sessionScope.LIST_VIEW_HISTORY != null}">
                        <c:if test="${not empty sessionScope.LIST_VIEW_HISTORY}" var="checkData">
                            <!-- Begin Page Content -->
                            <div class="container-fluid">
                                <!-- DataTales Example -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">View transaction history</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>OrderID</th>
                                                        <th>Customer Name</th>
                                                        <th>Phone</th>
                                                        <th>Total price</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>OrderID</th>
                                                        <th>Customer Name</th>
                                                        <th>Phone</th>
                                                        <th>Total price</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.LIST_VIEW_HISTORY}" var="dto" >
                                                            <tr>
                                                                <td>${dto.orderID}</td>
                                                                <td>${dto.customerName}</td>
                                                                <td>${dto.phone}</td>
                                                                <td>${dto.totalMoneyOfOrder}</td>
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
