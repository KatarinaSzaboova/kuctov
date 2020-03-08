<#macro mydlg delete_page>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 80%;
  max-width:500px;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

@keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

/* The Close Button */
.modal_close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.modal_close:hover,
.modal_close:focus {
  color: #e91e63;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 2px 16px;
  background-color: #333;
  color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
  padding: 8px 16px;
  display: flex;
  flex-direction: row;
  flex-direction: row-reverse;
  background-color: #ddd;
}
/*====================================*/
.m_button {
  background-color: #333;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.m_button:hover {
  background-color: #e91e63;
}
/*====================================*/
</style>
<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span onclick="closeModalYesNo()" class="modal_close">&times;</span>
      <h2>Vymazať položku</h2>
    </div>
    <div class="modal-body">
      <br>
      <p>Ste si istí, že chcete vymazať položku z databázy?</p>
      <br>
    </div>
    <div class="modal-footer">
      <form action="${delete_page}" method="post">
         <input name="seldelid" id="seldelid" value="" hidden></input>
         <button class="m_button" type="submit" name="action" value="delete">Vymazať</button>
      </form>
      <button onclick="closeModalYesNo()" class="m_button">Zrušiť</button>
    </div>
  </div>

</div>
<script>
// When the user clicks the button, open the modal
function openModalYesNo() {
    if (document.getElementById("selectedid").value) {
        document.getElementById("myModal").style.display = "block";
    }
}
// When the user clicks on <span> (x), close the modal
function closeModalYesNo() {
  document.getElementById("myModal").style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  var modal = document.getElementById("myModal");
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
</#macro>