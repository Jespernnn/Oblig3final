

function endreBiletten() {
    console.log("Halla");
    const kunde = {
        id: $('#id').val(),
        film: $('#film').val(),
        antall: $('#antall').val(),
        fornavn: $('#fornavn').val(),
        etternavn: $('#etternavn').val(),
        telefonnr: $('#telefonnr').val(),
        epost: $('#epost').val()
    };
    console.log('Kunde object:', kunde);

    $.ajax({
        url: "/oppdater",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(kunde),
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error updating ticket:", textStatus, errorThrown);
            alert("An error occurred while updating the ticket: " + errorThrown);
        }
    });
}
$(function(){
    const id = window.location.search.substring(1);
    const url = "/hentBilett?" + id;
    $.get(url, function(kunde){
        $('#id').val(kunde.id);
        $('#film').val(kunde.film);
        $('#antall').val(kunde.antall);
        $('#fornavn').val(kunde.fornavn);
        $('#etternavn').val(kunde.etternavn);
        $('#telefonnr').val(kunde.telefonnr);
        $('#epost').val(kunde.epost);
    });
});