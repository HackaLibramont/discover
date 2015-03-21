/**
 * Created by Administrator on 21/03/2015.
 */
var map;

function initialize() {
    var mapOptions = {
        center: { lat: -34.397, lng: 150.644},
        zoom: 8
    };

    var origin1 = new google.maps.LatLng(55.930385, -3.118425);
    var destinationB = new google.maps.LatLng(50.087692, 14.421150);

    var flightPlanCoordinates = [
        origin1,
        destinationB
    ];

/*
    var service = new google.maps.DistanceMatrixService();
    service.getDistanceMatrix(
        {
            origins: [origin1],
            destinations: [destinationB],
            travelMode: google.maps.TravelMode.DRIVING,
            unitSystem: google.maps.UnitSystem.METRIC,
            durationInTraffic: false,
            avoidHighways: false,
            avoidTolls: false
        }, callback);

    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    }); */

   /* map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions); */

    routingMap(
        ["55.930385","-3.118425"],
        [
            ["50.087692","14.421150"],
            ["55.930385","14.421150"]
        ]);
}
google.maps.event.addDomListener(window, 'load', initialize);
window.onresize=function(){

};

function callback(response, status) {
    if (status == google.maps.DistanceMatrixStatus.OK) {
        var origins = response.originAddresses;
        var destinations = response.destinationAddresses;

        for (var i = 0; i < origins.length; i++) {
            var results = response.rows[i].elements;
            for (var j = 0; j < results.length; j++) {
                var element = results[j];
                var distance = element.distance.text;
                alert(distance);
                var duration = element.duration.text;
                alert(duration);
                var from = origins[i];
                var to = destinations[j];
            }
        }
    }
}

function proximityMap(actualLocalisation,tabLocations)
{
    var eltBound = 0;
    var actualLoc = new google.maps.LatLng(actualLocalisation[0],actualLocalisation[1]);
    var mapOptions = {
        center: actualLoc,
        zoom: 8
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);

    // current position marker
    var CurrentPositionMarker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
        position: actualLoc,
        title:""
    });
    CurrentPositionMarker.setMap(map);

    // map bounds
    var bounds = new google.maps.LatLngBounds();
    bounds.extend(actualLoc);
    eltBound++;

    // Add all locations markers
    var tabLatLng = [];
    for(var i = 0; i < tabLocations.length; i++)
    {
        tabLatLng.push(new google.maps.LatLng(tabLocations[i][0],tabLocations[i][1]));
        bounds.extend(new google.maps.LatLng(tabLocations[i][0],tabLocations[i][1]));
        eltBound++;
    }

    for(var i = 0; i < tabLatLng.length; i++)
    {
        createMarker(tabLatLng[i]);
    }

    // if elt = 1 then, only current position so no bounding on the map
    if(eltBound > 1)
        map.fitBounds(bounds);
}

function routingMap(actualLocalisation,tabLocations)
{
    var eltBound = 0;
    var actualLoc = new google.maps.LatLng(actualLocalisation[0],actualLocalisation[1]);
    var mapOptions = {
        center: actualLoc,
        zoom: 8
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
    var flightPlanCoordinates = [];

    // current position marker
    var CurrentPositionMarker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
        position: actualLoc,
        title:""
    });
    CurrentPositionMarker.setMap(map);
    flightPlanCoordinates.push(actualLoc);

    // map bounds
    var bounds = new google.maps.LatLngBounds();
    bounds.extend(actualLoc);
    eltBound++;

    // Add all locations markers
    var tabLatLng = [];
    for(var i = 0; i < tabLocations.length; i++)
    {
        tabLatLng.push(new google.maps.LatLng(tabLocations[i][0],tabLocations[i][1]));
        bounds.extend(new google.maps.LatLng(tabLocations[i][0],tabLocations[i][1]));
        eltBound++;
    }



    for(var i = 0; i < tabLatLng.length; i++)
    {
        flightPlanCoordinates.push(tabLatLng[i]);
    }

    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    flightPath.setMap(map);

    for(var i = 0; i < tabLatLng.length; i++)
    {
        createMarker(tabLatLng[i]);
    }

    // if elt = 1 then, only current position so no bounding on the map
    if(eltBound > 1)
        map.fitBounds(bounds);
}

function createMarker(latLng)
{
    var marker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/red-dot.png",
        position: latLng,
        title:""
    });

    var infowindow = new google.maps.InfoWindow({
        content: 'lol'
    });

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map,marker);
    });

    marker.setMap(map);
}