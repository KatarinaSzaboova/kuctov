<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align: left;"><h1>Zoznam firiem</h1></th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/firma_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť firmu">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/firma_edit"><button class="m_button">Nová firma</button></a>
            <button onclick="openModalYesNo()" class="m_button">Zmazať položku</button>
        </div>
    </th>
  </tr>
</table>

<br>
<table id="m_page_tbl">
    <tr>
        <th>ID</th>
        <th>ID_Používateľ</th>
        <th>Názov</th>
        <th>Adresa</th>
        <th>PSČ</th>
        <th>IČO</th>
        <th>DIČ</th>
        <th>IČ_DPH</th>
    </tr>
        <#list firmas as firma>
            <tr>
                <td>${firma.id}</td>
                <td>${firma.id_pouzivatel}</td>
                <td>${firma.nazov}</td>
                <td>${firma.adresa}</td>
                <td>${firma.psc}</td>
                <td>${firma.ico}</td>
                <td>${firma.dic}</td>
                <td>${firma.ic_dph}</td>
            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/firma_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
