<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <title>Category Created Successfully</title>
        <script>
        	$.ready(setTimeout(function(){
        		alert("aaaa");
        		window.location.href = "createNewCatagory.htm";
        	}, 2000));
        	
        </script>
    </head>
    <body>
    	<div class="alert alert-success">
		  <strong>Success!</strong> New Category Created Successfully: ${category.title}
		</div>
    </body>
</html>