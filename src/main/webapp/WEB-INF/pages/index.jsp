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
                                <table style="width:200px; color:white;">
                                    <tr>
                                        <td colspan='3'>Départ</td>
                                        <td style="text-align:right;"><img  style='width:32px;' src="<c:url value="/resources/img/position.png"/>"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan='3' style="text-align:right;"><input style="height:30px;" type="text" class="inputMenu" name="userLongitude" id="userLongitude1"></td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td colspan='3' style="text-align:right;"><input style="height:30px;" type="text" class="inputMenu" name="userLatitude" id="userLatitude1"></td>
                                        <td/>
                                    </tr>
                                    <tr style="padding-top:5px;">
                                        <td colspan='2'>Distance</td>
                                        <td style="text-align:right;"><input style="height:30px; width:56px;" type="text" class="inputMenu" name="userDistance" id="userDistance1"></td>
                                        <td style="text-align:center;">m</td>
                                    </tr>
                                    <tr>
                                        <td title="Hébergement"><input type="checkbox" name="" value="1"/><img style='width:32px;' src="<c:url value="/resources/img/activities_hotel_blanc.png"/>"/></td>
                                        <td title="Lieu"><input type="checkbox" name="" value="2"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_lieu_blanc.png"/>"/></td>
                                        <td title="Loisir"><input type="checkbox" name="" value="3"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_loisir_blanc.png"/>"/></td>
                                        <td title="Sport"><input type="checkbox" name="" value="4"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_sport_blanc.png"/>"/></td>
                                    </tr>
                                    <tr>
                                        <td title="Patrimoine"><input type="checkbox" name="" value="5"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_musee_blanc.png"/>"/></td>
                                        <td title="Production"><input type="checkbox" name="" value="6"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_restaurant_blanc.png"/>"/></td>
                                        <td title="Evénement"><input type="checkbox" name="" value="7"/><img  style='width:32px;' src="<c:url value="/resources/img/activities_event_blanc.png"/>"/></td>
                                        <td title="Trouver des activités" style="text-align:right;"><img style='width:32px; cursor:pointer;' src="<c:url value="/resources/img/activities_start.png"/>" alt="Voir les poins d'interêt" class="submitButton" onclick="findActivitiesAround(event)"></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="tab-pane textContent itineraireContent" id="d">
                                <img src="<c:url value="/resources/img/position.png"/>" class="positionImage">
                                <p class="labels">Départ</p>
                                <input type="text" class="inputMenu" name="userPosition" id="userLongitude2">
                                <input type="text" class="inputMenu" name="userPosition" id="userLatitude2">
                                <p class="labels">Distance</p>
                                <input type="text" class="inputMenu inputHours" name="userDistance" id="userDistance2" width="30px">
                                <div class="menu-box">
                                    <a href="#">
                                        <img src="<c:url value="/resources/img/start.png"/>" alt="Voir les poins d'interêt" class="submitButton">
                                    </a>
                                </div>
                            </div>

                        </div><!-- /tab-content -->
                    </div><!-- /tabbable -->
            </div><!-- /row -->
                <div id="language">
                    <ul>
                        <li><img src="<c:url value="/resources/img/francais.png"/>" alt="Français" class="submitButton"></li>
                        <li><img src="<c:url value="/resources/img/anglais.png"/>" alt="Anglais" class="submitButton"></li>
                        <li><img src="<c:url value="/resources/img/allemand.png"/>" alt="Allemand" class="submitButton"></li>
                        <li><img src="<c:url value="/resources/img/neerlandais.png"/>" alt="Néerlandais" class="submitButton"></li>
                    </ul>
                </div>
                <div id="icone_partage">
                    <ul>
                    <li><img src="<c:url value="/resources/img/twitter.png" />" alt="Twitter" class="image_language"></li>
                    <li><img src="<c:url value="/resources/img/facebook.png"/>" alt="Facebook" class="image_language"></li>
                    <li><img src="<c:url value="/resources/img/gplus.png"/>" alt="Google plus" class="image_language"></li>
                    </ul>
                </div>
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