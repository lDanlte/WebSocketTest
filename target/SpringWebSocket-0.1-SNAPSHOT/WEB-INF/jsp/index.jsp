<%-- 
    Document   : index
    Created on : 28.08.2015, 15:28:25
    Author     : dantonov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="<c:url value="/lib/jquery/jquery-2.1.4.min.js"/>"></script>
        <script src="<c:url value="/lib/webSocket/sockjs-1.0.3.min.js"/>"></script>
        <script src="<c:url value="/lib/webSocket/stomp.min.js"/>"></script>
        
        <title>JSP Page</title>
    </head>
    
    <body>
        <h3>Generated value = ${value}</h3>
        <div id="conteiner" style="padding: 10px,10px,10px,10px; margin-bottom: 20px; display: none;">

                Area id:      <div id="areaId"      style="color: cornflowerblue; display: inline;"></div> <br />
                Last update:  <div id="date"        style="color: cornflowerblue; display: inline;"></div> <br />
                Temperature:  <div id="temperature" style="color: cornflowerblue; display: inline;"></div> <br />
            
        </div>
        <div style="display: inline">
            <input id="areaIdText" type="text" size="10" placeholder="areaId">
            <button onclick="updateStatus();">getStatus</button>
            <button onclick="connectToServer();" style="margin-left: 5px;">THIS IS SPARTA!!!</button>
            <button onclick="closeConnection();" style="margin-left: 5px;">Close Connection</button>
        </div>
        
        <script>
            var bool = true, socket;
            
            
            function updateStatus(){
                var areaIdText = $("#areaIdText").val();
                $.ajax({
                    url: getUrl(areaIdText),
                    dataType: "json",
                    success: function(result){
      
                        setStatus(result.temperatureDto);
                        
                        if(bool){
                            $("#conteiner").css("display", "");
                            bool = false;
                        }
                    }
                });
            }
            
            function getUrl(areaId){
                return "http://localhost:8080/SpringWebSocket/api/status/" +
                        areaId +"/temperature";
            }
            
            function connectToServer(){
                socket = new SockJS("http://localhost:8080/SpringWebSocket/socket");
                var client = Stomp.over(socket);
                client.connect("guest", "guest", function(){
                    
                    client.subscribe("/app/temperature", function(message){
                        setStatus(JSON.parse(message.body));
                    });
                    
                    client.subscribe("/topic/temperature", function(message){
                        setStatus(JSON.parse(message.body));
                    });
                });
            }
            
            function closeConnection(){
                socket.close();
            }
            
            function setStatus(temperature){
                $("#areaId").html(temperature.areaId);
                $("#date").html(temperature.datetime);
                $("#temperature").html(temperature.value.toFixed(1) + " &degC");
            }
        </script>
        
    </body>
</html>
