<#import "macro_menu.ftl" as mm>
<#import "macro_modal.ftl" as mmodal>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/mytable.css">
<script type="text/javascript" src="/public/js/mytable.js"></script>


<table style="width:100%">
  <tr>
    <th style="text-align:left;">
      <div class="flex-containerL">
      <form action="/pokladna" method="GET">
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
      <form action="/pokladna" method="GET">
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
      <div>Pokladňa</div>
      </div>
    </th>
    <th style="text-align: right;">
        <div class="flex-container">
            <form action="/pokladna_change" method="get">
                <input class="m_button" type="submit" name="zmen" value="Zmeniť údaje">
                <input name="selectedid" id="selectedid" value="" hidden></input>
            </form>
            <a href="/pokladna_edit"><button class="m_button">Nová položka</button></a>
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
        <th>Číslo Dokladu</th>
        <th>Vzor</th>
        <th>Dátum</th>
        <th>Príjem/Výdaj</th>
        <th>Ovplyvňuje základ dane</th>
        <th>Suma bez DPH</th>
        <th>Suma s DPH</th>

    </tr>
        <#list pokladnas as pokladna>
            <tr>
                <td>${pokladna.id}</td>
                <td>${pokladna.id_firma}</td>
                <td>${pokladna.cislo_dokladu}</td>
                <td>${pokladna.vzor}</td>
                <td>${pokladna.datum}</td>
                <td>${pokladna.prijem_vydaj}</td>
                <td>${pokladna.ovplyv_zd}</td>
                <td>${pokladna.suma_bez_dph}</td>
                <td>${pokladna.suma_s_dph}</td>

            </tr>
        </#list>
</table>
<@mmodal.mydlg delete_page="/pokladna_delete" />
<script>
    initTablePage();
</script>
</@mm.menu_page>
