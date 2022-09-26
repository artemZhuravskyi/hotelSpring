<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Rooms and Prices</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">

    <style>
        .custom {
            font-family: "Times New Roman";
            font-style: italic;
        }
    </style>
    <link rel="stylesheet" href="css/image_hover.css"/>
    <link rel="stylesheet" href="css/button.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <meta name="theme-color" content="#7952b3">


</head>
<body>

<header id="header">

    <jsp:include page="blocks/header.jsp"/>
</header>

<main class="custom pt-5">

    <section class="text-center bg-gradient mt-5 ">
        <div class="py-lg-3"
             style="background: rgb(255,255,255);
background: linear-gradient(0deg, rgba(255,255,255,1) 0%, rgba(230,255,255,1) 25%, rgba(230,255,255,1) 50%, rgba(230,255,255,1) 75%, rgba(255,255,255,1) 100%);">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light fw-bold"><fmt:message key="all_rooms_announcement"/></h1>
                <p class="lead text-muted"><fmt:message key="all_rooms_announcement_description"/></p>

                <div class="col-4 d-grid mx-auto">
                    <a href="/create-application">
                        <button type="submit" id="button" class="btn-lg"><fmt:message key="all_rooms_announcement_button"/></button>
                    </a>
                </div>

            </div>
        </div>
    </section>

    <div class="container py-5">
        <div class="row justify-content-md-center mb-2">

            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
            <h1 class="col-4 col-md-auto d-flex align-self-center justify-content-center mx-4"><fmt:message key="rooms_and_prices"/></h1>
            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
        </div>
        <div class="container">
            <div class="row row-cols-1 g-3">
                <c:forEach items="${rooms}" var="room">
                    <c:set var="room" value="${room}" scope="request"/>
                    <c:if test="${room.getImages().toString().contains('1')}">
                        <c:if test="${room.getRoomClass().name() == 'ECONOMY'}">
                            <h1><fmt:message key="Economy"/></h1>
                        </c:if>
                        <c:if test="${room.getRoomClass().name() == 'STANDARD'}">
                            <h1><fmt:message key="Standard"/></h1>
                        </c:if>
                        <c:if test="${room.getRoomClass().name() == 'JUNIOR_SUITE'}">
                            <h1><fmt:message key="Junior suite"/></h1>
                        </c:if>
                        <c:if test="${room.getRoomClass().name() == 'SUITE'}">
                            <h1><fmt:message key="Suite"/></h1>
                        </c:if>
                    </c:if>
                    <div>
                        <jsp:include page="blocks/room.jsp"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</main>

<footer class="text-muted">
    <div class="container">
        <p class="float-end ">
            <a href="#"><button class="btn" id="button"><fmt:message key="back_to_top"/></button></a>
        </p>

    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
