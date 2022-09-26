<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<div class="col mb-4" >
    <div class="card shadow-sm" style="background-color: #fff3cc">

            <c:set var="id">${room.getId()}</c:set>
            <c:set var="roomPath">${room.getImages().toString()}</c:set>
            <c:set var="bedroom">${room.getImages().getBedroom()}</c:set>
        <a href="info-room-${id}">
            <div class="image">
                <img src="img/${roomPath}/${bedroom}" style="object-fit: cover;" width="100%" height="350"/>
                <div class="overlay">
                    <div class="text"><h2 class="title"><fmt:message key="details"/></h2></div>
                </div>
            </div>
        </a>
        <div class="card-body">
            <div class="row">
                <div class="col-8 rounded mx-4" style="border: #9da7a7 solid 1px">
                    <p><fmt:message key="room_info_description"/></p>
                </div>
                <div class="col-3 rounded" style="border: #9da7a7 solid 1px">
                    <table class="table table-borderless mb-2">

                        <tbody>
                        <tr style="height: 30px">
                            <td class="fw-bold ps-0"><fmt:message key="person"/></td>
                            <td class="float-end pe-0">${room.getPersonNumber()}</td>
                        </tr>
                        <tr>
                            <td class="fw-bold ps-0"><fmt:message key="price"/></td>
                            <td class="float-end pe-0">${room.getPrice()}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="d-grid mx-auto h-25 pb-2">
                        <a href="order-room-${id}">
                        <button type="button" id="button" class="col-12 btn-lg"><fmt:message key="order_button"/></button>
                        </a>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
