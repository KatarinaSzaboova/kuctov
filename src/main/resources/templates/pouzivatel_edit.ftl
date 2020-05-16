<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<link rel="stylesheet" href="/public/css/edit_record.css">

<div class="m_container">
<h1>${title}</h1>
<#assign fa = "${form_action}">
<form action="${form_action}" method="post">
<input name="selectedid" id="selectedid" value="${selectedid}" hidden></input>
    <p>
        <label for="login_meno">Prihlasovacie meno</label>
        <input type="text"  maxlength="40"
        class="m_input_text" id="login_meno" name="login_meno" value="${login_meno}" placeholder="prihlasovacie meno" required>
    </p>
    <p>
        <label for="login_heslo">Heslo</label>
        <input maxlength="40"
        type="password" class="m_input_text" id="login_heslo" name="login_heslo" value="${login_heslo}" placeholder="heslo" required>
    </p>
    <p>
        <label for="email">Mailová adresa</label>
        <input type="email"
        class="m_input_text" id="email" name="email" value="${email}" placeholder="mailová adresa" required>
    </p>
    <p>
        <label for="meno">Meno používateľa</label>
        <input type="text"  maxlength="40"
        class="m_input_text" id="meno" name="meno" value="${meno}" placeholder="meno" required>
    </p>
    <p>
        <label for="priezvisko">Priezvisko používateľa</label>
        <input type="text"  maxlength="40"
        class="m_input_text" id="priezvisko" name="priezvisko" value="${priezvisko}" placeholder="priezvisko" required>
    </p>
    <p>
        <label for="adresa">Adresa</label>
        <input type="text"  maxlength="60"
        class="m_input_text" id="adresa" name="adresa" value="${adresa}" placeholder="adresa" required>
    </p>
    <p>
        <label for="psc">PSČ</label>
        <input type="text" pattern="[0-9]{5}"
        class="m_input_text" id="psc" name="psc" value="${psc}" placeholder="PSČ" required>
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
