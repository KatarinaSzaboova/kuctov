<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align:left;">
      <div class="flex-containerL">
      <form action="/df" method="GET">
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
      <form action="/df" method="GET">
      <select class="m_select" style="width:200px;" name="selectedfirma" id="selectedfirma" selected="${selectedfirma}" onchange="this.form.submit();">
        <#list firmy as firma>
          <#if selectedfirma == firma>
            <option value="${firma}" selected>${firma}</option>
          <#else>
            <option value="${firma}">${firma}</option>
          </#if>
        </#list>
      </select>
      </form>
      <div>Došlé faktúry</div>
      </div>
    </th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/df_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť údaje">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/df_edit"><button class="m_button">Nová položka</button></a>
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
        <th>Číslo faktúry</th>
        <th>Dodávateľ</th>
        <th>Dátum</th>
        <th>Dátum splatnosti</th>
        <th>Suma bez DPH</th>
        <th>Celková suma</th>
    </tr>
        <#list dfs as df>
            <tr>
                <td>${df.id}</td>
                <td>${df.id_firma}</td>
                <td>${df.cislo_faktury}</td>
                <td>${df.dodavatel}</td>
                <td>${df.datum}</td>
                <td>${df.datum_splatnosti}</td>
                <td>${df.suma_bez_dph}</td>
                <td>${df.suma_s_dph}</td>
            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/df_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
