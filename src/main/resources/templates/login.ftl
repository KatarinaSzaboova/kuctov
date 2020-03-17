<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial, Helvetica, sans-serif; 
  background-image: url('public/img/login.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
button {
  background-color: #333;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}
button:hover {
  background-color: #e91e63;
  color: white;
}
.container {
  padding: 16px;
  background-color: #f2f2f2;
}
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.2); /* Black w/ opacity */
  padding-top: 60px;
  opacity: 0.8;
}
/* Modal Content/Box */
.modal-content {
  background-color: #fefefe, 0.5;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
  max-width: 600px;
}
/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}
@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}
</style>
<link href="public/img/favicon.ico" rel="icon" type="image/x-icon" />
</head>
<body>
<div id="logindlg" class="modal">
  <form class="modal-content animate" action="/login" method="post">
    <div class="container">
      <h2>kuctov - prihlásenie</h2> 
      <label for="meno"><b>Používateľ</b></label>
      <input type="text" placeholder="zadajte meno" name="meno" value="${meno}" required>
      <label for="psw"><b>Heslo</b></label>
      <input type="password" placeholder="zadajte heslo" name="heslo" value="${heslo}" required>
      <button type="submit">Prihlásiť sa</button>
    </div>
  </form>
</div>
<script>
document.getElementById('logindlg').style.display='block'
</script>
</body>
</html>
