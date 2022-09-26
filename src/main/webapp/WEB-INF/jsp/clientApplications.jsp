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
    <title><fmt:message key="application_announcement"/></title>

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
<jsp:include page="blocks/header.jsp"/>
<main class="custom mt-5 pt-2">

    <section class="mt-5 text-center h-0"
             style="background: rgb(255,255,255);
background: linear-gradient(0deg, rgba(255,255,255,1) 0%, rgba(230,255,255,1) 25%, rgba(230,255,255,1) 50%, rgba(230,255,255,1) 75%, rgba(255,255,255,1) 100%);">
        <div class="py-lg-3 d-flex align-self-center justify-content-center">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light fw-bold"><fmt:message key="application_announcement"/></h1>
                <p class="lead text-muted"><fmt:message key="application_announcement_description"/></p>
                <div class="col-4 d-grid mx-auto h-25">
                </div>
            </div>
        </div>
    </section>
    <div class="container border-warning w-50 mt-5">
        <c:choose>
            <c:when test="${clientApplications.size() == 0}">
                <div class="position-absolute top-50 start-50 translate-middle border-bottom border-warning">
                    <h1><fmt:message key="app_description"/></h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <c:forEach items="${clientApplications}" var="application">
                        <div class="card border-warning mb-3 col-12">
                            <div class="row g-0">
                                <div class="col-md-12">
                                    <div class="card-body">
                                        <h4 class="card-title border-bottom border-warning pb-2"><fmt:message key="application_head"/>${application.getId()}</h4>
                                        <table class="card-text table table-borderless">
                                            <tbody>
                                            <tr>
                                                <td class="fw-bold"><fmt:message key="application_arrive_date"/></td>
                                                <td>${application.getFirstDate()}</td>

                                            </tr>
                                            <tr>
                                                <td class="fw-bold"><fmt:message key="application_leave_date"/></td>
                                                <td>${application.getLastDate()}</td>
                                            </tr>
                                            <tr>
                                                <td class="fw-bold"><fmt:message key="application_room_class"/></td>
                                                <td>${application.getRoomClass()}</td>
                                            </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>
