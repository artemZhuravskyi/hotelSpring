<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <link rel="stylesheet" href="/css/underline.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg py-1 fixed-top border-bottom border-secondary" style="background: #ffdb87">
    <div class="container">

        <div class="collapse navbar-collapse " id="navbarNav">
            <ul class="navbar-nav text-body ">

                <li class="nav-brand nav-item px-1 mx-2">
                    <a class="nav-link text-body" href="all-rooms">Home</a>
                </li>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/client-applications">Application</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/orders">Orders</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasRole('ROLE_MANAGER')">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/all-applications">Users Applications</a>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="navbar-nav ms-auto">

                <%--                <div class="me-auto float-end" style="display: inline-block">--%>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/create-application">Make Application</a>
                    </li>
                </sec:authorize>
                <%--                </div>--%>
                <%--                <div class="me-auto float-end" style="display: inline-block">--%>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body" href="/logout">Log Out</a>
                    </li>
                </sec:authorize>
                <%--                </div>--%>
                <%--                <div class="col float-end">--%>
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item px-1 mx-2">
                        <a class="nav-link text-body bord" href="/login">Log In</a>
                    </li>
                </sec:authorize>
                <%--                </div>--%>
            </ul>
        </div>
    </div>
</nav>

</body>
