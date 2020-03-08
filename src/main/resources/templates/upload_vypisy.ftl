<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<style>
body {
    background-color: aliceblue;
}
.m_container {
  background-color: aliceblue;
  padding: 20px;
  max-width: 800px;
  margin:0 auto;
}
/*====================================*/
input[type=submit] {
  background-color: #333;
  color: white;
  padding: 12px 40px;
  border: 1px solid white;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}
input[type=submit]:hover {
  background-color: #e91e63;
}
input[type="file"] {
  position: absolute;
  left: 0;
  opacity: 0;
  top: 0;
  bottom: 0;
  width: 100%;
}
#m_drag_div {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  color: white;
  background-color: #333;
  border-radius: 4px;
}
#m_drag_div.dragover {
  background-color: #333;
}
.m_drag_label {
  display: inline-block;
  position: relative;
  height: 300px;
  width: 100%;
}
</style>

<div class="m_container">

<h1>Nahrať súbor bankových výpisov</h1>
<form action="/upload_vypisy" method="post" enctype="multipart/form-data">
<label class="m_drag_label" for="fileinput">
  <div id="m_drag_div">
    <label id="filename_empty">Pre výber súboru kliknite, alebo potiahnite súbor sem!</label>
    <label id="filename"></label>
  </div>
  <input type="file" id="fileinput" name="file" title="">
</label>
<p><input type="submit" value="Nahrať súbor"></p>
</form>
</div>

<script>
var fileInput = document.querySelector('input[type=file]');
var filenameContainer = document.querySelector('#filename');
var dropzone = document.querySelector('#m_drag_div');
fileInput.addEventListener('change', function() {
	filenameContainer.innerText = fileInput.value.split('\\').pop();
});
fileInput.addEventListener('dragenter', function() {
	dropzone.classList.add('dragover');
});
fileInput.addEventListener('dragleave', function() {
	dropzone.classList.remove('dragover');
});
</script>

</@mm.menu_page>
