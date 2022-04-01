/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    consultarusuario();
    //var usuarios = document.getElementById('consultausuarios');
    function consultarusuario(){
        $.ajax({
            url:"consultausuario",
            dataType: 'json',
            success: function (data) {
                var tabla = document.getElementById("tabla");
                console.log(data);
                tabla.innerHTML='';
                var dat=data.length;
                if(dat>0){
                    tabla.innerHTML=`
                    <tr>
                        <th>Documento</th>
                    <th>Documento</th>
                    <th>Documento</th>
                    <th>Documento</th>
                    <th>Documento</th>
                    </tr>
                    `;
                    alert("despues de la tabla");
                }
            }
        });
    }
    function consultarcliente(){
        
    }
});
