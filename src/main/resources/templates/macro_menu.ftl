<#macro menu_page>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
    margin:0;
    font-family: Arial, Helvetica, sans-serif;
}

.navbar {
  overflow: hidden;
  background-color: #333;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1;
}

.navbar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.navbar a:hover {
  background: #ddd;
  color: black;
}

.topnav-right {
  float: right;
}

.main {
  padding: 16px;
  margin-top: 30px;
}

.logo {
  padding: 0;
  float: left;
}

/* Style the active class (and buttons on mouse-over) */
.active {
  background-color: #e91e63;
  color: white;
}
/*====================================*/
.m_dropdown {
  float: left;
  overflow: hidden;
}

.m_dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .m_dropdown:hover .dropbtn {
  background-color: #e91e63;
}

.m_dropdown-content {
  display: none;
  position: fixed;
  background-color: #f9f9f9;
  min-width: 180px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.m_dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.m_dropdown-content a:hover {
  background-color: #ddd;
}

.m_dropdown:hover .m_dropdown-content {
  display: block;
}
/*====================================*/
#onScrollButton {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: #333;
  color: white;
  cursor: pointer;
  padding: 12px;
  border-radius: 4px;
}
#onScrollButton:hover {
  background-color: #e91e63;
}
/*====================================*/
#snackbar {
  visibility: hidden;
  min-width: 250px;
  margin-left: -125px;
  background-color: red;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 1;
  left: 50%;
  bottom: 30px;
  font-size: 17px;
}

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 3.5s;
  animation: fadein 0.5s, fadeout 0.5s 3.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}
/*====================================*/
.m_select {
  font-size: 18px;
  /*padding: 12px 20px;*/
  padding: 12px;
  margin: 8px 2px;
  display: inline-block;
  border: 1px solid #ccc;
  /*border-radius: 4px;*/
  box-sizing: border-box;
}
/*====================================*/
</style>
<link rel="stylesheet" href="/public/css/m_common.css">
<link href="public/img/favicon.ico" rel="icon" type="image/x-icon" />
</head>
<body onload="onLoadBody();">

<div class="navbar" id="myDIV">
  <a href="/" style="padding:0"><img class="logo" src="public/img/ksz.png"></img></a>
  <a href="/pokladna" class="btn">Pokladňa</a>
  <a href="/vf" class="btn">Vyšlé faktúry</a>
  <a href="/df" class="btn">Došlé faktúry</a>
  <a href="/banka" class="btn">Banka</a>
  <div class="m_dropdown">
      <button class="dropbtn">Zostavy
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="m_dropdown-content">
        <a href="#">Stav podnikania</a>
        <a href="#">Kniha vyšlých faktúr</a>
      </div>
  </div>
  <div class="topnav-right">
    <div class="m_dropdown">
      <button class="dropbtn">Nastavenia
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="m_dropdown-content">
        <a href="/pouzivatel">Používatelia</a>
        <a href="/firma">Firmy</a>
        <a href="#">Partneri</a>
        <a href="/bank_ucet">Bankové účty</a>
        <a href="/upload_vypisy">Načítať súbor bankových výpisov</a>
      </div>
    </div> 
    <div class="m_dropdown">
      <button class="dropbtn"> 
        <i class="fa fa-fw fa-user"></i>
        <i class="fa fa-caret-down"></i>
      </button>
      <div class="m_dropdown-content" style="right:0">
        <a href="#">Profil používateľa</a>
        <a href="/login">Odhlásiť</a>
      </div>
    </div> 
  </div> 
</div>

<div class="main">
   <#nested>
</div>

<button onclick="topFunction()" id="onScrollButton" title="Go to top">Nahor</button>
<#if error??>
<div id="snackbar">${error}</div>
</#if>
<#if info??>
<div id="snackbar" style="background-color:#333;">${info}</div>
</#if>

<script>
<#if error??>
    onShowSnackBar();
</#if>
<#if info??>
    onShowSnackBar();
</#if>

function onLoadBody() {
}

//Get the button
var muScrollButton = document.getElementById("onScrollButton");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    muScrollButton.style.display = "block";
  } else {
    muScrollButton.style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
/*====================================*/
function onShowSnackBar() {
  var x = document.getElementById("snackbar");
  x.className = "show";
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
</script>

</body>
</html> 
  
</#macro>
