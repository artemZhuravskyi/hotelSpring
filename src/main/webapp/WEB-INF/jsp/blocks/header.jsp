<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.example.demo.service.UserService" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<head>
    <link rel="stylesheet" href="/css/underline.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg py-1 fixed-top border-bottom border-secondary" style="background: #fff3cc">
    <div class="container">

        <div class="collapse navbar-collapse " id="navbarNav">
            <ul class="navbar-nav text-body ">

                <li class="nav-brand nav-item px-1 mx-2">
                    <a class="nav-link text-body" href="/all-rooms"><fmt:message key="home"/></a>
                </li>


                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/client-applications"><fmt:message key="application"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/orders"><fmt:message key="orders"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_MANAGER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/all-applications"><fmt:message
                                key="users_applications"/></a>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="navbar-nav ms-auto">

                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/create-application"><fmt:message
                                key="make_application"/></a>
                    </li>
                </sec:authorize>
                <li class="nav-item px-1 mx-2">

                <a class="nav-link text-body" href="?lang=en">EN</a>
                </li>
                <li class="nav-item px-1 mx-2">
                <a class="nav-link text-body" href="?lang=ru">RU</a>
                </li>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/logout"><fmt:message key="logout"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body bord" href="/login"><fmt:message key="login"/></a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

</body>
