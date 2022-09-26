<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="register_head"/></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <meta name="generator" content="Hugo 0.84.0">
    <meta name="theme-color" content="#7952b3">

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/album/">
    <link rel="stylesheet" href="css/login.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<section>
    <div class="row m-0">
        <div class="col d-flex align-items-center justify-content-center">
            <form method="post" action="/registration" class="needs-validation" style="width: 23rem;" novalidate>

                <h2 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;"><fmt:message key="register_head"/></h2>

                <div class="form-outline mb-4">
                    <label class="form-label"><fmt:message key="first_name"/></label>
                    <input type="text" class="form-control form-control-lg" id="validationCustom01" name="firstName" required/>
                </div>

                <div class="form-outline mb-4">
                    <label class="form-label"><fmt:message key="last_name"/></label>
                    <input type="text" class="form-control form-control-lg" id="validationCustom02" name="lastName" required/>
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label"><fmt:message key="email"/></label>
                <c:if test="${emailException != null}">
                    <p class="text-danger"><fmt:message key="email_taken"/></p>
                </c:if>
                    <input type="email" class="form-control form-control-lg" id="validationCustom03" name="email" required/>
                </div>

                <div class="form-outline mb-4">
                    <label class="form-label"><fmt:message key="password"/></label>
                <c:if test="${passwordException != null}">
                    <p class="text-danger"><fmt:message key="password_incorrect"/></p>
                </c:if>
                    <input type="password" class="form-control form-control-lg" id="validationCustom04" name="password" required/>
                </div>

                <div class="pt-1 mb-3">
                    <button class="btn btn-info btn-lg btn-block text-dark" type="submit"><fmt:message key="register_button"/></button>
                </div>

            </form>
        </div>
        <div class="col-sm-6 p-0 bg-image d-none d-sm-block img-responsive" style="background-size: cover">
            <img class="vh-100 w-100"
                 src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img3.webp"
                 style="object-fit: cover">
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


<script type="text/javascript" src="script/validate_form.js"></script>

</body>
</html>
