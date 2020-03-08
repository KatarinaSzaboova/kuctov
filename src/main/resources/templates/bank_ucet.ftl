<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align: left;"><h1>Zoznam bankových účtov</h1></th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/bank_ucet_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť účet">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/bank_ucet_edit"><button class="m_button">Nový účet</button></a>
            <button onclick="openModalYesNo()" class="m_button">Zmazať položku</button>
        </div>
    </th>
  </tr>
</table>

<br>
<table id="m_page_tbl">
    <tr>
        <th>ID</th>
        <th>ID Firmy</th>
        <th>Názov</th>
        <th>BIC</th>
        <th>IBAN</th>
        <th>Mena</th>
    </tr>
        <#list bank_ucets as bank_ucet>
            <tr>
                <td>${bank_ucet.id}</td>
                <td>${bank_ucet.id_firma}</td>
                <td>${bank_ucet.nazov}</td>
                <td>${bank_ucet.bic}</td>
                <td>${bank_ucet.iban}</td>
                <td>${bank_ucet.mena}</td>
            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/bank_ucet_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
