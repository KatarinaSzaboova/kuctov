 <#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="meno">Dátum</label>
        <input class="m_input_text" id="datum" name="datum" value="${datum}" placeholder="dátum" required>
    </p>
       <p>
        <label for="partner">Partner</label>
        <input class="m_input_text" id="partner" name="partner" value="${partner}" placeholder="partner" required>
    </p>
    <p>
        <label for="partner_iban">IBAN</label>
        <input class="m_input_text" id="partner_iban" name="partner_iban" value="${partner_iban}" placeholder="partner iban" required>
    </p>
    <p>
        <label for="prijem_vydaj">Príjem / výdaj</label>
        <input class="m_input_text" id="prijem_vydaj" name="prijem_vydaj" value="${prijem_vydaj}" placeholder="P/V" required>
    </p>
    <p>
        <label for="ovplyv_zd">Ovplyvňuje základ dane</label>
        <input class="m_input_text" id="ovplyv_zd" name="ovplyv_zd" value="${ovplyv_zd}" placeholder="A/N" required>
    </p>
    <p>
        <label for="suma">Suma</label>
        <input class="m_input_text" id="suma" name="suma" value="${suma}" placeholder="suma" required>
    </p>
    <p>
        <div class="btn-group">
          <#if fa == "/banka_new">
            <button type="submit" name="action" value="next">Uložit a ďalej</button>
          </#if>
          <button type="submit" name="action" value="save">Uložiť</button>
          <button onclick="window.location.href='/banka';">Zrušiť</button>
        </div>
    </p>
</form>
</div>
</@mm.menu_page>