<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="cislo_dokladu">Číslo Dokladu</label>
        <input type="number" step="1"
        class="m_input_text" id="cislo_dokladu" name="cislo_dokladu" value="${cislo_dokladu}" placeholder="číslo dokladu" required>
    </p>
    <p>
        <label for="vzor">Vzor</label>
        <input type="text"  maxlength="20"
        class="m_input_text" id="vzor" name="vzor" value="${vzor}" placeholder="vzor" required>
    </p>
    <p>
        <label for="cena_bez_dph">Cena bez DPH</label>
        <input type="number" step="0.01"
        class="m_input_text" id="suma_bez_dph" name="suma_bez_dph" value="${suma_bez_dph}" placeholder="cena bez DPH" required>
    </p>
    <p>
        <label for="ovplyv_zd">Ovplyvňuje základ dane (A/N)</label>
        <input type="text" pattern="[AN]{1}"
        class="m_input_text" id="ovplyv_zd" name="ovplyv_zd" value="${ovplyv_zd}" placeholder="A/N" required>
    </p>
    <p>
        <label for="meno">Dátum</label>
        <input type="date"
        class="m_input_text" id="datum" name="datum" value="${datum}" placeholder="dátum" required>
    </p>
    <p>
        <div class="btn-group">
          <#if fa == "/pokladna_new">
            <button type="submit" name="action" value="next">Uložit a ďalší doklad</button>
          </#if>
          <button type="submit" name="action" value="save">Uložiť</button>
          <button onclick="window.location.href='/pokladna';">Zrušiť</button>
        </div>
    </p>
</form>
</div>
</@mm.menu_page>