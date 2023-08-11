const stompClient = new StompJs.Client({
  brokerURL: 'wss://cpoo-router.mightycode.tech/router'
  // brokerURL: 'ws://localhost:8081/router'
});

stompClient.onConnect = (frame) => {
  setConnected(true);
  console.log('Connected: ' + frame);
  stompClient.subscribe('/domain/' + $("#domain").val() + '/messages', (message) => {
    showMessage(message.body);
  });
};

stompClient.onWebSocketError = (error) => {
  console.error('WebSocket error: ', error);
};

stompClient.onStompError = (frame) => {
  console.error('Broker error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#messages").html("");
}

function connect() {
  stompClient.activate();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  stompClient.publish({
    destination: "/router/route",
    body: JSON.stringify({ 'from': $("#from").val(), 'to': $("#to").val(), 'body': $("#body").val() })
  });
}

function showMessage(message) {
  $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', (e) => e.preventDefault());
  $("#connect").click(() => connect());
  $("#disconnect").click(() => disconnect());
  $("#send").click(() => sendMessage());
});
