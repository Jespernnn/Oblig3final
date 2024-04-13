const navnArray = [];

function hentData() {
    if (!validerData()) {
        console.log('Form is not valid');
        return;
    }

    let person = {
        film: document.getElementById("film").value,
        antall: document.getElementById("antall").value,
        fornavn: document.getElementById("fornavn").value,
        etternavn: document.getElementById("etternavn").value,
        telefonnr: document.getElementById("telefonnr").value,
        epost: document.getElementById("epost").value
    };
    navnArray.push(person);

    let ut = "<table class='table table-striped'><tr>" +
        "<th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>Epost</th>" +
        "</tr>";

    for (person in navnArray) {
        ut += "<tr>" +
            "<td>" + person.film + "</td>" +
            "<td>" + person.antall + "</td>" +
            "<td>" + person.fornavn + "</td>" +
            "<td>" + person.etternavn + "</td>" +
            "<td>" + person.telefonnr + "</td>" +
            "<td>" + person.epost + "</td>" + "</tr>";
    }
    ut += "</table>";

    document.getElementById("hentData").innerHTML = ut;
    console.log(person);
    oppdaterTabell();
}

function fjernData() {
    for(let i=0;i<navnArray.length;i++){
        navnArray.length=0;
    }
    oppdaterTabell();
}

function oppdaterTabell() {
    let ut = "<table class='table table-striped'><tr>" +
        "<th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>Epost</th>" +
        "</tr>";

    for (let person of navnArray) {
        ut += "<tr>" +
            "<td>" + person.film + "</td>" +
            "<td>" + person.antall + "</td>" +
            "<td>" + person.fornavn + "</td>" +
            "<td>" + person.etternavn + "</td>" +
            "<td>" + person.telefonnr + "</td>" +
            "<td>" + person.epost + "</td>" + "</tr>";
    }
    ut += "</table>";

    document.getElementById("hentData").innerHTML = ut;
}

function validerData() {
    console.log('Validerer data');
    const fornavn = document.getElementById("fornavn");
    const etternavn = document.getElementById("etternavn");
    const telefonnr = document.getElementById("telefonnr");
    const epost = document.getElementById("epost");
    const film = document.getElementById("film");
    const antall = document.getElementById("antall");

    document.getElementById("personError").innerHTML='';


    if (!fornavn.value.trim()) {
        document.getElementById("personError").innerHTML += 'First name is required.<br>';
    }
    if (!etternavn.value.trim()) {
        document.getElementById("personError").innerHTML += 'Last name is required.<br>';
    }
    const tlfnummerRegex = /^(\+47)?\s?(\d{2}\s?){4}$/;
    if (!tlfnummerRegex.test(telefonnr.value.trim())) {
        document.getElementById("personError").innerHTML += 'Please enter a valid Norwegian phone number.<br>';
    }

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-ZåäöÅÄÖ]{2,}$/;
    if (!emailRegex.test(epost.value.trim())) {
        document.getElementById("personError").innerHTML += 'Please enter a valid email address.<br>';
    }

    if (film.value === "") {
        document.getElementById("personError").innerHTML += 'Please select a film.<br>';
    }

    if (antall.value.trim() === "" || parseInt(antall.value) < 1) {
        document.getElementById("personError").innerHTML += 'Please enter a valid number of tickets.<br>';
    }
    if (document.getElementById("personError").innerHTML !== '') {
        return false;
    }

    return true;
}