/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function createSala() {
    var nickname = document.getElementById("nickname").value;
    var numSala = document.getElementById("idsala").value;
    let url = "/salas/"+numSala;
    const data = new FormData();
    console.log("entra a la funcion " + url );
    fetch(url, {
        method: 'GET'
    }).then(function (response) {
        if (response.ok) {
            console.log("crear la sala: " + numSala);
            window.locate.href("http://localhost:8080/index.html");
            return response.text();
        } else {
            console.log("no fue posible crear la sala");
            throw "Error en la llamada Ajax";
        }
    });
    
}

