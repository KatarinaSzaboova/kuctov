<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align:left;">
      <div class="flex-containerL">
      <form action="/banka" method="GET">
        <select class="m_select" style="width:90px;margin-left:0;" name="selectedyear" id="selectedyear" onchange="this.form.submit();">
          <#list roky as rok>
            <#if selectedyear == rok>
              <option value="${rok}" selected>${rok}</option>
            <#else>
              <option value="${rok}">${rok}</option>
            </#if>
          </#list>
        </select>
      </form>
      <form action="/banka" method="GET">
      <select class="m_select" style="width:200px;" name="selectedfirma" id="selectedfirma" onchange="this.form.submit();">
        <#list firmy as firma>
          <#if selectedfirma == firma>
            <option value="${firma}" selected>${firma}</option>
          <#else>
            <option value="${firma}">${firma}</option>
          </#if>
        </#list>
      </select>
      </form>
      <form action="/banka" method="GET">
      <select class="m_select" style="width:200px;" name="selecteducet" id="selecteducet" onchange="this.form.submit();">
        <#list ucty as ucet>
          <#if selecteducet == ucet>
            <option value="${ucet}" selected>${ucet}</option>
          <#else>
            <option value="${ucet}">${ucet}</option>
          </#if>
        </#list>
      </select>
      </form>
      <div>Banka</div>
      </div>
    </th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/banka_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť údaje">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/banka_edit"><button class="m_button">Nová položka</button></a>
            <button onclick="openModalYesNo()" class="m_button">Zmazať položku</button>
        </div>
    </th>
  </tr>
</table>

<br>
<table id="m_page_tbl">
    <tr>
        <th>ID</th>
        <th>ID Účet</th>
        <th>Dátum</th>
        <th>Ovplyvňuje základ dane</th>
        <th>Celková suma</th>
        <th>Partner</th>
        <th>Partner IBAN</th>


    </tr>
        <#list bankas as banka>
            <tr>
                <td>${banka.id}</td>
                <td>${banka.id_ucet}</td>
                <td>${banka.datum}</td>
                <td>${banka.ovplyv_zd}</td>
                <td>${banka.suma}</td>
                <td>${banka.partner}</td>
                <td>${banka.partner_iban}</td>
            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/banka_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
