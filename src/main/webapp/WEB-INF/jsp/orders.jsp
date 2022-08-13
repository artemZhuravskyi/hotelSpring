<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">

    <style>
        .custom {
            font-family: "Times New Roman";
            font-style: italic;
        }
    </style>
    <link rel="stylesheet" href="css/image_hover.css"/>
    <link rel="stylesheet" href="css/button.css"/>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Favicons -->

    <meta name="theme-color" content="#7952b3">

</head>
<body>
<jsp:include page="blocks/header.jsp"/>
<main class="custom pt-5">
    <section class="mt-1 text-center h-0"
             style="background: rgb(230,255,255) linear-gradient(180deg, rgba(230,255,255,1) 80%, rgba(255,255,255,1) 100%);">
        <div class="py-lg-3 d-flex align-self-center justify-content-center">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light fw-bold">Your Orders</h1>
                <p class="lead text-muted">Here displays all your already paid and still not paid orders </p>
                <div class="col-4 d-grid mx-auto h-25">
                </div>
            </div>
        </div>
    </section>

    <div class="container mt-5 py-2 border-warning border rounded">

        <div class="col-12 row ">


            <%--        </div>--%>
            <%--        <div class="col-12 row ">--%>
            <div class="row col-3">
                <div class="col-12 "></div>
                <div class="col-12 text-center">
                    <h2 class=" border-bottom border-warning pb-2" style="display: inline-block">Confirm orders</h2>
                </div>
            </div>
            <%--    <div class="col-8 " style="width: 69%">--%>
            <%--    </div>--%>
            <div class="col-9 row">
                <div class="col-10">

                    <table class="table">
                        <thead>
                        <form action="/orders">
                            <tr>
                                <th scope="col" style="border-color: #ffc107;">
                                    <%--                                <input type="submit" class="border border-0 bg-body fw-bold" name="sorting" value="id">--%>
                                    id
                                </th>
                                <th scope="col" style="border-color: #ffc107;">
                                    <%--                                <input type="submit" class="border border-0 bg-body fw-bold" name="sorting" value="room.roomClass">--%>
                                    Room Class
                                </th>
                                <th scope="col" style="border-color: #ffc107;">
                                    <%--                                <input type="submit" class="border border-0 bg-body fw-bold" name="sorting" value="firstDate">--%>
                                    First Date
                                </th>
                                <th scope="col" style="border-color: #ffc107;">
                                    <%--                                <input type="submit" class="border border-0 bg-body fw-bold" name="sorting" value="lastDate">--%>
                                    Last Date
                                </th>
                                <th scope="col" style="border-color: #ffc107;">

                                    <%--                                <input type="submit" class="border border-0 bg-body fw-bold" name="sorting" value="Price">--%>
                                    Price

                                </th>

                            </tr>
                        </form>
                        </thead>

                        <%--                    <div class="col-9 row">--%>
                        <c:forEach items="${orders}" var="order">
                        <c:if test="${order.getStatus() == 'NOT_PAID'}">
                        <tbody>
                        <tr>
                            <th scope="row" style="border-color: #ffc107;">${order.getId()}</th>
                            <td style="border-color: #ffc107;">${order.getRoom().getRoomClass().toString()}</td>
                            <td style="border-color: #ffc107;">${order.getFirstDate()}</td>
                            <td style="border-color: #ffc107;">${order.getLastDate()}</td>
                            <td style="border-color: #ffc107;">${order.getPrice()}</td>
                        </tr>
                            <%--            </div>--%>
                        </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-2 row" style="display:inline-block; margin-top: 84px">
                    <c:forEach items="${orders}" var="order">
                        <c:if test="${order.getStatus() == 'NOT_PAID'}">
                            <div class="col-10 mb-1">
                                <form action="/pay-order/${order.getId()}" class="my-0" method="post">
                                    <input class="form-control form-control-sm btn btn-sm border border-warning"
                                           type="submit" value="Pay"/>
                                </form>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

        <br>
        <br>
        <div class="col-10 border-bottom border-warning ms-4 mb-2" style="width: 95%"></div>
        <br>
        <div class="col-12 row">
            <div class="text-center mt-1" style=" width: 23%">
                <h2 class="border-bottom border-warning pb-2" style="display: inline-block;">Payed orders</h2>
            </div>
            <div class="col-8" style="">
                <%--                <table class="table" style="border-color: #ffc107;">--%>


                <table class="table mt-1" style="border-color: #ffc107; width: 91%">
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <c:if test="${order.getStatus() == 'PAID'}">
                            <tr>
                                <th style="border-color: #ffc107;" scope="row">${order.getId()}</th>
                                <td style="border-color: #ffc107;">${order.getRoom().getRoomClass().toString()}</td>
                                <td style="border-color: #ffc107;">${order.getFirstDate()}</td>
                                <td style="border-color: #ffc107;">${order.getLastDate()}</td>
                                <td style="border-color: #ffc107;">${order.getPrice()}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


    </div>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {

        fetch(window.location.origin + '/api/v1/orders')
            .then(res => res.json())
            .then(res => {
                jQuery.each(res, (index, item) => {
                    let tag = document.createElement("p");
                    let text = document.createTextNode(item.status);
                    tag.appendChild(text);
                    let element = document.getElementById("confirm");
                    element.appendChild(tag);
                });
            });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
