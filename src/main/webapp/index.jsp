<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <meta charset="UTF-8">
    <title><%= my.webapp.Environment.getProperty("name") %></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
  <style>

	.card {
		margin-bottom: 5px;
	}
  </style>
  </head>
<body>
      <main role="main" class="container">

          	<div class="container" style="margin-top: 25px;" id="cards">
          		<div class="row justify-content-md-center" style="margin-bottom: 10%">
          			<form class="form-inline" action="javascript:void(0);">
      					  <input type="text" class="form-control mb-2 mr-sm-4 form-control-lg" id="addDownload" placeholder="Ссылка на файл...">

                        <input type="submit" id="addDownloadButton" class="btn btn-success mb-2" value="Добавить" />
      				</form>
          		</div>

          	</div>

          </main>
      <!-- Bootstrap core JavaScript
      ================================================== -->
      <!-- Placed at tde end of the document so the pages load faster -->
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


  <script type="text/javascript" src="./js/client.js"></script>
  <script type="text/javascript" src="./js/front.js"></script>
  </body>
</html>
