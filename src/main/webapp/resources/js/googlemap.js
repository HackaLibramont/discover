/**
 * Created by Administrator on 21/03/2015.
 */
var map;
var currentWindow;
var markers = [];

function initialize() {
    var mapOptions = {
        center: { lat: -34.397, lng: 150.644},
        zoom: 8
    }

    currentWindow = null;
    markers = [];
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

    proximityMap(
        ["55.930385","-3.118425"],
        [
            ["50.087692","14.421150"],
            ["55.930385","14.421150"]
        ]);
}
google.maps.event.addDomListener(window, 'load', initialize);
window.addEventListener("resize", function (event) {
    var bounds = new google.maps.LatLngBounds();
    var eltBound = 0;
    for(i = 0; i < markers.length; i++)
    {
        bounds.extend(markers[i].getPosition());
        eltBound++;
    }
    if(eltBound > 1)
        map.fitBounds(bounds);
});
google.maps.event.addListener(map, 'click', function(event) {
    setCursorPosition(event.latLng.lat(), event.latLng.lng());
});

function callback(response, status) {
    tabResult = [];
    if (status != google.maps.DistanceMatrixStatus.OK) {
        alert('Error was: ' + status);
    }
    else {
        var origins = response.originAddresses;
        var destinations = response.destinationAddresses;
        var outputDiv = document.getElementById('outputDiv');

        for (var i = 0; i < origins.length; i++) {
            var results = response.rows[i].elements;
            for (var j = 0; j < results.length; j++) {

                tabResult.push([origins[i],destinations[j],results[j].duration.text]);
            }
        }
    }
    return tabResult;
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
    markers.push(CurrentPositionMarker);
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

    var service = new google.maps.DistanceMatrixService();

    for(var i = 0; i < tabLatLng.length; i++)
    {
        markers.push(createMarker(tabLatLng[i]));
    }

    var markerCluster = new MarkerClusterer(map, markers);
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
    markers.push(CurrentPositionMarker);
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
        markers.push(createMarker(tabLatLng[i]));
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

    var statement = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="firstHeading" class="firstHeading">Nom activité</h1>'+
        '<h2 id="firstHeading" class="firstHeading">Type</h2>'+
        '<div id="bodyContent">'+
        '<p><b>Totre</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
        'sandstone rock formation in the southern part of the '+
        'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
        'south west of the nearest large town, Alice Springs; 450&#160;km '+
        '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
        'features of the Uluru - Kata Tjuta National Park. Uluru is '+
        'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
        'Aboriginal people of the area. It has many springs, waterholes, '+
        'rock caves and ancient paintings. Uluru is listed as a World '+
        'Heritage Site.</p>'+
        '<p><b>3 rue chants doiseaux, 6791, Athus </b></p>' +
        '<h4>Contact :</h4>'+
        '<p><a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
        'https://en.wikipedia.org/w/index.php?title=Uluru</a></p> ' +
        '<p>azerty.7789@hotmail.com</p>'+
        '<p>+352 691 678 496</p>' +
        '</div>'+
        '</div>';

    var infowindow = new google.maps.InfoWindow({
        content: statement,
        maxWith: "250px",
        minWith: "150px"
    });

    google.maps.event.addListener(marker, 'click', function() {
        if(currentWindow != null)
        {
            currentWindow.close();
        }
        currentWindow = infowindow;
        infowindow.open(map,marker);
    });

    marker.setMap(map);

    return marker;
}

