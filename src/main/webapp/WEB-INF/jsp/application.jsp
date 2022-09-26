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
    <meta name="theme-color" content="#7952b3">

    <title><fmt:message key="make_application_announcement"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">
    <link rel="stylesheet" href="../static/css/image_hover.css"/>
    <link rel="stylesheet" href="../static/css/button.css"/>
    <link rel="stylesheet" href="../static/css/datepicker.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>

    <style>
        .custom {
            font-family: "Times New Roman";
            font-style: italic;
        }
    </style>

</head>
<body>
<header id="header">
    <jsp:include page="blocks/header.jsp"/>
</header>
<main class="custom vh-100 pt-5 mt-5">
    <section class="pt-2 text-center h-0" style="background: rgb(255,255,255);
background: linear-gradient(0deg, rgba(255,255,255,1) 0%, rgba(230,255,255,1) 25%, rgba(230,255,255,1) 50%, rgba(230,255,255,1) 75%, rgba(255,255,255,1) 100%);">
        <div class="py-lg-3 d-flex align-self-center justify-content-center">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light fw-bold"><fmt:message key="make_application_announcement"/></h1>
                <p class="lead text-muted"><fmt:message key="make_application_description"/></p>
                <div class="col-4 d-grid mx-auto h-25">
                </div>
            </div>
        </div>
    </section>

    <div class="container d-flex justify-content-center">
        <div class="rounded row col-4 mt-5 d-flex" style="border: #ffe367 solid 1px">
            <form action="/create-application" class="text-center" method="post">


                    <div class="d-inline-flex my-4" >
                        <select class="form-control text-center" name="roomClass">
                            <option value="ECONOMY"><fmt:message key="Economy"/></option>
                            <option value="STANDARD"><fmt:message key="Standard"/></option>
                            <option value="JUNIOR_SUITE"><fmt:message key="Junior suite"/></option>
                            <option value="SUITE"><fmt:message key="Suite"/></option>
                        </select>
                    </div>
                    <br>
                    <div class="col-12">
                        <input class="border datepicker rounded text-center" type="text" name="date"
                               value="01/01/2022 - 01/15/2022"/>
                    </div>
                    <br>
                    <div class="col-12">
                        <button id="button" class="btn-lg" type="submit">
                            <fmt:message key="send_button"/>
                        </button>
                    </div>

            </form>
        </div>
    </div>

</main>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    $(function () {
        $('input[name="date"]').daterangepicker({
            opens: 'right'
        });
    });
</script>
</body>
</html>
