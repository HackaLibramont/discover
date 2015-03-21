<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 20-03-15
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        html, body, #map-canvas { height: 100%; margin: 0; padding: 0;}
    </style>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false">
    </script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                center: { lat: -34.397, lng: 150.644},
                zoom: 8
            };

            var flightPlanCoordinates = [
                new google.maps.LatLng(37.772323, -122.214897),
                new google.maps.LatLng(21.291982, -157.821856),
                new google.maps.LatLng(-18.142599, 178.431),
                new google.maps.LatLng(-27.46758, 153.027892)
            ];
            var flightPath = new google.maps.Polyline({
                path: flightPlanCoordinates,
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2
            });


            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

            flightPath.setMap(map);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>

    <link rel="stylesheet" href="<c:url value="/resources/lib/bootstrap-3.3.4-dist/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/lib/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/lib/jasny-bootstrap/css/jasny-bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/navmenu.css" />">
    <script src="<c:url value="/resources/lib/jquery/jquery-1.11.2.min.js" />"></script>
    <script src="<c:url value="/resources/lib/bootstrap-3.3.4-dist/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/lib/jasny-bootstrap/js/jasny-bootstrap.min.js" />"></script>

</head>
<body>
<div class="navmenu navmenu-default navmenu-fixed-left offcanvas-sm">
    <a class="navmenu-brand visible-md visible-lg" href="#">Project name</a>
    <ul class="nav navmenu-nav">
        <li class="active"><a href="./">Slide in</a></li>
        <li><a href="../navmenu-push/">Push</a></li>
        <li><a href="../navmenu-reveal/">Reveal</a></li>
        <li><a href="../navbar-offcanvas/">Off canvas navbar</a></li>
    </ul>
    <ul class="nav navmenu-nav">
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
            <ul class="dropdown-menu navmenu-nav">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
            </ul>
        </li>
    </ul>
</div>

<div class="navbar navbar-default navbar-fixed-top hidden-md hidden-lg">
    <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".navmenu">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Project name</a>
</div>

<div id="map-canvas"></div>
</body>
</html>