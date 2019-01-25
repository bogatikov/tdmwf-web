function add(id)
{
    // Карточки
    var cards = document.getElementById("cards");

    // Строка карточки
    var row = document.createElement('div');
    row.className = 'row';

    // Карточка
    var card = document.createElement('div');
    card.className = "card";
    card.id = "id-" + id;
    card.style = "width: 100%";


    // Тело карточки
    var card_body = document.createElement('div');
    card_body.className = "card-body";


    // Строка в теле карточки
    var body_row = row.cloneNode(true);

    // Колонки
    var col = document.createElement('div');
    col.className = "col";
    var col2 = col.cloneNode(true);
    var col3 = col.cloneNode(true);
    var col4 = col.cloneNode(true);
    var col5 = col.cloneNode(true);


    var collapse = document.createElement("div");
    collapse.className = "collapse";
    collapse.id = "collapse-" + id;

    var collapse_card = card.cloneNode(true);
    var collapse_card_body = card_body.cloneNode(true);
    var collapse_chart_card_body = card_body.cloneNode(true);
    collapse_chart_card_body.innerHTML = "<canvas id=\"chart_" + id + "\"></canvas>"

    collapse_card.appendChild(collapse_card_body);
    collapse_card.appendChild(collapse_chart_card_body);
    collapse.appendChild(collapse_card);



    row.appendChild(card);
    card.appendChild(card_body);
    card_body.appendChild(body_row);
    card_body.appendChild(collapse);


    body_row.appendChild(col);
    body_row.appendChild(col2);
    body_row.appendChild(col3);
    body_row.appendChild(col4);
    body_row.appendChild(col5);


    cards.appendChild(row);

    var temp = downloads[id]["filename"].split("/");
    var name = temp[temp.length-1];


    chartConfigs["chart_" + id] = {
                type: 'line',
                data: {
                    datasets: [{
                        label: name,
                        backgroundColor: "#000",
                        borderColor: "#FF0000",
                        data: [],
                        fill: false,
                    }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Speed test'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Время, сек'
                            }
                        }],
                        yAxes: [{
                            display: true,
                            scaleLabel: {
                                display: true,
                                labelString: 'Скорость Кб/с'
                            }
                        }]
                    }
                }
            };
            var ctx = document.getElementById('chart_' + id).getContext('2d');
            chartElement["chart_" + id] = new Chart(ctx, chartConfigs["chart_" + id]);


    var cols = document.getElementById("id-" + id).getElementsByClassName("col");
    cols[0].innerHTML = '#' + id;
    cols[1].innerHTML = name;

    cols[2].innerHTML = '<div class="btn-group" role="group"><button type="button" onclick="resumeDownload('+ id +')" class="btn btn-secondary"><i class="fas fa-play"></i></button><button onclick="pauseDownload('+ id +')" type="button" class="btn btn-secondary"><i class="fas fa-pause"></i></button></div>';

    cols[3].innerHTML = '<div class="progress"><div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div></div>';
    cols[4].innerHTML = '<button class="btn btn-light" type="button" data-toggle="collapse" data-target="#collapse-' + id + '" aria-expanded="false" aria-controls="collapseExample"><i class="fa fa-info"></i></button>';

}


var chartElement = [];
var chartConfigs = [];

function update(id)
{

    var card = document.getElementById("id-" + id);
    if (card == null) {
        add(id);
        card = document.getElementById("id-" + id)
    }
    var cols = card.getElementsByClassName("col");

    var fileId = cols[0];
    var fileName = cols[1];
    var buttons = cols[2].getElementsByClassName("btn");
    var play = buttons[0];
    var pause = buttons[1];
    if (downloads[id]["status"] == 0) {
        changeState(play, pause);
    } else if (downloads[id]["status"] == 1) {
        changeState(pause, play);
    } else if (downloads[id]["status"] == 1) {
        changeState(play, play);
    }
    var progress = cols[3].getElementsByClassName("progress-bar");
    progress[0].innerHTML = downloads[id]["progress"].toFixed(2) + "%";
    progress[0].style.width =  downloads[id]["progress"].toFixed(2) + "%";

    var threads = card.getElementsByClassName("card-body")[1];
    threads.innerHTML = "";


    for (key in downloads[id]["threads"]) {
        var new_html = 'Поток #'+ key +'<div class="progress"><div class="progress-bar" role="progressbar" style="width: '+ downloads[id]["threads"][key]["progress"] +'%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div></div></br>';
        threads.innerHTML += new_html;
    }


    if (lastSpeedUpdate[id] == undefined) {
        lastSpeedUpdate[id] = 0;
    } else if ((((new Date()).getTime() - lastSpeedUpdate[id]) > 1000) && (downloads[id]["fileSize"] > downloads[id]["downloaded"])) {
        getSpeed(id);
        lastSpeedUpdate[id] = (new Date()).getTime();
    }


}

function changeState(first, second)
{
    if (first.className != "btn btn-secondary active") {
        first.className = "btn btn-secondary active";
        second.className = "btn btn-secondary";
    }
}
