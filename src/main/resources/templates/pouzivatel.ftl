<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align: left;"><h1>Zoznam používateľov</h1></th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/pouzivatel_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť používateľa">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/pouzivatel_edit"><button class="m_button">Nový používateľ</button></a>
            <button onclick="openModalYesNo()" class="m_button">Zmazať položku</button>
        </div>
    </th>
  </tr>
</table>

<br>
<table id="m_page_tbl">
    <tr>
        <th>ID</th>
        <th>Prihlasovacie meno</th>
        <th>Mail</th>
        <th>Meno používateľa</th>
        <th>Priezvisko</th>
        <th>Adresa</th>
        <th>PSČ</th>
    </tr>
        <#list pouzivatels as pouzivatel>
            <tr>
                <td>${pouzivatel.id}</td>
                <td>${pouzivatel.login_meno}</td>
                <td>${pouzivatel.email}</td>
                <td>${pouzivatel.meno}</td>
                <td>${pouzivatel.priezvisko}</td>
                <td>${pouzivatel.adresa}</td>
                <td>${pouzivatel.psc}</td>
            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/pouzivatel_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
