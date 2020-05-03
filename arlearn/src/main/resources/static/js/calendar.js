
function llenarCalendario(anio,mes)
{

    var monthNames=["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
    var displacementdays=[0, 1, 2, 3, 4, 5,6];
    
    var currentDate;
    if (mes==0 && anio==0)
    {
        currentDate=new Date();
    }else
    {
        currentDate=new Date(anio,mes,1);
    }

    var mesnombre=monthNames[currentDate.getMonth()];

    document.getElementById("mesnombre").innerHTML=mesnombre;
    document.getElementById("numeroanio").innerHTML=currentDate.getFullYear();

    var numberMonth=document.getElementById("numeromes");
    var currentMonth=(currentDate.getMonth()+1)+"";

    var large=currentMonth.length;
    if (large==2)
    {
        numberMonth.innerHTML=(currentDate.getMonth()+1);
    }
    else
    {
        numberMonth.innerHTML="0"+(currentDate.getMonth()+1);
    }

    var firstDay=new Date((currentDate.getMonth()+1)+' '+'01'+', '+currentDate.getFullYear()+' 12:00:00');
    var displace=displacementdays[firstDay.getUTCDay()];
    
    for (var i=1;i<=37;i++)
    {
        var celd= document.getElementById("dia"+i)
        celd.innerHTML="";
    }

    var dia=1;
    var totaldays=daysInMonth(currentDate.getMonth()+1,currentDate.getFullYear());
    
    for (var i=displace+1; i<=totaldays+displace;i++)
    {
        document.getElementById("dia"+i).innerHTML=dia;
        dia++;
    }
}


function daysInMonth(mes, año) 
{
	return new Date(año, mes, 0).getDate();
}

function getInformation(celd)
{
    var day=celd;
    var month=document.getElementById("numeromes").innerHTML;
    var year=document.getElementById("numeroanio").innerHTML;
    var selectedDate= year+"-"+month+"-"+day;

    document.getElementById("fechaEvento").value=selectedDate;
    alert("tu bieja")

    console.log(day+"-"+month+"-"+year);
}

function prevMonth()
{
    var month=parseInt(document.getElementById("numeromes").innerHTML)-1;
    var year=parseInt(document.getElementById("numeroanio").innerHTML);

    if (month==0)
    {
        month=11;
        year--
    }
    else
    {
      month--;   
    }

    llenarCalendario(year,month);


}

function nextMonth()
{
    var month=parseInt(document.getElementById("numeromes").innerHTML)-1;
    var year=parseInt(document.getElementById("numeroanio").innerHTML);

    if (month==11)
    {
        month=0;
        year++
    }
    else
    {
      month++;   
    }

    llenarCalendario(year,month);
}






