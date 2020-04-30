
function llenarCalendario()
{
    var nombreMeses=["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
    var dias=[0, 1, 2, 3, 4, 5,6];
    var currentDate=new Date();
    var mesnombre=nombreMeses[currentDate.getMonth()];

    document.getElementById("mesnombre").innerHTML=mesnombre;
    document.getElementById("numeroanio").innerHTML=currentDate.getFullYear();
    document.getElementById("numeromes").innerHTML="0"+(currentDate.getMonth()+1);

    var primerDia=new Date((currentDate.getMonth()+1)+' '+'01'+', '+currentDate.getFullYear()+' 12:00:00');
    var desplazamiento=dias[primerDia.getUTCDay()];
    
    for (var i=1;i<=35;i++)
    {
        document.getElementById("dia"+i).innerHTML="";
    }

   

    var dia=1;
    var diasenmes=diasEnUnMes(currentDate.getMonth()+1,currentDate.getFullYear());
    
    console.log("diasenemes "+diasenmes);

    for (var i=desplazamiento+1; i<=diasenmes+desplazamiento;i++)
    {
        console.log(dia)
        document.getElementById("dia"+i).innerHTML=dia;
        dia++;
        
    }

    
}

function diasEnUnMes(mes, año) {
	return new Date(año, mes, 0).getDate();
}



