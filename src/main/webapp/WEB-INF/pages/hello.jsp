<html>
<head>
    <link href="....js">
</head>
<body>
    <input type="button" onclick="loadActivites()"/>
	${list}
<div id="c"></div>
</body>
</html>

<script>
    function loadActivites()
    {
        $.ajax(url =...).done(function(html) {$("#c").html(html);})
    }

</script>