<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="nazov">Názov účtu</label>
        <input type="text" maxlength="40"
        class="m_input_text" id="nazov" name="nazov" value="${nazov}" placeholder="nazov" required>
    </p>
    <p>
        <label for="bic">BIC</label>
        <input type="text" minlength="8" maxlength="8"
        class="m_input_text" id="bic" name="bic" value="${bic}" placeholder="bic / swift číslo" required>
    </p>
    <p>
        <label for="iban">IBAN</label>
        <input type="text" pattern="[A-Z]{2}[0-9]{22}"
        class="m_input_text" id="iban" name="iban" value="${iban}" placeholder="iban" required>
    </p>
    <p>
        <label for="mena">Mena</label>
        <input type="text" pattern="EUR{3}"
        class="m_input_text" id="mena" name="mena" value="${mena}" required readonly>
    </p>
    <p>
        <div class="btn-group">
          <#if fa == "/bank_ucet_new">
            <button type="submit" name="action" value="next">Uložit a pridať ďalší bankový účet</button>
          </#if>
          <button type="submit" name="action" value="save">Uložiť</button>
          <button onclick="window.location.href='/bank_ucet';">Zrušiť</button>
        </div>
    </p>
</form>
</div>
</@mm.menu_page>
