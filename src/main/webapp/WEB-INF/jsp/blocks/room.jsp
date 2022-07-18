<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="col">
  <div class="card shadow-sm">
    <div class="bg-image hover-overlay hover-zoom hover-shadow ripple">
    <img src="img/${room.getPathToImage()}"
         style="object-fit: cover;" width="100%" height="350"/>
    <a href="#">
      <div class="mask" style="background-color: rgba(57, 192, 237, 0.2)"></div>
    </a>
    </div>
    <div class="card-body">
      <div class="row" style="height: 84px">
      <div class="col-9">
        <p>Очень крутой номер, там ты блин можешь кайфовать и тррахаться</p>
      </div>
      <div class="col">
      <table class="table table-borderless">

        <tbody>
          <tr style="height: 30px">
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
    <div class="float-end d-grid gap-2 mx-auto " style="width:246px">
      <button type="submit" class="btn btn-lg btn-outline-secondary">Order</button>
    </div>
    </div>

  </div>
</div>
