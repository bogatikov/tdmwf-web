var socket = new WebSocket("ws://localhost:8080/web-interface/websocketendpoint");

socket.onopen = function() {
  console.log("Соединение установлено.");
};

var downloaded = 0;
socket.onclose = function(event) {
      if (event.wasClean) {
          alert('Соединение закрыто чисто');
      } else {
          alert('Обрыв соединения');
      }
      alert('Код: ' + event.code + ' причина: ' + event.reason);
    };


var downloads = [];
var id;
    socket.onmessage = function(event) {
        console.log("Получены данные " + event.data);
        var data = JSON.parse(event.data);

        downloads[data["id"]] = data;



        update(data["id"]);


    };
    socket.onerror = function(error) {
      alert("Ошибка " + error.message);
    };



document.getElementById('addDownloadButton').addEventListener('click', function() {
    addDownload();
});

function pauseDownload(id)
{
  var json = JSON.stringify({
      "cmd": "pause",
      "id": id
  });
  socket.send(json);
  return false;
}
function resumeDownload(id)
{
  var json = JSON.stringify({
      "cmd": "resume",
      "id": id
  });
  socket.send(json);
  return false;
}

function addDownload()
{
    var outgoingMessage = document.getElementById("addDownload").value;
      var json = JSON.stringify({
          "cmd": "add",
          "data": outgoingMessage
      });
      socket.send(json);
      return false;
}


var timers = [];
var values = [];
var lastSpeedUpdate = [];
function getSpeed(id)
{
    if (values[id] == undefined) {
        values[id] = 0;
        timers[id] = 0;
    }
    var speed = ((downloads[id]["downloaded"] - values[id])/1024).toFixed(2);
    values[id] = downloads[id]["downloaded"];

    timers[id]++;
    addData(id, timers[id], speed);
}

function addData (id, tick, speed){
			if (chartConfigs["chart_" + id].data.datasets.length > 0) {
				chartConfigs["chart_" + id].data.labels.push(tick);

				chartConfigs["chart_" + id].data.datasets.forEach(function(dataset) {
					dataset.data.push(speed);
				});

				chartElement["chart_" + id].update();
			}
		}