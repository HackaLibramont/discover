<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>

    <%-- Map Google --%>
    <style type="text/css">
        html, body, #map-canvas { height: 100%; margin: 0; padding: 0;}
    </style>

    <link rel="stylesheet" href="<c:url value="/resources/lib/bootstrap-3.3.4-dist/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/lib/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/lib/jasny-bootstrap/css/jasny-bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/navmenu.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.vertical-tabs.css" />">
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/logo_onglet.png"/>" />
</head>
<body>
    <div class="navmenu navmenu-default navmenu-fixed-left offcanvas-sm">
        <a class="navmenu-brand visible-md visible-lg" href="#"><img src="<c:url value="/resources/img/logo.png"/>"></a>

        <div class="container">

            <div class="row">
                    <div class="tabs-left">
                        <ul class="nav nav-tabs">
                            <li class="active" class="tabs-a"><a href="#a" data-toggle="tab"><img src="<c:url value="/resources/img/menu_accueil_couleur.png"/>" class="smallerLogo"></a></li>
                            <li><a href="#b" class="tabs-b" data-toggle="tab"><img src="<c:url value="/resources/img/menu_connexion_couleur.png"/>" onmouseover="hover(this)" onmouseout="unhover(this)" class="smallerLogo"></a></li>
                            <li><a href="#c" class="tabs-c" data-toggle="tab"><img src="<c:url value="/resources/img/menu_alentours_couleur.png"/>" class="smallerLogo"></a></li>
                            <li><a href="#d" class="tabs-d" data-toggle="tab"><img src="<c:url value="/resources/img/menu_itineraire_couleur.png"/>" class="smallerLogo"></a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane textContent homeContent active" id="a">

                                <p class="textContent"><span >Home</span></p>
                                <p class="textContent"><span >Connexion</span></p>
                                <p class="textContent"><span >Alentours</span></p>
                                <p class="textContent"><span >Itinéraires</span></p>
                            </div>

                            <div class="tab-pane textContent connectionContent" id="b">
                                <p class="labels">E-mail</p>
                                <input type="text" class="inputMenu" name="userMail">
                                <p class="labels">Mot de passe</p>
                                <input type="password" class="inputMenu" name="userPassword">
                                <div id="connexion">
                                    <a href="">
                                    <img src="<c:url value="/resources/img/start.png"/>" alt="Se connecter" class="submitButton">
                                    </a>
                                    <p class="subscriptionButton">S'inscrire</p>
                                </div>
                            </div>

                            <div class="tab-pane textContent alentoursContent" id="c">
                                <img src="<c:url value="/resources/img/position.png"/>" class="positionImage">
                                <p class="labels">Départ</p>
                                <input type="text" class="inputMenu" name="userLongitude" id="userLongitude1">
                                <input type="text" class="inputMenu" name="userLatitude" id="userLatitude1">
                                      <span>
                                        <span class="hoursLetter">Distance : </span>
                                        <input type="text" class="inputMenu inputHours" name="userDistance" id="userDistance1" width="30px">
                                        <img src="<c:url value="/resources/img/time.png"/>" class="hoursImage">
                                    </span>
                                <a href="">
                                    <img src="<c:url value="/resources/img/start.png"/>" alt="Voir les poins d'interêt" class="submitButton" onclick="findActivitiesAround()">
                                </a>


                            </div>
                            <div class="tab-pane textContent itineraireContent" id="d">
                                <img src="<c:url value="/resources/img/position.png"/>" class="positionImage">
                                <p class="labels">Départ</p>
                                <input type="text" class="inputMenu" name="userLongitude" id="userLongitude2">
                                <input type="text" class="inputMenu" name="userLatitude" id="userLatitude2">
                                <img src="<c:url value="/resources/img/position.png"/>" class="positionImage">


                        <span>
                            <p class="labels">Durée : </p>
                            <input type="text" class="inputMenu inputHours" name="userDistance" id="userDistance2" width="30px">
                            <img src="<c:url value="/resources/img/time.png"/>" class="hoursImage">
                        </span>
                                <a href="">
                                    <img src="<c:url value="/resources/img/start.png"/>" alt="Voir l'itinéraire" class="submitButton" onclick="findActivitiesPath()">
                                </a>

                            </div>

                        </div><!-- /tab-content -->
                    </div><!-- /tabbable -->
            </div><!-- /row -->
        </div><!-- /container -->
    </div>

    <div class="navbar navbar-default navbar-fixed-top hidden-md hidden-lg">
        <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".navmenu">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#"><img src="<c:url value="/resources/img/logo.png"/>" class="smallLogo"></a>
    </div>

    <div id="map-canvas"></div>
</body>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/resources/lib/bootstrap-3.3.4-dist/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/lib/jasny-bootstrap/js/jasny-bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/lib/markerclusterer/markerclusterer.js" />"></script>
<script src="<c:url value="/resources/js/tab.js"/>"></script>
<script src="<c:url value="/resources/js/lists.js"/>"></script>
<script src="<c:url value="/resources/js/googlemap.js"/>"></script>

</html>