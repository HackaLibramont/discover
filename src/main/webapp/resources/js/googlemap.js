/**
 * Created by Administrator on 21/03/2015.
 */
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


function timeTravel() {
    var origin1 = new google.maps.LatLng(55.930385, -3.118425);
    var origin2 = "Greenwich, England";
    var destinationA = "Stockholm, Sweden";
    var destinationB = new google.maps.LatLng(50.087692, 14.421150);

    var service = new google.maps.DistanceMatrixService();
    service.getDistanceMatrix(
        {
            origins: [origin1, origin2],
            destinations: [destinationA, destinationB],
            travelMode: google.maps.TravelMode.DRIVING,
            transitOptions: TransitOptions,
            unitSystem: UnitSystem,
            durationInTraffic: false,
            avoidHighways: false,
            avoidTolls: false
        }, callback);
}

function callback(response, status) {
    if (status == google.maps.DistanceMatrixStatus.OK) {
        var origins = response.originAddresses;
        var destinations = response.destinationAddresses;

        for (var i = 0; i < origins.length; i++) {
            var results = response.rows[i].elements;
            for (var j = 0; j < results.length; j++) {
                var element = results[j];
                var distance = element.distance.text;
                var duration = element.duration.text;
                alert(duration);
                var from = origins[i];
                var to = destinations[j];
            }
        }
    }
}
