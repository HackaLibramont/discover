<html>
<body>
	<h1>${message}</h1>
    <!DOCTYPE html>
    <html>
    <head>
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
    </head>
    <body>
    <div id="map-canvas"></div>
    </body>
    </html>
</body>
</html>