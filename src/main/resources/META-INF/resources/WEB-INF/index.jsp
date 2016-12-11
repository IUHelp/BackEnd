<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IUhelp | HOME</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="images/iu.png">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="material.min.css">

    <link rel="stylesheet" href="card.css">

    <link rel="stylesheet" href="styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js" integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>

    <style>
    #view-source {
      position: fixed;
      display: block;
      right: 0;
      bottom: 0;
      margin-right: 40px;
      margin-bottom: 40px;
      z-index: 900;
    }
    </style>




<link rel="stylesheet" type="text/css" href="mystyle.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
<style>
  * {
    font-family: Verdana, Arial, sans-serif;
  }
  a:link {
    color:#000;
    text-decoration: none;
  }
  a:visited {
    color:#000;
  }
  a:hover {
    color:#33F;
  }
  .button {
    background: -webkit-linear-gradient(top,#008dfd 0,#0370ea 100%);
    border: 1px solid #076bd2;
    border-radius: 3px;
    color: #fff;
    display: none;
    font-size: 13px;
    font-weight: bold;
    line-height: 1.3;
    padding: 8px 25px;
    text-align: center;
    text-shadow: 1px 1px 1px #076bd2;
    letter-spacing: normal;
  }
  .center {
    padding: 10px;
    text-align: center;
  }
  .final {
    color: black;
    padding-right: 3px;
  }
  .interim {
    color: gray;
  }
  .info {
    font-size: 14px;
    text-align: center;
    color: #777;
    display: none;
  }
  .right {
    float: right;
  }
  .sidebyside {
    display: inline-block;
    width: 45%;
    min-height: 40px;
    text-align: left;
    vertical-align: top;
  }
  #headline {
    font-size: 40px;
    font-weight: 300;
  }
  #info {
    font-size: 20px;
    text-align: center;
    color: #777;
    visibility: hidden;
  }
  #results {
    font-size: 14px;
    font-weight: bold;
    border: 1px solid #ddd;
    padding: 15px;
    text-align: left;
    min-height: 40px;
  }
  #start_button {
    border: 0;
    background-color:transparent;
    padding: 0;
  }
  .feedback{
   margin-top: 10px;
   margin-bottom: 10px;
   margin-right: 25%;
   margin-left: 25%;
}
  
</style>
</head>

<body>

<div class="container-fluid">
<nav class="navbar navbar-default" style="background-color: #7A1705">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="">
            <img alt="Brand" src="images/iub.png">
          </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
             <div class="container-fluid">
                       <div class="navbar-form navbar-right">

                              <button type="button" class="btn btn-success "> Voice Search</button>
                              <button type="button" class= "btn btn-primary "> Text Search</button>
                              <button type="button" class="btn btn-info "> Feedback</button>

                       </div>
             </div>

        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
</nav>



<div class="container-fluid">
  <div class="collapse in" id="searchbox">
        <div class="col-md-4 col-md-offset-3">

                <div class="form-group has-feedback">
                    <label for="search" class="sr-only">Search</label>
                    <input type="text" class="form-control" name="searchQuery" id="search" placeholder="search">
                      <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    <button id="searchbutton" type="submit" class="btn btn-default" onclick="httpGetAsyncText()">Search</button>
                </div>
        </div>
  </div>
</div>

<div class="container">
<div class="collapse" id="hello">

         <!-- <h1 class="center" id="headline"> -->
         <!--  <a href="http://dvcs.w3.org/hg/speech-api/raw-file/tip/speechapi.html">
            Web Speech API</a> Demonstration</h1> -->
        <div id="info">
          <p id="info_start">Click on the microphone icon and begin speaking.</p>
          <p id="info_speak_now">Speak now.</p>
          <p id="info_no_speech">No speech was detected. You may need to adjust your
            <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
              microphone settings</a>.</p>
          <p id="info_no_microphone" style="display:none">
            No microphone was found. Ensure that a microphone is installed and that
            <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
            microphone settings</a> are configured correctly.</p>
          <p id="info_allow">Click the "Allow" button above to enable your microphone.</p>
          <p id="info_denied">Permission to use microphone was denied.</p>
          <p id="info_blocked">Permission to use microphone is blocked. To change,
            go to chrome://settings/contentExceptions#media-stream</p>
          <p id="info_upgrade">Web Speech API is not supported by this browser.
             Upgrade to <a href="//www.google.com/chrome">Chrome</a>
             version 25 or later.</p>
        </div>
        <div class="right">
          <button id="start_button" onclick="startButton(event)">
            <img id="start_img" src="images/mic.gif" alt="Start"></button>
        </div>
        <div id="results">
          <span id="final_span" class="final"></span>
          <span id="interim_span" class="interim"></span>
          <p>
        </div>
        <div class="center">
              <div class="sidebyside" style="text-align:right">
                <button type="submit" class="btn btn-default" onclick="httpGetAsync()">Search</button>
                <!-- <div id="copy_info" class="info">
                  Press Control-C to copy text.<br>(Command-C on Mac.)
                </div> -->
              </div>
          <p>
              <div id="div_language">
                <select id="select_language" onchange="updateCountry()"></select>
                &nbsp;&nbsp;
                <select id="select_dialect"></select>
              </div>
        </div>

</div>
</div>
</div>
<script>

$("#search").keyup(function(event){
    if(event.keyCode == 13){
        $("#searchbutton").click();
    }
});
</script>


<div class="container">
  <div class="collapse" id="form">
      <div class= "feedback">
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="input" placeholder="Name">
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">Please enter the IU Email address</small>
      </div>
      <div class="form-group">
        <label for="Textarea">Comments</label>
        <textarea class="form-control" id="Textarea" rows="3"></textarea>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
  </div>
</div>  


<script>
$(document).ready(function(){
    $(".btn-success").click(function(){
        $("#hello").collapse('show');
        $("#searchbox").collapse('hide');
        $("#form").collapse('hide');
    });
    $(".btn-primary").click(function(){
        $("#hello").collapse('hide');
        $("#searchbox").collapse('show');
        $("#form").collapse('hide');
    });
     $(".btn-info").click(function(){
        $("#hello").collapse('hide');
        $("#searchbox").collapse('hide');
        $("#form").collapse('show');
    });
});
</script>

    <!-- to display search results -->
    <br>
    <br>
    <div class="container" id ="SearchResult"></div>

<script>
$(document).ready(function(){
    $(".btn-info").click(function(){
        alert("inside javascript func above");
        if ($('button').text() == "see more"){
            alert("inside javascript func");
            $("#info").collapse('show');
            document.getElementById("textResult").value= "see less";
            alert("end of if");
        }else{
            $("#info").collapse('hide');
            document.getElementById("textResult").value= "see more";
        }
    });

});
</script>



<script type="text/javascript" src="myjavascript.js"></script>

</body>
</html>