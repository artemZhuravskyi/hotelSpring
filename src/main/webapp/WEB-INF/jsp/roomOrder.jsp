<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Rooms and Prices</title>

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
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white">About</h4>
                    <p class="text-muted">Add some information about the album below, the author, or any other
                        background context. Make it a few sentences long so folks can pick up some informative tidbits.
                        Then, link them off to some social networking sites or contact information.</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <h4 class="text-white">Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-white">Крутотень</a></li>
                        <li><a href="#" class="text-white">Like on Facebook</a></li>
                        <li><a href="#" class="text-white">Email me</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm fixed-top">
        <div class="container">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor"
                     stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2"
                     viewBox="0 0 24 24">
                    <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/>
                    <circle cx="12" cy="13" r="4"/>
                </svg>
                <strong>Album</strong>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>

<main class="custom vh-100 py-4" style="background-color: rgb(204, 255, 255)">


    <div class="container py-5">
        <div class="row justify-content-md-center mb-2">

            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
            <h1 class="col-4 col-md-auto d-flex align-self-center justify-content-center mx-4">Заказ номера</h1>
            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
        </div>
        <c:set var="roomPath">${room.getImages().toString()}</c:set>
        <c:set var="bedroom">${room.getImages().getBedroom()}</c:set>
        <c:set var="balcony">${room.getImages().getBalcony()}</c:set>
        <c:set var="toilet">${room.getImages().getToilet()}</c:set>
        <div class="card" style="max-width: 100%;">
            <div class="row g-0">
<%--                <div class="col-md-6">--%>
<%--                    <img src="img/room_economy_1.jpg" class="img-fluid rounded-start">--%>
<%--                </div>--%>
                <div id="carouselExampleControls" class="col-6 carousel slide" data-bs-ride="carousel">
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
                <div class="col-md-6" style="background-color: rgb(230, 255, 255)">

                    <form action="">

                        <div class="card-body d-flex justify-content-center">
                            <div class="row">
                                <div class="col align-self-center">
                                    <input class="border datepicker rounded text-center" type="text" name="daterange"
                                           value="01/01/2018 - 01/15/2018"/>
                                </div>
                                <div class="col">
                                    <button id="button" class="btn-lg" type="submit" style="background-color: rgb(230, 255, 255)">Order</button>
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
        $('input[name="daterange"]').daterangepicker({
            opens: 'right'
        });
    });
</script>


</body>
</html>
