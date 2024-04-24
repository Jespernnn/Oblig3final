//recieves the data from the single ticket. put in some error logging when i had some faults here.
$(function() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    if (id) {
        const url = "/hentBooking?id=" + id;
        $.get(url, function(customer) {
            $('#id').val(customer.id);
            $('#film').val(customer.film);
            $('#antall').val(customer.antall);
            $('#fornavn').val(customer.fornavn);
            $('#etternavn').val(customer.etternavn);
            $('#telefonnr').val(customer.telefonnr);
            $('#epost').val(customer.epost);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.error("Error retrieving ticket:", textStatus, errorThrown);
            alert("An error occurred while retrieving the ticket: " + errorThrown);
        }).always(function() {
            $('#endreButton').prop('disabled', false);
        });
    } else {
        console.log("No ID provided in URL.");
        $('#endreButton').prop('disabled', true); // Disable button if no ID
    }
});
// function for changing the values for ticket
function endreBiletten() {
    if (!$('#id').val()) {
        alert('No valid ID loaded for updating.');
        return;
    }

    console.log("Halla");
    const customer = {
        id: $('#id').val(),
        film: $('#film').val(),
        antall: $('#antall').val(),
        fornavn: $('#fornavn').val(),
        etternavn: $('#etternavn').val(),
        telefonnr: $('#telefonnr').val(),
        epost: $('#epost').val()
    };
    console.log('customer object:', customer);
//if the ticket is updated it will transfer us back to the index.html
    $.ajax({
        url: "/oppdater",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function() {
            alert('Update successful!');
            window.location.href = 'index.html';
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error updating ticket:", textStatus, errorThrown);
            alert("An error occurred while updating the ticket: " + errorThrown);
        }
    });
}