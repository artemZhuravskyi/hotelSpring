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
    <meta name="author" content="zhuravksuyio arte,m">
    <meta name="generator" content="Hugo 0.84.0">
    <meta name="theme-color" content="#7952b3">
    <title><fmt:message key="room_order_title"/></title>

    <style>
        .custom {
            font-family: "Times New Roman";
            font-style: italic;
        }
    </style>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">
    <link rel="stylesheet" href="css/image_hover.css"/>
    <link rel="stylesheet" href="css/button.css"/>
    <link rel="stylesheet" href="css/datepicker.css"/>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Favicons -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>

</head>
<body>

<header id="header">
    <jsp:include page="blocks/header.jsp"/>
</header>

<main class="custom py-4">


    <div class="container py-5">
        <div class="row justify-content-md-center mb-2">

            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
            <h1 class="col-4 col-md-auto d-flex align-self-center justify-content-center mx-4"><fmt:message key="${room.getRoomClass().toString()}"/>
                • <fmt:message key="room_num"/>${room.getId()}</h1>
            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
        </div>
        <c:set var="roomPath">${room.getImages().toString()}</c:set>
        <c:set var="bedroom">${room.getImages().getBedroom()}</c:set>
        <c:set var="balcony">${room.getImages().getBalcony()}</c:set>
        <c:set var="toilet">${room.getImages().getToilet()}</c:set>
            <div class="row mt-3 border">
                <div class="col-6 ps-0 carousel slide" id="carouselExampleControls" data-bs-ride="carousel">
                    <div class="carousel-inner" style="height: 638px">
                        <div class="carousel-item active w-100 h-100">
                            <img src="img/${roomPath}/${bedroom}" class="w-100 h-100" alt="..."
                                 style="object-fit: cover; object-position: center center">
                        </div>
                        <div class="carousel-item w-100 h-100">
                            <img src="img/${roomPath}/${balcony}" class="w-100 h-100" alt="..."
                                 style="object-fit: cover; object-position: center center">
                        </div>
                        <div class="carousel-item w-100 h-100">
                            <img src="img/${roomPath}/${toilet}" class="w-100 h-100" alt="..."
                                 style="object-fit: cover; object-position: center center">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <div class="col-6 d-flex justify-content-center row">

                    <div class="align-self-center border col-12 w-50" style="background-color: rgb(245, 255, 255); width:fit-content;" >
                        <table class="table table-borderless" >
                            <tbody>
                            <tr>
                                <td class="fw-bold"><fmt:message key="person"/></td>
                                <td class="float-end">${room.getPersonNumber()}</td>
                            </tr>
                            <tr>
                                <td class="fw-bold"><fmt:message key="price"/></td>
                                <td class="float-end">${room.getPrice()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="align-self-start border col-12" style="background-color: rgb(245, 255, 255); width:fit-content;">
                        <form action="/order-room-${room.getId()}" method="post">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col align-self-center">
                                        <input class="border datepicker rounded text-center" type="text"
                                               name="dateRange"
                                               value="03/16/2022 - 03/1 7/2022"/>
                                    </div>
                                    <div class="col">

                                        <button id="button" class="btn-lg" type="submit"
                                                style="background-color: rgb(245, 255, 255)"><fmt:message key="order_button"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
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
        let reservedDates = ${reservedDates};
        // reservedDates = reservedDates.map(x => new Date(x));
        console.log(reservedDates);
        let datepicker = $('input[name="dateRange"]');
        datepicker.daterangepicker({
            isInvalidDate: function (date) {
                console.log(date);
                return reservedDates.includes(date["_d"].getTime());
            }
        });
        datepicker.on('apply.daterangepicker', function (ev, picker) {
            //do something, like clearing an input
            console.log(ev);
            console.log(picker);
        });

    });

</script>


</body>
</html>
