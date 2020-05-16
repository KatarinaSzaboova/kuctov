<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="cislo_faktury">Číslo faktúry</label>
        <input type="number" step="1"
        class="m_input_text" id="cislo_faktury" name="cislo_faktury" value="${cislo_faktury}" placeholder="číslo faktúry" required>
    </p>
    <p>
        <label for="dodavatel">Dodávateľ</label>
        <input type="text"  maxlength="40"
        class="m_input_text" id="dodavatel" name="dodavatel" value="${dodavatel}" placeholder="dodavatel" required>
    </p>
    <p>
        <label for="cena_bez_dph">Cena bez DPH</label>
        <input type="number" step="0.01"
        class="m_input_text" id="suma_bez_dph" name="suma_bez_dph" value="${suma_bez_dph}" placeholder="cena bez DPH" required>
    </p>
    <p>
        <label for="meno">Dátum</label>
        <input type="date"
        class="m_input_text" id="datum" name="datum" value="${datum}" placeholder="dátum" required>
    </p>
    <p>
        <div class="btn-group">
          <#if fa == "/df_new">
            <button type="submit" name="action" value="next">Uložit a ďalšia faktúra</button>
          </#if>
          <button type="submit" name="action" value="save">Uložiť</button>
          <button onclick="window.location.href='/df';">Zrušiť</button>
        </div>
    </p>
</form>
</div>
</@mm.menu_page>
