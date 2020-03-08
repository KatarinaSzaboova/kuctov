
function initTablePage() {
    var table = document.getElementById('m_page_tbl');
    selected = table.getElementsByClassName('selected');
    table.onclick = highlightTblLine;
}
function highlightTblLine(e) {
    var table = document.getElementById('m_page_tbl');
    selected = table.getElementsByClassName('selected');
    if (selected[0]) selected[0].className = '';
    e.target.parentNode.className = 'selected';
    var selid = parseInt(e.target.parentNode.children[0].innerHTML);
    if (Number.isNaN(selid)) {
        document.getElementById("selectedid").value = null;
        document.getElementById("seldelid").value = null;
    } else {
        var value = e.target.parentNode.children[0].innerHTML;
        document.getElementById("selectedid").value = value;
        document.getElementById("seldelid").value = value;
    }
}
