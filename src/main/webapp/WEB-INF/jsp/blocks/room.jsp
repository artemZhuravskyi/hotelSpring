<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="col mb-4" >
    <div class="card shadow-sm" style="background-color: rgb(230, 255, 255)">

            <c:set var="id">${room.getId()}</c:set>
            <c:set var="roomPath">${room.getImages().toString()}</c:set>
            <c:set var="bedroom">${room.getImages().getBedroom()}</c:set>
        <a href="info-room-${id}">
            <div class="image">
                <img src="img/${roomPath}/${bedroom}" style="object-fit: cover;" width="100%" height="350"/>
                <div class="overlay">
                    <div class="text"><h2 class="title">Подробнее</h2></div>
                </div>
            </div>
        </a>
        <div class="card-body">
            <div class="row">
                <div class="col-9">
                    <p></p>
                </div>
                <div class="col">
                    <table class="table table-borderless mb-2">

                        <tbody>
                        <tr style="height: 30px">
                            <td class="fw-bold ps-0">Person</td>
                            <td class="float-end pe-0">${room.getPersonNumber()}</td>
                        </tr>
                        <tr>
                            <td class="fw-bold ps-0">Price</td>
                            <td class="float-end pe-0">${room.getPrice()}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="d-grid gap-2 mx-auto h-25">
                        <a href="order-room-${id}">
                        <button type="button" id="button" class="col-12 btn-lg">Order</button>
                        </a>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
