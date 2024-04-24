//getting the hentAlleBilletter at the start makes the table visible all the time and not only when pressing the bestill button
$(function(){
    hentAlleBilletter();
});
//making the table with all the data aswell as the endre and slette buttons.
function visAlleBilletter(billetter) {
    let ut = "<table class='table table-striped'>" +
        "<tr><th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefon</th><th>Epost</th><th></th></tr>";
    for (let i=0; i < billetter.length; i++) {
        ut += "<tr><td>" + billetter[i].film +
            "</td><td>" + billetter[i].antall + "</td><td>" +
            billetter[i].fornavn + "</td><td>" +
            billetter[i].etternavn + "</td><td>" +
            billetter[i].telefonnr + "</td><td>" +
            billetter[i].epost + "</td><td>" +
            "<a class='btn btn-primary' href='endreBooking.html?id=" + billetter[i].id + "'>Endre</a></td>"+
            "<td> <button class='btn btn-danger' onclick='slettEnKunde(" + billetter[i].id +")'>Slett</button></td>"+
            "</tr>";
    }
    $('#alleBilletter').html(ut);
}
//bestillBillett get the values of all the inputs and checks if they have the right values. if not we print a fault.
function bestillBillett() {
    const film = $('#film').val();
    const antall = $('#antall').val();
    const fornavn = $('#fornavn').val();
    const etternavn = $('#etternavn').val();
    const telefonnr = $('#telefonnr').val();
    const epost = $('#epost').val();

    const epostRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const telefonnrRegex = /^[0-9]{8}$/;
    const navnRegex = /^[a-zA-ZæøåÆØÅ .\-]+$/;

    let isValid = true;

    $('.validation-msg').text('');

    if (!film) {
        $('#personError').text("Vennligst velg en film.");
        isValid = false;
    }
    if (!antall || antall < 1) {
        $('#personError').text("Antall må være større enn 0.");
        isValid = false;
    }
    if (!navnRegex.test(fornavn)) {
        $('#personError').text("Vennligst oppgi et gyldig fornavn.");
        isValid = false;
    }
    if (!navnRegex.test(etternavn)) {
        $('#personError').text("Vennligst oppgi et gyldig etternavn.");
        isValid = false;
    }
    if (!telefonnrRegex.test(telefonnr)) {
        $('#personError').text("Vennligst oppgi et gyldig telefonnummer med 8 sifre.");
        isValid = false;
    }
    if (!epostRegex.test(epost)) {
        $('#personError').text("Vennligst oppgi en gyldig epost-adresse.");
        isValid = false;
    }

    if (isValid) {
        const customer = { film, antall, fornavn, etternavn, telefonnr, epost };
        const url = "/lagre";
        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(customer),
            success: function() {
                window.location.href = 'index.html';
            }
        });
        hentAlleBilletter();
    }
}

//deletes all tickets in one button
function slettAlle() {
    const url = "/slettAlle";
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function (){
            alert('Alle billeter ble slettet');
            window.location.href = 'index.html';
        },
        error: function (error) {
            alert("Det oppsto en feil")
        }
    })
}
//deletes a single ticket in one button
function slettEnKunde(id) {
    const url = "/slettBooking?id=" + id;
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            alert("slettet");
            window.location.href = 'index.html';
        },
        error: function (error) {
            alert("det oppsto en feil");
        }
    });
}
//recieves all the tickets from the bookingkontroller
function hentAlleBilletter(){
    $.get("/hentAlle", function(billetter){
        visAlleBilletter(billetter);
    });
}
//sorts all the tickets with a button
    function sorterTickets() {
        const url = "/sorter";
        $.ajax({
            url: url,
            type: 'GET',
            success: function (sortedBookings) {
                visAlleBilletter(sortedBookings);
            },
            error: function (error) {
                alert("fault.");
            }
        });
    }
