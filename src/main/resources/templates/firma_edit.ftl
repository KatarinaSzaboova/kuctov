<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="nazov">Názov</label>
        <input class="m_input_text" id="nazov" name="nazov" value="${nazov}" placeholder="názov" required>
    </p>
    <p>
        <label for="adresa">Adresa</label>
        <input class="m_input_text" id="adresa" name="adresa" value="${adresa}" placeholder="adresa" required>
    </p>
    <p>
        <label for="psc">PSČ</label>
        <input class="m_input_text" id="psc" name="psc" value="${psc}" placeholder="psč" required>
    </p>
    <p>
        <label for="ico">IČO</label>
        <input class="m_input_text" id="ico" name="ico" value="${ico}" placeholder="ičo" required>
    </p>
    <p>
        <label for="dic">DIČ</label>
        <input class="m_input_text" id="dic" name="dic" value="${dic}" placeholder="dič" required>
    </p>
    <p>
        <label for="ic_dph">IČ_DPH</label>
        <input class="m_input_text" id="ic_dph" name="ic_dph" value="${ic_dph}" placeholder="ič_dph" required>
    </p>
    <p>
        <div class="btn-group">
          <#if fa == "/pouzivatel_new">
            <button type="submit" name="action" value="next">Uložit a ďalšia faktúra</button>
          </#if>
          <button type="submit" name="action" value="save">Uložiť</button>
          <button onclick="window.location.href='/pouzivatel';">Zrušiť</button>
        </div>
    </p>
</form>
</div>
</@mm.menu_page>
