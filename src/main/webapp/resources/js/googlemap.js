/**
 * Created by Administrator on 21/03/2015.
 */
var map;
var currentWindow;
var markers = [];

function initialize() {

    var styles =[{"featureType":"administrative","elementType":"all","stylers":[{"visibility":"on"},{"lightness":33}]},{"featureType":"landscape","elementType":"all","stylers":[{"color":"#f2e5d4"}]},{"featureType":"poi.park","elementType":"geometry","stylers":[{"color":"#c5dac6"}]},{"featureType":"poi.park","elementType":"labels","stylers":[{"visibility":"on"},{"lightness":20}]},{"featureType":"road","elementType":"all","stylers":[{"lightness":20}]},{"featureType":"road.highway","elementType":"geometry","stylers":[{"color":"#c5c6c6"}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"color":"#e4d7c6"}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"color":"#fbfaf7"}]},{"featureType":"water","elementType":"all","stylers":[{"visibility":"on"},{"color":"#acbcc9"}]}];

    var styledMap = new google.maps.StyledMapType(styles,
        {name: "Styled Map"});

    var mapOptions = {
        center: { lat: 49.9274427, lng: 5.3813976},
        zoom: 7
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    map.mapTypes.set('map_style', styledMap);
    map.setMapTypeId('map_style');

    currentWindow = null;
    markers = [];
    var origin1 = new google.maps.LatLng(55.930385, -3.118425);
    var destinationB = new google.maps.LatLng(50.087692, 14.421150);

    var flightPlanCoordinates = [
        origin1,
        destinationB
    ];

    google.maps.event.addListener(map, 'click', function(event) {
        setCursorPosition(event.latLng.lat(), event.latLng.lng());
    });
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

    // current position marker
    var CurrentPositionMarker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/red-dot.png",
        position: actualLoc,
        title:"You are here"
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
        tabLatLng.push(new google.maps.LatLng(tabLocations[i].geoY,tabLocations[i].geoX));
        bounds.extend(new google.maps.LatLng(tabLocations[i].geoY,tabLocations[i].geoX));
        eltBound++;
    }

    for(var i = 0; i < tabLatLng.length; i++)
    {
        markers.push(createMarker(tabLatLng[i],tabLocations[i]));
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
    var flightPlanCoordinates = [];

    // current position marker
    var CurrentPositionMarker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/red-dot.png",
        position: actualLoc,
        title:"You are here"
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

function createMarker(latLng,tabLocation)
{
    var marker = new google.maps.Marker({
        icon: "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png",
        position: latLng,
        title:""
    });

    var statement = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h1 id="firstHeading" class="firstHeading">'+ tabLocation.name +'</h1>'+
        '<h2 id="firstHeading" class="firstHeading">'+ tabLocation.location.name+'</h2>'+
        '<div id="bodyContent">'+
        '<p>'+ tabLocation.description+'</p>'+
        '<p><b>'+ tabLocation.mainContact.number + ' ' + tabLocation.mainContact.address +'</b></p>' +
        '<h4>Contact :</h4>'+
        '<p><a href="'+ tabLocation.mainContact.website+'">'
        + tabLocation.mainContact.website +'</a></p> ' +
        '<p>'+tabLocation.mainContact.email+'</p>'+
        '<p>'+tabLocation.mainContact.phone+'</p>' +
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

