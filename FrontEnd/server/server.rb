require 'em-websocket'
require 'rubygems'
require 'json'

EventMachine::WebSocket.start(:host=>"localhost", :port => "8080") do |ws|
    ws.onopen do
        puts "Websocket opened"
    end
    ws.onmessage do |msg|
        puts msg
        ws.send(msg)
    end
    ws.onclose do
        puts "Websocket closed"
    end
end
        
