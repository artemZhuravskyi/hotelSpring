<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Room Information</title>

    <style>
        .custom {
            font-family: "Times New Roman";
            font-style: italic;
        }
    </style>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">
    <link rel="stylesheet" href="css/image_hover.css"/>
    <link rel="stylesheet" href="css/button.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

<header id="header">
    <jsp:include page="blocks/header.jsp"/>
</header>

<main class="custom py-4">


    <div class="container py-5">
        <div class="row justify-content-md-center mb-2">

            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
            <h1 class="col-4 col-md-auto d-flex align-self-center justify-content-center mx-4">
                ${room.getRoomClass().toString()} • Номер №${room.getId()}</h1>
            <img class="col-3" src="img/border.png" style="height: 90px; object-fit: cover">
        </div>

        <div class="card" style="max-width: 100%; background-color: rgb(240, 255, 255)">
            <div class="row">
                <c:set var="roomPath">${room.getImages().toString()}</c:set>
                <c:set var="bedroom">${room.getImages().getBedroom()}</c:set>
                <c:set var="balcony">${room.getImages().getBalcony()}</c:set>
                <c:set var="toilet">${room.getImages().getToilet()}</c:set>

                <div id="carouselExampleControls" class="col-12 carousel slide" data-bs-ride="carousel">
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
            </div>
            <div class="row pt-3">
                <div class="col">
                    <div class="card-body position-relative " style="left: 200px; width: 276px">
                        <h4 class="text-center fw-bold">Удобства</h4>
                        <p class="text-center">Кодиционер, ванная, две кровати, балкон</p>
                    </div>
                </div>
                <div class="col">
                    <div class="position-relative pt-3" style="left: 150px; width: 276px; ">
                        <table class="table table-borderless" >
                            <tbody>
                            <tr>
                                <td class="fw-bold">Person</td>
                                <td class="float-end">${room.getPersonNumber()}</td>
                            </tr>
                            <tr>
                                <td class="fw-bold">Price</td>
                                <td class="float-end">${room.getPrice()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <img class="col-3 me-5" src="img/border.png" style="height: 90px; width: 300px; object-fit: cover">
                <div class="col-2 h-75 align-self-center">
                    <a href="order-room-${id}">
                    <button type="submit" id="button" class="col-12 btn-lg" >Order</button>
                    </a>
                </div>
                <img class="col-3 ms-5" src="img/border.png" style="height: 90px; width: 300px; object-fit: cover">
            </div>
            <div class="card-body text-center">
                <div class="container">
                    <p>Оснащення номера
                        широке двоспальне ліжко
                        кондиціонер
                        безкоштовний Wi-Fi до 500 Кбіт/с
                        кабельне телебачення
                        міський телефонний зв'язок
                        У вартість включено
                        сніданок у ресторані (шведський стіл) - з 07:00 до 10:30
                        безкоштовний WI-FI до 500 Кбіт/с
                        служба телефонних операторів (послуга "Wake up" за проханням гостя)
                        міський телефонний зв'язок у межах Києва
                        кабельне телебачення (31 канал)
                        інформаційний термінал у холі готелю
                        гардероб
                        прибирання номера
                        цілодобова охорона
                        виклик таксі
                        Також в номері
                        прибирання номеру – щодня
                        зміна рушників – щодня
                        зміна постільної білизни - 1 раз в 3 дня</p>

                </div>
            </div>
        </div>
    </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


</body>
</html>
