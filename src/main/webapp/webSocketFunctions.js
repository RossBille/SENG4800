function webSocketTest()
{
    if ("WebSocket" in window) //if web socket is supported in this browser
    {
        alert("WebSocets are supported. (Yay!)");
    }

    else
    {
        alert("WebSockets are not supported, reverting to 'POST' for data trasmission");
    }
}

function hello()
{
    alert("Hello");
}


   