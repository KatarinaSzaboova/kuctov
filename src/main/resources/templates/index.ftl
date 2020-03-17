<#import "macro_menu.ftl" as mm>

<@mm.menu_page>

<style>
#home_page {
  font-family: Arial, Helvetica, sans-serif;
  background-image: url('public/img/login.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  font-size: 120%;
}
.m_card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  text-align: left;
  background-color: #f1f1f1;
  opacity: 0.7;
}
.m_footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   /*background-color: #333;*/
   color: white;
   text-align: center;
}
</style>

<body id="home_page">

<div style="color:white;">
  <h2>Vitajte v aplikácii pre jednoduché účtovníctvo</h2>
</div>
<div>
    <div class="m_card">
      <h3>Aplikácia kuctov</h3>
      <p>
        Aplikácia kuctov predstavuje zjednodúšenú verziu webovej aplikácie pre modelovanie porcesov
        jednoduchého účtovníctva. Aplikácia bola vytvorená v rámci vypracovania diplomovej práce.
      </p>
      <p>
        V aplikácii je implementovná základná funkcionalita týkajúca sa správy pokladne, vyšlých
        a došlých faktúr a banky. Funkcionalitu zabezpečujú tabuľky používateľov, firiem, bankových účtov, apod. Ako
        špeciálna rozširujúca funkcionalita pre aplikáciu zameranej na jednoduché účtovníctvo,
        je implementované načítanie bankových výpisov vo formáte CSV.
      </p>
    </div>
</div>
<br>
<div>
    <div class="m_card">
      <h3>Použité technológie</h3>
      <p>
        Aplikácia je napísaná v jazyku <a href="https://www.java.com/en//">Java</a>.
        Pre zabezpečenie webového rozhrania bol zvolený
        mikro-framework <a href="http://www.pippo.ro/">Pippo</a>.
      </p>
      <p>
        Pre vytváranie šablón (template) jednotlivých stránok bola použitá knižnica
        <a href="https://freemarker.apache.org/">Freemarker</a>.
      </p>
      <p>
        Pre generovanie dátových vzoriek bola zvolená knižnica
        <a href="https://www.mockneat.com">MockNeat</a>.
      </p>
      <p>
        Napojenie na MySQL databázu zabezpečuje ODBC (Open Database Connectivity)
        <a href="https://dev.mysql.com/downloads/connector/j/">ovládač</a>.
      </p>
      <p>
        Návrh stránok bol založený najmä na príkladoch HTML, kaskádových štýloch (CSS)
        a skriptoch JavaScript zo stránky <a href="https://www.w3schools.com/">w3schools</a>.
      </p>
    </div>
</div>
<div class="m_footer">
  <p>Bratislava 2020, Katarína Szabóová</p>
</div>
</body>

</@mm.menu_page>