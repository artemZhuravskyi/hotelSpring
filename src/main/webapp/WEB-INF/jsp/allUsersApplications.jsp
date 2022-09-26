<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Applications</title>

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
<header id="header">
    <jsp:include page="blocks/header.jsp"/>
</header>
<main class="custom py-4 mt-5">
    <div class="container">
        <c:set var="count" value="1" scope="page"/>
        <c:choose>
            <c:when test="${allApplications.size() == 0}">
                <div class="position-absolute top-50 start-50 translate-middle border-bottom border-warning">
                    <h1><fmt:message key="all_app_description"/></h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach items="${allApplications}" var="application">
                        <div class="col-12 border border-warning rounded p-2 mb-3">
                            <form method="POST" action="/application-response">
                                <div class="row">
                                    <c:forEach items="${application.getRoom()}" var="room">
                                        <div class="col d-inline-flex">
                                            <label>
                                                <input type="radio" name="roomId" value="${room.getId()}"/>
                                                <input type="hidden" name="applicationId" value="${application.getId()}">
                                                <img style="object-fit: cover;" width="100%" height="350"
                                                     src="img/${room.getImages().toString()}/${room.getImages().getBedroom()}"
                                                     alt="Option ${count + 1}">
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="d-flex justify-content-center mt-3">
                                    <input type="submit" value="Submit"/>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
