function getReservations()
{
    var reservations="hola";
    $.ajax({
        url: 'http://localhost:8085/reservation',
        type: 'GET',
        async: false,
        contentType: "application/json",
        dataType : "json",
        crossDomain:true,
        success: function(json) {
                reservations = JSON.parse(JSON.stringify(json));
        },
        error: function(error) {
            console.log(error.responseText);
            
            alert('Disculpe, existió un problema');
        }
    });

    console.log(reservations)


    $.each(reservations, function(i, item) 
    {
        console.log(item);

        
        var img=item['room'].category;
        img=img.toLowerCase();

        $('#principalContainer').append
        (
            
            '<div id="reservations" class="container">'
            +'<img id="key" src="/images/key.png" th:src=@{/images/key.png} th:href=@{/images/key.png}><p id="reservationumber">'+(i+1)+'</p>'
            +'<div class="row first">'
            +'<div class="col"><p><i class="far fa-calendar-check"></i>Check-in: ' +item.beginDate+'</p></div>'
            +'<div class="col"><p><i class="fas fa-calendar-check"></i>Check-out: '+item.endDate+'</p></div>'
            +'<div class="col"><p><i class="fas fa-male"></i>Adultos: '+ item.adultsCuantity+'</p><p><i class="fas fa-child"></i>Niños: '+ item.childsCuantity +'</p></div></div>'
            +'<div class="row">'
    +'<div class="col"><i class="fab fa-hire-a-helper"></i>Tipo de reserva: '+item.reservationType+'</p></div>'
    
    +'<div class="col"><p><i class="fas fa-bed"></i>Tipo de habitacion: '+ item['room'].type+'</p></div>'

    +'<div class="col categoria"><p><img id="category" src="/images/'+img+'.png" th:src=@{/images/'+img+'.png} th:href=@{/images/'+img+'.png}> Categoria:'+item['room'].category+'</p></div></div></div>'
        );

        
            i++;





    });

    
}